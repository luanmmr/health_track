<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<form action="atividades" method="post" style="margin-top: 20px;">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${ editarAtividade.codigo }">
    
<div class="form-row">
   <div class="input-group mb-3 col-md-6">
      <div class="input-group-prepend">
          <label class="input-group-text" for="inputGroupSelect01">Atividade</label>
      </div>
      <select name="atividade" class="custom-select" id="inputGroupSelect01">
          <option value="${ fn:toLowerCase(editarAtividade.titulo) }" selected> ${ editarAtividade.titulo } </option>
      </select>
  </div>
  
  <div class="input-group mb-3 col-md-6">
    <div class="input-group-prepend">
        <label class="input-group-text" for="inputGroupSelect01">Ritmo</label>
    </div>
   <select name="ritmo" class="custom-select"  id="inputGroupSelect01" required>
       <option value="${ editarAtividade.ritmo.codigo}" selected>
       	${ editarAtividade.ritmo.nomeRitmo }
       </option>
       
       <c:if test="${ editarAtividade.ritmo.codigo != 1}">
       	<option value="1">Leve</option>
       </c:if>
       
       <c:if test="${ editarAtividade.ritmo.codigo != 2}">
       	<option value="2">Moderado</option>
       </c:if>
       
       <c:if test="${ editarAtividade.ritmo.codigo != 3}">
       	<option value="3">Intenso</option>
       </c:if>
   </select>
</div>
</div>
   
    <div class="form-row">       
      <div class="form-group col-md-6">
        <label for="dt-inicio" class="label-custom">Início:</label>
         <input type="datetime-local" name="dt-inicio" step="1" class="form-control input-custom" 
         				value=<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss"
                                       value='${ editarAtividade.dataInicio.time}'/>
                                       required>
       </div>
         
       <div class="form-group col-md-6">
        <label for="dt-fim" class="label-custom">Fim:</label>
         <input type="datetime-local" name="dt-fim" step="1" class="form-control input-custom" 
                value=<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss"
                                       value='${ editarAtividade.dataFim.time}'/> required>
       </div>  
    </div>
    
    <div class="form-row">
      <div class="input-group mb-3 col-md-6" style="margin-top: 20px">
			   <div class="input-group-prepend">
	        <label class="input-group-text" for="inputGroupSelect01">Estilo</label>
			   </div>
			   <select name="estilo" class="custom-select" id="inputGroupSelect01" required>
			      <option value="${ editarAtividade.estilo.codigo}" selected>
			      	${ editarAtividade.estilo.nomeEstilo }
			      </option>
			      
			      <c:if test="${ editarAtividade.estilo.codigo != 1}">
			      	<option value="1">Borboleta</option>
			      </c:if>
			      
			      <c:if test="${ editarAtividade.estilo.codigo != 2}">
			      	<option value="2">Peito</option>
			      </c:if>
			      
			      <c:if test="${ editarAtividade.estilo.codigo != 3}">
			      	<option value="3">Costas</option>
			      </c:if>
			   </select>
		  </div>
    </div>
    
    <div class="form-row">
      <div class="form-group col-md-4" style="margin-top: 6px;">
        <input class="btn btn-sm btn-primary" type="submit" value="Atualizar">
      </div>                            
    </div>

</form>