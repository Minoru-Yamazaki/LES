package br.com.ecommerceorquideas.test;

import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;

import br.com.ecommerceorquideas.dao.CartoesCompraDAO;
import br.com.ecommerceorquideas.dao.CuponsCompraDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.domain.CartoesCompra;
import br.com.ecommerceorquideas.domain.CuponsCompra;

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
    	IDAO dao = new CartoesCompraDAO();
    	
    	CartoesCompra cartoes = new CartoesCompra();
    	cartoes.setId(2);
    	
    	dao.excluir(3);
    }
	

}
