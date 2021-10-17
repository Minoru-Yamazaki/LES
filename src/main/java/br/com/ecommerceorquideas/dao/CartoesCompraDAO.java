package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.CartoesCompra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CartoesCompraDAO implements IDAO{

	private static final String INSERT = "INSERT INTO cartoes_compra(numero, bandeira, total, car_id, com_id) VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE cartoes_compra SET numero=?, bandeira=?, total=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cartoes_compra WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public CartoesCompraDAO() {
	}

	public CartoesCompraDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		CartoesCompra cartoesCompra = (CartoesCompra) entidade;
		aviso = new Aviso();
		boolean commit = false;
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cartoesCompra.getNumero());
			preparedStatement.setString(2, cartoesCompra.getBandeira());
			preparedStatement.setDouble(3, cartoesCompra.getTotal());
			preparedStatement.setInt(4, cartoesCompra.getIdCartao());
			preparedStatement.setInt(5, cartoesCompra.getIdCompra());
						
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				cartoesCompra.setId(rs.getInt(1));
			}

			preparedStatement.close();
			
			if (commit) {
				connection.commit();
			}			
			aviso.addMensagem("cartão salvo com sucesso");

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
	public Object alterar(EntidadeDominio entidade) {
		CartoesCompra cartoesCompra = (CartoesCompra) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cartoesCompra.getNumero());
			preparedStatement.setString(2, cartoesCompra.getBandeira());
			preparedStatement.setDouble(3, cartoesCompra.getTotal());
			preparedStatement.setInt(4, cartoesCompra.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Cartão alterado com sucesso");

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
			aviso.addMensagem("Cartão excluido com sucesso");

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
		List<EntidadeDominio> cartoes = new ArrayList<>();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "cartoes_compra");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cartoes.add(new CartoesCompra(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cartoes;
	}

}
