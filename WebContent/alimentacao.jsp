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
  	<title>Alimentação</title>
  	<%@ include file="includes/dashboard-style.jsp" %>
	</head>
	
	<body id="page-top">
		<%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->

            <h1 class="h3 mb-2 text-gray-800">Alimentação</h1>
            <p class="mb-4">Registros de sua alimentação no dia.
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
                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${ porcentagemMeta }%</div>
                                    </div>
                                    <div class="col">
                                        <div class="progress">
                                          <div class="progress-bar bg-success progress-bar-striped progress-bar-animated" 
                                               role="progressbar" aria-valuenow="${ porcentagemMeta }" aria-valuemin="0" aria-valuemax="100" 
                                               style="width: ${ porcentagemMeta }%">
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
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">DATA</div>
                                <div class="h6 mb-0 font-weight-bold text-gray-800">
                                  <form action="alimentacao" method="get" id="form-data">
                                    <input type="hidden" name="action" value="alterar-data">
                                    <input type="date" value="${ dtExibidaAli }" onchange="document.getElementById('form-data').submit();"
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
                    Alimentos Ingeridos
                  </h6>
                  
                  <a href="#" class="btn btn-primary btn-icon-split" data-toggle="modal" 
                     data-target="#addAlimentacao">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Adicionar</span>
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
                
                <c:if test="${ not empty listaAlimentacao }">
                <div class="table-responsive">
                   <table class="table table-striped">
                       <thead style="background-color: #1cc88a; color: white;">
                           <tr>
                               <th>Alimento</th>
                               <th>Período</th>
                               <th>Data</th>
                               <th>Quantidade</th>
                               <th>Total Kcal</th>
                               <th></th>
                               <th></th>
              
                           </tr>
                       </thead>
                       <tbody>
                           
                        <c:forEach items="${ listaAlimentacao }" var="alimentacao">
                        
                           <tr>
                               <td>${ alimentacao.alimento.nome }</td>
                               <td>${ alimentacao.periodoRefeicao.nomePeriodo}</td>
                               <td>
                                 <fmt:formatDate pattern="dd/MM/yyy HH:mm:ss" 
                                                 value="${ alimentacao.dataIngestao.time}"/>
                               </td>
                               <td>
                                 ${ alimentacao.quantidade}
                               </td>
                               <td>${ alimentacao.totalKcal}</td>
                               <td><a href="atividades?action=editar&atividade&id=${ atividade.codigo }">
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
               <c:if test="${ empty listaAlimentacao }">
               	<p>Nenhuma alimentação registrada nesse dia.</p>
               </c:if>
               
              </div>
            </div>
          </div>
        </div>
        
        
        <div class="row">
          <div class="col-lg-12 mb-4">
                  <a href="#" class="btn btn-primary btn-icon-split" data-toggle="modal" 
                     data-target="#addAlimentacao">
                    <span class="icon text-white-50">
                        <i class="fas fa-plus"></i>
                    </span>
                    <span class="text">Adicionar</span>
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
		<div class="modal fade" id="addAlimentacao" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		    aria-hidden="true">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content" style="border-radius: 8px;">
		            <div class="modal-header">
		                <h5 class="modal-title" id="exampleModalLabel">Registrar Alimentação</h5>
		                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">X</span>
		                </button>
		            </div>
		            <div class="modal-body">
		              
		              <form action="atividades" method="post">
                    <input type="hidden" name="action" value="cadastrar">
                    
                    <div class="form-row">
	                     <div class="mb-3 col-md-12">
	                        Se não encontrar o alimento, <a href="alimentos?action=cadastrar">clique aqui</a> para adicionar.
	                     </div>
                    </div>
                    
	                  <div class="form-row">
	                     <div class="input-group mb-3 col-md-12">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text">Alimento</label>
	                        </div>
	                        <select id="atv" name="atividade" class="custom-select" required>
	                            <option value="" selected>Selecione...</option>
	                            <option value="caminhada">Caminhada</option>
	                            <option value="corrida">Corrida</option>
	                            <option value="ciclismo">Ciclismo</option>
	                            <option value="natacao">Natação</option>
	                        </select>
	                     </div>
                    </div>
                    
                     <div class="form-row">
	                     <div class="input-group mb-3 col-md-8">
	                        <div class="input-group-prepend">
	                            <label class="input-group-text">Período</label>
	                        </div>
	                        <select id="atv" name="atividade" class="custom-select" required>
	                            <option value="" selected>Selecione...</option>
	                            <option value="caminhada">Caminhada</option>
	                            <option value="corrida">Corrida</option>
	                            <option value="ciclismo">Ciclismo</option>
	                            <option value="natacao">Natação</option>
	                        </select>
	                     </div>
                    </div>
                    
                    <div class="form-row">       
                      <div class="form-group col-md-5">
                        <label for="dt-inicio" class="label-custom">Data:</label>
                         <input type="date" name="dt-inicio" step="1" class="form-control input-custom" 
                         	      value="" required>
                       </div>
                         
                       <div class="form-group col-md-2">
                        <label for="dt-fim" class="label-custom">Quant.:</label>
                         <input type="number" name="dt-fim" step="1" class="form-control input-custom" 
                         	      required>
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
		
		<%@ include file="includes/alimentacao-js.jsp" %>
	</body>
</html>
