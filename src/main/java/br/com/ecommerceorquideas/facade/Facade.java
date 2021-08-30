package br.com.ecommerceorquideas.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ecommerceorquideas.dao.CartaoDAO;
import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.EnderecoDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.domain.Cartao;
import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Login;
import br.com.ecommerceorquideas.mapa.CartaoMapa;
import br.com.ecommerceorquideas.mapa.ClienteMapa;
import br.com.ecommerceorquideas.mapa.EnderecoMapa;
import br.com.ecommerceorquideas.mapa.IMapa;
import br.com.ecommerceorquideas.mapa.LoginMapa;
import br.com.ecommerceorquideas.strategy.CliVerificaSenha;
import br.com.ecommerceorquideas.strategy.IStrategy;
import br.com.ecommerceorquideas.warning.Aviso;

public class Facade implements IFacade{
	
	private Map<String, List<IStrategy>> rnEntidade;
	private Map<String, IDAO> mapDAO;
	private Map<String, IMapa> entityToMap;
	
	public Facade() {
		defineValidadores();
		defineDAOs();
		defineMapas();
	}

	@Override
	public Object salvar(EntidadeDominio entidade) {
		
		Aviso aviso = verificaRN(entidade);

		if (aviso.getMensagens() != null) {
			return aviso;
		}

		IDAO dao = mapDAO.get(entidade.getClass().getName());
		
		return dao.salvar(entidade);
	}

	@Override
	public Object excluir(EntidadeDominio entidade) {
		
		IDAO dao = mapDAO.get(entidade.getClass().getName());
		
		return dao.excluir(entidade.getId());
	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		
		IDAO dao = mapDAO.get(entidade.getClass().getName());
		
		return dao.alterar(entidade);
	}

	@Override
	public Object consultar(EntidadeDominio entidade) {
		
		IDAO dao = mapDAO.get(entidade.getClass().getName());
		IMapa mapa = entityToMap.get(entidade.getClass().getName());
		HashMap<String, String> map = mapa.mapeia(entidade);
		
		List<Object> retorno = (List<Object>) dao.consultar(map);
		if(retorno.size() == 0)
			return new Aviso("Nenhum resultado obtido"); 
		
		return retorno;
	}
	
	private void defineDAOs() {
		mapDAO = new HashMap<String, IDAO>();
		
		mapDAO.put(Cartao.class.getName(), new CartaoDAO());
		mapDAO.put(Cliente.class.getName(), new ClienteDAO());
		mapDAO.put(Endereco.class.getName(), new EnderecoDAO());
		mapDAO.put(Login.class.getName(), new LoginDAO());
	}
	
	private void defineMapas() {
		entityToMap = new HashMap<String, IMapa>();
		
		entityToMap.put(Cartao.class.getName(), new CartaoMapa());
		entityToMap.put(Cliente.class.getName(), new ClienteMapa());
		entityToMap.put(Endereco.class.getName(), new EnderecoMapa());
		entityToMap.put(Login.class.getName(), new LoginMapa());
	}
	
	private void defineValidadores() {
		rnEntidade = new HashMap<String, List<IStrategy>>();
		
		List<IStrategy> validadoresCliente = new ArrayList<IStrategy>();
		validadoresCliente.add(new CliVerificaSenha());
		rnEntidade.put(Cliente.class.getName(), validadoresCliente);
		
		List<IStrategy> validadoresCartao = new ArrayList<IStrategy>();
		//validadoresCartao.add(new MatValidadorSigla());
		rnEntidade.put(Cartao.class.getName(), validadoresCartao);
		
		List<IStrategy> validadoresEndereco = new ArrayList<IStrategy>();
		//validadoresCartao.add(new MatValidadorSigla());
		rnEntidade.put(Endereco.class.getName(), validadoresEndereco);
		
		List<IStrategy> validadoresLogin = new ArrayList<IStrategy>();
		//validadoresCartao.add(new MatValidadorSigla());
		rnEntidade.put(Login.class.getName(), validadoresLogin);
	}
	
	private Aviso verificaRN(EntidadeDominio entidade) {
		Aviso aviso = new Aviso();
		String nomeClasse = entidade.getClass().getName();
		List<IStrategy> regras = rnEntidade.get(nomeClasse);
		
		for (IStrategy validador : regras) {
			String erro = validador.processar(entidade);
			if (erro != null) {
				aviso.addMensagem(erro);
			}
		}
		return aviso;
	}

}
