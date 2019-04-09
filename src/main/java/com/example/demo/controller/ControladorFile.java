package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.beans.Diccionario;
import com.example.demo.dto.DiccionarioDTO;
import com.example.demo.interfaces.IDiccionarioSERVICE;
import com.example.demo.services.UploadFileService;
import com.example.demo.util.TransformarNumInglesEspanol;
import com.example.demo.util.ValidarTransformar;


@Controller				//--->> lo mas importante y lo que indica que es el controlador.
@RequestMapping("/file")
public class ControladorFile {
//Controlador general
	
	//@Autowired  //inyecto DAO de la entidad que queiro manejar
	//ICategoriaSERVICES categoriaservice;  
	
	@Autowired
    private UploadFileService uploadFileService;
	
	@Autowired
    private IDiccionarioSERVICE diccionarioservice;
	
		
		
	@RequestMapping("/cargamasiva")	//   http://localhost:8080/vibbo/index
	public String cargamasiva (HttpServletRequest request) {
		System.out.println("ControladorFile - index");
		//request.setAttribute("mensaje", ""); //enviamos a request
		
		HttpSession session=request.getSession(true); //NUBE - ABRO SESION
		request.setAttribute("areacarga",""); //enviamos a request
		session.setAttribute("areacarga",""); //enviamos a request
		
		return "cargamasiva";
	}    //   http://localhost:8080/file/cargamasiva
	
	@RequestMapping(value = "/accioncargarfileenred", method = RequestMethod.POST) ////ponemos accion, y method tipo post
	public String accioncargarfileenred (HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException, ServletException {
		System.out.println("ControladorFile - accioncargarfileenred");
		
		//Iniciamos variables  ----------------------------------------------------------
		//HttpSession session=request.getSession(true); //NUBE - ABRO SESION
		
		String url="";
		String mensaje="";
		String ruta="";
		String textovalidado="";
		String salida = "";
		boolean seguir=false;
				
		//Si el archivo, contiene algo, lo guardamos para tenerlo ----------------------------------------------------------
		if(!file.isEmpty()) {
			try {
				System.out.println("fichero lleno, pasamos a procesarlo");
				//Rescatamos el nombre del fichero 
				url=file.getOriginalFilename();
				System.out.println("url: " + url);
				//Subuimos el archivo/file
				ruta=uploadFileService.saveFile(file);  
				System.out.println("Venimos de uploadFileSevice. ----");
				System.out.println("ruta: " + ruta);
				mensaje="Archivo subido a red. ";
				System.out.println(mensaje);
				seguir=true;
			}catch(Exception e) {
				//En caso de error, no subira nada y pondremos una url por defecto al anuncio(en caso de que el usuario no ponga ninguna imagen se cargara esta)
				//url="producto.png";
				mensaje="ERROR: no se ha seleccionado ningun file.";
				seguir=false;
				System.out.println("Se ha ido por catch. mensaje: " + mensaje);     
			}
		 }        
		
		
		//generamos texto para mostrar en caja de texto ----------------------------------------------------------
		if (seguir) {
			InputStream f;
			try {
				System.out.println("cargar texto - inicio. ruta: " + ruta);     
				f=new FileInputStream(ruta); //(url.get.getBytes());  //Marco_Fichero_Productos.jt_ruta_fichero.getText());
				int size=f.available();
				for (int i = 0; i < size; i++) {
					salida+=(char)f.read();
				}
				System.out.println("salida: " + salida);
				//String areacarga=salida; //Marco_Fichero_Productos.jta_resultado.setText(salida);
			
			}catch (Exception e) {
				System.out.println(e);
			}
		
		//validar texto ----------------------------------------------------------
			ValidarTransformar vt=new ValidarTransformar();
			textovalidado=vt.validarFormato(salida); //nos ha hecho una primera evaluacion
			System.out.println("textovalidado: "+textovalidado);
				
			if (vt.getErrores()==0) {
				mensaje=mensaje+"Contenido sin errores. Puede proceder a cargar en la base de datos. Examinadas "+vt.getLineas()+" lineas";
			}else {
				System.out.println("errores en revisión; " + vt.getErrores());
				mensaje=mensaje+"Contenido CON ERRORES. Proceda a su revisión antes de cargar. Examinadas "+vt.getLineas()+" lineas";
			}
		}
		
		//llevamos a caja de texto. ----------------------------------------------------------
		request.setAttribute("mensaje",mensaje); //enviamos a request
		request.setAttribute("areacarga",textovalidado); //enviamos a request
		//session.setAttribute("areacarga",salida); //enviamos a session, para que lo coja. con request no funciona.
		return "cargamasiva";
	}    
	
	@RequestMapping("/accioncargarfileenbbdd")	//   http://localhost:8080/vibbo/index
	public String accioncargarfileenbbdd (HttpServletRequest request) {
		System.out.println("ControladorFile - accioncargarfileenbbdd -------------inicio------------");
		
		//String areacarga=request.getParameter("Forms(formfichero).areacarga");
		String areacarga=request.getParameter("areacarga");
		
		System.out.println(" Validamos primero areacarg : " + areacarga + " ----------------------------");
		
		//servicio devuelva STRING "alta realizada"/"ya existe registro" altamasiva(diccionarioDTO) (de uno en uno);
		ValidarTransformar vt=new ValidarTransformar();
		String textovalidado=vt.validarFormato(areacarga);; //nos ha hecho una primera evaluacion
		String salidaacumulada="";
		String id=""; 
		List<Diccionario> listadiccionario=new ArrayList<Diccionario>();
		int altas=0;
		int yaexisten=0;
				//------------
		System.out.println("COMENZAMOS LA CARGA -----------------------------------");
				String[] lineacargar=textovalidado.split("\n");
			
				for (int linea=0; linea < lineacargar.length; linea++) {
								
					String[] detectarLineaconError = lineacargar[linea].split(">>ERROR");
						
					if (detectarLineaconError.length>1) {
						//linea con error que no podemos cargar
						salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- NO CARGADA \n";
						System.out.println("linea examinada: " + lineacargar[linea]+" |detectada con errores");
					}else {
						//no tiene error. vamos a cargarla
						
						System.out.println("linea examinamos: " + lineacargar[linea]+" ||");
						String[] campo = lineacargar[linea].split(";");
						
						if  (campo.length!=1) { //vale 1 si es la ultima linea y por lo tanto no hay que tratarla. 
						
							Diccionario diccionario=new Diccionario();
						
							diccionario.setId(0);
							diccionario.setEspanol(campo[0]);
							diccionario.setIngles(campo[1]);
							diccionario.setTipo(campo[2]);
							diccionario.setCategoria(campo[3]);
							diccionario.setNum_uso(Integer.parseInt(campo[4]));
						
							id=diccionarioservice.encontarparejaespanolingles(diccionario.getEspanol(), diccionario.getIngles());
							System.out.println("ACCESO A TABLA DICCIONARIO. id DEVUELTO: " + id);
							int numid=Integer.parseInt(id);
						
							if(numid<0) { //es que NO EXISTE, podemos darla de alta. Vienen el valor -1.
								listadiccionario.add(diccionario); 
							
								salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- ALTA correcta.\n";
								System.out.println("linea examinada: " + lineacargar[linea]+" |se intentaba ir a bbdd y alta correcta");
								altas++;
							}else {
								salidaacumulada=salidaacumulada+lineacargar[linea]+" >>-- REGISTRO YA EXISTE. NO CARGADO.\n";
								System.out.println("linea examinada: " + lineacargar[linea]+" |se intentaba ir a bbdd y ya existe");
								yaexisten++;
							}
						}
					}
				}
				
		String mensaje="SE HAN DADO DE ALTA: "+altas+". Ya existian: " + yaexisten+" (lineas analizadas: " + lineacargar.length+")";		
		diccionarioservice.altaDiccionario(listadiccionario);			
				
				
				
		//------------		
		System.out.println("textovalidado: " + salidaacumulada); 
		
		request.setAttribute("areacarga",salidaacumulada); //enviamos a request
		request.setAttribute("mensaje",mensaje); //enviamos a request
		
		return "cargamasiva";
	}
	
	
	
	@RequestMapping(value = "/accionconvertirfileenredNUM", method = RequestMethod.POST) ////ponemos accion, y method tipo post
	public String accionconvertirfileenredNUM (HttpServletRequest request, @RequestParam("fileNUM") MultipartFile fileNUM) throws IOException, ServletException {
		System.out.println("ControladorFile - accionconvertirfileenredNUM");
		
		//Iniciamos variables  ----------------------------------------------------------
		//HttpSession session=request.getSession(true); //NUBE - ABRO SESION
		
		String url="";
		String mensaje="";
		String ruta="";
		String textovalidado="";
		boolean seguir=false;
		String salida = "";
		
				
		//Si el archivo, contiene algo, lo guardamos para tenerlo ----------------------------------------------------------
		System.out.println("nombre fichero: " + fileNUM);
		if(!fileNUM.isEmpty()) {
			try {
				System.out.println("fichero lleno, pasamos a procesarlo");
				//Rescatamos el nombre del fichero 
				url=fileNUM.getOriginalFilename();
				System.out.println("url: " + url);
				//Subuimos el archivo/file
				ruta=uploadFileService.saveFile(fileNUM);  
				System.out.println("Venimos de uploadFileSevice. ----");
				System.out.println("ruta: " + ruta);
				mensaje="Archivo subido a red. ";
				System.out.println(mensaje);
				seguir=true;
			}catch(Exception e) {
				//En caso de error, no subira nada y pondremos una url por defecto al anuncio(en caso de que el usuario no ponga ninguna imagen se cargara esta)
				//url="producto.png";
				mensaje="ERROR: no se ha seleccionado ningun file.";
				seguir=false;
				System.out.println("Se ha ido por catch. mensaje: " + mensaje);     
			}
		 }        
		
		
		//generamos texto para mostrar en caja de texto ----------------------------------------------------------
		if (seguir) {
			InputStream f;
			salida = "";
			try {
				System.out.println("cargar texto - inicio. ruta: " + ruta);     
				f=new FileInputStream(ruta); //(url.get.getBytes());  //Marco_Fichero_Productos.jt_ruta_fichero.getText());
				int size=f.available();
				for (int i = 0; i < size; i++) {
					salida+=(char)f.read();
				}
				System.out.println("salida: " + salida);
				//String areacarga=salida; //Marco_Fichero_Productos.jta_resultado.setText(salida);
			
			}catch (Exception e) {
				System.out.println(e);
			}
		
		//validar texto ----------------------------------------------------------
			TransformarNumInglesEspanol tnie=new TransformarNumInglesEspanol();
			textovalidado=tnie.transformaranuestroformato(salida); //nos ha hecho una primera evaluacion
			System.out.println("1) textotransformado: "+textovalidado);
			
			/*
			ValidarTransformar vt=new ValidarTransformar();
			textovalidado=vt.validarFormato(textovalidado); //nos ha hecho una primera evaluacion
			System.out.println("textovalidado: "+textovalidado);
				
			if (vt.getErrores()==0) {
				mensaje=mensaje+"Contenido sin errores. Puede proceder a cargar en la base de datos. Examinadas "+vt.getLineas()+" lineas";
			}else {
				System.out.println("errores en revisión; " + vt.getErrores());
				mensaje=mensaje+"Contenido CON ERRORES. Proceda a su revisión antes de cargar. Examinadas "+vt.getLineas()+" lineas";
			}
			*/
		}
		
		//llevamos a caja de texto. ----------------------------------------------------------
		request.setAttribute("mensaje",mensaje); //enviamos a request
		request.setAttribute("areacarga",textovalidado); //enviamos a request
		//session.setAttribute("areacarga",salida); //enviamos a session, para que lo coja. con request no funciona.
		return "cargamasiva";
	}    
	
}
