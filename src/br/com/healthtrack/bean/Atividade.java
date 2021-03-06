package br.com.healthtrack.bean;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;

/**
 * A classe abstrata Atividade representa as atividades do mundo real. Essa classe servir� 
 * com base para cria��o de classes mais espec�ficas e que herdaram seus atributos e
 * comportamentos que est�o presentes em qualquer atividade.
 * @author Luan Ribeiro
 * @version 1.0
 */
public abstract class Atividade implements Serializable {
	
	private int codigo;
	private String titulo;
	private Calendar dataInicio;
	private Calendar dataFim;
	private double kcalPerdida;
	private Usuario usuario;
	private RitmoAtividade ritmo;
	
	/**
	 * Construtor no qual � definido a data/hora de in�cio e fim da atividade, e o usu�rio 
	 * que a realizou.
	 * @param c�digo da atividade
	 * @param dataInicio Data/Hora do In�cio da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usu�rio
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Atividade(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
					 RitmoAtividade ritmo) {
		setCodigo(codigo);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setUsuario(usr);
		setRitmo(ritmo);
	}
	
	public Atividade(int codigo, Calendar dataInicio, Calendar dataFim,
			 		 RitmoAtividade ritmo) {
	     setCodigo(codigo);
		 setDataInicio(dataInicio);
		 setDataFim(dataFim);
		 setRitmo(ritmo);
    }
	
	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Atividade() {
		
	}

	/**
	 * Obt�m o c�digo da atividade
	 * @return O c�digo da atividade
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define o c�digo da atividade
	 * @param O c�digo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m a data/hora do in�cio da atividade
	 * @return A data/hora do in�cio
	 */
	public Calendar getDataInicio() {
		return dataInicio;
	}
	
	/**
	 * Define a data/hora do in�cio da atividade
	 * @param dataInicio Data/Hora Do In�cio da atividade
	 */
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	/**
	 * Obt�m a data/hora do fim da atividade
	 * @return A data/hora do fim
	 */
	public Calendar getDataFim() {
		return dataFim;
	}
	
	/**
	 * Define a data/hora do fim da atividade
	 * @param dataFim Data/Hora Do Fim da atividade
	 */
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	
	/**
	 * Obt�m o total de kcal perdida com a atividade
	 * @return O total de kcal
	 */
	public double getKcalPerdida() {
		return kcalPerdida;
	}
	
	public void setKcalPerdida(double kcalPerdida) {
		this.kcalPerdida = kcalPerdida;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida, se baseando na data/hora do in�cio
	 * e fim da atividade. Devido a fatores intr�nsecos de cada atividade, fica a encargo de
	 * cada subclasse fazer a implementa��o desse m�todo abstrato.
	 */
	public abstract void calcularKcalPerdida();
	
	/**
	 * Obt�m o usu�rio que realizou a atividade
	 * @return O usu�rio
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Define o usu�rio que realizou a atividade
	 * @param usuario Usu�rio da atividade
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obt�m o ritmo da atividade
	 * @return O ritmo
	 */
	public RitmoAtividade getRitmo() {
		return ritmo;
	}
	
	/**
	 * Define o ritmo da atividade(leve, moderado, intenso)
	 * @param ritmo Ritmo da Atividade
	 */
	public void setRitmo(RitmoAtividade ritmo) {
		this.ritmo = ritmo;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	protected double kcalPerdida(double mets, double peso) {
		  /*
		  * � utilizado a seguinte f�rmula
		  * (MET * 3,5) * Peso.Usuario * 5 (5 Kcal para cada litro de oxig�nio consumido)
		  * Obtenho assim o gasto cal�rico por minuto do usu�rio. Depois multiplico pelo
		  * tempo em minutos que durou a atividade f�sica, obtendo assim o total de kcal
		  * perdida.
		  * 
		  */
		  mets *= 3.5;
		  double litrosOxigenio = mets * peso / 1000;
		  double kcalPorMinuto = litrosOxigenio * 5;
		  LocalTime hrInicio = LocalTime.of(getDataInicio().get(Calendar.HOUR_OF_DAY),
				                            getDataInicio().get(Calendar.MINUTE), 
				                            getDataInicio().get(Calendar.SECOND));
		  
		  LocalTime hrFim = LocalTime.of(getDataFim().get(Calendar.HOUR_OF_DAY),
	              						 getDataFim().get(Calendar.MINUTE), 
	              						 getDataFim().get(Calendar.SECOND));
		  
		  double tempoAtvMinutos = (Duration.between(hrInicio, hrFim).getSeconds()) / 60;
		  
		  return Double.parseDouble(String.format("%.2f", kcalPorMinuto * tempoAtvMinutos).replace(',', '.'));
	}
	
	/**
	 * String formatada com todos os dados da Atividade
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("{\n\n"
				+ "\"codigo\": %d,\n"
				+ "\"atividade\": \"%s\",\n"
				+ "\"ritmo\": \"%s\",\n"
				+ "\"dataInicio\": \"%s\",\n"
				+ "\"dataFim\": \"%s\",\n"
				+ "\"kcalPerdida\": %.1f, \n"
				+ "\"usuario\": %s,\n", getCodigo(), getClass().getName(), getRitmo(), 
				getDataInicio().getTime(), getDataFim().getTime(), getKcalPerdida(), 
				getUsuario());
	}

}
