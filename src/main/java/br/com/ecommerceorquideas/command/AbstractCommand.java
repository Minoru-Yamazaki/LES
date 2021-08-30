package br.com.ecommerceorquideas.command;

import br.com.ecommerceorquideas.facade.Facade;
import br.com.ecommerceorquideas.facade.IFacade;

public abstract class AbstractCommand implements ICommand{
	
	protected IFacade fachada = new Facade();
}
