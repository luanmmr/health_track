<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Luan Ribeiro">
  <title>Atividades</title>
  <%@ include file="includes/dashboard-style.jsp" %>
  <link href="resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
 
</head>

<body id="page-top">
  <%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->

            <h1 class="h3 mb-2 text-gray-800">Atividades</h1>
            <p class="mb-4">Aqui te atualizaremos como estão suas atividades no dia. Portanto, os dados estão embasados em sua
              meta de gasto calórico, na quantidade de calorias perdidas até o momento e nas atividades realizadas no dia.
            </p>

        
         <!-- Content Row -->
        <div class="row">
        
              <!-- Pending Requests Card Example -->
	           <div class="col-xl-3 col-md-6 mb-4">
	               <div class="card border-left-warning shadow h-100 py-2">
	                   <div class="card-body">
	                       <div class="row no-gutters align-items-center">
	                           <div class="col mr-2">
	                               <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
	                                   Gasto Calório</div>
	                               <div class="h5 mb-0 font-weight-bold text-gray-800">${ gastoCaloricoTotal } kcal</div>
	                           </div>
	                           <div class="col-auto">
	                               <i class="fas fa-running fa-2x text-gray-300"></i>
	                           </div>
	                       </div>
	                   </div>
	               </div>
	             </div>
        
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Meta de Gasto Calórico
                                </div>
                                <div class="row no-gutters align-items-center">
                                    <div class="col-auto">
                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                    </div>
                                    <div class="col">
                                        <div class="progress">
                                          <div class="progress-bar bg-success progress-bar-striped progress-bar-animated" 
                                               role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" 
                                               style="width: 50%">
                                          </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
        
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-info shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">DATA DE INÍCIO</div>
                                <div class="h6 mb-0 font-weight-bold text-gray-800">
                                  <form action="atividades" method="get" id="form-data">
                                    <input type="hidden" name="action" value="alterar-data">
                                    <input type="date" value="${ dtExibidaAtv }" onchange="document.getElementById('form-data').submit();"
                                           name="data" class="input-date font-weight-bold text-gray-800 h5 mb-0 mr-3">
                                  </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
        </div>

        <!-- Content Row -->
        <div class="row">
          <div class="col-lg-12 mb-4">

            <div class="card shadow mb-4">
              <div class="card-header py-3 space-title-button">
                  <h6 class="m-0 font-weight-bold text-primary">
                    Atividades Realizadas
                  </h6>
                  
                  <a href="#" class="btn btn-primary btn-icon-split" data-toggle="modal" 
                     data-target="#addAtividade">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Atividade</span>
                  </a>
                  
              </div>
              <div class="card-body">
                <c:if test="${ not empty msgSucesso }">
                  <a class="btn btn-success btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-check"></i>
                       </span>
                       <span class="text">${ msgSucesso }</span>
                  </a>
                  <br><br>
                </c:if>
                <c:if test="${ not empty msgErro }">
                  <a class="btn btn-warning btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-exclamation-triangle"></i>
                       </span>
                       <span class="text">${ msgErro }</span>
                  </a>
                  <br><br>
                </c:if>
                
                <c:if test="${ not empty listaAtividades}">
                <div class="table-responsive">
                   <table class="table table-striped">
                       <thead style="background-color: #1cc88a; color: white;">
                           <tr>
                               <th>Atividade</th>
                               <th>Intensidade</th>
                               <th>Início</th>
                               <th>Fim</th>
                               <th>Gasto Calórico</th>
                               <th></th>
                               <th></th>
              
                           </tr>
                       </thead>
                       <tbody>
                           
                        <c:forEach items="${ listaAtividades }" var="atividade">
                        
                           <tr>
                               <td>${ atividade.titulo }</td>
                               <td>${ atividade.ritmo.nomeRitmo}</td>
                               <td>
                                 <fmt:formatDate pattern="dd/MM/yyy HH:mm:ss" 
                                                 value="${ atividade.dataInicio.time}"/>
                               </td>
                               <td>
                                 <fmt:formatDate type="both" value="${ atividade.dataFim.time }"/>
                               </td>
                               <td>${ atividade.kcalPerdida}</td>
                               <td><a href="atividades?action=editar&atividade=${ fn:toLowerCase(atividade.titulo) }&id=${ atividade.codigo }">
                               				<i class="fas fa-edit"></i>
                               		 </a>
                               </td>
                               <td>
                               <form action="atividades" method="post" id="form-excluir-${ atividade.codigo }">
                                <input type="hidden" name="action" value="excluir">
                               	<input type="hidden" name="id" value="${ atividade.codigo }">
                               	<a href="#" onclick="document.getElementById('form-excluir-${ atividade.codigo }').submit();" 
                               				class="fas fa-window-close"></a>
                               </form>
                               </td>
                           </tr>
                       
                        </c:forEach>
                        
                       </tbody>
                   </table>
               </div>
               </c:if>
               <c:if test="${ empty listaAtividades }">
               	<p>Nenhuma atividade registrada nesse dia.</p>
               </c:if>
               
              </div>
            </div>
          </div>
        </div>
        
        
        <div class="row">
          <div class="col-lg-12 mb-4">
                  <a href="#" class="btn btn-primary btn-icon-split" data-toggle="modal" 
                     data-target="#addAtividade">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Atividade</span>
                  </a>
          </div>
        </div>
        
    </div>
    <!-- /.container-fluid -->

    <%@ include file="includes/end-main-content.jsp" %>
    
    <%@ include file="includes/dashboard-footer.jsp" %>

    <%@ include file="includes/end-content-page-wrapper.jsp" %>

    <%@ include file="includes/scroll-top-button.jsp" %>

    <%@ include file="includes/modal-logout.jsp" %>
    
		<!-- Logout Modal-->
		<div class="modal fade" id="addAtividade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		    aria-hidden="true">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content" style="border-radius: 8px;">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">Registrar nova atividade</h5>
		                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">X</span>
		                </button>
		            </div>
		            <div class="modal-body">
		              
		              <form action="atividades" method="post">
                    <input type="hidden" name="action" value="cadastrar">
                    
	                  <div class="form-row">
	                     <div class="input-group mb-3 col-md-6">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text">Atividade</label>
	                        </div>
	                        <select id="atv" name="atividade" class="custom-select" required>
	                            <option value="" selected>Selecione...</option>
	                            <option value="caminhada">Caminhada</option>
	                            <option value="corrida">Corrida</option>
	                            <option value="ciclismo">Ciclismo</option>
	                            <option value="natacao">Natação</option>
	                        </select>
	                     </div>
                      
	                      <div class="input-group mb-3 col-md-6">
	                         <div class="input-group-prepend">
	                             <label class="input-group-text" for="ritmoAtividade">Ritmo</label>
	                         </div>
	                         <select name="ritmo" class="custom-select" id="ritmoAtividade" 
	                         				 data-toggle="tooltip" data-placement="top" data-html="true"
	                         				 title="texto" required>
	                             <option value="" selected>Selecione...</option>
	                             <option value="1">Leve</option>
	                             <option value="2">Moderado</option>
	                             <option value="3">Intenso</option>
	                         </select>
	                      </div>
                    </div>
                   
                    <div class="form-row">       
                      <div class="form-group col-md-6">
                        <label for="dt-inicio" class="label-custom">Início:</label>
                         <input type="datetime-local" name="dt-inicio" step="1" class="form-control input-custom" 
                         	      value="${ dtExibidaAtv }T00:00:00" required>
                       </div>
                         
                       <div class="form-group col-md-6">
                        <label for="dt-fim" class="label-custom">Fim:</label>
                         <input type="datetime-local" name="dt-fim" step="1" class="form-control input-custom" 
                         	      value="${ dtExibidaAtv }T00:00:00" required>
                       </div>  
                    </div>
     
                     
                   <div class="form-row" >    
	                   <div class="form-group col-md-6" id="estilo-ntc" style="display:none">
	                   		<br>
	                      <div class="input-group mb-3">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text" for="inputGroupSelect02">Estilo</label>
	                        </div>
	                        <select id="estilo-natacao" name="estilo-natacao" class="custom-select" id="inputGroupSelect02">
	                            <option value="" selected>Selecione...</option>
	                            <option value="1">Borboleta</option>
	                            <option value="2">Peito</option>
	                            <option value="3">Costas</option>
	                        </select>
	                      </div>
	                   </div>
                      
                      <div class="form-group col-md-6" id="dtc" style="display:none">
                        <label for="distancia" class="label-custom">Distância em KM:</label>
                        <input type="number" step="0.01" id="distancia" name="distancia" class="form-control input-custom" 
                               placeholder="Ex: 12,5">
                      </div>                             
                    </div>
                    
                    <div class="modal-footer">
	                    <input class="btn btn-secondary" type="button" value="Cancelar" data-dismiss="modal">
	                    <input class="btn btn-primary" type="submit" value="Adicionar">
                    </div>
                </form>
		            
		            </div>
		            
		        </div>
		    </div>
		</div>
    

  <%@ include file="includes/atividade-js.jsp" %>
</body>

</html>