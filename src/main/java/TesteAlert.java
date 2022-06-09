import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteAlert{	

	@Test
	public void deveInteragirComAlertSimples(){
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String Texto = alert.getText();
		Assert.assertEquals("Alert Simples", Texto);
		alert.accept();
		
		driver.findElement(By.id("ElementosForm:nome")).sendKeys(Texto);
		
	}
}