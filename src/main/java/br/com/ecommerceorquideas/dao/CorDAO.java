package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.Cor;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Genero;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CorDAO implements IDAO{
	
	private static final String INSERT = "INSERT INTO cores(cor) VALUES(?)";
	private static final String UPDATE = "UPDATE cores SET cor=?, ativo=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cores WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public CorDAO() {
	}

	public CorDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Cor cor = (Cor) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cor.getCor());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				cor.setId(rs.getInt(1));
			}

			preparedStatement.close();
			connection.commit();
			
			aviso.addMensagem("Cor salva com sucesso");

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
		Cor cor = (Cor) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cor.getCor());
			preparedStatement.setInt(2, cor.getAtivo());
			preparedStatement.setInt(3, cor.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Cor alterada com sucesso");

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
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(DELETE);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();
			aviso.addMensagem("Excluido com sucesso");

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
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object consultar(HashMap<String, String> map) {
		List<EntidadeDominio> cores = new ArrayList<>();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "cores");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cores.add(new Cor(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cores;
	}

}
