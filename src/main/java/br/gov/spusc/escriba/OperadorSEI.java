package br.gov.spusc.escriba;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



public class OperadorSEI extends OperadorSistema {
	
	private String url_login = "https://sei.fazenda.gov.br/sei/inicializar.php";
	
	void fazerLogin(CredencialAcesso credencial) {
		acessarUrl(url_login);
		// Find the text input element by its name
		WebElement campoLogin = driver.findElement(By.id("txtUsuario"));
		campoLogin.sendKeys(credencial.getLogin());

		// Find the text input element by its name
		WebElement campoSenha = driver.findElement(By.id("pwdSenha"));
		campoSenha.sendKeys(credencial.getSenha());

		// Find the text input element by its name
		WebElement botaoAcessar = driver.findElement(By.id("sbmLogin"));
		botaoAcessar.click();

		@SuppressWarnings("unused")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
	}

}
