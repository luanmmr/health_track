<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<meta name="description" content="">
 	 	<meta name="author" content="Luan Ribeiro">
		<title>Editar Atividade</title>
		<%@ include file="includes/dashboard-style.jsp" %>
	</head>
<body id="page-top">
  <%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Editar</h1>
        </div>

        <!-- Content Row -->
        <div class="row">
          <div class="col-lg-8 mb-4">
          <!-- Approach -->
            <div class="card shadow mb-4">
              <div class="card-body">
								<c:if test="${ editarAtividade.titulo == 'Caminhada' }">
                  <%@ include file="includes/editar/caminhada.jsp" %>
                </c:if>
                <c:if test="${ editarAtividade.titulo == 'Ciclismo' }">
                  <%@ include file="includes/editar/caminhada.jsp" %>
                </c:if>
                <c:if test="${ editarAtividade.titulo == 'Corrida' }">
                  <%@ include file="includes/editar/caminhada.jsp" %>
                </c:if>
                <c:if test="${ editarAtividade.titulo == 'Natacao' }">
                  <%@ include file="includes/editar/natacao.jsp" %>
                </c:if>	
              </div>
            </div>
          </div>
          
          <div class="col-lg-4 mb-4">
          <!-- Approach -->
            <div class="card shadow mb-4">
              <div class="card-body">
                <img src="resources/images/img-propaganda.jpg" width="100%" heigth="100%" />
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