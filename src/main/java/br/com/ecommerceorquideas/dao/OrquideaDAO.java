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

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.Imagem;
import br.com.ecommerceorquideas.model.Orquidea;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class OrquideaDAO implements IDAO{

	private static final String INSERT = "INSERT INTO orquideas(ativo, categoria_ativacao, justificativa_ativacao, categoria_inativacao, justificativa_inativacao, quantidade, cor, valor_custo, valor_venda, codigo_barras, descricao, nome, genero, tipo, tamanho, clima, sombreamento, tempo_floracao, umidade_ambiente, fornecedor, data_hora, usuario, prc_id ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE orquideas SET ativo=?, categoria_ativacao=?, justificativa_ativacao=?, categoria_inativacao=?, justificativa_inativacao=?, quantidade=?, cor=?, valor_custo=?, valor_venda=?, codigo_barras=?, descricao=?, nome=?, genero=?, tipo=?, tamanho=?, clima=?, sombreamento=?, tempo_floracao=?, umidade_ambiente=?, fornecedor=?, data_hora=?, usuario=?, prc_id=? WHERE id=?";
	private static final String DELETE = "DELETE FROM orquideas WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public OrquideaDAO() {
	}

	public OrquideaDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Orquidea orquidea = (Orquidea) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, orquidea.getAtivo());
			preparedStatement.setString(2, orquidea.getCategoriaAtivacao());
			preparedStatement.setString(3, orquidea.getJustificativaAtivacao());
			preparedStatement.setString(4, orquidea.getCategoriaInativacao());
			preparedStatement.setString(5, orquidea.getJustificativaInativacao());
			preparedStatement.setInt(6, orquidea.getQuantidade());
			preparedStatement.setString(7, orquidea.getCor());
			preparedStatement.setDouble(8, orquidea.getValorCusto());
			preparedStatement.setDouble(9, orquidea.getValorVenda());
			preparedStatement.setString(10, orquidea.getCodigoBarras());
			preparedStatement.setString(11, orquidea.getDescricao());
			preparedStatement.setString(12, orquidea.getNome());
			preparedStatement.setString(13, orquidea.getGenero());
			preparedStatement.setString(14, orquidea.getTipo());
			preparedStatement.setString(15, orquidea.getTamanho());
			preparedStatement.setString(16, orquidea.getClima());
			preparedStatement.setString(17, orquidea.getSombreamento());
			preparedStatement.setString(18, orquidea.getTempoFloracao());
			preparedStatement.setString(19, orquidea.getUmidadeAmbiente());
			preparedStatement.setString(20, orquidea.getFornecedor());
			Timestamp time = new Timestamp(orquidea.getDataHora().getTime());
			preparedStatement.setTimestamp(21, time);
			preparedStatement.setString(22, orquidea.getUsuario());
			preparedStatement.setInt(23, orquidea.getIdPrecificacao());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				orquidea.setId(rs.getInt(1));
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
		Orquidea orquidea = (Orquidea) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setInt(1, orquidea.getAtivo());
			preparedStatement.setString(2, orquidea.getCategoriaAtivacao());
			preparedStatement.setString(3, orquidea.getJustificativaAtivacao());
			preparedStatement.setString(4, orquidea.getCategoriaInativacao());
			preparedStatement.setString(5, orquidea.getJustificativaInativacao());
			preparedStatement.setInt(6, orquidea.getQuantidade());
			preparedStatement.setString(7, orquidea.getCor());
			preparedStatement.setDouble(8, orquidea.getValorCusto());
			preparedStatement.setDouble(9, orquidea.getValorVenda());
			preparedStatement.setString(10, orquidea.getCodigoBarras());
			preparedStatement.setString(11, orquidea.getDescricao());
			preparedStatement.setString(12, orquidea.getNome());
			preparedStatement.setString(13, orquidea.getGenero());
			preparedStatement.setString(14, orquidea.getTipo());
			preparedStatement.setString(15, orquidea.getTamanho());
			preparedStatement.setString(16, orquidea.getClima());
			preparedStatement.setString(17, orquidea.getSombreamento());
			preparedStatement.setString(18, orquidea.getTempoFloracao());
			preparedStatement.setString(19, orquidea.getUmidadeAmbiente());
			preparedStatement.setString(20, orquidea.getFornecedor());
			Timestamp time = new Timestamp(orquidea.getDataHora().getTime());
			preparedStatement.setTimestamp(21, time);
			preparedStatement.setString(22, orquidea.getUsuario());
			preparedStatement.setInt(23, orquidea.getIdPrecificacao());
			
			preparedStatement.setInt(24, orquidea.getId());

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
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			exluirDados(connection, id);//************************************************

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
		List<Object> orquideas = new ArrayList<>();
		Orquidea orquidea;
		IDAO dao;

		try {
			connection = Conexao.getConnection();

			String find = GeraSQL.select(map, "orquideas");
			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				orquidea = new Orquidea(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getDouble(9),
						rs.getDouble(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19),
						rs.getString(20), rs.getString(21), rs.getDate(22), rs.getString(23), rs.getInt(24));

				map = new HashMap<>();
				map.put("orq_id", orquidea.getId().toString());
				dao = new ImagemDAO(connection);
				orquidea.setImagens((List<Imagem>) dao.consultar(map));
				
				orquideas.add(orquidea);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return orquideas;
	}
	
	private void exluirDados(Connection connection, Integer id) throws SQLException {
		/*
		try {
			
			IDAO dao = new LoginDAO(connection);
			dao.excluir(id);

			HashMap<String, String> map = new HashMap<>();
			map.put("cli_id", id.toString());
			dao = new EnderecoDAO(connection);
			List<Endereco> enderecos = (List<Endereco>) dao.consultar(map);
			for (EntidadeDominio endereco : enderecos) {
				dao.excluir(endereco.getId());
			}

			dao = new CartaoDAO(connection);
			List<Cartao> cartoes = (List<Cartao>) dao.consultar(map);
			for (EntidadeDominio cartao : cartoes) {
				dao.excluir(cartao.getId());
			}
		} catch (Exception e) {
			throw e;
		}
		*/
		
	}

}
