package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe PressaoArterial ir� instanciar objetos que ser�o utilizados para registrar
 * a press�o arterial do usu�rio. Ser� guardado a press�o sist�lica e diast�lica, bem como
 * a data/hora do registro da aferi��o. 
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
	 * Construtor no qual � definido a press�o sist�lica, diast�lica e data/hora do registro
	 * da aferi��o
	 * @param dataRegistro Data/Hora do registro da aferi��o
	 * @param sistolica Press�o Sist�lica
	 * @param diastolica Press�o Diast�lica
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
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public PressaoArterial() {
		
	}
	
	/**
	 * Define a data de registro da aferi��o da press�o arterial
	 * @param data Data/Hora da aferi��o
	 */
	public void setDataRegistro(Calendar data) {
		dataRegistro = data;
	}
	
	/**
	 * Obt�m a data do registro da aferi��o
	 * @return A data da aferi��o
	 */
	public Calendar getDataRegistro() {
		return dataRegistro;
	}
	
	/**
	 * Define a press�o sist�lica
	 * @param sistolica Press�o Sist�lica
	 */
	public void setSistolica(int sistolica) {
		this.sistolica = sistolica;
	}
	
	/**
	 * Obt�m a press�o sist�lica
	 * @return A press�o sist�lica
	 */
	public int getSistolica() {
		return sistolica;
	}
	
	/**
	 * Define a press�o diast�lica
	 * @param diastolica Press�o Diast�lica
	 */
	public void setDiastolica(int diastolica) {
		this.diastolica = diastolica;
	}
	
	/**
	 * Obt�m a press�o diast�lica
	 * @return A press�o diast�lica
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
