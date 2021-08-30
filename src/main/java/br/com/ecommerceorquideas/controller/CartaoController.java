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

import br.com.ecommerceorquideas.dao.CartaoDAO;
import br.com.ecommerceorquideas.domain.Cartao;

@CrossOrigin("*")
@RestController
@RequestMapping("/cartao")
public class CartaoController {
	/*
	private CartaoDAO dao;
	
	@PostMapping
    public void salvar(@RequestBody Cartao cartao) {
		dao = new CartaoDAO();
		dao.salvar(cartao);
    }
	//
	@GetMapping(value = "/{chaveValor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cartao>> consultar(@PathVariable("chaveValor") String chaveValor) {
		dao = new CartaoDAO();
		
		String[] splitted= chaveValor.split("-");
		HashMap<String, String> map = new HashMap<>(); 
		map.put(splitted[0], splitted[1]);
		
		List<Cartao> cartoes = (List<Cartao>) dao.consultar(map);
		return ResponseEntity.ok(cartoes);
	}
	
	@PutMapping//(value = "/{id}")
	public void atualizar(@RequestBody Cartao cartao) {
		dao = new CartaoDAO();
		dao.alterar(cartao);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		dao = new CartaoDAO();
		dao.excluir(id);
	}
	*/
}
