package br.com.healthtrack.bean;

import java.util.Calendar;

/**
 * A classe Ciclismo representa um tipo de atividade mais específica do que a sua superclasse 
 * Atividade. Ela implementa a interface DistanciaPercorrida para que consiga definir a 
 * distância percorrida com a atividade.
 * @author Luan Ribeiro
 * @version 1.0
 */
public class Ciclismo extends Atividade implements DistanciaPercorrida {
	
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
	public Ciclismo(int codigo, Calendar dataInicio, Calendar dataFim, Usuario usr, 
					 double distancia, RitmoAtividade ritmo) {
		super(codigo, dataInicio, dataFim, usr, ritmo);
		setDistancia(distancia);
		calcularKcalPerdida();
	}
	
	/**
	 * Construtor com bloco de instruções vazio e sem parâmetros
	 */
	public Ciclismo() {
		
	}
	
	/**
	 * Define a distância percorrida com o ciclismo
	 * @param distancia Distância Percorrida
	 */
	@Override
	public void setDistancia(double distancia) {
		this.distanciaPercorrida = distancia;
	}
	
	/**
	 * Obtém a distância percorrida com o ciclismo
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
			setKcalPerdida(kcalPerdidaMinuto(6, getUsuario().getPeso().getPeso()));
		}else if(getRitmo() == "Moderado") {
			setKcalPerdida(kcalPerdidaMinuto(10, getUsuario().getPeso().getPeso()));
		}else {
			setKcalPerdida(kcalPerdidaMinuto(16, getUsuario().getPeso().getPeso()));
		}*/
	}
	
	private double kcalPerdidaMinuto(double mets, double peso) {
		/*
		 * Fonte
		 * http://www.cdof.com.br/MET_compendium.pdf
		 * 
		 * Ciclismo Leve
		 * Até 19,2 km/h = MET 6
         * Ciclismo Moderado
         * De 19,2 km/h até 25,6 km/h = MET 10
         * Ciclismo Intenso
         * De 25,6 km/h até 32,2 km/h = MET 16
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
	 * String formatada com todos os dados do Ciclismo
	 * @return Retorna uma representação em string do objeto
	 */
	@Override
	public String toString() {
		return String.format("%s\"distanciaPercorrida\": %.1f \n\n}", super.toString(), distanciaPercorrida);
	}
}
