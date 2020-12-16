package br.com.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A classe Ingestacao representa a ingest�o de cada alimento feita pelo usu�rio. � registrado a data,
 * a quantidade de cada alimento e o per�odo em que foi feito a ingest�o por exemplo, lanche da 
 * tarde, almo�o, caf� da manh� etc... � poss�vel calcular para cada ingest�o, a quantidade de
 * calorias consumida.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Ingestao implements Serializable {
	
	private int codigo;
	private Usuario usuario;
	private Alimento alimento;
	private PeriodoRefeicao periodoRefeicao;
	private Calendar dataIngestao;
	private int quantidade;
	private double totalKcal;
	
	/**
	 * Construtor no qual � definido o usu�rio que realizou a ingest�o, o alimento ingerido,
	 * o per�odo(Caf� da manh�, lanche da tarde etc...), a data/hora e a quantidade.
	 * que a realizou.
	 * @param usuario Usu�rio que fez a ingest�o
	 * @param alimento Alimento ingerido
	 * @param periodo Per�odo(Caf� da manh�, lanche da tarde etc...)
	 * @param dataIngestao Data/hora da ingest�o
	 * @param quantidade Quantidade
	 */
	public Ingestao(int codigo, Usuario usuario, Alimento alimento, PeriodoRefeicao periodo, 
					Calendar dataIngestao, int quantidade) {
        
		setCodigo(codigo);
		setUsr(usuario);
		setAlimento(alimento);
		setPeriodoRefeicao(periodo);
		setDataIngestao(dataIngestao);
		setQuantidade(quantidade);
		calcularTotalKcal();
	}
	
	/**
	 * Construtor com bloco de instru��es vazio e sem par�metros
	 */
	public Ingestao() {
		
	}
	
	/**
	 * Obt�m o codigo da ingest�o
	 * @return Um int que representa o codigo da ingest�o
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Obt�m o usu�rio que fez a ingest�o
	 * @return O usu�rio
	 */
	public Usuario getUsr() {
		return usuario;
	}
	
	/**
	 * Obt�m o alimento que foi ingerido
	 * @return O alimento
	 */
	public Alimento getAlimento() {
		return alimento;
	}
	
	/**
	 * Obt�m o per�odo da refei��o
	 * @return Uma string que representa o per�odo
	 */
	public PeriodoRefeicao getPeriodoRefeicao() {
		return periodoRefeicao;
	}
	
	/**
	 * Obt�m a data/hora da ingest�o
	 * @return A data/hora
	 */
	public Calendar getDataIngestao() {
		return dataIngestao;
	}
	
	/**
	 * Obt�m a quantidade do alimento que foi ingerido
	 * @return Um int que representa a quantidade do alimento
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Obt�m o total de kcal que foi consumido com a ingest�o
	 * @return O total de kcal
	 */
	public double getTotalKcal() {
		return totalKcal;
	}
	
	/**
	 * Define o c�digo da ingest�o
	 * @param codigo C�digo da Ingest�o
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Define o usu�rio que fez a ingest�o
	 * @param usr Usu�rio
	 */
	public void setUsr(Usuario usr) {
		this.usuario = usr;
	}
	
	/**
	 * Define o alimento que foi ingerido
	 * @param alimento Alimento Ingerido
	 */
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	
	/**
	 * Define o per�odo da refei��o
	 * @param periodoRefeicao Per�odo(Caf� da manh�, almo�o, janta etc...)
	 */
	public void setPeriodoRefeicao(PeriodoRefeicao periodoRefeicao) {
		this.periodoRefeicao = periodoRefeicao;
	}
	
	/**
	 * Define a data/hora da ingest�o
	 * @param dataIngestao Data/Hora da ingest�o
	 */
	public void setDataIngestao(Calendar dataIngestao) {
		this.dataIngestao = dataIngestao;
	}
	
	/**
	 * Define a quantidade do alimento que foi ingerido
	 * @param quantidade Quantidade
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	/**
	 * Calcula a quantidade de calorias que foi consumida ao ingerir o alimento. As calorias
	 * de cada alimento s�o multiplicadas pela quantidade de vezes que foi ingerido o mesmo 
	 * alimento, definido no m�todo construtor
	 */
	public void calcularTotalKcal() {
		this.totalKcal = alimento.getKcal() * quantidade;
	}
	
	/**
	 * String formatada com todos os dados da ingest�o
	 * @return Retorna uma representa��o em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("----------------------\n"
				+ "C�digo: %d\n"
				+ "Usu�rio: \n|%s\n"
				+ "Alimento: \n|%s\n"
				+ "Per�odo: %s\n"
				+ "Data/Hora: %s\n"
				+ "Quantidade: %d\n"
				+ "Total Kcal: %.1f\n"
				+ "----------------------\n", getCodigo(), getUsr(), getAlimento(), 
				getPeriodoRefeicao(), getDataIngestao().getTime(), getQuantidade(), 
				getTotalKcal());
	}
}
