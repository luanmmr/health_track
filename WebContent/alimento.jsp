<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Luan Ribeiro">
  <title>Cadastrar Alimento</title>
  <%@ include file="includes/dashboard-style.jsp" %>
</head>

<body id="page-top">
  <%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Alimento</h1>
        </div>

        <!-- Content Row -->
        <div class="row">
          <div class="col-lg-8 mb-4">
          <!-- Approach -->
            <div class="card shadow mb-4">
              <div class="card-body">
                  
                  <c:if test="${ not empty msgSucesso }">
                     <a class="btn btn-success btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-check"></i>
                       </span>
                       <span class="text">${ msgSucesso }</span>
                     </a>
                   </c:if>
                   <c:if test="${ not empty msgErro }">
                     <a class="btn btn-warning btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-exclamation-triangle"></i>
                       </span>
                       <span class="text">${ msgErro }</span>
                     </a>
                   </c:if>
                   
                  <form action="alimentos" method="post" style="margin-top: 20px;">
                  <input type="hidden" name="action" value="create">
                  
                  
                  <fieldset>
    									<legend><strong>Obrigatório</strong></legend>
                  
                  <div class="form-row">       
                      <div class="form-group col-md-6">
                        <label for="nome" class="label-custom">Nome:</label>
                         <input type="text" name="nome" class="form-control input-custom"
                                maxlength="30" placeholder="Ex: laranja, suco, pão ... " required>
                       </div>
                  </div>
                
                   <div class="form-row" style="margin-top: 20px;">
                      <div class="input-group mb-3 col-md-6">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text">Grupo</label>
	                        </div>
	                        <select name="grupo" class="custom-select" required>
	                            <option value="" selected>Selecione ...</option>
	                            <c:forEach items="${ grupoAlimento }" var="grupo">
	                            	<option value="${ grupo.codigo }">${ grupo.nomeGrupo }</option>
	                            </c:forEach>
	                        </select>
	                     </div>
                      
                      <div class="input-group mb-3 col-md-6">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text">Medida</label>
	                        </div>
	                        <select name="medida" class="custom-select" required>
	                            <option value="" selected>Selecione ...</option>
	                            <c:forEach items="${ medidas }" var="medida">
	                            	<option value="${ medida.codigo }">
	                            		${ medida.nomeMedida } - ${ medida.nomeAbreviado }
	                            	</option>
	                            </c:forEach>
	                        </select>
	                     </div>
                   </div>
                   
                   <div class="form-row">
                   		<div class="form-group col-md-3">
                        <label for="valor-medida" class="label-custom">Valor Medida:</label>
                         <input type="number" step="0.1" name="valor-medida" 
                         				class="form-control input-custom" required>
                       </div>
                      <div class="form-group col-md-3">
                        <label for=kcal class="label-custom">Total de Kcal:</label>
                         <input type="number" step="0.1" name="kcal" 
                         				class="form-control input-custom" required>
                       </div>
                   </div>
                   
                   </fieldset>
                   
                   <fieldset style="margin-top: 10px;">
    									<legend><strong>Opcional</strong></legend>
                   
                   <div class="form-row">       

                      <div class="form-group col-md-2">
                        <label for="carboidrato" class="label-custom">Carboidrato:</label>
                         <input type="number" step="0.1" name="carboidrato" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-3">
                        <label for="gordura-total" class="label-custom">Gordura Total:</label>
                         <input type="number" step="0.1" name="gordura-total" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="proteina" class="label-custom">Proteína:</label>
                         <input type="number" step="0.1" name="proteina" value="0"
                         			  class="form-control input-custom">
                       </div>
                       
                  </div>
                  
                  <div class="form-row">       
                      <div class="form-group col-md-3">
                        <label for="gordura-saturada" class="label-custom">Gordura Saturada:</label>
                         <input type="number" step="0.1" name="gordura-saturada" value="0"
                         				class="form-control input-custom">
                       </div>
                         
                      <div class="form-group col-md-3">
                        <label for="gordura-trans" class="label-custom">Gordura Trans:</label>
                         <input type="number" step="0.1" name="gordura-trans" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="colesterol" class="label-custom">Colesterol:</label>
                         <input type="number" step="0.1" name="colesterol" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="sodio" class="label-custom">Sódio:</label>
                         <input type="number" step="0.1" name="sodio" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                  </div>
                  
                 	<div class="form-row">       
                      <div class="form-group col-md-2">
                        <label for="potassio" class="label-custom">Potássio:</label>
                         <input type="number" step="0.1" name="potassio" value="0"
                         				class="form-control input-custom">
                       </div>
                         
                      <div class="form-group col-md-3">
                        <label for="fibra" class="label-custom">Fibra Dietética:</label>
                         <input type="number" step="0.1" name="fibra" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="acucares" class="label-custom">Açúcares:</label>
                         <input type="number" step="0.1" name="acucares" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="vitamina-a" class="label-custom">Vitamina A:</label>
                         <input type="number" step="0.1" name="vitamina-a" value="0"
                         			  class="form-control input-custom">
                       </div>
               		</div>
               		
               		<div class="form-row">       
                      <div class="form-group col-md-2">
                        <label for="vitamina-c" class="label-custom">Vitamina C:</label>
                         <input type="number" step="0.1" name="vitamina-c" value="0"
                         			  class="form-control input-custom">
                       </div>
                         
                      <div class="form-group col-md-2">
                        <label for="calcio" class="label-custom">Cálcio:</label>
                         <input type="number" step="0.1" name="calcio" value="0"
                         				class="form-control input-custom">
                       </div>
                       
                       <div class="form-group col-md-2">
                        <label for="ferro" class="label-custom">Ferro:</label>
                         <input type="number" step="0.1" name="ferro" value="0"
                         				class="form-control input-custom">
                       </div>
               		</div>
               		
               		</fieldset>
			
                     <div class="form-row" style="margin-top: 20px;">
                       <div class="form-group col-md-4" style="margin-top: 6px;">
                         <input class="btn btn-sm btn-primary" type="submit" value="Cadastrar">
                         <a href="alimentacao?action=listar"  class="btn btn-sm btn-primary">Voltar</a>
                       </div>
                     </div>

                </form>
              </div>
            </div>
          </div>
          
          <div class="col-lg-4 mb-4">
          <!-- Approach -->
            <div class="card shadow mb-4">
              <div class="card-body">
              	
              	<div>
              		<h5>Grupo de Alimentos</h5> <br>
              		<strong>Carboidratos</strong> - As principais fontes de carboidrato são: arroz, pão, 
              		batata, massa, mandioca, cereais, etc.
              	</div>
              	<hr>
              	<div>
              		<strong>Hortaliças</strong> - É o grupo das verduras e legumes, fontes de vitaminas, 
              		minerais e fibras.
              	</div>
              	<hr>
              	<div>
              		<strong>Frutas</strong> - Abacaxi, maçã, banana, kiwi, caju, acerola, etc.
              	</div>
              	<hr>
              	<div>
              		<strong> Leite e Derivados</strong> - Os principais alimentos deste grupo são: queijo, 
              		leite, iogurtes, etc.
              	</div>
              	<hr>
              	<div>
              		<strong>Carnes e ovos</strong> - Os principais alimentos deste grupo são: peixe, 
              		frango, carne, ovos, etc.
              	</div>
              	<hr>
              	<div>
              		<strong>Leguminosas e oleaginosas</strong> - Compõem esse grupo: feijão, soja, lentilha, 
              		grão de bico, castanhas etc.
              	</div>
              	<hr>
              	<div>
              		<strong>Óleos e Gorduras</strong> - São alimentos desse grupo: azeite, manteiga, 
              		óleo de soja, etc.
              	</div>
              	<hr>
              	<div>
              		<strong>Açúcares e Doces</strong> - Os alimentos que compõem esse grupo são: açúcar, mel, 
              		chocolate, sorvete, bolo, etc.
              	</div>
                
                
              </div>
            </div>
          </div>
          
        </div>
    </div>
    <!-- /.container-fluid -->

    <%@ include file="includes/end-main-content.jsp" %>
    
    <%@ include file="includes/dashboard-footer.jsp" %>

    <%@ include file="includes/end-content-page-wrapper.jsp" %>

    <%@ include file="includes/scroll-top-button.jsp" %>

    <%@ include file="includes/modal-logout.jsp" %>
    

  <%@ include file="includes/default-dashboard-js.jsp" %>
</body>

</html>