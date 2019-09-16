package br.gov.spusc.escriba;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
	}

	void acessarProcesso(String nupSEI) {
		WebElement txtPesquisaRapida = driver.findElement(By.xpath("//input[@id = 'txtPesquisaRapida']"));
		txtPesquisaRapida.sendKeys(nupSEI);
		txtPesquisaRapida.sendKeys(Keys.RETURN);
	}

	public String incluirDocumento(String tipoDocumento, Integer numeroDocumentoModelo, Map<String, String> mapaSubstituicoes) throws InterruptedException {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);

		// clica em inserir documento
		driver.switchTo().frame(OperadorSistema.encontrarElemento(wait, By.id("ifrVisualizacao")));
		WebElement btnIncluirDocumento = OperadorSistema.encontrarElemento(wait,
				By.xpath("//img[@alt = 'Incluir Documento']"));
		btnIncluirDocumento.click();

		// clica no tipo de documento
		WebElement btnOpcaoTipoDocumento = OperadorSistema.encontrarElemento(wait,
				By.xpath("//a[text() = '" + tipoDocumento + "']"));
		btnOpcaoTipoDocumento.click();

		// clica em documento modelo
		WebElement lblDocumentoModelo = OperadorSistema.encontrarElemento(wait,
				By.xpath("//label[contains(text(), 'Documento Modelo')]"));
		lblDocumentoModelo.click();

		// preenche o código do documento modelo
		WebElement txtCodigoDocumentoModelo = OperadorSistema.encontrarElemento(wait,
				By.xpath("//input[@id = 'txtProtocoloDocumentoTextoBase']"));
		txtCodigoDocumentoModelo.sendKeys(numeroDocumentoModelo.toString());

		// seleciona nivel de acesso do documento
		WebElement lblNivelAcessoPublico = OperadorSistema.encontrarElemento(wait,
				By.xpath("//label[@id = 'lblPublico']"));
		lblNivelAcessoPublico.click();

		// clica em confirmar dados
		WebElement btnConfirmarDados = OperadorSistema.encontrarElemento(wait, By.xpath("//button[@id = 'btnSalvar']"));
		btnConfirmarDados.click();

		// esperar abrir a janela popup
		do {
			TimeUnit.SECONDS.sleep(1);
		} while (driver.getWindowHandles().size() == 1);

		// abriu janela para editar o documento, então navega até a janela
		for (String tituloJanela : driver.getWindowHandles()) {
			driver.switchTo().window(tituloJanela);
		}

		String numeroDocumentoGerado = driver.getTitle().split(" - ")[1];

		editarDocumento(mapaSubstituicoes);

		return numeroDocumentoGerado;

	}

	private void editarDocumento(Map<String, String> mapaSubstituicoes) throws InterruptedException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		
		
		
		
		// encontrar o iframe que contem o corpo do documento a ser editado
		WebElement welAutor = OperadorSistema.encontrarElemento(wait, By.xpath("//*[@id=\"cke_5_contents\"]/iframe"));
		/*
		driver.switchTo().defaultContent();
		List<WebElement> frmIFrames = null;
		int espera = 15;
		do {
			TimeUnit.SECONDS.sleep(2);
			frmIFrames = OperadorSistema.encontrarElementos(wait, By.tagName("iframe"));
		} while (--espera >= 0 && (frmIFrames == null || frmIFrames.size() <= 1));
		

		// este wait não deve ter seu tempo alterado, pois é usado apenas para buscar a
		// variante <autor> no frame correto; se for aumentada, pode ter impacto
		// negativo em função da quantidade de frames que pode conter um documento
		Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(2))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		WebElement welAutor = null;

		for (WebElement frmIFrame : frmIFrames) {
			driver.switchTo().frame(frmIFrame);

			try {
				// welAutor = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[contains(text(), '<autor>')]"));
				welAutor = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[contains(text(), '<autor>')]"));
			} catch (Exception e) {
				welAutor = null;
			}

			if (welAutor != null) {
				break;
			} else {
				driver.switchTo().defaultContent();
			}
		}
		 */
		
		// clica no primeiro paragrafo encontrado no iframe
		TimeUnit.SECONDS.sleep(5);
		System.out.println(welAutor);
		welAutor.click();
		
		// volta ao conteúdo default
		driver.switchTo().defaultContent();
		
		// clica no botão localizar
		WebElement btnLocalizar = OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[@title = 'Localizar']"));
		//WebElement btnLocalizar = OperadorSistema.encontrarElemento(wait, By.id("cke_198"));
		System.out.println(btnLocalizar);
		btnLocalizar.click();

		// clica na aba substituir
		WebElement tabSubstituir = OperadorSistema.encontrarElemento(wait, By.xpath("//a[contains(text(), 'Substituir')]"));
		TimeUnit.SECONDS.sleep(1);
		tabSubstituir.click();
		
		// repetir este pedaço para todos os textos a serem substituídos no documento
		for (String chave : mapaSubstituicoes.keySet()) {
			String textoSubstituto = mapaSubstituicoes.get(chave);
			chave = "{{" + chave + "}}";

			// appendLogArea(logArea, "Substituindo '" + chave + "' por '" + textoSubstituto + "'");
			
			// preenche o texto a ser encontrado
			WebElement txtPesquisar = OperadorSistema.encontrarElemento(wait, By.xpath("(//div[@role = 'tabpanel' and not(contains(@style, 'display: none'))]//input[@type = 'text'])[1]"));
			txtPesquisar.clear();
			txtPesquisar.sendKeys(chave);
			
			// preenche o texto para substituição
			WebElement txtSubstituir = OperadorSistema.encontrarElemento(wait, By.xpath("(//div[@role = 'tabpanel' and not(contains(@style, 'display: none'))]//input[@type = 'text'])[2]"));
			txtSubstituir.clear();
			txtSubstituir.sendKeys(textoSubstituto);
			
			// clica em substituir tudo
			WebElement btnSubstituirTudo = OperadorSistema.encontrarElemento(wait, By.xpath("//a[@title = 'Substituir Tudo']"));
			btnSubstituirTudo.click();
			
			// clica em ok na mensagem apresentada
			// MyUtils.appendLogArea(logArea, "Resultado da substituição de '" + chave + "' por '" + textoSubstituto + "': " + driver.switchTo().alert().getText());
			System.out.println("Resultado da substituição de '" + chave + "' por '" + textoSubstituto + "': " + driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}		

		// clica em fechar
		WebElement btnFechar = OperadorSistema.encontrarElemento(wait, By.xpath("//span[text() = 'Fechar']"));
		btnFechar.click();
		
		// procura o botão salvar, conferindo que ele esteja habilitado
		WebElement btnSalvar = OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and not(@aria-disabled)]"));
		btnSalvar.click();
		
		TimeUnit.MILLISECONDS.sleep(500);
		
		// aguarda até que o botão de salvar esteja novamente desabilitado para fechar a janela
		OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and @aria-disabled]"));
		
		driver.close();
		driver.switchTo().window(janelaPrincipal);
	}

}
