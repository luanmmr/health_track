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
  <title>Perfil</title>
  <%@ include file="includes/dashboard-style.jsp" %>
</head>

<body id="page-top">
  <%@ include file="includes/dashboard-side-top-bar.jsp" %>

    <!-- Begin Page Content -->
    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Perfil</h1>
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
                   
                  <form action="user" method="post" style="margin-top: 20px;">
                  <input type="hidden" name="action" value="atualizar">
                
                   <div class="form-row">
                      <div class="form-group col-md-6">
                        <label for="nome" class="label-custom">Nome:</label>
                         <input type="text" name="nome" class="form-control input-custom" 
                                value="${ user.nome }" required>
                      </div>
                      
                      <div class="form-group col-md-6">
                        <label for="sobrenome" class="label-custom">Sobrenome:</label>
                           <input type="text" name="sobrenome" class="form-control input-custom" 
                                  value="${ user.sobrenome }" required>
                      </div>
                   </div>
                   
                   <div class="form-row">       
                      <div class="form-group col-md-6">
                        <label for="email" class="label-custom">Email:</label>
                         <input type="email" name="email" class="form-control input-custom"
                                value="${ user.email }" required>
                       </div>
                         
                      <div class="form-group col-md-6">
                        <label for="senha" class="label-custom">Senha:</label>
                         <input type="password" name="senha" class="form-control input-custom" 
                                value="${ pwd }" required>
                       </div>  
                    </div>
                       
                    <div class="form-row">       
                      <div class="form-group col-md-6">
                        <label for="data" class="label-custom">Data de Nasc:</label>
                         <input type="date" name="data" class="form-control input-custom" 
                                value='<fmt:formatDate pattern="yyyy-mm-dd" 
                                                       value="${ user.dataNascimento.time }" />' required>
                       </div>
                    </div>
                       
                        
                     <div class="form-row">
                       <div class="form-group col-md-4" style="margin-top: 6px;">
                         <input class="btn btn-sm btn-primary" type="submit" value="Atualizar">
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