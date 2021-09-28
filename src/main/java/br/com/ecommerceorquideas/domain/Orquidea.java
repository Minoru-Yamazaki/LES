package br.com.ecommerceorquideas.domain;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Orquidea extends EntidadeDominio{

	private Integer ativo;
	private String categoriaAtivacao;
	private String justificativaAtivacao;
	private String categoriaInativacao;
	private String justificativaInativacao;
	private Integer quantidade;
	private String cor;
	private Double valorCusto;
	private Double valorVenda;
	private String codigoBarras;
	private String descricao;
	private String nome;
	private String genero;
	private String tipo;
	private String tamanho;
	private String clima;
	private String sombreamento;
	private String tempoFloracao;
	private String umidadeAmbiente;
	private String fornecedor;
	private Date dataHora;
	private String usuario;
	private Integer idPrecificacao;
	private List<Imagem> imagens;
	
	public Orquidea(Integer id, Integer ativo, String categoriaAtivacao, String justificativaAtivacao,
			String categoriaInativacao, String justificativaInativacao, Integer quantidade,
			String cor, Double valorCusto, Double valorVenda, String codigoBarras, String descricao,
			String nome, String genero, String tipo, String tamanho, String clima,
			String sombreamento, String tempoFloracao, String umidadeAmbiente, String fornecedor,
			Date dataHora, String usuario, Integer idPrecificacao){
		super(id);
		this.ativo = ativo;
		this.categoriaAtivacao = categoriaAtivacao;
		this.justificativaAtivacao = justificativaAtivacao;
		this.categoriaInativacao = categoriaInativacao;
		this.justificativaInativacao = justificativaInativacao;
		this.quantidade = quantidade;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.codigoBarras = codigoBarras;
		this.descricao = descricao;
		this.nome = nome;
		this.genero = genero;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.clima = clima;
		this.sombreamento = sombreamento;
		this.tempoFloracao = tempoFloracao;
		this.umidadeAmbiente = umidadeAmbiente;
		this.fornecedor = fornecedor;
		this.dataHora = dataHora;
		this.usuario = usuario;
		this.idPrecificacao = idPrecificacao;
	}
}
