package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Natacao representa um tipo de atividade mais específica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * distância percorrida com a corrida.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Natacao extends Atividade {
	
	private EstiloNatacao estilo;
	
	/**
	 * Construtor no qual é definido o código da atividade, a data de início e fim da 
	 * atividade, usuário da atividade e a distância percorrida. Posteriormente é calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param código da atividade
	 * @param dataInicio Data/Hora do Início da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usuário
	 * @param estilo Estilo da Natação
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Natacao(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
				   EstiloNatacao estilo, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setEstilo(estilo);
		calcularKcalPerdida();
	}
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Natacao() {	
	}
	
	/**
	 * Obtém o estilo da natação
	 * @return O estilo da natação
	 */
	public EstiloNatacao getEstilo() {
		return estilo;
	}
	
	/**
	 * Define o estilo da natação
	 * @param estilo Estilo
	 */
	public void setEstilo(EstiloNatacao estilo) {
		this.estilo = estilo;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida se baseando no tempo, equivalente 
	 * metabólico(MET) da atividade e peso do usuário
	 */
	@Override
	public void calcularKcalPerdida() {	
		if (getEstilo().getCodigo() == 1) {
			setKcalPerdida(kcalPerdida(11, getUsuario().getPeso()));
			
		}else if(getEstilo().getCodigo() == 2) {
			setKcalPerdida(kcalPerdida(10, getUsuario().getPeso()));
			
		}else if(getEstilo().getCodigo() == 3) {
			setKcalPerdida(kcalPerdida(7, getUsuario().getPeso()));
		}
	}
	
	/**
	 * String formatada com todos os dados da Natação
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"estilo\": \"%s\" \n\n}", super.toString(), getEstilo());
	}

}
