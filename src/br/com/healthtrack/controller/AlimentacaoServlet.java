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

import br.com.healthtrack.bean.Alimento;
import br.com.healthtrack.bean.Ingestao;
import br.com.healthtrack.bean.PeriodoRefeicao;
import br.com.healthtrack.bean.Usuario;
import br.com.healthtrack.dao.AlimentoDAO;
import br.com.healthtrack.dao.DAOFactory;
import br.com.healthtrack.dao.IngestaoDAO;
import br.com.healthtrack.dao.PeriodoRefeicaoDAO;
import br.com.healthtrack.exception.DBException;

/**
 * Servlet implementation class AlimentacaoServlet
 */
@WebServlet("/alimentacao")
public class AlimentacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IngestaoDAO ingestaoDAO;
	private AlimentoDAO alimentoDAO;
	private PeriodoRefeicaoDAO periodoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimentacaoServlet() {
        super();
        ingestaoDAO = DAOFactory.getIngestaoDAO();
        alimentoDAO = DAOFactory.getAlimentoDAO();
        periodoDAO = DAOFactory.getPeriodoRefeicaoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		session.setAttribute("alimentos", alimentoDAO.listar());
		session.setAttribute("periodos", periodoDAO.listar());
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
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		
		  switch(action) {
		  case "cadastrar" :
		    cadastrar(request, response, usuario);
		    break;
		  
		  case "excluir" :
			excluir(request, response, usuario);
		  }

	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, Calendar data) 
		throws ServletException, IOException {
		
		List<Ingestao> lista = ingestaoDAO.listarDia(usuario, data);
		
		// Total KCAL
		double totalCaloriasAli = 0;
		for (Ingestao igt : lista)
			totalCaloriasAli += igt.getTotalKcal();
		request.setAttribute("totalCaloriasAli", totalCaloriasAli);
		
		// Data das atividades
	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    request.getSession().setAttribute("dtExibidaAli", f.format(data.getTime()));
        request.setAttribute("listaAlimentacao", lista);
        
        request.getRequestDispatcher("alimentacao.jsp").forward(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response, Usuario usuario) 
		throws ServletException, IOException {
		
		Calendar data = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Alimento alimento = new Alimento(Integer.parseInt(request.getParameter("alimento")));
		PeriodoRefeicao periodo = new PeriodoRefeicao(Integer.parseInt(request.getParameter("periodo")));
		int quantidade = Integer.parseInt(request.getParameter("quantidade"));
		
		try {
		  data.setTime(f.parse(request.getParameter("data")));
		  ingestaoDAO.cadastrar(new Ingestao(0, usuario, alimento, periodo, data, quantidade));
		  
		  request.setAttribute("msgSucesso", "Alimentação cadastrada!");
		  
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao processar a data.");
			
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", "Erro ao cadastrar alimentação.");
		}
		
		listar(request, response, usuario, data);	
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response, Usuario usuario) 
		throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		ingestaoDAO.remover(id);
		request.setAttribute("msgSucesso", "Alimentação excluída.");
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
		Calendar data = Calendar.getInstance();
		
		try {
		  data.setTime(f.parse((String) session.getAttribute("dtExibidaAli")));

		} catch (ParseException e) {
		    e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao processar a data.");

		}
		
		listar(request, response, usuario, data);
	}

}
