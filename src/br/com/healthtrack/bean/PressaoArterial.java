package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe PressaoArterial irá instanciar objetos que serão utilizados para registrar
 * a pressão arterial do usuário. Será guardado a pressão sistólica e diastólica, bem como
 * a data/hora do registro da aferição. 
 * @author Luan Ribeiro
 * @version 1.0
 */
public class PressaoArterial {
	
	private int codigo;
	private Usuario usuario;
	private Calendar dataRegistro;
	private int sistolica;
	private int diastolica;
	
	/**
	 * Construtor no qual é definido a pressão sistólica, diastólica e data/hora do registro
	 * da aferição
	 * @param dataRegistro Data/Hora do registro da aferição
	 * @param sistolica Pressão Sistólica
	 * @param diastolica Pressão Diastólica
	 */
	public PressaoArterial(int codigo, Usuario usuario, Calendar dataRegistro, int sistolica, int diastolica) {
		setCodigo(codigo);
		setUsuario(usuario);
		setDataRegistro(dataRegistro);
		setSistolica(sistolica);
		setDiastolica(diastolica);
	}
	
	public PressaoArterial(int codigo) {
		super();
		this.codigo = codigo;
	}


	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public PressaoArterial() {
		
	}
	
	/**
	 * Define a data de registro da aferição da pressão arterial
	 * @param data Data/Hora da aferição
	 */
	public void setDataRegistro(Calendar data) {
		dataRegistro = data;
	}
	
	/**
	 * Obtém a data do registro da aferição
	 * @return A data da aferição
	 */
	public Calendar getDataRegistro() {
		return dataRegistro;
	}
	
	/**
	 * Define a pressão sistólica
	 * @param sistolica Pressão Sistólica
	 */
	public void setSistolica(int sistolica) {
		this.sistolica = sistolica;
	}
	
	/**
	 * Obtém a pressão sistólica
	 * @return A pressão sistólica
	 */
	public int getSistolica() {
		return sistolica;
	}
	
	/**
	 * Define a pressão diastólica
	 * @param diastolica Pressão Diastólica
	 */
	public void setDiastolica(int diastolica) {
		this.diastolica = diastolica;
	}
	
	/**
	 * Obtém a pressão diastólica
	 * @return A pressão diastólica
	 */
	public int getDiastolica() {
		return diastolica;
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
