package br.com.healthtrack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.GrupoAlimento;
import br.com.healthtrack.bean.Medida;
import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.GrupoAlimentoDAO;
import br.com.healthtrack.dao.MedidaDAO;
import br.com.healthtrack.exception.DBException;

/**
 * Servlet implementation class AlimentoServlet
 */
@WebServlet("/alimentos")
public class AlimentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GrupoAlimentoDAO grupoDAO;
	private MedidaDAO medidaDAO;
	private AlimentoDAO alimentoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimentosServlet() {
        super();
        grupoDAO = DAOFactory.getGrupoAlimentoDAO();
        medidaDAO = DAOFactory.getMedidaDAO();
        alimentoDAO = DAOFactory.getAlimentoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
		case "cadastrar" :
		  cadastrar(request, response);
		  break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch(action) {
		case "create" :
		  create(request, response);
		  break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("grupoAlimento", grupoDAO.lista());
		session.setAttribute("medidas", medidaDAO.lista());
		
		request.getRequestDispatcher("alimento.jsp").forward(request, response);
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		Medida medida = new Medida(Integer.parseInt(request.getParameter("medida")));
		double valorMedida = Double.parseDouble(request.getParameter("valor-medida"));
		double totalKcal = Double.parseDouble(request.getParameter("kcal"));
		GrupoAlimento grupoAlimento = new GrupoAlimento(Integer.parseInt(request.getParameter("grupo")));
		Alimento alimento = new Alimento(0, request.getParameter("nome"), medida, valorMedida, totalKcal, grupoAlimento);
		alimento.setCarboidrato(Double.parseDouble(request.getParameter("carboidrato")));
		alimento.setGorduraTotal(Double.parseDouble(request.getParameter("gordura-total")));
		alimento.setProteina(Double.parseDouble(request.getParameter("proteina")));
		alimento.setGorduraSaturada(Double.parseDouble(request.getParameter("gordura-saturada")));
		alimento.setGorduraTrans(Double.parseDouble(request.getParameter("gordura-trans")));
		alimento.setColesterol(Double.parseDouble(request.getParameter("colesterol")));
		alimento.setSodio(Double.parseDouble(request.getParameter("sodio")));
		alimento.setPotassio(Double.parseDouble(request.getParameter("potassio")));
		alimento.setFibraDietetica(Double.parseDouble(request.getParameter("fibra")));
		alimento.setAcucares(Double.parseDouble(request.getParameter("acucares")));
		alimento.setVitaminaA(Double.parseDouble(request.getParameter("vitamina-a")));
		alimento.setVitaminaC(Double.parseDouble(request.getParameter("vitamina-c")));
		alimento.setCalcio(Double.parseDouble(request.getParameter("calcio")));
		alimento.setFerro(Double.parseDouble(request.getParameter("ferro")));
		
		try {
		  alimentoDAO.cadastrar(alimento);
		  request.setAttribute("msgSucesso", "Alimento cadastrado!");
			
		} catch (DBException e) {
		    e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao cadastrar alimento.");
		    
		}
		
		request.getRequestDispatcher("alimento.jsp").forward(request, response);
	}
	
	

}
