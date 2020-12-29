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
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		Calendar data = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		if (request.getParameter("data-alterada") == null) {
		  session.setAttribute("dtExibidaAtv", f.format(data.getTime()));
	      request.setAttribute("listaAtividades", dao.listaAtividadesDia(usuario.getCodigo(), data));
		  request.getRequestDispatcher("atividades.jsp").forward(request, response);
		
		} else {
		  try {
			data.setTime(f.parse(request.getParameter("data")));
			session.setAttribute("dtExibidaAtv", f.format(data.getTime()));
			request.setAttribute("listaAtividades", dao.listaAtividadesDia(usuario.getCodigo(), data));
			
		  } catch (ParseException e) {
			  e.printStackTrace();
			  request.setAttribute("msgErro", "Erro ao processar a data.");
			  
		  } finally {
			  request.getRequestDispatcher("atividades.jsp").forward(request, response);
			  
		  }
		}
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
		
		case "excluir":
			excluir(request, response);
			break;

		default:
			break;
		}
	}
	
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		int codigoAtv =  Integer.parseInt(request.getParameter("id"));
		dao.excluir(codigoAtv);
		
		request.setAttribute("msgSucesso", "Atividade excluída");
		
		posExclusao(request, response);
	}
	
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		String atividade = request.getParameter("atividade");
		int idRitmo = Integer.parseInt(request.getParameter("ritmo"));
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		int idEstiloNatacao = Integer.parseInt(request.getParameter("estilo-natacao"));
		double distancia = Double.parseDouble(request.getParameter("distancia"));

		try {
		  dataInicio.setTime(f.parse(request.getParameter("dt-inicio")));
		  dataFim.setTime(f.parse(request.getParameter("dt-fim")));
		  
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
			request.setAttribute("msgErro", "Erro ao processar a data.");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao registrar atividade");
		}

		posCadastro(request, response, dataInicio, session, usuario);
		
	}
	
	
	private void posCadastro(HttpServletRequest request, HttpServletResponse response, 
		Calendar data, HttpSession session, Usuario usuario) throws ServletException, IOException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

		session.setAttribute("dtExibidaAtv", f.format(data.getTime()));
		request.setAttribute("listaAtividades", dao.listaAtividadesDia(usuario.getCodigo(), data));
		request.getRequestDispatcher("atividades.jsp").forward(request, response);
		
	}
	
	private void posExclusao(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		
		Calendar data = Calendar.getInstance();
		
		try {
		  data.setTime(f.parse((String) session.getAttribute("dtExibidaAtv")));
		  request.setAttribute("listaAtividades", dao.listaAtividadesDia(usuario.getCodigo(), data));
		  
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		
		request.getRequestDispatcher("atividades.jsp").forward(request, response);
	}

}
