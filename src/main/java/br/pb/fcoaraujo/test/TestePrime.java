package br.pb.fcoaraujo.test;
import static br.pb.fcoaraujo.core.DriverFactory.getDriver;
import static br.pb.fcoaraujo.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.pb.fcoaraujo.core.DSL;
import br.pb.fcoaraujo.core.DriverFactory;

public class TestePrime {
	
	private DSL dsl;
		
	@Before
	public void inicializa() {
		dsl = new DSL();
		
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=8e54a");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
		Assert.assertTrue(dsl.isCheckMarcado("j_idt312:console:0"));
		
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isCheckMarcado("j_idt312:console:1"));		
	}

	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=c967a");
		dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_input']/../..//span"));
//		driver.findElement(By.id("j_idt311:option_3")).click();
		dsl.clicarRadio(By.xpath("//div[@id='j_idt311:option_panel']//div//ul//li[@id='j_idt311:option_3']"));
		Assert.assertEquals("Option3", dsl.obterTexto("j_idt311:option_label"));
	}

}
