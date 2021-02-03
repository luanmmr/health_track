package br.com.healthtrack.bean;

import java.util.Calendar;
import java.util.Dictionary;

import br.com.healthtrack.dao.impl.OraclePesoDAO;
import br.com.healthtrack.dao.impl.OraclePressaoArterialDAO;
import br.com.healthtrack.util.CriptografiaSenha;

/**
 * Esta classe Usuario representa uma pessoa do mundo real que irá desempenhar algumas
 * funções como realizar atividades e ingerir alimentos.
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
	 * @param pressaoArterial Pressão Arterial
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
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Usuario() {
		
	}
	
	/**
	 * Obtém o código do usuário
	 * @return Um int que representa o código do usuário
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Define um código para o usuário
	 * @param codigo Código
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtém o nome do usuário
	 * @return Uma string que representa o nome do usuário
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Define um nome para o usuário
	 * @param nome Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Obtém o sobrenome do usuário
	 * @return Uma string que representa o sobrenome do usuário
	 */
	public String getSobrenome() {
		return sobrenome;
	}
	
	/**
	 * Define um sobrenome para o usuário
	 * @param sobrenome Sobrenome
	 */
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	/**
	 * Obtém o email do usuário
	 * @return Uma string contendo um email do cliente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Define o email do usuário
	 * @param email Email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Obtém a senha do usuário
	 * @return Uma string que representa a senha do usuário
	 */
	public String getSenha() {
		return senha;
	}
	
	/**
	 * Define uma senha para o usuário
	 * @param senha Senha
	 */
	public void setSenha(String senha) {
		this.senha = CriptografiaSenha.criptografar(senha);
	}
	
	/**
	 * Obtém a data de nascimento do usuário
	 * @return A data de nascimento
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	/**
	 * Define a data de nascimento do usuário
	 * @param dataNascimento Data de Nascimento
	 */
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * Obtém a altura do usuário
	 * @return A altura
	 */
	public double getAltura() {
		return altura;
	}
	
	/**
	 * Define a altura do usuário
	 * @param altura Altura
	 */
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	/**
	 * Define o peso do usuário e calcula o IMC dele toda vez em que é feito 
	 * qualquer alteração no peso do usuário.
	 * @param peso Peso e Data da Pesagem do usuário
	 */
	public void setPeso(double peso) {
		this.peso = peso;
		setImc();	
	}
	
	/**
	 * Define a Pressão Arterial do usuário
	 * @param pressaoArterial Pressão Arterial
	 */
	public void setPressaoArterial(int sistolica, int diastolica) {
		this.sistolica = sistolica;
		this.diastolica = diastolica;
	}
	
	/**
	 * Obtém o peso e data/hora da pesagem
	 * @return Peso e Data/hora da Pesagem
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * Obtém o IMC mais recente do usuário
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
	 * Define a o IMC do usuário
	 * Calcula o IMC do usuário toda vez em que é feito qualquer alteração no peso do usuário
	 */
	private void setImc() {
		this.imc = getPeso() / Math.pow(getAltura(), 2.0);
	}
	
}
