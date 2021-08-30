package br.com.ecommerceorquideas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerceorquideas.command.AlterarCommand;
import br.com.ecommerceorquideas.command.ConsultarCommand;
import br.com.ecommerceorquideas.command.ExcluirCommand;
import br.com.ecommerceorquideas.command.ICommand;
import br.com.ecommerceorquideas.command.SalvarCommand;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.viewhelper.CartaoVH;
import br.com.ecommerceorquideas.viewhelper.ClienteVH;
import br.com.ecommerceorquideas.viewhelper.EnderecoVH;
import br.com.ecommerceorquideas.viewhelper.IViewHelper;
import br.com.ecommerceorquideas.viewhelper.LoginVH;


@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class Controller {
	
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	public Controller() {
		carregarCommand();
		carregarVH();
	}
	
	//comando -> exemplo: salvar-cartao
	@PostMapping(value = "{comando}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody String body, @PathVariable("comando") String comando) {
		 
		String[] splitted= comando.split("-");
				
		IViewHelper vh = vhs.get(splitted[1]);
		
		EntidadeDominio entidade = vh.jsonToObject(body);
		
		ICommand command = commands.get(splitted[0]);

		return ResponseEntity.ok(command.execute(entidade));
	}
	
	private void carregarCommand() {
		commands = new HashMap<String, ICommand>();
		commands.put("salvar", new SalvarCommand());
		commands.put("excluir", new ExcluirCommand());
		commands.put("consultar", new ConsultarCommand());
		commands.put("alterar", new AlterarCommand());
	}
	
	private void carregarVH() {
		vhs = new HashMap<String, IViewHelper>();	
		vhs.put("cartao", new CartaoVH());
		vhs.put("cliente", new ClienteVH());
		vhs.put("endereco", new EnderecoVH());
		vhs.put("login", new LoginVH());
	}

}
