<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="Luan Ribeiro">
  <title>Dashboard - Health Track</title>
  <%@ include file="includes/dashboard-style.jsp" %>
</head>

<body id="page-top">
  <%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                    class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
        </div>

        <!-- Content Row -->
        <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <c:if test="${ user.imc < 18.5 }">
            	<c:set var="stilo" value="danger" />
            	<c:set var="classificacao" value="Abaixo do Peso" />
            </c:if>
            
            <c:if test="${ user.imc >= 18.5 &&  user.imc <= 24.9}">
            	<c:set var="stilo" value="success" />
            	<c:set var="classificacao" value="Normal" />
            </c:if>
            
            <c:if test="${ user.imc > 24.9 &&  user.imc <= 30}">
            	<c:set var="stilo" value="warning" />
            	<c:set var="classificacao" value="Sobrepeso" />
            </c:if>
            
            <c:if test="${ user.imc > 30.0 }">
            	<c:set var="stilo" value="danger" />
            	<c:set var="classificacao" value="Obesidade" />
            </c:if>
            
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-${ stilo } shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-${ stilo } text-uppercase mb-1">
                                    IMC</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">
                                	<fmt:formatNumber type="number" maxFractionDigits="2" value="${ user.imc }" />
                                	<span style="margin-left: 10px; font-size: 15px; background-color: black; 
                                						   border-radius: 10px; color: white; padding: 4px;">${ classificacao }</span>
                                	
                                </div>
                            </div>
      
                        </div>
                    </div>
                </div>
            </div>

         <!-- Earnings (Monthly) Card Example -->
         		<c:if test="${ porcentagemMeta < 50 }">
         			<c:set var="stilo" value="danger" />
         		</c:if>
         		<c:if test="${ porcentagemMeta > 50 && porcentagemMeta < 100 }">
         		  <c:set var="stilo" value="info" />
         		</c:if>
         		<c:if test="${ porcentagemMeta >= 100 }">
         			<c:set var="stilo" value="success" />
         		</c:if>
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-${ stilo } shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-${ stilo } text-uppercase mb-1">Meta de Gasto Calórico
                                </div>
                                <div class="row no-gutters align-items-center">
                                    <div class="col-auto">
                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${ porcentagemMeta }%</div>
                                    </div>
                                    <div class="col">
                                        <div class="progress">
                                          <div class="progress-bar bg-${ stilo } progress-bar-striped progress-bar-animated" 
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

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-info shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                    Total Calorias</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${ caloriasObtidasAli } kcal</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-apple-alt fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            
            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    Calorias Perdidas</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${ caloriasPerdidasAtv } kcal</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-biking fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Content Row -->

        <div class="row">

            <!-- Chart Atividades (Últimos 12 meses) -->
            <div class="col-xl-8 col-lg-7">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Atividades (Últimos 12 meses)</h6>
                        <div class="dropdown no-arrow">
                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <div class="dropdown-header">Dropdown Header:</div>
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </div>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-area">
                            <canvas id="myAreaChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Chart Atividades por Segmento (Mês) -->
            <div class="col-xl-4 col-lg-5">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Atividades por Segmento (Mês)</h6>
                        <div class="dropdown no-arrow">
                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <div class="dropdown-header">Dropdown Header:</div>
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </div>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-pie pt-4 pb-2">
                            <canvas id="myPieChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        
        
            <div class="row">
            
            <!-- Area Chart -->
            <div class="col-xl-4 col-lg-5">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Alimentos Consumidos (Semanal)</h6>
                        <div class="dropdown no-arrow">
                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <div class="dropdown-header">Dropdown Header:</div>
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </div>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-area">
                            <canvas id="chartAlimentosSegmentoSemanal"></canvas>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Ganho/Perda de Calorias (Semanal)</h6>
                        <div class="dropdown no-arrow">
                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                                <div class="dropdown-header">Dropdown Header:</div>
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </div>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-area">
                            <canvas id="myBarChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            

        </div>

		 <%--<%@ include file="includes/elementos-teste.jsp" %> --%>
				
    </div>
    <!-- /.container-fluid -->

    <%@ include file="includes/end-main-content.jsp" %>
    
    <%@ include file="includes/dashboard-footer.jsp" %>

    <%@ include file="includes/end-content-page-wrapper.jsp" %>

    <%@ include file="includes/scroll-top-button.jsp" %>

    <%@ include file="includes/modal-logout.jsp" %>
    
  <%@ include file="includes/default-dashboard-js.jsp" %>
  <%@ include file="includes/dashboard-js.jsp" %>
</body>

</html>