package br.gov.spusc.escriba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import br.gov.spusc.escriba.pojo.SolicitacaoDocumentoSEI;

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

	public SolicitacaoDocumentoSEI inserirDocumento(SolicitacaoDocumentoSEI documentoACriar) throws InterruptedException {
		
		String tipoDocumento = documentoACriar.getTipoDocumento();
		Integer numeroDocumentoModelo = documentoACriar.getNumeroDocumentoModelo();
		Map<String, List<String>> mapaSubstituicoes = documentoACriar.getMapaDeMarcacoes();
		

		Wait<WebDriver> wait = gerarWait(60, 3);

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
		
		documentoACriar.setNumeroDocumentoGerado(driver.getTitle().split(" - ")[1]);
		substituirParagrafos(mapaSubstituicoes);
		substituirTextosSimples(mapaSubstituicoes);		
		documentoACriar.setIdentificacaoDocumentoGerado(obterIdentificacaoDocumentoCriado(documentoACriar));
		driver.close();
		driver.switchTo().window(janelaPrincipalDoNavegador);
		
		acessarProcesso(documentoACriar.getNupSei());
		
		return documentoACriar;

	}
	
	@SuppressWarnings("serial")
	private void substituirParagrafos(Map<String, List<String>> mapaSubstituicoes) throws InterruptedException {
		// repetir este pedaço para todos os textos a serem substituídos no documento
		for (String chave : mapaSubstituicoes.keySet()) {
			List<String> valorMarcacao = mapaSubstituicoes.get(chave);
			if(valorMarcacao == null || valorMarcacao.size() < 2) {
				continue;				
			}
			String marcacao = "{{" + chave + "}}";
			WebElement paragrafo = obterParagrafoMarcacao(marcacao);
			paragrafo.click();
			
			paragrafo.sendKeys(Keys.chord(Keys.HOME));
			paragrafo.click();
			
			Iterator<String> paragrafosIterator = valorMarcacao.iterator();
			while(paragrafosIterator.hasNext()) {
				String conteudoParagrafo = paragrafosIterator.next();
				paragrafo.sendKeys(conteudoParagrafo);
				paragrafo.sendKeys(Keys.chord(Keys.ENTER));
				TimeUnit.SECONDS.sleep(1);				
				paragrafo = paragrafo.findElement(By.xpath("following-sibling::p"));
			}
			mapaSubstituicoes.put(chave, new ArrayList<String>() {{ add(""); }});
		}
		
	}

	private void substituirTextosSimples(Map<String, List<String>> mapaSubstituicoes) throws InterruptedException {
		Wait<WebDriver> wait = gerarWait(60, 3);
		
		WebElement welAutor = obterIframeConteudo();
		welAutor.click();
		
		// volta ao conteúdo default
		driver.switchTo().defaultContent();
		
		// clica no botão localizar
		WebElement btnLocalizar = OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[@title = 'Localizar']"));
		//WebElement btnLocalizar = OperadorSistema.encontrarElemento(wait, By.id("cke_198"));
		System.out.println(btnLocalizar);
		btnLocalizar.click();
		
		TimeUnit.SECONDS.sleep(2);

		// clica na aba substituir
		WebElement tabSubstituir = OperadorSistema.encontrarElemento(wait, By.xpath("//a[contains(text(), 'Substituir')]"));
		TimeUnit.SECONDS.sleep(1);
		tabSubstituir.click();
		
		// repetir este pedaço para todos os textos a serem substituídos no documento
		for (String chave : mapaSubstituicoes.keySet()) {
			List<String> valorMarcacao = mapaSubstituicoes.get(chave);
			if(valorMarcacao == null || valorMarcacao.isEmpty() || valorMarcacao.size() > 1) {
				continue;
			}
			String textoSubstituto = valorMarcacao.get(0);
			chave = "{{" + chave + "}}";
			
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
		
		// driver.switchTo().frame(OperadorSistema.encontrarElemento(wait, By.xpath("//*[@id=\"ifrEditorSalvar\"]/iframe")));		

		// clica em fechar
		WebElement btnFechar = OperadorSistema.encontrarElemento(wait, By.xpath("//span[text() = 'Fechar']"));
		btnFechar.click();
		
		// procura o botão salvar, conferindo que ele esteja habilitado
		WebElement btnSalvar = OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and not(@aria-disabled)]"));
		btnSalvar.click();
		
		TimeUnit.MILLISECONDS.sleep(500);
		
		// aguarda até que o botão de salvar esteja novamente desabilitado para fechar a janela
		OperadorSistema.encontrarElemento(wait, By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and @aria-disabled]"));
		
	}

	private WebElement obterIframeConteudo() throws InterruptedException {
		Wait<WebDriver> wait = gerarWait(60, 2);
		Wait<WebDriver> wait2 = gerarWait(2, 1);
		// encontrar o iframe que contem o corpo do documento a ser editado
		driver.switchTo().defaultContent();
		List<WebElement> frmIFrames = null;
		int espera = 15;
		do {
			TimeUnit.SECONDS.sleep(2);
			frmIFrames = OperadorSistema.encontrarElementos(wait, By.tagName("iframe"));
		} while (--espera >= 0 && (frmIFrames == null || frmIFrames.size() <= 1));

		WebElement welAutor = null;
		
		for (WebElement frmIFrame : frmIFrames) {
			driver.switchTo().frame(frmIFrame);

			try {
				welAutor = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[contains(text(), '{{')]"));
			} catch (Exception e) {
				welAutor = null;
			}

			if (welAutor != null) {
				break;
			} else {
				driver.switchTo().defaultContent();
			}
		}
		return welAutor;
	}
	
	private WebElement obterParagrafoMarcacao(String marcacao) {
		Wait<WebDriver> wait2 = gerarWait(2, 1);
		
		WebElement iframe = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[@id=\"cke_4_contents\"]/iframe"));
		driver.switchTo().frame(iframe);
		
		List<WebElement> paragrafos = OperadorSistema.encontrarElementos(wait2, By.xpath("/html/body/p"));		
		for (WebElement paragrafo : paragrafos) {
			System.out.println(paragrafo.getText());
			if(paragrafo.getText().contentEquals(marcacao)) {
				return paragrafo;
			}
		}
		return null;
	}

	private String obterIdentificacaoDocumentoCriado(SolicitacaoDocumentoSEI documentoACriar) {
		return "[Falta implementar OperadorSEI.obterIdentificacaoDocumentoCriado()]";
		/*
		Wait<WebDriver> wait = gerarWait(60, 3);
		WebElement iframeNumeroDocumento = OperadorSistema.encontrarElemento(wait, By.xpath("//*[@id=\"cke_3_contents\"]/iframe"));
		return iframeNumeroDocumento.getText();
		iframeNumeroDocumento.click();
		
		List<WebElement> elementosDaAreaDeIdentificaoDocumento = iframeNumeroDocumento.findElements(
				By.className("Texto_Alinhado_Esquerda_Maiusculo"));
		for (WebElement elemento : elementosDaAreaDeIdentificaoDocumento) {
			System.out.println(elemento.getText());			
			if(elemento.getText() != null 
					&& !elemento.getText().isBlank() 
					&& elemento.getText().toUpperCase().startsWith(documentoACriar.getTipoDocumento().toUpperCase())) {
				return elemento.getText();
			}
		}
		return null;
		*/
	}

}
