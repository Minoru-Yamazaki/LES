package br.com.ecommerceorquideas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartoesCompra extends EntidadeDominio{

	private String numero;
	private String bandeira;
	private Double total;
	private Integer idCartao;
	private Integer idCompra;
	
	public CartoesCompra(Integer id, String numero, String bandeira, Double total, Integer car_id, Integer com_id) {
		super(id);
		this.numero = numero;
		this.bandeira = bandeira;
		this.total = total;
		this.idCartao = car_id;
		this.idCompra = com_id;
	}
}
