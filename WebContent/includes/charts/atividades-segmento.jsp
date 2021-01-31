<script>
//Pie Chart Example
var labels = [];
var values = [];

<c:forEach items="${ chartAtividadesSegmentoLabels }" var="label">
labels.push("${ label }");
</c:forEach>

<c:forEach items="${ chartAtividadesSegmentoValues }" var="value">
values.push(${ value });
</c:forEach>

var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
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
		  text: 'Atividades por Segmento (Mês)',
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
      xPadding: 15,
      yPadding: 15,
      displayColors: true,
      caretPadding: 10
    },
    legend: {
      display: true,
      position: 'bottom',
    },
    cutoutPercentage: 60
  },
});
</script>