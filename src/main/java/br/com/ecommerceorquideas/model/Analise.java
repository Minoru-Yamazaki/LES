package br.com.ecommerceorquideas.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analise extends EntidadeDominio{

	private Date dataInicial;
	private Date dataFinal;	
}


