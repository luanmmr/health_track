package br.com.healthtrack.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CriptografiaSenha {
	
	public static String criptografar(String senha) {
		String senhaCriptografa = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes("ISO-8859-1"));
			
			BigInteger hash = new BigInteger(1, md.digest());
			
			senhaCriptografa = hash.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return senhaCriptografa;
	}

}
