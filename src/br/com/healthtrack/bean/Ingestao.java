package br.com.healthtrack.bean;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A classe Ingestacao representa a ingestão de cada alimento feita pelo usuário. É registrado a data,
 * a quantidade de cada alimento e o período em que foi feito a ingestão por exemplo, lanche da 
 * tarde, almoço, café da manhã etc... É possível calcular para cada ingestão, a quantidade de
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
	 * Construtor no qual é definido o usuário que realizou a ingestão, o alimento ingerido,
	 * o período(Café da manhã, lanche da tarde etc...), a data/hora e a quantidade.
	 * que a realizou.
	 * @param usuario Usuário que fez a ingestão
	 * @param alimento Alimento ingerido
	 * @param periodo Período(Café da manhã, lanche da tarde etc...)
	 * @param dataIngestao Data/hora da ingestão
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
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Ingestao() {
		
	}
	
	/**
	 * Obtém o codigo da ingestão
	 * @return Um int que representa o codigo da ingestão
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * Obtém o usuário que fez a ingestão
	 * @return O usuário
	 */
	public Usuario getUsr() {
		return usuario;
	}
	
	/**
	 * Obtém o alimento que foi ingerido
	 * @return O alimento
	 */
	public Alimento getAlimento() {
		return alimento;
	}
	
	/**
	 * Obtém o período da refeição
	 * @return Uma string que representa o período
	 */
	public PeriodoRefeicao getPeriodoRefeicao() {
		return periodoRefeicao;
	}
	
	/**
	 * Obtém a data/hora da ingestão
	 * @return A data/hora
	 */
	public Calendar getDataIngestao() {
		return dataIngestao;
	}
	
	/**
	 * Obtém a quantidade do alimento que foi ingerido
	 * @return Um int que representa a quantidade do alimento
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Obtém o total de kcal que foi consumido com a ingestão
	 * @return O total de kcal
	 */
	public double getTotalKcal() {
		return totalKcal;
	}
	
	/**
	 * Define o código da ingestão
	 * @param codigo Código da Ingestão
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Define o usuário que fez a ingestão
	 * @param usr Usuário
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
	 * Define o período da refeição
	 * @param periodoRefeicao Período(Café da manhã, almoço, janta etc...)
	 */
	public void setPeriodoRefeicao(PeriodoRefeicao periodoRefeicao) {
		this.periodoRefeicao = periodoRefeicao;
	}
	
	/**
	 * Define a data/hora da ingestão
	 * @param dataIngestao Data/Hora da ingestão
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
	 * de cada alimento são multiplicadas pela quantidade de vezes que foi ingerido o mesmo 
	 * alimento, definido no método construtor
	 */
	public void calcularTotalKcal() {
		this.totalKcal = alimento.getKcal() * quantidade;
	}
	
	/**
	 * String formatada com todos os dados da ingestão
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("----------------------\n"
				+ "Código: %d\n"
				+ "Usuário: \n|%s\n"
				+ "Alimento: \n|%s\n"
				+ "Período: %s\n"
				+ "Data/Hora: %s\n"
				+ "Quantidade: %d\n"
				+ "Total Kcal: %.1f\n"
				+ "----------------------\n", getCodigo(), getUsr(), getAlimento(), 
				getPeriodoRefeicao(), getDataIngestao().getTime(), getQuantidade(), 
				getTotalKcal());
	}
}
