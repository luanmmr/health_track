package br.com.healthtrack.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tests {

	public static void main(String[] args) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.set(dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH), dataAtual.get(Calendar.DATE), 0, 0, 0);
		Calendar dataCompara = Calendar.getInstance();
		
		try {
		  dataCompara.setTime(f.parse("2021-01-21"));
		  dataCompara.add(Calendar.SECOND, 1);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (dataCompara.before(dataAtual)) {
			System.out.println("dataCompara é antes");
		}
		
		System.out.println(dataAtual.get(Calendar.MONTH));

	}

}
