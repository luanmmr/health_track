<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
	<title>Log In - Acesse sua conta</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<meta http-equiv="X-UA-Compatible" content="IE-edge">
  <meta name="author" content="Luan Ribeiro">
  <meta name="description" content="">
  <meta name="keywords" content="">
	<%@ include file="includes/dashboard-style.jsp" %>
	<link href="resources/css/custom/login.css" rel="stylesheet">
	
	</head>
	<body>
	  <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
	    <div class="container">
	      <div class="card login-card">
	        <div class="row no-gutters">
	          <div class="col-md-6">
	            <img src="resources/images/img-login.jpg" alt="login" class="login-card-img">
	          </div>
	          <div class="col-md-6">
	            <div class="card-body">
	              <div class="brand-wrapper">
	                <img src="resources/images/logo-health-track.jpg" alt="logo" class="logo">
	              </div>
	              <p class="login-card-description">
	                 <c:if test="${ empty msgSucesso && empty msgErro }">
	                   Acesse sua conta
	                 </c:if>
	                 <c:if test="${ not empty msgSucesso }">
	                   <a class="btn btn-success btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-check"></i>
                       </span>
                       <span class="text">${ msgSucesso }</span>
                     </a>
	                 </c:if>
	                 <c:if test="${ not empty msgErro }">
	                   <a class="btn btn-danger btn-icon-split">
                       <span class="icon text-white-50">
                           <i class="fas fa-exclamation-triangle"></i>
                       </span>
                       <span class="text">${ msgErro }</span>
                     </a>
	                 </c:if>
	              </p>
	              <form action="login" method="post">
	                <div class="form-group">
	                  <label for="email" class="sr-only">Email</label>
	                  <input type="email" name="email" id="email" class="form-control" placeholder="Endereço de email" required>
	                </div>
	                <div class="form-group mb-4">
	                  <label for="password" class="sr-only">Password</label>
	                  <input type="password" name="password" id="password" class="form-control" placeholder="***********" required>
	                </div>
	                <input name="login" id="login" class="btn btn-block login-btn mb-4" type="submit" value="Entrar" >
	              </form>
	              <a href="#" class="small">Esqueceu a senha?</a>
	              <p>Não tem uma conta? <a href="signup.jsp" >Cadastra-se aqui!</a></p>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
    </main>

	  <%@ include file="includes/bootstrap-js.jsp" %>
	</body>
</html>