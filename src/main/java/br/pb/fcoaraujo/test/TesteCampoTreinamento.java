package br.pb.fcoaraujo.test;
import static br.pb.fcoaraujo.core.DriverFactory.getDriver;
import static br.pb.fcoaraujo.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.pb.fcoaraujo.core.DSL;

public class TesteCampoTreinamento {
	
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
	
//	@Test
	public void testeTextField(){
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));		
	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Francisco");
		Assert.assertEquals("Francisco", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Araujo");
		Assert.assertEquals("Araujo", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea(){
		dsl.escrever("elementosForm:sugestoes", "teste\n\naslakflfdg\nUltima linha");		
		Assert.assertEquals("teste\n\naslakflfdg\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	
	@Test
	public void deveInteragirComRadioButton() {
		getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());
	}	
	
	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));		
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Superior"));		
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());	
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Futebol", "O que eh esporte?")));
	}
	
	@Test
	public void deveinteragirComBotoes(){
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}	

	@Test
	public void deveinteragirComLinks(){
		dsl.clicarlink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("Resultado"));		
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){				
//		System.out.println(driver.findElement(By.tagName("body")).getText());
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				dsl.obterTexto(By.className("facilAchar")));		
	}	


	@Test
	public void testeJavaScript(){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		WebElement element = getDriver().findElement(By.id("ElementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Francisco", "Botao", "elementosForm:tableUsuarios");
	}
	
}

