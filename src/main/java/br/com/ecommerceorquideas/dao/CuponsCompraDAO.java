package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.CuponsCompra;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CuponsCompraDAO implements IDAO{

	private static final String INSERT = "INSERT INTO cupons_compra(nome, descricao, tipo_cupom, valor, cup_id, com_id) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE cupons_compra SET nome=?, descricao=?, tipo_cupom=?, valor=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cupons_compra WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public CuponsCompraDAO() {
	}

	public CuponsCompraDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		CuponsCompra cuponsCompra = (CuponsCompra) entidade;
		aviso = new Aviso();
		boolean commit = false;
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cuponsCompra.getNome());
			preparedStatement.setString(2, cuponsCompra.getDescricao());
			preparedStatement.setString(3, cuponsCompra.getTipoCupom());
			preparedStatement.setDouble(4, cuponsCompra.getValor());
			preparedStatement.setInt(5, cuponsCompra.getIdCupom());
			preparedStatement.setInt(6, cuponsCompra.getIdCompra());
						
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				cuponsCompra.setId(rs.getInt(1));
			}

			preparedStatement.close();
			
			if (commit) {
				connection.commit();
			}			
			aviso.addMensagem("Cupom salvo com sucesso");

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
		CuponsCompra cuponsCompra = (CuponsCompra) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cuponsCompra.getNome());
			preparedStatement.setString(2, cuponsCompra.getDescricao());
			preparedStatement.setString(3, cuponsCompra.getTipoCupom());
			preparedStatement.setDouble(4, cuponsCompra.getValor());
			preparedStatement.setInt(5, cuponsCompra.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Cupom alterado com sucesso");

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
			aviso.addMensagem("Cupom excluido com sucesso");

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
		List<EntidadeDominio> cupons = new ArrayList<>();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "cupons_compra");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cupons.add(new CuponsCompra(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getDouble(5), rs.getInt(6), rs.getInt(7)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cupons;
	}

}
