package br.com.ecommerceorquideas.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rule {

	private Condition condition;
	private ChartOptionsRule chartOptions;
}
