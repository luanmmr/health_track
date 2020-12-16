package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Caminhada representa um tipo de atividade mais específica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * distância percorrida na caminhada.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Caminhada extends Atividade implements DistanciaPercorrida {
	
	private double distanciaPercorrida;
	
	/**
	 * Construtor no qual é definido o código da atividade, a data de início e fim da 
	 * atividade, usuário da atividade e a distância percorrida. Posteriormente é calculado 
	 * a quantidade de kcal que foi perdida.
	 * @param código da atividade
	 * @param dataInicio Data/Hora do Início da atividade
	 * @param dataFim Data/Hora do Fim da atividade
	 * @param usr Usuário
	 * @param distancia Distância Percorrida
	 * @param ritmo Ritmo da Atividade(Leve, Moderado, Intenso)
	 */
	public Caminhada(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
					 double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setDistancia(distancia);
		calcularKcalPerdida();
	}
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Caminhada() {
		
	}
	
	/**
	 * Define a distância percorrida com a caminhada
	 * @param distancia Distância Percorrida
	 */
	@Override
	public void setDistancia(double distancia) {
		this.distanciaPercorrida = distancia;
	}
	
	/**
	 * Obtém a distância percorrida com a caminhada
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
		/*if (getRitmo() == "Leve") {
			setKcalPerdida(kcalPerdidaMinuto(3.3, getUsuario().getPeso().getPeso()));
		}else if(getRitmo() == "Moderado") {
			setKcalPerdida(kcalPerdidaMinuto(5, getUsuario().getPeso().getPeso()));
		}else {
			setKcalPerdida(kcalPerdidaMinuto(8, getUsuario().getPeso().getPeso()));
		}*/
	}
	
	private double kcalPerdidaMinuto(double mets, double peso) {
		/*
		 * Fonte
		 * http://www.cdof.com.br/MET_compendium.pdf
		 * 
		 * Caminhada Leve
		 * Até 4,8 km/h = MET 3.3
         * Caminhada Moderada
         * De 4,8 km/h até 6,4 km/h = MET 5
         * Caminhada Intensa
         * De 6,4 km/h até 8 km/h = MET 8
         * 
		 * É utilizado a seguinte fórmula
		 * (MET * 3,5) * Peso.Usuario * 5 (5 Kcal para cada litro de oxigênio consumido)
		 * Obtenho assim o gasto calórico por minuto do usuário. Depois multiplico pelo
		 * tempo em minutos que durou a atividade física, obtendo assim o total de kcal
		 * perdida.
		 * 
		 */
		
		// Divido por 1000 para obter em litros, em detrimento de ml
		return (mets * 3.5) * peso / 1000 * 5;
	}
	
	/**
	 * String formatada com todos os dados da Caminhada
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"distanciaPercorrida\": %.1f \n\n}", super.toString(), distanciaPercorrida);
	}
}
