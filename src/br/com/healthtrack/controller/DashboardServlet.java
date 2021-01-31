package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.image.impl.IntArgb;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.IngestaoDAO;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AtividadeDAO atividadesDAO;
	private IngestaoDAO ingestoesDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        atividadesDAO = DAOFactory.getAtividadeDAO();
        ingestoesDAO = DAOFactory.getIngestaoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		
		metaCalorias(request, usuario);
		chartAtividadesMes(request, usuario);
		chartAtividadesSegmento(request, usuario);
		chartGanhoPerdaCaloriasSemanal(request, usuario);
		chartAlimentosSegmentoSemanal(request, usuario);
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void chartAtividadesMes(HttpServletRequest request, Usuario usuario) {
		SimpleDateFormat fMesMMM = new SimpleDateFormat("MMM yy");
		SimpleDateFormat fMesMMyy = new SimpleDateFormat("MM/yy");
		Calendar dataAtual = Calendar.getInstance();
		String[] mesesMMM = new String[12];
		int[] valoresMeses = new int[12];
		
		for(int x = 11; x >= 0; x--) {
		  mesesMMM[x] = fMesMMM.format(dataAtual.getTime());
		  valoresMeses[x] = atividadesDAO.quantAtvMes(fMesMMyy.format(dataAtual.getTime()), usuario);
		  dataAtual.add(Calendar.MONTH, -1);
		}
		
		request.setAttribute("chartAtvMesMMMyy", mesesMMM);
		request.setAttribute("chartAtvMesQuant", valoresMeses);
	}
	
	private void chartAtividadesSegmento(HttpServletRequest request, Usuario usuario) {
		SimpleDateFormat fMesMMyy = new SimpleDateFormat("MM/yy");
		Calendar dataAtual = Calendar.getInstance();
		Map<String, Integer> hashMap = atividadesDAO.atvSegmento(fMesMMyy.format(dataAtual.getTime()), usuario);
		
		List<String> atvs = new ArrayList<String>();
		List valores = new ArrayList();
		
		for (String atv : hashMap.keySet()) {
			atvs.add(atv);
			valores.add(hashMap.get(atv));
		}
		
		request.setAttribute("chartAtividadesSegmentoLabels", atvs);
		request.setAttribute("chartAtividadesSegmentoValues", valores);
	}
	
	private void chartGanhoPerdaCaloriasSemanal(HttpServletRequest request, Usuario usuario) {
		Calendar dataAtual = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM");
		dataAtual.setFirstDayOfWeek(Calendar.SUNDAY);
		dataAtual.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String[] labelsDatas = new String[7];
		double[] valuesPerda = new double[7];
		double[] valuesGanho = new double[7];

		for (int x = 0; x < 7; x++) {
		  labelsDatas[x] = f.format(dataAtual.getTime());
		  valuesPerda[x] = atividadesDAO.caloriasPerdidasDia(usuario, dataAtual);
		  valuesGanho[x] = ingestoesDAO.caloriasGanhasDia(usuario, dataAtual);
		  dataAtual.add(Calendar.DATE, 1);
		}
		
		request.setAttribute("chartGanhoPerdaCaloriasSemanalLabels", labelsDatas);
		request.setAttribute("chartGanhoPerdaCaloriasSemanalPerda", valuesPerda);
		request.setAttribute("chartGanhoPerdaCaloriasSemanalGanho", valuesGanho);
	}
	
	private void chartAlimentosSegmentoSemanal(HttpServletRequest request, Usuario usuario) {
		List<String> labels = new ArrayList<String>();
		List values = new ArrayList();
		Map<String, Integer> alimentos = ingestoesDAO.alimentosSegmentoSemanal(usuario);
		
		for (String alimento : alimentos.keySet()) {
			labels.add(alimento);
			values.add(alimentos.get(alimento));
		}
		
		request.setAttribute("chartAlimentosSegmentoSemanalLabels", labels);
		request.setAttribute("chartAlimentosSegmentoSemanalValues", values);
	}
	
	private void metaCalorias(HttpServletRequest request, Usuario usuario) {
		Calendar data = Calendar.getInstance();
		int porcentagemMeta = 0;
		
		
		// Atividades - Calorias Perdidas
		List<Atividade> listaAtv = atividadesDAO.listaAtividadesDia(usuario, data);
		double caloriasPerdidasAtv = 0;
		
		for (Atividade atv : listaAtv)
		  caloriasPerdidasAtv += atv.getKcalPerdida();
		
		// Alimentações - Calorias Obtidas
		List<Ingestao> listaAli = ingestoesDAO.listarDia(usuario, data);
		double caloriasObtidasAli = 0;
		
		for (Ingestao ali : listaAli)
			caloriasObtidasAli += ali.getTotalKcal();
		
		porcentagemMeta = Integer.parseInt(String.format("%.0f", 
							( caloriasPerdidasAtv - caloriasObtidasAli) / usuario.getMetaGastoCalorico() * 100));
		request.setAttribute("porcentagemMeta", porcentagemMeta);
		request.setAttribute("caloriasObtidasAli", caloriasObtidasAli);
		request.setAttribute("caloriasPerdidasAtv", caloriasPerdidasAtv);
		int[] arrayTeste = {17, 517, 127, 27, 87, 97, 67, 57, 37, 147, 987, 257};
		request.setAttribute("testeChart", arrayTeste);
	}
	

}
