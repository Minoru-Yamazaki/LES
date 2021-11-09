package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Troca;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class TrocaDAO implements IDAO{

	private static final String INSERT = "INSERT INTO trocas(pro_id, quantidade, qtde_max, preco, com_id) VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE trocas SET pro_id=?, quantidade=?, qtde_max=?, preco=? WHERE id=?";
	private static final String DELETE = "DELETE FROM trocas WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public TrocaDAO() {
	}

	public TrocaDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Troca troca = (Troca) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, troca.getProId());
			preparedStatement.setInt(2, troca.getQuantidade());
			preparedStatement.setInt(3, troca.getQtdeMax());
			preparedStatement.setDouble(4, troca.getPreco());
			preparedStatement.setInt(5, troca.getComId());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				troca.setId(rs.getInt(1));
			}

			preparedStatement.close();
			connection.commit();
			aviso.addMensagem("Salvo com sucesso");

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
		Troca troca = (Troca) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setInt(1, troca.getProId());
			preparedStatement.setInt(2, troca.getQuantidade());
			preparedStatement.setInt(3, troca.getQtdeMax());
			preparedStatement.setDouble(4, troca.getPreco());
			preparedStatement.setInt(5, troca.getId());

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();
			aviso.addMensagem("Alterado com sucesso");

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
		List<Object> trocas = new ArrayList<>();
		boolean transacao = false;
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				transacao = true;
			}

			String find = GeraSQL.select(map, "trocas");
			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				trocas.add(new Troca(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), false));
			}

			if(transacao) {
				preparedStatement.close();
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return trocas;
	}

}
