package br.com.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A classe abstrata Atividade representa as atividades do mundo real. Essa classe servirá 
 * com base para criação de classes mais específicas e que herdaram seus atributos e
 * comportamentos que estão presentes em qualquer atividade.
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
	 * Construtor no qual é definido a data/hora de início e fim da atividade, e o usuário 
	 * que a realizou.
	 * @param código da atividade
	 * @param dataInicio Data/Hora do Início da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usuário
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
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Atividade() {
		
	}

	/**
	 * Obtém o código da atividade
	 * @return O código da atividade
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define o código da atividade
	 * @param O código
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtém a data/hora do início da atividade
	 * @return A data/hora do início
	 */
	public Calendar getDataInicio() {
		return dataInicio;
	}
	
	/**
	 * Define a data/hora do início da atividade
	 * @param dataInicio Data/Hora Do Início da atividade
	 */
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	/**
	 * Obtém a data/hora do fim da atividade
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
	 * Obtém o total de kcal perdida com a atividade
	 * @return O total de kcal
	 */
	public double getKcalPerdida() {
		return kcalPerdida;
	}
	
	public void setKcalPerdida(double kcalPerdida) {
		this.kcalPerdida = kcalPerdida;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida, se baseando na data/hora do início
	 * e fim da atividade. Devido a fatores intrínsecos de cada atividade, fica a encargo de
	 * cada subclasse fazer a implementação desse método abstrato.
	 */
	public abstract void calcularKcalPerdida();
	
	/**
	 * Obtém o usuário que realizou a atividade
	 * @return O usuário
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	
	/**
	 * Define o usuário que realizou a atividade
	 * @param usuario Usuário da atividade
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtém o ritmo da atividade
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
	
	/**
	 * String formatada com todos os dados da Atividade
	 * @return Retorna uma representação em string do objeto
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
