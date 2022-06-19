import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrime {
	
	private WebDriver driver;
	private DSL dsl;
		
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
		dsl = new DSL(driver);
		
	}
	
	@After
	public void finaliza() {
//		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=8e54a");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt312:console:0']/../..//span"));
		Assert.assertTrue(dsl.isCheckMarcado("j_idt312:console:0"));
		
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isCheckMarcado("j_idt312:console:1"));		
	}

	@Test
	public void deveInteragirComSelectPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=c967a");
		dsl.clicarRadio(By.xpath("//*[@id='j_idt311:option_input']/../..//span"));
//		driver.findElement(By.id("j_idt311:option_3")).click();
		dsl.clicarRadio(By.xpath("//div[@id='j_idt311:option_panel']//div//ul//li[@id='j_idt311:option_3']"));
		Assert.assertEquals("Option3", dsl.obterTexto("j_idt311:option_label"));
	}

}
