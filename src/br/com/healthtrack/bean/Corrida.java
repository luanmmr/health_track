package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Corrida representa um tipo de atividade mais específica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * distância percorrida com a corrida.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Corrida extends Atividade implements DistanciaPercorrida {
	
	private double distanciaPercorrida;
	
	/**
	 * Construtor no qual é definido o código da atividade, a data de início e fim da 
	 * atividade, usuário da atividade e a distância percorrida. Posteriormente é calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param código da atividade
	 * @param dataInicio Data/Hora do Início da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usuário
	 * @param distancia Distância Percorrida em KM
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Corrida(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
				   double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setDistancia(distancia);
		calcularKcalPerdida();
	}
	
	public Corrida(int codigo, Calendar dataInicio, Calendar dataFim,
	 		 	   double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, ritmo);
		setDistancia(distancia);
	}
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Corrida() {
		
	}
	
	/**
	 * Define a distância percorrida com a corrida
	 * @param distancia Distância Percorrida
	 */
	@Override
	public void setDistancia(double distancia) {
		this.distanciaPercorrida = distancia;
	}
	
	/**
	 * Obtém a distância percorrida com a corrida
	 * @return A distância percorrida
	 */
	@Override
	public double getDistancia() {
		return distanciaPercorrida;
	}
	
	/**
	 * Calcula a quantidade de kcal perdida se baseando no tempo, equivalente 
	 * metabólico(MET) da atividade e peso do usuário
	 */
	@Override
	public void calcularKcalPerdida() {	
	  /*
	  * Fonte
	  * http://www.cdof.com.br/MET_compendium.pdf
	  * 
	  * Corrida Leve
	  * Até 10,7 km/h = MET 11
      * Corrida Moderado
      * De 10,7 km/h até 12,8 km/h = MET 13.5
      * Corrida Intenso
      * De 12,8 km/h até 16,1 km/h = MET 16
      *
	  */
	  if (getRitmo().getCodigo() == 1) {
	    setKcalPerdida(kcalPerdida(11, getUsuario().getPeso()));
	    
	  }else if(getRitmo().getCodigo() == 2) {
		setKcalPerdida(kcalPerdida(13.5, getUsuario().getPeso()));
		
	  }else {
		setKcalPerdida(kcalPerdida(16, getUsuario().getPeso()));
		
	  }
	}
	
	
	/**
	 * String formatada com todos os dados da Corrida
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"distanciaPercorrida\": %.1f \n\n}", super.toString(), distanciaPercorrida);
	}
}
