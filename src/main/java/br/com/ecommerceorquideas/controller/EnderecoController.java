package br.com.ecommerceorquideas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerceorquideas.dao.EnderecoDAO;
import br.com.ecommerceorquideas.domain.Endereco;

@CrossOrigin("*")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	/*
	private EnderecoDAO dao;
	
	@PostMapping
    public void salvar(@RequestBody Endereco endereco) {
		dao = new EnderecoDAO();
		dao.salvar(endereco);
    }
	//
	@GetMapping(value = "/{chaveValor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Endereco>> consultar(@PathVariable("chaveValor") String chaveValor) {
		dao = new EnderecoDAO();
		
		String[] splitted= chaveValor.split("-");
		HashMap<String, String> map = new HashMap<>(); 
		map.put(splitted[0], splitted[1]);
		
		List<Endereco> enderecos = (List<Endereco>) dao.consultar(map);
		return ResponseEntity.ok(enderecos);
	}
	
	@PutMapping//(value = "/{id}")
	public void atualizar(@RequestBody Endereco endereco) {
		dao = new EnderecoDAO();
		dao.alterar(endereco);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		dao = new EnderecoDAO();
		dao.excluir(id);
	}
	*/
}
