package br.com.healthtrack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.UsuarioDAO;
import br.com.healthtrack.util.CriptografiaSenha;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        dao = DAOFactory.getUsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		if (dao.autenticar(email, senha)) {
			HttpSession session = request.getSession();
			Usuario usuario = dao.buscarUsuario(email);
			usuario.setSenha(senha);
			session.setAttribute("pwd", senha);
			session.setAttribute("user", usuario);
	        response.sendRedirect("dashboard");
				
		} else {
			request.setAttribute("msgErro", "Usuário e/ou senha incorreto");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		
		}
			
	}

}
