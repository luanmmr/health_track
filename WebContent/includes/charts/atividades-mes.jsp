    <script>

    var meses = [];
    var quants = [];
    
    <c:forEach items="${ chartAtvMesMMMyy }" var="mes">
    	meses.push("${ mes }");
    </c:forEach>
    
    <c:forEach items="${ chartAtvMesQuant }" var="quant">
    	quants.push(${ quant });
		</c:forEach>

    // Area Chart Example
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: meses,
        datasets: [{
          label: "Atividades",
          lineTension: 0.0,
          backgroundColor: "rgba(78, 115, 223, 0.05)",
          borderColor: "rgba(78, 115, 223, 1)",
          pointRadius: 3,
          pointBackgroundColor: "rgba(78, 115, 223, 1)",
          pointBorderColor: "rgba(78, 115, 223, 1)",
          pointHoverRadius: 3,
          pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
          pointHoverBorderColor: "rgba(78, 115, 223, 1)",
          pointHitRadius: 10,
          pointBorderWidth: 2,
          data: quants
        }],
      },
      options: {
    	  title: {
    		  display: true,
    		  text: 'Atividades (Últimos 12 meses)',
    		  position: 'left',
    		  fontSize: '11',
    		  padding: 0
    	  },
        maintainAspectRatio: false,
        layout: {
          padding: {
            left: 5
          }
        },
        scales: {
          xAxes: [{
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
              gridLines: {
                display: true,
                drawBorder: false,
                padding: 5
              },
              ticks: {
                maxTicksLimit: 7,
                padding: 15
              }
           }]
        },
        legend: {
          display: false
        },
        tooltips: {
          backgroundColor: "rgb(255,255,255)",
          bodyFontColor: "#858796",
          titleMarginBottom: 10,
          titleFontColor: '#6e707e',
          titleFontSize: 14,
          borderColor: '#dddfeb',
          borderWidth: 1,
          xPadding: 15,
          yPadding: 15,
          displayColors: false,
          intersect: false,
          mode: 'index',
          caretPadding: 10
        }
      }
    });

    </script>