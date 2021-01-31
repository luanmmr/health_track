<script>
var labels = [];
var valuesGanho = [];
var valuesPerda = [];

<c:forEach items="${ chartGanhoPerdaCaloriasSemanalLabels }" var="label">
	labels.push("${ label }");
</c:forEach>

<c:forEach items="${ chartGanhoPerdaCaloriasSemanalGanho }" var="value1">
	valuesGanho.push(${ value1 });
</c:forEach>

<c:forEach items="${ chartGanhoPerdaCaloriasSemanalPerda }" var="value2">
	valuesPerda.push(${ value2 });
</c:forEach>

var ctx = document.getElementById("myBarChart");
var myBarChart = new Chart(ctx, {
  type: 'bar',
  data: {
    datasets: [{
    	label: 'Ganho',
      data: valuesGanho,
      backgroundColor: "#4e73df"
    	}, 
    	{
    		label: 'Perda',
    		data: valuesPerda,
    		backgroundColor: "#1cc88a",
    		type: 'bar'	
    }],
    labels: labels
  },
  options: {
	  title: {
		  display: true,
		  text: 'Ganho/Perda de Calorias (Semanal)',
		  position: 'left',
		  fontSize: '11',
		  padding: 0
	  },
    maintainAspectRatio: false,
    tooltips: {
    	backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 8,
      titleMarginBottom: 0,
      yPadding: 6,
      displayColors: true,
      caretPadding: 5
    },
   legend: {
      display: true,
   },
    scales: {
        xAxes: [{
            gridLines: {
                display: false,
                drawBorder: false
            }
        }],
        yAxes: [{
            gridLines: {
                display: true,
                drawBorder: false
            },
            ticks: {
                maxTicksLimit: 8,
                padding: 15
            }
        }]
    },
  },
});
</script>