package br.com.healthtrack.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class tests {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar data = Calendar.getInstance();
		
		System.out.println(f.format(data.getTime()));
		
		data.add(Calendar.DATE, 1);
		
		System.out.println(f.format(data.getTime()));

	}
	
}
