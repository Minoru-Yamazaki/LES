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

import br.com.ecommerceorquideas.domain.CartoesCompra;
import br.com.ecommerceorquideas.domain.Compra;
import br.com.ecommerceorquideas.domain.CuponsCompra;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Produto;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CompraDAO implements IDAO{

	private static final String INSERT = "INSERT INTO compras(status, data, valor, frete, cidade, estado, pais, bairro, tipo_logradouro, logradouro, numero, complemento, tipo_residencia, cep, cli_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE compras SET status=?, data=?, valor=?, frete=?, cidade=?, estado=?, pais=?, bairro=?, tipo_logradouro=?, logradouro=?, numero=?, complemento=?, tipo_residencia=?, cep=? WHERE id=?";
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
		Compra pedido = (Compra) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, pedido.getStatus());
			Timestamp time = new Timestamp(pedido.getData().getTime());
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setDouble(3, pedido.getValor());
			preparedStatement.setDouble(4, pedido.getFrete());
			preparedStatement.setString(5, pedido.getCidade());
			preparedStatement.setString(6, pedido.getEstado());
			preparedStatement.setString(7, pedido.getPais());
			preparedStatement.setString(8, pedido.getBairro());
			preparedStatement.setString(9, pedido.getTipoLogradouro());
			preparedStatement.setString(10, pedido.getLogradouro());
			preparedStatement.setString(11, pedido.getNumero());
			preparedStatement.setString(12, pedido.getComplemento());
			preparedStatement.setString(13, pedido.getTipoResidencia());
			preparedStatement.setString(14, pedido.getCep());
			preparedStatement.setInt(15, pedido.getCliId());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				pedido.setId(rs.getInt(1));
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
			Timestamp time = new Timestamp(pedido.getData().getTime());
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setDouble(3, pedido.getValor());
			preparedStatement.setDouble(4, pedido.getFrete());
			preparedStatement.setString(5, pedido.getCidade());
			preparedStatement.setString(6, pedido.getEstado());
			preparedStatement.setString(7, pedido.getPais());
			preparedStatement.setString(8, pedido.getBairro());
			preparedStatement.setString(9, pedido.getTipoLogradouro());
			preparedStatement.setString(10, pedido.getLogradouro());
			preparedStatement.setString(11, pedido.getNumero());
			preparedStatement.setString(12, pedido.getComplemento());
			preparedStatement.setString(13, pedido.getTipoResidencia());
			preparedStatement.setString(14, pedido.getCep());
			preparedStatement.setInt(15, pedido.getId());
			
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
				compra = new Compra(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14), rs.getString(15), rs.getInt(16));
				
				map = new HashMap<>();
				map.put("com_id", compra.getId().toString());
				dao = new ProdutoDAO(connection);
				compra.setProdutos((List<Produto>) dao.consultar(map));
				
				dao = new CuponsCompraDAO(connection);
				compra.setCupons((List<CuponsCompra>) dao.consultar(map));
				
				dao = new CartoesCompraDAO(connection);
				compra.setCartoes((List<CartoesCompra>) dao.consultar(map));
				
				compras.add(compra);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return compras;
	}

}
