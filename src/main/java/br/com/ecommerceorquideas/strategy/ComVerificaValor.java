package br.com.ecommerceorquideas.strategy;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.CupomDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.model.CartoesCompra;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.Cupom;
import br.com.ecommerceorquideas.model.CupomCompra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.model.Produto;

public class ComVerificaValor implements IStrategy{

	@Override
	public List<String> processar(EntidadeDominio entidade) {
		Compra compra = (Compra) entidade;
				
		Double saldo = verificaValorCompra(compra);
		Double valorCartoes = calculaTotalCartoes(compra.getCartoes());
		boolean qtdeCuponsOk = verificaCuponsUsados(compra);
		List<String> mensagensCupom = verificaValidadeCupons(compra);
		
		if(saldo == 0.0 && mensagensCupom == null) {
			List<String> mensagens = atualizaEstoque(compra);
			
			if(mensagens != null) {
				return mensagens;
			}
			excluiCuponsUsados(compra);
			
			return null; //valor correto
		
		}else if(saldo > 0) {
			return Arrays.asList("Valor informado no cartão está incorreto!");
		
		}else if(saldo < 0 && qtdeCuponsOk && valorCartoes == 0 && mensagensCupom == null){
			List<String> mensagens = atualizaEstoque(compra);
			if(mensagens != null) {
				return mensagens;
			}
			
			geraCupomTroca(compra, saldo);
			excluiCuponsUsados(compra);
			return null;
		
		}else if(qtdeCuponsOk && mensagensCupom == null){
			return Arrays.asList("Você inseriu dinheiro a mais");

		}else if(mensagensCupom != null){
			return mensagensCupom;
		
		}else {
			return Arrays.asList("Você inseriu cupons a mais");
		}		
	}
	
	private List<String> atualizaEstoque(Compra compra) {
		List<Produto> produtos = compra.getProdutos();
		List<String> mensagens = new ArrayList<String>();
		OrquideaDAO dao = new OrquideaDAO();
		Orquidea orquidea;
		
		//Verifica se algum produto não tem a quantidade suficiente
		for(Produto produto : produtos) {
			orquidea = consultarOrquidea(produto, dao);
			
			if(orquidea.getQuantidade() < produto.getQuantidade()) {
				mensagens.add("Quantidade insuficiente no estoque do produto: " + orquidea.getNome());
			}			
		}
		
		if(mensagens.size() > 0) {
			return mensagens;
		}
		//Atualiza estoque
		for(Produto produto : produtos) {
			orquidea = consultarOrquidea(produto, dao);
			
			orquidea.setQuantidade(orquidea.getQuantidade() - produto.getQuantidade());
			
			if(orquidea.getQuantidade() == 0) {
				orquidea.setAtivo(0);//Desativa produto
				orquidea.setCategoriaInativacao("Fora do mercado");
				orquidea.setCategoriaAtivacao("");
			}
			dao.alterar(orquidea);
		}
		return null;
	}
	
	private Orquidea consultarOrquidea(Produto produto, OrquideaDAO dao) {
		HashMap<String, String> map = new HashMap<>();
				
		map.put("id", produto.getIdProduto().toString());
		List<Orquidea> orquideas = (List<Orquidea>) dao.consultar(map);
		
		return orquideas.get(0);
	}
	
	private void excluiCuponsUsados(Compra compra) {
		List<CupomCompra> cupons = compra.getCupons();
		CupomDAO cupomDao = new CupomDAO();
		
		for(CupomCompra cupom : cupons) {
			if(cupom.getTipoCupom().equals("troca")) {
				cupomDao.excluir(cupom.getIdCupom());
			}
		}
	}
	
	private void geraCupomTroca(Compra compra, Double valor) {
		
		SimpleDateFormat formatoPadrao = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		SimpleDateFormat formatoNome = new SimpleDateFormat("ddMMyyyyHHmmss");
		String dataHora = formatoPadrao.format(compra.getData()).toString();
		
		String descricao = "Cupom gerado da compra do dia: ";
		descricao += dataHora;
		
		String nome = "Cupom de troca " + formatoNome.format(compra.getData()).toString();
		System.out.println(nome);
		Date dataValidade = geraDataValidade(compra.getData());
		
		Cupom cupom = new Cupom(
				nome, descricao.toString(), "troca",
				(valor * -1), dataValidade, compra.getCliId());
		
		IDAO dao = new CupomDAO();
		
		try {
			dao.salvar(cupom);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Date geraDataValidade(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MONTH, 3);        
		
		return calendar.getTime();
	}
	
	private boolean verificaCuponsUsados(Compra compra) {
		List<CupomCompra> cupons = compra.getCupons();
		Double totalCompra = calculaTotalProdutos(compra.getProdutos()) + compra.getFrete();
		int tamanhoLista = cupons.size();
		Double[] valores = new Double[tamanhoLista];
		Double menor;
		Double somatorio = 0.0;
		
		//Adiciona valores dos cupons na lista
		for(int i = 0; i < tamanhoLista;  i++) {
			valores[i] = cupons.get(i).getValor();
		}
		
		//BubbleSort para organizar de forma decrescente
		for(int i = 1; i < tamanhoLista; i++) {
			for(int j = 0; j < tamanhoLista - i; j++) {
				if(valores[j] < valores[j+1]) {
					menor = valores[j];
					valores[j] = valores[j+1];
					valores[j+1] = menor;
				}
			}			
		}
		
		for(int i = 0; i < tamanhoLista - 1; i++) {
			somatorio += valores[i];
			if(somatorio > totalCompra) {
				return false;
			}
		}
		
		return true;
	}
	
	private Double verificaValorCompra(Compra compra) {
		Double total = 0.0;
		
		total = calculaTotalProdutos(compra.getProdutos());
		total += compra.getFrete();
		total += (calculaTotalCupons(compra.getCupons()) * -1);
		total += (calculaTotalCartoes(compra.getCartoes()) * -1);
		
		return total;
	}
	
	private Double calculaTotalProdutos(List<Produto> produtos) {
		Double total = 0.0;
		for(Produto produto : produtos){
			total += (produto.getPreco() * produto.getQuantidade());
		}
		return total;
	}
	
	private Double calculaTotalCartoes(List<CartoesCompra> cartoes) {
		Double total = 0.0;
		for(CartoesCompra cartao : cartoes){
			total += cartao.getTotal();
		}		
		return total;
	}
	
	private Double calculaTotalCupons(List<CupomCompra> cupons) {
		Double total = 0.0;
		for(CupomCompra cupom : cupons){
			total += cupom.getValor();
		}
		return total;
	}
	
	private List<String> verificaValidadeCupons(Compra compra){
		
		List<String> mensagens = new ArrayList<String>();
		List<CupomCompra> cupons = compra.getCupons();
		Date hoje = new Date();
		
		//compara todos os cupons utilizados se são válidos
		for(CupomCompra cupom : cupons) {
			if(cupom.getValidade().before(hoje)) {
				mensagens.add("Cupom: '" + cupom.getNome() + "' não é válido");
			}
		}
		
		if(mensagens.size() > 0 ) {
			return mensagens;
		}
		
		return null;
	}

}
