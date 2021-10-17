package br.com.ecommerceorquideas.util;

import java.util.Random;

public class GeraCodigo {

	public static String gerar() {
		String caracteres[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
    			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
    			"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
    			"T", "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$", "%", "&", "*", "1", "2",
    			"3", "4", "5", "6", "7", "8", "9", "0"};
    	Random random = new Random();
    	int n;
    	String codigo = "";
    	
    	for(int i = 0; i < 10; i++) {
    		n = random.nextInt(68);
    		codigo += caracteres[n];
    	}
    	
		return codigo;
	}

}
