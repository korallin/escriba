package br.gov.spusc.escriba;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Level;

import org.json.JSONObject;
import org.openqa.selenium.By;
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

public class OperadorSistema {
	
	protected WebDriver driver;
	
	void inicializarDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");		
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

}
