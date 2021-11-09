package br.com.ecommerceorquideas.model.dashboard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChartOptions {

	private Titulo title;
	private Titulo subtitle;
	@JsonProperty("yAxis")
	private EixoY yAxis;
	@JsonProperty("xAxis")
	private EixoX xAxis;
	private Legend legend;
	private List<Serie> series;
	private Responsive responsive;
	
	
}
