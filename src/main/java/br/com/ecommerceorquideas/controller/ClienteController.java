package br.com.ecommerceorquideas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.facade.Facade;

@CrossOrigin("*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	/*
	private Facade facade = new Facade();
	
	@PostMapping
    public void salvar(@RequestBody Cliente cliente) {
		facade.salvar(cliente);
    }
	
	@PutMapping(value = "/consultar")
	public List<Cliente> consultar(@RequestBody Cliente cliente) {
		
		List<Cliente> clientes = (List<Cliente>) facade.consultar(cliente);
		return clientes;
	}
	
	@PutMapping(value = "/atualizar")
	public void atualizar(@RequestBody Cliente cliente) {
		facade.alterar(cliente);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		
		//facade.excluir(id);
	}
	*/
}
