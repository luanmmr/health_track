<script>
//Pie Chart Example
var labels = [];
var values = [];

<c:forEach items="${ chartAlimentosSegmentoSemanalLabels }" var="label">
labels.push("${ label }");
</c:forEach>

<c:forEach items="${ chartAlimentosSegmentoSemanalValues }" var="value">
values.push(${ value });
</c:forEach>

var ctx = document.getElementById("chartAlimentosSegmentoSemanal");
var myPieChart = new Chart(ctx, {
  type: 'polarArea',
  data: {
    labels: labels,
    datasets: [{
      data: values,
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc', '#f6c23e', '#4e73df', '#e74a3b', '#858796', '#f8f9fc', '#5a5c69'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)"
    }],
  },
  options: {
	  title: {
		  display: true,
		  text: 'Alimentos Consumidos (Semanal)',
		  position: 'bottom',
		  fontSize: '11',
		  padding: 10
	  },
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: true,
      caretPadding: 10
    },
    legend: {
      display: false
    },
    cutoutPercentage: 60
  },
});
</script>