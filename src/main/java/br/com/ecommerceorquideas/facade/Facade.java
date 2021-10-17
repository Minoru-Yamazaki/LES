package br.com.ecommerceorquideas.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ecommerceorquideas.dao.AdministradorDAO;
import br.com.ecommerceorquideas.dao.CartaoDAO;
import br.com.ecommerceorquideas.dao.ClienteDAO;
import br.com.ecommerceorquideas.dao.CompraDAO;
import br.com.ecommerceorquideas.dao.CorDAO;
import br.com.ecommerceorquideas.dao.CupomAdminDAO;
import br.com.ecommerceorquideas.dao.CupomDAO;
import br.com.ecommerceorquideas.dao.EnderecoDAO;
import br.com.ecommerceorquideas.dao.GeneroDAO;
import br.com.ecommerceorquideas.dao.IDAO;
import br.com.ecommerceorquideas.dao.ImagemDAO;
import br.com.ecommerceorquideas.dao.LoginAdminDAO;
import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.dao.OrquideaDAO;
import br.com.ecommerceorquideas.dao.ProdutoDAO;
import br.com.ecommerceorquideas.mapa.AdministradorMapa;
import br.com.ecommerceorquideas.mapa.CartaoMapa;
import br.com.ecommerceorquideas.mapa.ClienteMapa;
import br.com.ecommerceorquideas.mapa.CompraMapa;
import br.com.ecommerceorquideas.mapa.CorMapa;
import br.com.ecommerceorquideas.mapa.CupomAdminMapa;
import br.com.ecommerceorquideas.mapa.CupomMapa;
import br.com.ecommerceorquideas.mapa.EnderecoMapa;
import br.com.ecommerceorquideas.mapa.GeneroMapa;
import br.com.ecommerceorquideas.mapa.IMapa;
import br.com.ecommerceorquideas.mapa.ImagemMapa;
import br.com.ecommerceorquideas.mapa.LoginAdminMapa;
import br.com.ecommerceorquideas.mapa.LoginMapa;
import br.com.ecommerceorquideas.mapa.OrquideaMapa;
import br.com.ecommerceorquideas.mapa.ProdutoMapa;
import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.Cartao;
import br.com.ecommerceorquideas.model.Cliente;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.Cor;
import br.com.ecommerceorquideas.model.Cupom;
import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.Endereco;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Genero;
import br.com.ecommerceorquideas.model.Imagem;
import br.com.ecommerceorquideas.model.Login;
import br.com.ecommerceorquideas.model.LoginAdmin;
import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.model.Produto;
import br.com.ecommerceorquideas.strategy.CarVerificaCadastro;
import br.com.ecommerceorquideas.strategy.CliVerficaPreencTelefone;
import br.com.ecommerceorquideas.strategy.PesVerificaCPF;
import br.com.ecommerceorquideas.strategy.CliVerificaData;
import br.com.ecommerceorquideas.strategy.PesVerificaEmail;
import br.com.ecommerceorquideas.strategy.CliVerificaPreencSexo;
import br.com.ecommerceorquideas.strategy.CliVerificaSenha;
import br.com.ecommerceorquideas.strategy.GenVerificaNome;
import br.com.ecommerceorquideas.strategy.IStrategy;
import br.com.ecommerceorquideas.strategy.ComInseriData;
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
		mapDAO.put(Cor.class.getName(), new CorDAO());
		mapDAO.put(Genero.class.getName(), new GeneroDAO());
		mapDAO.put(CupomAdmin.class.getName(), new CupomAdminDAO());
		mapDAO.put(LoginAdmin.class.getName(), new LoginAdminDAO());
		mapDAO.put(Administrador.class.getName(), new AdministradorDAO());
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
		entityToMap.put(Cor.class.getName(), new CorMapa());
		entityToMap.put(Genero.class.getName(), new GeneroMapa());
		entityToMap.put(CupomAdmin.class.getName(), new CupomAdminMapa());
		entityToMap.put(LoginAdmin.class.getName(), new LoginAdminMapa());
		entityToMap.put(Administrador.class.getName(), new AdministradorMapa());
	}

	private void defineValidadores() {
		rnSalvar = new HashMap<String, List<IStrategy>>();

		List<IStrategy> salvarCliente = new ArrayList<IStrategy>();
		salvarCliente.add(new CliVerificaSenha());
		salvarCliente.add(new PesVerificaCPF());
		salvarCliente.add(new PesVerificaEmail());
		salvarCliente.add(new CliVerficaPreencTelefone());
		salvarCliente.add(new CliVerificaData());
		salvarCliente.add(new CliVerificaPreencSexo());
		rnSalvar.put(Cliente.class.getName(), salvarCliente);

		
		List<IStrategy> salvarLogin = new ArrayList<IStrategy>();
		salvarLogin.add(new CliVerificaSenha());
		rnSalvar.put(Login.class.getName(), salvarLogin);
		
		List<IStrategy> salvarPedido = new ArrayList<IStrategy>();
		salvarPedido.add(new ComInseriData());
		rnSalvar.put(Compra.class.getName(), salvarPedido);
		
		List<IStrategy> salvarGenero = new ArrayList<IStrategy>();
		salvarGenero.add(new GenVerificaNome());
		rnSalvar.put(Genero.class.getName(), salvarGenero);
		
		List<IStrategy> salvarCartao = new ArrayList<IStrategy>();
		salvarCartao.add(new CarVerificaCadastro());
		rnSalvar.put(Cartao.class.getName(), salvarCartao);
		
		//*****************ADMINISTRADOR**************************
		List<IStrategy> salvarAdministrador = new ArrayList<IStrategy>();
		salvarAdministrador.add(new PesVerificaCPF());
		salvarAdministrador.add(new CliVerificaSenha());
		salvarAdministrador.add(new PesVerificaEmail());
		rnSalvar.put(Administrador.class.getName(), salvarAdministrador);
		
		List<IStrategy> salvarLoginAdmin = new ArrayList<IStrategy>();
		salvarLoginAdmin.add(new CliVerificaSenha());
		rnSalvar.put(LoginAdmin.class.getName(), salvarLoginAdmin);
		
		// **************alterar*******************

		rnAlterar = new HashMap<String, List<IStrategy>>();

		List<IStrategy> alterarCliente = new ArrayList<IStrategy>();
		alterarCliente.add(new PesVerificaCPF());
		alterarCliente.add(new CliVerficaPreencTelefone());
		alterarCliente.add(new CliVerificaData());
		alterarCliente.add(new CliVerificaPreencSexo());
		rnAlterar.put(Cliente.class.getName(), alterarCliente);

		List<IStrategy> alterarLogin = new ArrayList<IStrategy>();
		alterarLogin.add(new CliVerificaSenha());
		rnAlterar.put(Login.class.getName(), alterarLogin);
		
		List<IStrategy> alterarLoginAdmin = new ArrayList<IStrategy>();
		alterarLoginAdmin.add(new CliVerificaSenha());
		rnAlterar.put(Login.class.getName(), alterarLoginAdmin);
		
		List<IStrategy> alterarGenero = new ArrayList<IStrategy>();
		alterarGenero.add(new GenVerificaNome());
		rnAlterar.put(Genero.class.getName(), alterarGenero);
		
		// **************consultar*******************
		
	}

	private Aviso verificaRN(EntidadeDominio entidade, Map<String, List<IStrategy>> map) {
		Aviso aviso = new Aviso();
		String nomeClasse = entidade.getClass().getName();
		List<IStrategy> regras;
		
		try {
			regras = map.get(nomeClasse);			
		} catch (Exception e) {
			System.out.println(e);
			return aviso;
		}
		
		if(regras == null)
			return aviso;

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
