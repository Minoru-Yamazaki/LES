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

import br.com.ecommerceorquideas.model.Cartao;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Mensagem;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class MensagemDAO implements IDAO{
	
	private static final String INSERT = "INSERT INTO mensagens(mensagem, data, cli_id) VALUES(?,?,?)";
	private static final String UPDATE = "UPDATE mensagens SET mensagem=?, data=? WHERE id=?";
	private static final String DELETE = "DELETE FROM mensagens WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public MensagemDAO() {
	}

	public MensagemDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Mensagem mensagem = (Mensagem) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, mensagem.getMensagem());
			Timestamp time = new Timestamp(mensagem.getData().getTime());
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setInt(3, mensagem.getCliId());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				mensagem.setId(rs.getInt(1));
			}

			preparedStatement.close();
			connection.commit();
			
			aviso.addMensagem("Mensagem salva com sucesso");

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
		Mensagem mensagem = (Mensagem) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, mensagem.getMensagem());
			Timestamp time = new Timestamp(mensagem.getData().getTime());
			preparedStatement.setTimestamp(2, time);
			preparedStatement.setInt(3, mensagem.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Mensagem alterada com sucesso");

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
			aviso.addMensagem("Mensagem excluida com sucesso");

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
		List<EntidadeDominio> mensagens = new ArrayList<>();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "mensagens");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				mensagens.add(new Mensagem(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getInt(4)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mensagens;
	}

}
