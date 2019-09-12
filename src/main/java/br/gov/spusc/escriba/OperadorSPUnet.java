package br.gov.spusc.escriba;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Level;

import org.json.JSONArray;
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

import br.gov.spusc.escriba.pojo.ObjetivoRequerimento;
import br.gov.spusc.escriba.pojo.Requerente;
import br.gov.spusc.escriba.pojo.Requerimento;



public class OperadorSPUnet {
	
	
	private WebDriver driver;
	private String url_login = "http://spunet.planejamento.gov.br";

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
	
	void fazerLogin(CredencialAcesso credencial) {
		acessarUrl(url_login);
		// Find the text input element by its name
		WebElement campoLogin = driver.findElement(By.id("username"));
		campoLogin.sendKeys(credencial.getLogin());

		// Find the text input element by its name
		WebElement campoSenha = driver.findElement(By.id("password"));
		campoSenha.sendKeys(credencial.getSenha());

		// Find the text input element by its name
		WebElement botaoAcessar = driver
				.findElement(By.cssSelector("#loginForm > div:nth-child(5) > div.form-actions > button"));
		botaoAcessar.click();

		@SuppressWarnings("unused")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
	}
	
	Requerimento obterRequerimentoPorNumeroAtendimento(String numeroAtendimento) {		
		JSONObject json =  obterJsonObject(montarURLRequerimentoPorNumeroAtendimento(numeroAtendimento));
		JSONArray jsonResposta = (JSONArray) json.get("resposta");
		JSONObject jsonRespostaMap = (JSONObject) jsonResposta.get(0);
		JSONArray jsonContent = (JSONArray) jsonRespostaMap.get("content");
		Requerimento requerimento = montarRequerimento((JSONObject) jsonContent.get(0));
		
		String urlRequerimento = montarURLRequerimento(requerimento);		
		JSONObject jsonRequerimento = (JSONObject) ((JSONArray) obterJsonObject(urlRequerimento).get("resposta")).get(0);
		
		JSONObject jsonRequerente = (JSONObject) jsonRequerimento.get("requerente");
		Requerente requerente = montarRequerente(jsonRequerente);
		requerimento.setRequerente(requerente);
		
		JSONObject jsonObjetivoRequerimento = (JSONObject) jsonRequerimento.get("objetivoRequerimento");
		ObjetivoRequerimento objetivoRequerimento = montarObjetivoRequerimento(jsonObjetivoRequerimento);
		requerimento.setObjetivoRequerimento(objetivoRequerimento);
		
		return requerimento;
	}

	private ObjetivoRequerimento montarObjetivoRequerimento(JSONObject json) {
		ObjetivoRequerimento objetivoRequerimento = new ObjetivoRequerimento();
		if(!json.isNull("descricao")) {
			objetivoRequerimento.setDescricao(json.getString("descricao"));			
		}
		
		JSONObject tipoObjetivoRequerimento = (JSONObject) json.get("tipoObjetivoRequerimento");
		objetivoRequerimento.setId(tipoObjetivoRequerimento.getInt("id"));
		objetivoRequerimento.setObjetivo(tipoObjetivoRequerimento.getString("descricao"));
		
		return objetivoRequerimento;
	}

	private Requerimento montarRequerimento(JSONObject json) {
		Requerimento requerimento = new Requerimento();
		requerimento.setNumeroAtendimento((String) json.get("numeroAtendimento"));
		requerimento.setCodigoIdentificacao((String) json.get("codigoIdentificacao"));
		requerimento.setNomeRequerente((String) json.get("nomeRequerente"));
		requerimento.setProcedimentoFormatado((String) json.get("procedimentoFormatado"));
		requerimento.setRequerente(new Requerente());		
		return requerimento;
	}
	
	private Requerente montarRequerente(JSONObject json) {
		Requerente requerente = new Requerente();
		requerente.setNome(json.getString("nome"));
		requerente.setCpfCnpj((String) json.get("cpfCnpj"));
		return requerente;
	}

	private String montarURLRequerimentoPorNumeroAtendimento(String numeroAtendimento) {
		return "http://spunet.planejamento.gov.br/servicos/api/requerimento/search?limit=5&offset=0&tipoPesquisaAtendimento=RECEBIDOS_NO_PERIODO&nuAtendimento=" + encodeValue(numeroAtendimento);
	}
	
	private String montarURLRequerimento(Requerimento requerimento) {
		// Requerimento
		// http://spunet.planejamento.gov.br//servicos/api/requerimento/codigoIdentificacao/53b2b01b43879786f322fc6837f411f2cbb5833726c5ee44f99c381258d0e7a1?cacheBuster=1563902929842
		StringBuilder _url = new StringBuilder("http://spunet.planejamento.gov.br//servicos/api/requerimento/codigoIdentificacao/");
		_url.append(requerimento.getCodigoIdentificacao());
		return _url.toString();
	}
	
	void acessarUrl(String url) {
		this.driver.get(url);
		@SuppressWarnings("unused")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
	}
	
	private JSONObject obterJsonObject(String url) {
		driver.get(url);
		WebElement preJson = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(
						By.cssSelector("body > pre")));
		JSONObject json = new JSONObject(preJson.getText());
		return json;
	}
	
	private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

}
