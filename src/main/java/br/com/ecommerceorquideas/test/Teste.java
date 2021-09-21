package br.com.ecommerceorquideas.test;

import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.warning.Aviso;

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
    
    public static void main(String[] args) {
    	Aviso aviso = new Aviso();
    	
    	if(aviso.getMensagens()==null) {
    		System.out.println("teste");
    	}
    	
    }
	

}
