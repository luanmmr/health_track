package br.com.healthtrack.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.exception.DBException;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/user")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
		
		case "atualizar":
			atualizar(request, response);
			break;

		default:
			break;
		}
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (dao.buscarUsuario(request.getParameter("email")) == null) {
			try {
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			  Calendar data = Calendar.getInstance();
			  data.setTime(format.parse(request.getParameter("data")));	
			  dao.cadastrar(new Usuario(0, request.getParameter("nome"),
					  request.getParameter("sobrenome"), request.getParameter("email"),
					  request.getParameter("senha"), data, 
					  Double.parseDouble(request.getParameter("altura")), 
					  Double.parseDouble(request.getParameter("peso")), 
					  Integer.parseInt(request.getParameter("sistolica")), 
					  Integer.parseInt(request.getParameter("diastolica")),
					  Integer.parseInt(request.getParameter("meta-gasto-kcal"))));
			  
			  request.setAttribute("msgSucesso", "Usuário criado!");
		    
			} catch (DBException e) {
			    e.printStackTrace();
				request.setAttribute("msgErro", "Erro ao cadastrar");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msgErro", "Dados inválidos. Usuário não Criado");
			}
			
		} else {
		    request.setAttribute("msgErro", "Esse email já existe!");
		}
		
		request.getRequestDispatcher("login.jsp").forward(request, response);	
	}
	
	private void atualizar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		if (!usuario.getEmail().equals(email)) {
			if (dao.buscarUsuario(email) != null) {
				request.setAttribute("msgErro", "Esse email já existe!");
				request.getRequestDispatcher("profile.jsp").forward(request, response);
				return;
			} 
		}
		
		try {
		  usuario.setNome(request.getParameter("nome"));
		  usuario.setSobrenome(request.getParameter("sobrenome"));
		  usuario.setEmail(request.getParameter("email"));
		  usuario.setSenha(request.getParameter("senha"));
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		  Calendar data = Calendar.getInstance();
		  data.setTime(format.parse(request.getParameter("data")));
		  usuario.setDataNascimento(data);
		  usuario.setMetaGastoCalorico(Integer.parseInt(request.getParameter("meta-gasto-kcal")));
		  
		  dao.atualizar(usuario);
		  
		  request.setAttribute("msgSucesso", "Perfil atualizado!");
		  session.setAttribute("user", usuario);
		  session.setAttribute("pwd", request.getParameter("senha"));
		
		} catch (ParseException e) {
		    e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao atualizar");
		    
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao atualizar");
		}
		
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
