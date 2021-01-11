package br.com.healthtrack.tests;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class tests {

	public static void main(String[] args) throws Exception {

		LocalTime h1 = LocalTime.of(11, 00, 00);
		LocalTime h2 = LocalTime.of(23, 10, 10);
	
		Calendar dt = Calendar.getInstance();
		
		System.out.println(Duration.between(h1, h2).getSeconds());
		System.out.println(dt.get(Calendar.HOUR));


		}
		
}
