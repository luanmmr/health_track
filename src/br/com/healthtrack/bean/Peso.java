package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Peso irá instanciar objetos que irão registrar o peso do usuário e a 
 * data/hora do registro da pesagem.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Peso {
	
	private int codigo;
	private Usuario usuario;
	private Calendar dataRegistro;
	private double peso;
	
	/**
	 * Construtor no qual é definido o peso e a data/hora do registro feito
	 * @param dataRegistro Data/Hora do registro da pesagem
	 * @param peso Peso
	 */
	public Peso(int codigo, Usuario usuario, Calendar dataRegistro, double peso) {
		setCodigo(codigo);
		setUsuario(usuario);
		setDataRegistro(dataRegistro);
		setPeso(peso);
	}
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Peso() {
		
	}
	
	public Peso(int codigo) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Define a data/hora do registro da pesagem
	 * @param data Data/Hora
	 */
	public void setDataRegistro(Calendar data) {
		dataRegistro = data;
	}
	
	/**
	 * Obtém a data do registro da pesagem
	 * @return A data do registro da pesagem
	 */
	public Calendar getDataRegistro() {
		return dataRegistro;
	}
	
	/**
	 * Define o peso
	 * @param peso Peso
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	/**
	 * Obtém o peso
	 * @return O peso
	 */
	public double getPeso() {
		return peso;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
