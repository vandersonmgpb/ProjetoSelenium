package br.pb.fcoaraujo.test;
import static br.pb.fcoaraujo.core.DriverFactory.getDriver;
import static br.pb.fcoaraujo.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.pb.fcoaraujo.core.DSL;


	public class TesteAlert {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveInteragirComAlertSimples() {		
		dsl.clicarBotao("alert");
		String Texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples",Texto);
				
		dsl.escrever("elementosForm:nome", Texto);
		
	}
	
	@Test
	public void deveInteragirComAlertConfirm() {		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
				
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());		
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTexto());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}	
}
