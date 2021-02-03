package br.com.healthtrack.bean;

import java.util.Calendar;
import java.util.Dictionary;

import br.com.healthtrack.dao.impl.OraclePesoDAO;
import br.com.healthtrack.dao.impl.OraclePressaoArterialDAO;
import br.com.healthtrack.util.CriptografiaSenha;

/**
 * Esta classe Usuario representa uma pessoa do mundo real que ir� desempenhar algumas
 * fun��es como realizar atividades e ingerir alimentos.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Usuario {

	private int codigo;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private Calendar dataNascimento;
	private double altura;
	private double peso;
	private int sistolica;
	private int diastolica;
	private double imc;
	private int metaGastoCalorico;
		
	/**
	 * Construtor
	 * @param nome Nome
	 * @param sobrenome Sobrenome
	 * @param senha Senha
	 * @param dateNascimento Data de Nascimento
	 * @param altura Altura
	 * @param peso Peso
	 * @param pressaoArterial Press�o Arterial
	 */
	public Usuario(int codigo, String nome, String sobrenome, String email, String senha, 
				  Calendar dateNascimento, double altura, double peso, int sistolica, int diastolica,
				  int metaGastoCalorico) {
		setCodigo(codigo);
		setNome(nome);
		setSobrenome(sobrenome);
		setEmail(email);
		setSenha(senha);
		setDataNascimento(dateNascimento);
		setAltura(altura);
		setPeso(peso);
		setPressaoArterial(sistolica, diastolica);
		setMetaGastoCalorico(metaGastoCalorico);
	}
	
	public Usuario(int codigo) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Usuario() {
		
	}
	
	/**
	 * Obt�m o c�digo do usu�rio
	 * @return Um int que representa o c�digo do usu�rio
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define um c�digo para o usu�rio
	 * @param codigo C�digo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obt�m o nome do usu�rio
	 * @return Uma string que representa o nome do usu�rio
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Define um nome para o usu�rio
	 * @param nome Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Obt�m o sobrenome do usu�rio
	 * @return Uma string que representa o sobrenome do usu�rio
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	
	/**
	 * Define um sobrenome para o usu�rio
	 * @param sobrenome Sobrenome
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	/**
	 * Obt�m o email do usu�rio
	 * @return Uma string contendo um email do cliente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Define o email do usu�rio
	 * @param email Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obt�m a senha do usu�rio
	 * @return Uma string que representa a senha do usu�rio
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Define uma senha para o usu�rio
	 * @param senha Senha
	 */
	public void setSenha(String senha) {
		this.senha = CriptografiaSenha.criptografar(senha);
	}
	
	/**
	 * Obt�m a data de nascimento do usu�rio
	 * @return A data de nascimento
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	/**
	 * Define a data de nascimento do usu�rio
	 * @param dataNascimento Data de Nascimento
	 */
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * Obt�m a altura do usu�rio
	 * @return A altura
	 */
	public double getAltura() {
		return altura;
	}
	
	/**
	 * Define a altura do usu�rio
	 * @param altura Altura
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	/**
	 * Define o peso do usu�rio e calcula o IMC dele toda vez em que � feito 
	 * qualquer altera��o no peso do usu�rio.
	 * @param peso Peso e Data da Pesagem do usu�rio
	 */
	public void setPeso(double peso) {
		this.peso = peso;
		setImc();	
	}
	
	/**
	 * Define a Press�o Arterial do usu�rio
	 * @param pressaoArterial Press�o Arterial
	 */
	public void setPressaoArterial(int sistolica, int diastolica) {
		this.sistolica = sistolica;
		this.diastolica = diastolica;
	}
	
	/**
	 * Obt�m o peso e data/hora da pesagem
	 * @return Peso e Data/hora da Pesagem
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Obt�m o IMC mais recente do usu�rio
	 * @return O IMC
	 */
	public double getImc() {
		return imc;
	}
	
	public int getSistolica() {
		return sistolica;
	}

	public int getDiastolica() {
		return diastolica;
	}

	public String getPressaoArterial() {
		return String.format("%d/%d", getSistolica(), getDiastolica());
	}
	
	public void setMetaGastoCalorico(int valor) {
		this.metaGastoCalorico = valor;
	}
	
	public int getMetaGastoCalorico() {
		return metaGastoCalorico;
	}
	/**
	 * Define a o IMC do usu�rio
	 * Calcula o IMC do usu�rio toda vez em que � feito qualquer altera��o no peso do usu�rio
	 */
	private void setImc() {
		this.imc = getPeso() / Math.pow(getAltura(), 2.0);
	}
	
}
