package br.com.ecommerceorquideas.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class Teste {

	
	public static String codificaBase64Encoder(String msg) {
        return new Base64().encodeToString(msg.getBytes());
    }

    /**
     * Decodifica string na base 64 (Decoder)
     */
    public static String decodificaBase64Decoder(String msg) {
        return new String(new Base64().decode(msg));
    }

    public static void test(String[] args) {

        String msgCodificada = codificaBase64Encoder("Senhaaa!");

        String msgDecodificada = decodificaBase64Decoder("SHVtZG9pcyE=");

        System.out.println("Mensagem Codificada: " + msgCodificada);
        System.out.println("Mensagem Decodificada: " + msgDecodificada);

    }
    
    public static void main(String[] args) throws SQLException {
    	List<Double> precos = new ArrayList<Double>();
    	precos.add(10.0);
    	precos.add(5.0);
    	precos.add(15.0);
    	precos.add(35.0);
    	precos.add(50.0);
    	precos.add(45.0);
    	precos.add(25.0);
    	
    	int tamanhoLista = precos.size(); 
    	
    	Double[] valores = new Double[tamanhoLista];
		Double menor;
		
		for(int i = 0; i < tamanhoLista;  i++) {
			valores[i] = precos.get(i);
		}
		
    	for(int i = 1; i < tamanhoLista; i++) {
			for(int j = 0; j < tamanhoLista - i; j++) {
				if(valores[j] < valores[j+1]) {
					menor = valores[j];
					valores[j] = valores[j+1];
					valores[j+1] = menor;
				}
			}			
		}
		
		
    	for(Double valor : valores) {
    		System.out.println(valor);
    	}
    }
	

}
