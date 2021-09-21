package br.com.ecommerceorquideas.facade;

import java.util.ArrayList;
import java.util.Arrays;
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
import br.com.ecommerceorquideas.strategy.IStrategy;
import br.com.ecommerceorquideas.strategy.cliente.VerficaPreencTelefone;
import br.com.ecommerceorquideas.strategy.cliente.VerificaCPF;
import br.com.ecommerceorquideas.strategy.cliente.VerificaData;
import br.com.ecommerceorquideas.strategy.cliente.VerificaEmail;
import br.com.ecommerceorquideas.strategy.cliente.VerificaPreencSexo;
import br.com.ecommerceorquideas.strategy.cliente.VerificaSenha;
import br.com.ecommerceorquideas.warning.Aviso;

public class Facade implements IFacade {

	private Map<String, List<IStrategy>> rnSalvar;
	private Map<String, List<IStrategy>> rnConsultar;
	private Map<String, List<IStrategy>> rnAlterar;
	private Map<String, IDAO> mapDAO;
	private Map<String, IMapa> entityToMap;
	Aviso aviso = new Aviso();

	public Facade() {
		defineValidadores();
		defineDAOs();
		defineMapas();
	}

	@Override
	public Object salvar(EntidadeDominio entidade) {
		aviso = verificaRN(entidade, rnSalvar);

		if (aviso.getMensagens() != null) {
			return aviso;
		}

		IDAO dao = mapDAO.get(entidade.getClass().getName());

		try {
			aviso = (Aviso) dao.salvar(entidade);
		} catch (Exception e) {
			e.printStackTrace();
			// aviso.addMensagem(mensagem);
		}
		return aviso;
	}

	@Override
	public Object excluir(EntidadeDominio entidade) {

		IDAO dao = mapDAO.get(entidade.getClass().getName());

		return dao.excluir(entidade.getId());
	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		aviso = verificaRN(entidade, rnAlterar);

		if (aviso.getMensagens() != null) {
			return aviso;
		}

		IDAO dao = mapDAO.get(entidade.getClass().getName());

		return dao.alterar(entidade);
	}

	@Override
	public Object consultar(EntidadeDominio entidade) {

		IDAO dao = mapDAO.get(entidade.getClass().getName());
		IMapa mapa = entityToMap.get(entidade.getClass().getName());
		HashMap<String, String> map = mapa.mapeia(entidade);

		List<Object> retorno = (List<Object>) dao.consultar(map);
		if (retorno.size() == 0) {
			return Arrays.asList(new Aviso("Nenhum resultado obtido"));
		}

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
		rnSalvar = new HashMap<String, List<IStrategy>>();

		List<IStrategy> salvarCliente = new ArrayList<IStrategy>();
		salvarCliente.add(new VerificaSenha());
		salvarCliente.add(new VerificaCPF());
		salvarCliente.add(new VerificaEmail());
		salvarCliente.add(new VerficaPreencTelefone());
		salvarCliente.add(new VerificaData());
		salvarCliente.add(new VerificaPreencSexo());
		rnSalvar.put(Cliente.class.getName(), salvarCliente);

		List<IStrategy> salvarCartao = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnSalvar.put(Cartao.class.getName(), salvarCartao);

		List<IStrategy> salvarEndereco = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnSalvar.put(Endereco.class.getName(), salvarEndereco);

		List<IStrategy> salvarLogin = new ArrayList<IStrategy>();
		salvarLogin.add(new VerificaSenha());
		rnSalvar.put(Login.class.getName(), salvarLogin);

		// **************alterar*******************

		rnAlterar = new HashMap<String, List<IStrategy>>();

		List<IStrategy> alterarCliente = new ArrayList<IStrategy>();
		alterarCliente.add(new VerificaCPF());
		alterarCliente.add(new VerficaPreencTelefone());
		alterarCliente.add(new VerificaData());
		alterarCliente.add(new VerificaPreencSexo());
		rnAlterar.put(Cliente.class.getName(), alterarCliente);

		List<IStrategy> alterarCartao = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnAlterar.put(Cartao.class.getName(), alterarCartao);

		List<IStrategy> alterarEndereco = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnAlterar.put(Endereco.class.getName(), alterarEndereco);

		List<IStrategy> alterarLogin = new ArrayList<IStrategy>();
		alterarLogin.add(new VerificaSenha());
		rnAlterar.put(Login.class.getName(), alterarLogin);

		// **************consultar*******************
		rnConsultar = new HashMap<String, List<IStrategy>>();

		List<IStrategy> consultarCliente = new ArrayList<IStrategy>();
		// consultarCliente.add(new VerificaSenha());
		rnConsultar.put(Cliente.class.getName(), consultarCliente);

		List<IStrategy> consultarCartao = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnConsultar.put(Cartao.class.getName(), consultarCartao);

		List<IStrategy> consultarEndereco = new ArrayList<IStrategy>();
		// validadoresCartao.add(new MatValidadorSigla());
		rnConsultar.put(Endereco.class.getName(), consultarEndereco);

		List<IStrategy> consultarLogin = new ArrayList<IStrategy>();
		// consultarLogin.add(new VerificaSenha());
		rnConsultar.put(Login.class.getName(), consultarLogin);

	}

	private Aviso verificaRN(EntidadeDominio entidade, Map<String, List<IStrategy>> map) {
		Aviso aviso = new Aviso();
		String nomeClasse = entidade.getClass().getName();
		List<IStrategy> regras = map.get(nomeClasse);

		for (IStrategy validador : regras) {
			List<String> erros = validador.processar(entidade);
			if (erros != null) {
				for (String erro : erros) {
					aviso.addMensagem(erro);
				}
			}
		}
		return aviso;
	}

}
