package br.com.ecommerceorquideas.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ecommerceorquideas.dao.CartaoDAO;
import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.CompraDAO;
import br.com.ecommerceorquideas.dao.CupomDAO;
import br.com.ecommerceorquideas.dao.EnderecoDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.ImagemDAO;
import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.dao.ProdutoDAO;
import br.com.ecommerceorquideas.domain.Cartao;
import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.Compra;
import br.com.ecommerceorquideas.domain.Cupom;
import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Imagem;
import br.com.ecommerceorquideas.domain.Login;
import br.com.ecommerceorquideas.domain.Orquidea;
import br.com.ecommerceorquideas.domain.Produto;
import br.com.ecommerceorquideas.mapa.CartaoMapa;
import br.com.ecommerceorquideas.mapa.ClienteMapa;
import br.com.ecommerceorquideas.mapa.CompraMapa;
import br.com.ecommerceorquideas.mapa.CupomMapa;
import br.com.ecommerceorquideas.mapa.EnderecoMapa;
import br.com.ecommerceorquideas.mapa.IMapa;
import br.com.ecommerceorquideas.mapa.ImagemMapa;
import br.com.ecommerceorquideas.mapa.LoginMapa;
import br.com.ecommerceorquideas.mapa.OrquideaMapa;
import br.com.ecommerceorquideas.mapa.ProdutoMapa;
import br.com.ecommerceorquideas.strategy.IStrategy;
import br.com.ecommerceorquideas.strategy.cliente.VerficaPreencTelefone;
import br.com.ecommerceorquideas.strategy.cliente.VerificaCPF;
import br.com.ecommerceorquideas.strategy.cliente.VerificaData;
import br.com.ecommerceorquideas.strategy.cliente.VerificaEmail;
import br.com.ecommerceorquideas.strategy.cliente.VerificaPreencSexo;
import br.com.ecommerceorquideas.strategy.cliente.VerificaSenha;
import br.com.ecommerceorquideas.strategy.pedido.InseriData;
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
		mapDAO.put(Cupom.class.getName(), new CupomDAO());
		mapDAO.put(Compra.class.getName(), new CompraDAO());
		mapDAO.put(Produto.class.getName(), new ProdutoDAO());
		mapDAO.put(Orquidea.class.getName(), new OrquideaDAO());
		mapDAO.put(Imagem.class.getName(), new ImagemDAO());
	}

	private void defineMapas() {
		entityToMap = new HashMap<String, IMapa>();

		entityToMap.put(Cartao.class.getName(), new CartaoMapa());
		entityToMap.put(Cliente.class.getName(), new ClienteMapa());
		entityToMap.put(Endereco.class.getName(), new EnderecoMapa());
		entityToMap.put(Login.class.getName(), new LoginMapa());
		entityToMap.put(Cupom.class.getName(), new CupomMapa());
		entityToMap.put(Compra.class.getName(), new CompraMapa());
		entityToMap.put(Produto.class.getName(), new ProdutoMapa());
		entityToMap.put(Orquidea.class.getName(), new OrquideaMapa());
		entityToMap.put(Imagem.class.getName(), new ImagemMapa());
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
		
		List<IStrategy> salvarCupom = new ArrayList<IStrategy>();
		//salvarCupom.add();
		rnSalvar.put(Cupom.class.getName(), salvarCupom);

		List<IStrategy> salvarPedido = new ArrayList<IStrategy>();
		salvarPedido.add(new InseriData());
		rnSalvar.put(Compra.class.getName(), salvarPedido);
		
		List<IStrategy> salvarProduto = new ArrayList<IStrategy>();
		//salvarPedido.add(new InseriData());
		rnSalvar.put(Produto.class.getName(), salvarProduto);
		
		List<IStrategy> salvarOrquidea = new ArrayList<IStrategy>();
		//salvarOrquidea.add(new InseriData());
		rnSalvar.put(Orquidea.class.getName(), salvarOrquidea);
		
		List<IStrategy> salvarImagem = new ArrayList<IStrategy>();
		//salvarImagem.add(new InseriData());
		rnSalvar.put(Imagem.class.getName(), salvarImagem);

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
		
		List<IStrategy> alterarCupom = new ArrayList<IStrategy>();
		//alterarCupom.add();
		rnAlterar.put(Cupom.class.getName(), alterarCupom);
		
		List<IStrategy> alterarCompra = new ArrayList<IStrategy>();
		//alterarCompra.add();
		rnAlterar.put(Compra.class.getName(), alterarCompra);
		
		List<IStrategy> alterarProduto = new ArrayList<IStrategy>();
		//alterarProduto.add();
		rnAlterar.put(Produto.class.getName(), alterarProduto);
		
		List<IStrategy> alterarOrquidea = new ArrayList<IStrategy>();
		//alterarOrquidea.add();
		rnAlterar.put(Orquidea.class.getName(), alterarOrquidea);
		
		List<IStrategy> alterarImagem = new ArrayList<IStrategy>();
		//alterarImagem.add();
		rnAlterar.put(Imagem.class.getName(), alterarImagem);

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
