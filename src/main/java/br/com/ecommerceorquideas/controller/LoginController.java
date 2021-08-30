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

import br.com.ecommerceorquideas.dao.LoginDAO;
import br.com.ecommerceorquideas.domain.Login;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
	/*
	private LoginDAO dao;
	
	@PostMapping
    public void salvar(@RequestBody Login login) {
		dao = new LoginDAO();
		dao.salvar(login);
    }
	//
	@GetMapping(value = "/{chaveValor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Login>> consultar(@PathVariable("chaveValor") String chaveValor) {
		dao = new LoginDAO();
		
		String[] splitted= chaveValor.split("-");
		HashMap<String, String> map = new HashMap<>(); 
		map.put(splitted[0], splitted[1]);
		
		List<Login> login = (List<Login>) dao.consultar(map);
		return ResponseEntity.ok(login);
	}
	
	@PutMapping//(value = "/{id}")
	public void atualizar(@RequestBody Login login) {
		dao = new LoginDAO();
		dao.alterar(login);
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		dao = new LoginDAO();
		dao.excluir(id);
	}
	*/
}
