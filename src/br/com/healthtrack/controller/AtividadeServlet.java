package br.com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.bean.Atividade;
import br.com.healthtrack.bean.Caminhada;
import br.com.healthtrack.bean.Ciclismo;
import br.com.healthtrack.bean.Corrida;
import br.com.healthtrack.bean.EstiloNatacao;
import br.com.healthtrack.bean.Natacao;
import br.com.healthtrack.bean.RitmoAtividade;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AtividadeDAO;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.OracleUsuarioDAO;
import br.com.healthtrack.exception.DBException;
import sun.net.www.content.audio.aiff;

/**
 * Servlet implementation class AtividadeServlet
 */
@WebServlet("/atividades")
public class AtividadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AtividadeDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtividadeServlet() {
        super();
        dao = DAOFactory.getAtividadeDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		
		default:
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		String atividade = request.getParameter("atividade");
		int idRitmo = Integer.parseInt(request.getParameter("ritmo"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		int idEstiloNatacao = Integer.parseInt(request.getParameter("estilo-natacao"));
		double distancia = Double.parseDouble(request.getParameter("distancia"));
			
		try {
		  dataInicio.setTime(format.parse(request.getParameter("dt-inicio")));
		  dataFim.setTime(format.parse(request.getParameter("dt-fim")));
		  
		  switch (atividade) {
		  case "caminhada":
			  dao.cadastrar(new Caminhada(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					  distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "corrida":
			  dao.cadastrar(new Corrida(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "ciclismo":
			  dao.cadastrar(new Ciclismo(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					 distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "natacao":
			  dao.cadastrar(new Natacao(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					new EstiloNatacao(idEstiloNatacao), new RitmoAtividade(idRitmo)));
			  break;
		  }
		  
		  request.setAttribute("msgSucesso", "Atividade registrada!");
		  
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao processar as datas.");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao registrar atividade");
		}
		
		request.getRequestDispatcher("atividades.jsp").forward(request, response);
	}

}
