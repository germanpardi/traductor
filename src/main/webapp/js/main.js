
  $('#textobuscar').keyup(
		function(){
			var textobuscar=$('#textobuscar').val();
			$('#tabla').children().remove();
			$.post('/buscar/{textobuscar}', {"textobuscar" : textobuscar}, function(diccionarioDTO){
				var pintarTabla =
					" <thead class='thead-dark'> <tr>"+
				      "<th scope='col' class='text-left'><h2>Espanol</h2></th>"+
				      "<th scope='col' class='text-left'><h2>Ingles</h2></th>"+ 
				      "<th scope='col' class='text-center'><h2>Tipo</h2></th>"+
				      "<th scope='col' class='text-left'><h2>Categoria</h2></th>"+
				      "<th scope='col' class='text-right'><h2>Num.Uso</h2></th>"+
				      "<th></th>"+
				      "<th></th></tr></thead>";
				    
				      
				    
					
					  $('#tabla').append(pintarTabla);
				for(var i=0;i<diccionarioDTO.length;i++){
					var nuevoTr =
					"<tr><td class='table-light'>"+diccionarioDTO[i].espanol+"</td>"+
					"<td class='table-light'>"+diccionarioDTO[i].ingles+"</td>"+
					"<td class='table-light'>"+diccionarioDTO[i].tipo+"</td>"+
					"<td class='table-light'>"+diccionarioDTO[i].categoria+"</td>"+
					"<td class='table-light'>"+diccionarioDTO[i].num_uso+"</td>"+
					"<td  class='table-light'><a href='editar?id="+diccionarioDTO[i].id+"' class='btn btn-info' role='button'>EDITAR</a>"+
			  		"<td  class='table-light'><a href='borrar?id="+diccionarioDTO[i].id+"' class='btn btn-info' role='button'>BORRAR</a>"; 
					
					  $('#tabla').append(  nuevoTr );
				}
					
				});
			}  		
			
		
  
  );