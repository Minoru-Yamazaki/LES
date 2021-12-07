package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.CartoesCompra;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.CupomCompra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Produto;
import br.com.ecommerceorquideas.model.Troca;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CompraDAO implements IDAO{

	private static final String INSERT = "INSERT INTO compras(status, data, valor, frete, cidade, estado, pais, bairro, tipo_logradouro, logradouro, numero, complemento, tipo_residencia, cep, cli_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE compras SET status=?, valor=?, frete=?, cidade=?, estado=?, pais=?, bairro=?, tipo_logradouro=?, logradouro=?, numero=?, complemento=?, tipo_residencia=?, cep=? WHERE id=?";
	private static final String DELETE = "DELETE FROM compras WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public CompraDAO() {
	}

	public CompraDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Compra compra = (Compra) entidade;
		aviso = new Aviso();
		IDAO dao;

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, compra.getStatus());
			Timestamp time = new Timestamp(compra.getData().getTime());
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setDouble(3, compra.getValor());
			preparedStatement.setDouble(4, compra.getFrete());
			preparedStatement.setString(5, compra.getCidade());
			preparedStatement.setString(6, compra.getEstado());
			preparedStatement.setString(7, compra.getPais());
			preparedStatement.setString(8, compra.getBairro());
			preparedStatement.setString(9, compra.getTipoLogradouro());
			preparedStatement.setString(10, compra.getLogradouro());
			preparedStatement.setString(11, compra.getNumero());
			preparedStatement.setString(12, compra.getComplemento());
			preparedStatement.setString(13, compra.getTipoResidencia());
			preparedStatement.setString(14, compra.getCep());
			preparedStatement.setInt(15, compra.getCliId());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				compra.setId(rs.getInt(1));
			}
			if(!compra.getCupons().isEmpty()) {
				dao = new CupomCompraDAO(connection);
				for(CupomCompra cupom : compra.getCupons()) {
					cupom.setIdCompra(compra.getId());
					dao.salvar(cupom);
				}
			}
			if(!compra.getCartoes().isEmpty()) {
				dao = new CartoesCompraDAO(connection);
				for(CartoesCompra cartao : compra.getCartoes()) {
					cartao.setIdCompra(compra.getId());
					dao.salvar(cartao);
				}
			}
			if(!compra.getProdutos().isEmpty()) {
				dao = new ProdutoDAO(connection);
				for(Produto produto : compra.getProdutos()) {
					produto.setIdCompra(compra.getId());
					dao.salvar(produto);
				}
			}			

			preparedStatement.close();
			connection.commit();
			
			aviso.addMensagem("Pedido salvo com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao salvar as informações");
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		Compra pedido = (Compra) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, pedido.getStatus());
			//Timestamp time = new Timestamp(pedido.getData().getTime());
			//preparedStatement.setTimestamp(2, time);
			preparedStatement.setDouble(2, pedido.getValor());
			preparedStatement.setDouble(3, pedido.getFrete());
			preparedStatement.setString(4, pedido.getCidade());
			preparedStatement.setString(5, pedido.getEstado());
			preparedStatement.setString(6, pedido.getPais());
			preparedStatement.setString(7, pedido.getBairro());
			preparedStatement.setString(8, pedido.getTipoLogradouro());
			preparedStatement.setString(9, pedido.getLogradouro());
			preparedStatement.setString(10, pedido.getNumero());
			preparedStatement.setString(11, pedido.getComplemento());
			preparedStatement.setString(12, pedido.getTipoResidencia());
			preparedStatement.setString(13, pedido.getCep());
			preparedStatement.setInt(14, pedido.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Pedido alterado com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao alterar as informações");
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object excluir(Integer id) {
		boolean commit = false;
		aviso = new Aviso();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			exluirDados(connection, id);
			
			preparedStatement = connection.prepareStatement(DELETE);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			if (commit) {
				connection.commit();
			}
			aviso.addMensagem("Pedido excluido com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao excluir as informações");
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (commit) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object consultar(HashMap<String, String> map) {
		List<EntidadeDominio> compras = new ArrayList<>();
		Compra compra;
		IDAO dao;
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "compras");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				compra = new Compra(rs.getInt(1), rs.getString(3), rs.getDate(4), rs.getDouble(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getString(14), rs.getString(15), rs.getString(16), rs.getInt(17));
				
				map = new HashMap<>();
				map.put("com_id", compra.getId().toString());
				dao = new ProdutoDAO(connection);
				compra.setProdutos((List<Produto>) dao.consultar(map));
				
				dao = new CupomCompraDAO(connection);
				compra.setCupons((List<CupomCompra>) dao.consultar(map));
				
				dao = new CartoesCompraDAO(connection);
				compra.setCartoes((List<CartoesCompra>) dao.consultar(map));
				
				dao = new TrocaDAO(connection);
				compra.setTrocas((List<Troca>) dao.consultar(map));
				
				compras.add(compra);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return compras;
	}
	
	private void exluirDados(Connection connection, Integer id) throws SQLException {
		
		try {
			IDAO dao;

			HashMap<String, String> map = new HashMap<>();
			map.put("com_id", id.toString());
			dao = new CupomCompraDAO(connection);
			List<CupomCompra> cupons = (List<CupomCompra>) dao.consultar(map);
			for (CupomCompra cupom : cupons) {
				dao.excluir(cupom.getId());
			}

			dao = new ProdutoDAO(connection);
			List<Produto> produtos = (List<Produto>) dao.consultar(map);
			for (Produto produto : produtos) {
				dao.excluir(produto.getId());
			}
			
			dao = new CartoesCompraDAO(connection);
			List<CartoesCompra> cartoes = (List<CartoesCompra>) dao.consultar(map);
			for (CartoesCompra cartao : cartoes) {
				dao.excluir(cartao.getId());
			}
			
			dao = new TrocaDAO(connection);
			List<Troca> trocas = (List<Troca>) dao.consultar(map);
			for (Troca troca : trocas) {
				dao.excluir(troca.getId());
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

}
