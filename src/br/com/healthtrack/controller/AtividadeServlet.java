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
		String action = request.getParameter("action");
		
		switch(action) {
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
			  request.getRequestDispatcher("atividades.jsp").forward(request, response);
		  }
		  break;
		
		case "editar" :
		  editar(request, response);
		  break;
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
			
		case "update" :
			update(request, response);
			break;
		}
	}
	
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario usuario, 
			Calendar data) throws ServletException, IOException {
      
	  List<Atividade> lista = dao.listaAtividadesDia(usuario, data);
	  double gastoCaloricoTotal = 0;
	  String porcentagemMeta = "";
	  
	  // Data das atividades
	  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	  request.getSession().setAttribute("dtExibidaAtv", f.format(data.getTime()));
      request.setAttribute("listaAtividades", lista);
      
      // Total Calorias perdidas
      for (Atividade atv : lista) {
    	  gastoCaloricoTotal += atv.getKcalPerdida();
      }
      request.setAttribute("gastoCaloricoTotal", String.format("%.2f", gastoCaloricoTotal));
      
      // Meta Gasto Calorico
      porcentagemMeta = String.format("%.0f", gastoCaloricoTotal / usuario.getMetaGastoCalorico() * 100);
      request.setAttribute("porcentagemMeta", porcentagemMeta);
      
	  request.getRequestDispatcher("atividades.jsp").forward(request, response);

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
		double distancia;
		int idEstiloNatacao;

		try {
		  dataInicio.setTime(f.parse(request.getParameter("dt-inicio")));
		  dataFim.setTime(f.parse(request.getParameter("dt-fim")));
		  
		  switch (atividade) {
		  case "caminhada":
			  distancia = Double.parseDouble(request.getParameter("distancia"));
			  dao.cadastrar(new Caminhada(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					  distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "corrida":
			  distancia = Double.parseDouble(request.getParameter("distancia"));
			  dao.cadastrar(new Corrida(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "ciclismo":
			  distancia = Double.parseDouble(request.getParameter("distancia"));
			  dao.cadastrar(new Ciclismo(0, dataInicio, dataFim, new Usuario(usuario.getCodigo()), 
					  					 distancia, new RitmoAtividade(idRitmo)));
			  break;
		  
		  case "natacao":
			  idEstiloNatacao = Integer.parseInt(request.getParameter("estilo-natacao"));
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
		
		listar(request, response, usuario, dataInicio);	
	}
	
	
	private void editar(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		int idAtv = Integer.parseInt(request.getParameter("id"));
		String atv = request.getParameter("atividade");
		
		switch(atv) {
		case "caminhada" :
		  request.setAttribute("editarAtividade", (Caminhada) dao.buscar(idAtv, atv, usuario));
		  break;
		
		case "corrida" :
		  request.setAttribute("editarAtividade", (Corrida) dao.buscar(idAtv, atv, usuario));
		  break;
		
		case "ciclismo" :
		  request.setAttribute("editarAtividade", (Ciclismo) dao.buscar(idAtv, atv, usuario));
		  break;
		
		case "natacao" :
		  request.setAttribute("editarAtividade", (Natacao) dao.buscar(idAtv, atv, usuario));
		  break;
		  
		}
		
		request.getRequestDispatcher("editar.jsp").forward(request, response);
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		Usuario usuario = null;
		Calendar data = null;
		int idAtv = Integer.parseInt(request.getParameter("id"));
		int idRitmo = Integer.parseInt(request.getParameter("ritmo"));
		String atv = request.getParameter("atividade");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		
		
		try {
		  dataInicio.setTime(f.parse(request.getParameter("dt-inicio")));
		  dataFim.setTime(f.parse(request.getParameter("dt-fim")));
		  
		  switch(atv) {	
		  case "caminhada" :
		    dao.update(new Caminhada(idAtv, dataInicio, dataFim,
		    		                 Double.parseDouble(request.getParameter("distancia")), 
		    		                 new RitmoAtividade(idRitmo)));
		    break;
		  
		  case "ciclismo" :
		    dao.update(new Ciclismo(idAtv, dataInicio, dataFim,
		    		                 Double.parseDouble(request.getParameter("distancia")), 
		    		                 new RitmoAtividade(idRitmo)));
		    break;
		  
		  case "corrida" :
		    dao.update(new Corrida(idAtv, dataInicio, dataFim,
		    		                 Double.parseDouble(request.getParameter("distancia")), 
		    		                 new RitmoAtividade(idRitmo)));
		    break;
		  
		  case "natacao" :
			    dao.update(new Natacao(idAtv, dataInicio, dataFim, null,
			    		               new EstiloNatacao(Integer.parseInt(request.getParameter("estilo"))), 
			    		               new RitmoAtividade(idRitmo)));
			    break;
		  }
		  
		  request.setAttribute("msgSucesso", "Atividade atualizada!");
		  HttpSession session = request.getSession();
		  usuario = (Usuario) session.getAttribute("user");
				
		} catch(ParseException e) {
		    e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao processar a data.");
		    
		} catch(DBException e) {
			e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao atualizar atividade.");
		}
		
		listar(request, response, usuario, dataInicio);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		int codigoAtv =  Integer.parseInt(request.getParameter("id"));
		dao.excluir(codigoAtv);
		
		request.setAttribute("msgSucesso", "Atividade excluída");
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("user");
		Calendar data = Calendar.getInstance();
		
		try {
		  data.setTime(f.parse((String) session.getAttribute("dtExibidaAtv")));

		} catch (ParseException e) {
		    e.printStackTrace();
		    request.setAttribute("msgErro", "Erro ao processar a data.");

		}
		
		listar(request, response, usuario, data);
	}
	
}
