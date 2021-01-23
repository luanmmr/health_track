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
import br.com.healthtrack.bean.Peso;
import br.com.healthtrack.bean.PressaoArterial;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.PesoDAO;
import br.com.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;

/**
 * Servlet implementation class PesoServlet
 */
@WebServlet("/pressaoArterial")
public class PressaoArterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PressaoArterialDAO pressaoArterialDAO;
	private UsuarioDAO usuarioDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PressaoArterialServlet() {
        super();
        pressaoArterialDAO = DAOFactory.getPressaoArterialDAO();
        usuarioDAO = DAOFactory.getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		
		switch (action) {
		case "listar" :
		  listar(request, response, usuario, null);
		  break;
		  
		case "alterar-data" :
		  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar data = Calendar.getInstance();
		  try {
			data.setTime(f.parse(request.getParameter("data")));
			listar(request, response, usuario, data);
			
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }
		  break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		
		switch (action) {
		case "excluir":
		  excluir(request, response, usuario);
		  break;
		  
		case "cadastrar" :
		  cadastrar(request, response, usuario);
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, Calendar data) 
		throws ServletException, IOException {
		
		List<PressaoArterial> listaPressaoArterial = null;
		HttpSession session = request.getSession();
		
		if (data == null) {
		  listaPressaoArterial = pressaoArterialDAO.listarUsuario(usuario);
		  session.setAttribute("dtExibidaPressao", data);
		  
		} else {
		  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  session.setAttribute("dtExibidaPressao", f.format(data.getTime()));
		  listaPressaoArterial = pressaoArterialDAO.listarUsuario(usuario, data);
		}
		
		// Lista de Pesos
		request.setAttribute("listaPressaoArterial", listaPressaoArterial);
		
		request.getRequestDispatcher("pressao-arterial.jsp").forward(request, response);
		
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response, Usuario usuario) 
		throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		pressaoArterialDAO.remover(id);
		
		request.setAttribute("msgSucesso", "Registro de Pressão Arterial excluído");
		
		listar(request, response, usuario, null);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response, Usuario usuario) 
		throws ServletException, IOException {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		int sistolica = Integer.parseInt(request.getParameter("sistolica"));
		int diastolica = Integer.parseInt(request.getParameter("diastolica"));
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.set(dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH), dataAtual.get(Calendar.DATE), 0, 0, 0);
		Calendar data = Calendar.getInstance();
		
		try {
		  data.setTime(f.parse(request.getParameter("data")));
		  pressaoArterialDAO.cadastrar(new PressaoArterial(0, usuario, data, sistolica, diastolica));
		  request.setAttribute("msgSucesso", "Pressão Arterial registrada!");
		  
		  data.add(Calendar.SECOND , 1);
		  if (!data.before(dataAtual)) {
			try {
			  usuario.setPressaoArterial(sistolica, diastolica);
			  usuarioDAO.atualizar(usuario);
			  
			} catch (DBException e) {
				e.printStackTrace();
				request.setAttribute("msgErro", "Não foi possível atualizar a sua pressão arterial.");
			}
		    
		  }
		  
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao processar a data.");
		}
		
		listar(request, response, usuario, data);
	}

}
