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
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.PesoDAO;

/**
 * Servlet implementation class PesoServlet
 */
@WebServlet("/peso")
public class PesoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PesoDAO pesoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesoServlet() {
        super();
        pesoDAO = DAOFactory.getPesoDAO();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, Calendar data) 
		throws ServletException, IOException {
		
		List<Peso> listaPeso = null;
		HttpSession session = request.getSession();
		
		if (data == null) {
		  listaPeso = pesoDAO.listarUsuario(usuario);
		  session.setAttribute("dtExibidaPeso", data);
		  
		} else {
		  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		  session.setAttribute("dtExibidaPeso", f.format(data.getTime()));
		  listaPeso = pesoDAO.listarUsuario(usuario, data);
		}
		
		// Lista de Pesos
		request.setAttribute("listaPeso", listaPeso);
		
		request.getRequestDispatcher("peso.jsp").forward(request, response);
		
	}

}
