package br.com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLException;
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

/**
 * Servlet implementation class AtividadeServlet
 */
@WebServlet("/atividades")
public class AtividadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AtividadeDAO dao;
	int idUsuario;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		idUsuario = new OracleUsuarioDAO().recuperarId(session.getAttribute("user").toString());
		
		List<Atividade> lista = dao.listar(idUsuario);
		
		request.setAttribute("lista", lista);
		
		request.getRequestDispatcher("atividades.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int atividade = Integer.parseInt(request.getParameter("atividade"));	
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			double distancia = Double.parseDouble(request.getParameter("distancia"));
			int ritmo = Integer.parseInt(request.getParameter("ritmo"));
			
			switch (atividade) {
			case 1:
				Caminhada caminhada = new Caminhada(0, data, data, new Usuario(idUsuario), 
													distancia, new RitmoAtividade(ritmo));
				dao.cadastrar(caminhada);
				break;
			
			case 2:
				Corrida corrida = new Corrida(0, data, data, new Usuario(idUsuario), distancia, 
											  new RitmoAtividade(ritmo));
				dao.cadastrar(corrida);
				break;
			
			case 3:
				Ciclismo ciclismo = new Ciclismo(0, data, data, new Usuario(idUsuario), distancia, 
						  						 new RitmoAtividade(ritmo));
				dao.cadastrar(ciclismo);
				break;
			
			case 4:
				Natacao natacao = new Natacao(0, data, data, new Usuario(idUsuario), 
											  new EstiloNatacao(1), new RitmoAtividade(ritmo));
				dao.cadastrar(natacao);
				break;
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("atividades.jsp").forward(request, response);
		
	}

}
