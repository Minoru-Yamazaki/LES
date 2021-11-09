package br.com.ecommerceorquideas.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.dao.CompraAnaliseDAO;
import br.com.ecommerceorquideas.model.Analise;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.Produto;
import br.com.ecommerceorquideas.model.dashboard.ChartOptions;
import br.com.ecommerceorquideas.model.dashboard.ChartOptionsRule;
import br.com.ecommerceorquideas.model.dashboard.Condition;
import br.com.ecommerceorquideas.model.dashboard.EixoX;
import br.com.ecommerceorquideas.model.dashboard.EixoY;
import br.com.ecommerceorquideas.model.dashboard.Legend;
import br.com.ecommerceorquideas.model.dashboard.Responsive;
import br.com.ecommerceorquideas.model.dashboard.Rule;
import br.com.ecommerceorquideas.model.dashboard.Serie;
import br.com.ecommerceorquideas.model.dashboard.Titulo;

public class Grafico {

	public static ChartOptions geraDados(Analise analise) {//
		ChartOptions chartOptions = new ChartOptions();
		
		chartOptions.setTitle(new Titulo(formataTitulo(analise)));
		//chartOptions.setSubtitle(new Titulo("teste"));
		chartOptions.setYAxis(new EixoY(new Titulo("Qtde. Vendida")));
		
		List<String> colunas = formataCategoriaEixoX(analise);
				
		chartOptions.setXAxis(new EixoX(colunas, new Titulo("Período")));
				
		chartOptions.setLegend(new Legend("vertical", "right", "middle"));
		
		List<Serie> series = formataSeries(analise, colunas);
		chartOptions.setSeries(series);
		
		Rule rule = new Rule(new Condition(500), new ChartOptionsRule(new Legend("horizontal", "center", "bottom")));
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(rule);
		
		chartOptions.setResponsive(new Responsive(rules));
		
		return chartOptions;
	}
	
	public static HashMap<String, Integer> geraMapaEixoX(List<String> chaves) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int tamanhoLista = chaves.size();
		
		for (int i = 0; i < tamanhoLista; i++) {
			map.put(chaves.get(i), i);
		}
		return map;
	}
	
	public static String formataTitulo(Analise analise) {
		String dataInicial = FormataData.ddMMyy(analise.getDataInicial());
		String dataFinal = FormataData.ddMMyy(analise.getDataFinal());
				
		return "De " + dataInicial + " a " + dataFinal;
	}
	
	public static List<String> formataCategoriaEixoX(Analise analise){
		String dataInicial = FormataData.MMMyy(analise.getDataInicial());
		String dataFinal = FormataData.MMMyy(analise.getDataFinal());
		Date proximoMes = analise.getDataInicial();
		List<String> colunas = new ArrayList<String>();
		boolean mesDiferente = true;
		String data;
		
		colunas.add(dataInicial);
		if(dataInicial.equals(dataFinal)) {
			return colunas;
		}else {
			while(true) {
				proximoMes = FormataData.mesSeguinte(proximoMes);
				data = FormataData.MMMyy(proximoMes);
				
				colunas.add(data);
				if(data.equals(dataFinal)) {
					return colunas;
				}				
			}			
		}		
	}
	
	public static List<Serie> formataSeries(Analise analise, List<String> colunas){
		
		//Separa produtos por id e adiciona data
		CompraAnaliseDAO analiseDAO = new CompraAnaliseDAO();
		List<Compra> compras = analiseDAO.consultar(analise);//Consulta todas as compras do período
		
		HashMap<Integer, List<Produto>> map = new HashMap<Integer, List<Produto>>();
		List<Produto> produtos;
		List<Integer> chaves = new ArrayList<Integer>();
		for (Compra compra : compras) {
			for (Produto produto : compra.getProdutos()) {
				if(map.get(produto.getIdProduto()) == null) {//verifica se existe id
					produtos = new ArrayList<Produto>();
					
					chaves.add(produto.getIdProduto());// Adiciona a chave (id) a lista	
				}else {
					produtos = map.get(produto.getIdProduto());					
				}
				produto.setData(compra.getData());
				produtos.add(produto);
				
				map.put(produto.getIdProduto(), produtos);// adiciona novo valor de map
			}
		}	
		/*
		for (Integer chave : chaves) {
			List<Produto> prod = map.get(chave);
			for (Produto produto : prod) {
				System.out.println(produto.getNome()+ "--" + produto.getQuantidade() + "--" + produto.getData());
			}
		}*/
		
		//Gera Mapa do eixo X
		HashMap<String, Integer> mapaEixoX = geraMapaEixoX(colunas);// exemplo {'jan-2021', 0}, {'fev-2021', 1}. . .
		
		//separa por data
		List<Serie> series = new ArrayList<Serie>();
		String data;
		int coluna;
		Serie serie;
		int quantidade;
		String nome = "";
		for (Integer chave : chaves) {
			List<Produto> prods = map.get(chave);
			List<Integer> dados = geraListaSerie(colunas.size());// Gera uma lista de 0 do mesmo tamanho que a lista 'colunas': [0,0,0,0,0,0]
			
			for (Produto produto : prods) {
				data =  FormataData.MMMyy(produto.getData());
				coluna = mapaEixoX.get(data);
				quantidade = dados.get(coluna);
				dados.set(coluna, quantidade + produto.getQuantidade());// atualiza qtde do produto
				nome = produto.getNome();
			}
			serie = new Serie(nome, dados);
			series.add(serie);
		}
		return series;
	}
	
	public static List<Integer> geraListaSerie(int tamanho){
		List<Integer> lista = new ArrayList<Integer>();
		
		while(tamanho > 0) {
			lista.add(0);
			tamanho--;
		}
		
		return lista;
	}
}
