package br.gov.spusc.escriba;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.gov.spusc.escriba.exception.ElementoNaoClicavelException;
import br.gov.spusc.escriba.exception.ElementoNaoEncontradoException;

public class OperadorSistema {
	
	protected WebDriver driver;
	protected String janelaPrincipalDoNavegador;
	protected WebElement ultimoElementoClicado;
	
	void inicializarDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver77.exe");		
		ChromeOptions options = new ChromeOptions();
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        
		WebDriver driver = new ChromeDriver(cap);
		this.driver = driver;
	}
	
	void acessarUrl(String url) {
		this.driver.get(url);
		@SuppressWarnings("unused")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
	}
	
	protected JSONObject obterJsonObject(String url) {
		driver.get(url);
		WebElement preJson = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector("body > pre")));
		JSONObject json = new JSONObject(preJson.getText());
		return json;
	}
	
	protected void encerrarDriver() {
		this.driver.close();
	}
	
	protected static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
	
	public void fecharPopup() {
		String primeiraJanela = "";
		for (String tituloJanela : driver.getWindowHandles()) {
			if (!primeiraJanela.equalsIgnoreCase("")) {
				driver.switchTo().window(tituloJanela);
				driver.close();
			} else {
				primeiraJanela = tituloJanela;
			}
		}

		driver.switchTo().window(primeiraJanela);
		janelaPrincipalDoNavegador = driver.getWindowHandle();
	}

	public static WebElement encontrarElemento(Wait<WebDriver> wait, By by) {
		return wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver t) {
				try {
					WebElement element = t.findElement(by);
					if (element == null) {
						System.out.println("Elemento não encontrado: " + by.toString());
					}					
					return element;
				} catch (NoSuchElementException e) {
					return null;
				}				
			}
		});
	}
	
	public static List<WebElement> encontrarElementos(Wait<WebDriver> wait, By by) {
		return wait.until(new Function<WebDriver, List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver t) {
				List<WebElement> elements = t.findElements(by);
				if (elements == null) {
					System.out.println("Nenhum elemento não encontrado: " + by.toString());
				}
				return elements;
			}
		});
	}
	
	protected Wait<WebDriver> gerarWait(int segundos, int intervaloTentativas) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(segundos))
				.pollingEvery(Duration.ofSeconds(intervaloTentativas)).ignoring(NoSuchElementException.class);
		return wait;
	}
	
	protected void clicar(WebElement elementoAClicar) throws ElementoNaoClicavelException {
		try {
			elementoAClicar.click();
			this.ultimoElementoClicado = elementoAClicar;
		} catch(ElementNotInteractableException e) {
			throw new ElementoNaoClicavelException(elementoAClicar);
		}
	}
	protected void clicar(By xpath) throws ElementoNaoClicavelException {
		clicar(xpath, 30, 1);
	}
	
	protected void clicar(By xpath, int timeout, int intervalo) throws ElementoNaoClicavelException {
		try {
			WebElement element = obterElementoClicavel(xpath, timeout, intervalo);			
			clicar(element);
		} catch (TimeoutException e) {
			throw new ElementoNaoEncontradoException(xpath.toString());
		}
	}

	protected WebElement obterElementoClicavel(By xpath, int timeout, int intervalo) {
		Wait<WebDriver> wait = gerarWait(timeout, intervalo);			
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(xpath));
		return element;
	}
}
