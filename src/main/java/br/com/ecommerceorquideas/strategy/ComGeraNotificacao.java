package br.com.ecommerceorquideas.strategy;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.MensagemDAO;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Mensagem;
import br.com.ecommerceorquideas.model.Troca;
import br.com.ecommerceorquideas.util.CupomTroca;
import br.com.ecommerceorquideas.util.FormataData;

public class ComGeraNotificacao implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
		boolean trocaAutorizada = compra.getStatus().equals("Troca autorizada");
		boolean trocaRejeitada = compra.getStatus().equals("Troca rejeitada");
		boolean trocaEfetuada = compra.getStatus().equals("Troca efetuada");
		
		if(trocaAutorizada || trocaRejeitada || trocaEfetuada) {
			int clienteId = compra.getCliId();
			IDAO dao = new MensagemDAO();
			Mensagem mensagem = new Mensagem();
			String msg;
			
			mensagem.setData(new Date());
			mensagem.setCliId(clienteId);
			
			if(trocaAutorizada) {
				msg = "Solicitação de troca autorizada ------ Data da compra: " + FormataData.ddMMyy(compra.getData());
				
			}else if(trocaRejeitada){
				msg = "Solicitação de troca rejeitada ------ Data da compra: " + FormataData.ddMMyy(compra.getData());
			}else {
				String descricao = "Cupom de troca de produto(s) da compra do dia : ";
				double valor = calcularValor(compra.getTrocas());
				
				CupomTroca.geraCupom(compra, valor, descricao);
				msg = "Cupom gerado ------ Data da compra: " + FormataData.ddMMyy(compra.getData());
			}
			mensagem.setMensagem(msg);
			
			try {
				dao.salvar(mensagem);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private double calcularValor(List<Troca> trocas) {
		double total = 0.0;
		
		for(Troca troca : trocas) {
			total += troca.getPreco() * troca.getQuantidade();
		}
		
		return total;
	}

	
}
