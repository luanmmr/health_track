    <!-- Bootstrap core JavaScript -->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/js/custom/sb-admin-2.min.js"></script>
    	 
	 <script>
		 $(function () {
			  $('[data-toggle="tooltip"]').tooltip()
			})
	 </script>
	 
	     <script>
		 $("#atv").change(function() {
		    $('#dtc').hide();
		    if(this.value == "caminhada" || this.value == "ciclismo" || this.value == "corrida") {
		    	document.getElementById('estilo-ntc').style.display = 'none';
		    	document.getElementById('dtc').style.display = 'initial';
		    	document.getElementById('estilo-natacao').required = false;
		    	document.getElementById('distancia').required = true;
		    	
		    } else if(this.value == "natacao") {
		    	document.getElementById('dtc').style.display = 'none';
		    	document.getElementById('estilo-ntc').style.display = 'initial';
		    	document.getElementById('distancia').required = false;
		    	document.getElementById('estilo-natacao').required = true;
		    	
		    }

		 });
	 </script>