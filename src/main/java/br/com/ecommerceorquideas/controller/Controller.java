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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerceorquideas.command.AlterarCommand;
import br.com.ecommerceorquideas.command.ConsultarCommand;
import br.com.ecommerceorquideas.command.ExcluirCommand;
import br.com.ecommerceorquideas.command.ICommand;
import br.com.ecommerceorquideas.command.SalvarCommand;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.viewhelper.AdministradorVH;
import br.com.ecommerceorquideas.viewhelper.AnaliseVH;
import br.com.ecommerceorquideas.viewhelper.CartaoVH;
import br.com.ecommerceorquideas.viewhelper.ClienteVH;
import br.com.ecommerceorquideas.viewhelper.CompraVH;
import br.com.ecommerceorquideas.viewhelper.CorVH;
import br.com.ecommerceorquideas.viewhelper.CupomAdminVH;
import br.com.ecommerceorquideas.viewhelper.CupomVH;
import br.com.ecommerceorquideas.viewhelper.EnderecoVH;
import br.com.ecommerceorquideas.viewhelper.GeneroVH;
import br.com.ecommerceorquideas.viewhelper.IViewHelper;
import br.com.ecommerceorquideas.viewhelper.ImagemVH;
import br.com.ecommerceorquideas.viewhelper.LoginAdminVH;
import br.com.ecommerceorquideas.viewhelper.LoginVH;
import br.com.ecommerceorquideas.viewhelper.MensagemVH;
import br.com.ecommerceorquideas.viewhelper.OrquideaVH;
import br.com.ecommerceorquideas.viewhelper.PrecificacaoVH;
import br.com.ecommerceorquideas.viewhelper.ProdutoVH;
import br.com.ecommerceorquideas.viewhelper.TrocaVH;


@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class Controller {
	
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	private ObjectMapper jsonDataMapper;

	public Controller(ObjectMapper jsonDataMapper) {
		this.jsonDataMapper = jsonDataMapper;
		carregarCommand();
		carregarVH();
	}
	
	//comando -> exemplo: salvar-cartao
	@PostMapping(value = "{comando}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crud(@RequestBody String body, @PathVariable("comando") String comando) {
		 
		String[] splitted= comando.split("-");
				
		IViewHelper vh = vhs.get(splitted[1]);
		
		EntidadeDominio entidade = vh.jsonToEntidade(body);
		
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
		vhs.put("cliente", new ClienteVH(jsonDataMapper));
		vhs.put("endereco", new EnderecoVH());
		vhs.put("login", new LoginVH());
		vhs.put("cupom", new CupomVH());
		vhs.put("compra", new CompraVH(jsonDataMapper));
		vhs.put("produto", new ProdutoVH());
		vhs.put("orquidea", new OrquideaVH());
		vhs.put("imagem", new ImagemVH());
		vhs.put("cor", new CorVH());
		vhs.put("genero", new GeneroVH());
		vhs.put("admcupom", new CupomAdminVH());
		vhs.put("admlogin", new LoginAdminVH());
		vhs.put("administrador", new AdministradorVH());
		vhs.put("troca", new TrocaVH());
		vhs.put("mensagem", new MensagemVH());
		vhs.put("analise", new AnaliseVH(jsonDataMapper));
		vhs.put("precificacao", new PrecificacaoVH());
	}
}
