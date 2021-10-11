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

import br.com.ecommerceorquideas.model.Cupom;
import br.com.ecommerceorquideas.model.CupomAdmin;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CupomAdminDAO implements IDAO{
	
	private static final String INSERT = "INSERT INTO adm_cupons(nome, codigo, descricao, tipo_cupom, valor, validade, adm_id) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE adm_cupons SET nome=?, codigo=?, descricao=?, tipo_cupom=?, valor=?, validade=? WHERE id=?";
	private static final String DELETE = "DELETE FROM adm_cupons WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public CupomAdminDAO() {
	}

	public CupomAdminDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		CupomAdmin cupom = (CupomAdmin) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cupom.getNome());
			preparedStatement.setString(2, cupom.getCodigo());
			preparedStatement.setString(3, cupom.getDescricao());
			preparedStatement.setString(4, cupom.getTipoCupom());
			preparedStatement.setDouble(5, cupom.getValor());
			Timestamp time = new Timestamp(cupom.getValidade().getTime());
			preparedStatement.setTimestamp(6, time);	
			preparedStatement.setInt(7, cupom.getAdmId());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				cupom.setId(rs.getInt(1));
			}

			preparedStatement.close();
			connection.commit();
			
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
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		CupomAdmin cupom = (CupomAdmin) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cupom.getNome());
			preparedStatement.setString(2, cupom.getCodigo());
			preparedStatement.setString(3, cupom.getDescricao());
			preparedStatement.setString(4, cupom.getTipoCupom());
			preparedStatement.setDouble(5, cupom.getValor());
			Timestamp time = new Timestamp(cupom.getValidade().getTime());
			preparedStatement.setTimestamp(6, time);	
			preparedStatement.setInt(7, cupom.getId());

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
			String find = GeraSQL.select(map, "adm_cupons");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cupons.add(new CupomAdmin(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDate(7),
						rs.getInt(8)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cupons;
	}

}
