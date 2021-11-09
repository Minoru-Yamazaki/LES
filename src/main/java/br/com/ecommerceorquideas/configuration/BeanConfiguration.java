package br.com.ecommerceorquideas.configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfiguration {

	@Bean
	public ObjectMapper jsonDataMapper() {

		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getDefault());
		mapper.setDateFormat(sdf);
		
		return mapper;
	}
}
