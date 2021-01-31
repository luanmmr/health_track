<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
  <title>Sign Up - Crie sua conta</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE-edge">
  <meta name="author" content="Luan Ribeiro">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <%@ include file="includes/dashboard-style.jsp" %>
  <link href="resources/css/custom/signup.css" rel="stylesheet">
  
  </head>
  <body>
    <main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
      <div class="container">
        <div class="card login-card">
          <div class="row no-gutters">
            <div class="col-md-6">
              <img src="resources/images/img-signup.jpg" alt="login" class="login-card-img">
            </div>
            <div class="col-md-6">
              <div class="card-body">
                <form action="user" method="post">
                  <input type="hidden" name="action" value="cadastrar">
                    
	                 <div class="form-row">
		                  <div class="col-md-6">
		                    <label for="nome" class="label-custom">Nome:</label>
		                     <input type="text" name="nome" class="form-control input-custom" required>
		                  </div>
		                  
		                  <div class="col-md-6">
		                    <label for="sobrenome" class="label-custom">Sobrenome:</label>
		                       <input type="text" name="sobrenome" class="form-control input-custom" required>
		                  </div>
	                 </div>
	                 
                   <div class="form-row">       
	                    <div class="col-md-6">
	                      <label for="email" class="label-custom">Email:</label>
	                       <input type="email" name="email" class="form-control input-custom" required>
	                     </div>
	                       
	                    <div class="col-md-6">
	                      <label for="senha" class="label-custom">Senha:</label>
	                       <input type="password" name="senha" class="form-control input-custom" required>
	                     </div>  
                    </div>
										
										<div class="form-row">
										   <div class="col-md-6">
	                       <label for="data" class="label-custom">Data de Nasc:</label>
	                        <input type="date" name="data" class="form-control input-custom" required>
	                       </div>
	                       
                      	<div class="col-md-6">
                          <label for="meta-gasto-kcal" class="label-custom">Meta Gasto Calórico (dia)</label>
	                         <input type="number" value="0" name="meta-gasto-kcal" 
	                                class="form-control input-custom">
                         </div>
                   </div>
										
                    <div class="form-row">
                    
                    	<div class="col-md-3">
                       <label for="peso" class="label-custom">Peso:</label>
                        <input type="text" name="peso" class="form-control input-custom" required>
                      </div>
                      
                      <div class="col-md-3">
                       <label for="altura" class="label-custom">Altura:</label>
                        <input type="text" name="altura" class="form-control input-custom" required>
                      </div>     
	                      
                      <div class="col-md-3">
                        <label for="sistolica" class="label-custom">Sistólica:</label>
                         <input type="number" value="0" name="sistolica" 
                              class="form-control input-custom">
                       </div>
                         
                      <div class="col-md-3">
                        <label for="diastolica" class="label-custom">Diastólica:</label>
                         <input type="number" value="0" name="diastolica" 
                              class="form-control input-custom">
                       </div> 

                     </div>
                      
                     
                      
                      <div class="form-row">  
                      	<div class="col-md-4" style="margin-top: 5px">
                          <label class="label-custom"></label>
                           <input class="btn btn-md btn-primary" type="submit" value="Criar">
                         </div>   
                      </div>
                  <br>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>

    <%@ include file="includes/bootstrap-js.jsp" %>
  </body>
</html>