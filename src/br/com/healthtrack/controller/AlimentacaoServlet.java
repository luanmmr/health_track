package br.com.healthtrack.controller;

import java.io.IOException;
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

import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.IngestaoDAO;

/**
 * Servlet implementation class AlimentacaoServlet
 */
@WebServlet("/alimentacao")
public class AlimentacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IngestaoDAO ingestaoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimentacaoServlet() {
        super();
        ingestaoDAO = DAOFactory.getIngestaoDAO();
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
		
		String action = request.getParameter("action");
		
		switch (action) {
		case "listar" :
		  listar(request, response, usuario, data);
		  break;
		
		case "alterar-data" :
		  try {
		    data.setTime(f.parse(request.getParameter("data")));
		    listar(request, response, usuario, data);
		    
		  } catch (ParseException e) {
			  e.printStackTrace();
			  request.setAttribute("msgErro", "Erro ao processar a data.");
			  request.getRequestDispatcher("alimentacao.jsp").forward(request, response);
		  }
		  break;
		  
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, Calendar data) 
		throws ServletException, IOException {
		
		List<Ingestao> lista = ingestaoDAO.listarDia(usuario, data);
		
		// Data das atividades
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    request.getSession().setAttribute("dtExibidaAli", f.format(data.getTime()));
        request.setAttribute("listaAlimentacao", lista);
        
        request.getRequestDispatcher("alimentacao.jsp").forward(request, response);
	}

}
