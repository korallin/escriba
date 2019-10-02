package br.gov.spusc.escriba;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import br.gov.spusc.escriba.exception.ElementoNaoClicavelException;
import br.gov.spusc.escriba.exception.ElementoNaoEncontradoException;
import br.gov.spusc.escriba.pojo.SolicitacaoDocumentoSEI;

public class OperadorSEI extends OperadorSistema {

	private String url_login = "https://sei.fazenda.gov.br/sei/inicializar.php";

	void fazerLogin(CredencialAcesso credencial) throws ElementoNaoClicavelException {
		acessarUrl(url_login);
		// Find the text input element by its name
		WebElement campoLogin = driver.findElement(By.id("txtUsuario"));
		campoLogin.sendKeys(credencial.getLogin());

		// Find the text input element by its name
		WebElement campoSenha = driver.findElement(By.id("pwdSenha"));
		campoSenha.sendKeys(credencial.getSenha());

		// Find the text input element by its name
		WebElement botaoAcessar = driver.findElement(By.id("sbmLogin"));
		clicar(botaoAcessar);
	}

	void acessarProcesso(String nupSEI) {
		WebElement txtPesquisaRapida = driver.findElement(By.xpath("//input[@id = 'txtPesquisaRapida']"));
		txtPesquisaRapida.sendKeys(nupSEI);
		txtPesquisaRapida.sendKeys(Keys.RETURN);
	}

	public SolicitacaoDocumentoSEI inserirDocumento(SolicitacaoDocumentoSEI documentoACriar) throws Exception {

		Map<String, List<String>> mapaSubstituicoes = documentoACriar.getMapaDeMarcacoes();

		SolicitacaoDocumentoSEI documentoGerado = criarDocumentoParaEdicao(documentoACriar);

		// preencherNumeroDocumento(documentoGerado);
		Map<String, List<String>> mapaSubstituicoesOrdenado = obterMapaDeMarcacoesComNumeroDocumentoGerado(documentoACriar);
		substituirMarcacoesSimples(mapaSubstituicoesOrdenado);
		
		mapaSubstituicoesOrdenado.putAll(mapaSubstituicoes);
		substituirMarcacoesDeParagrafos(mapaSubstituicoesOrdenado);
		substituirMarcacoesSimples(mapaSubstituicoesOrdenado);
		
		documentoACriar.setIdentificacaoDocumentoGerado(obterIdentificacaoDocumentoCriado(documentoGerado));

		salvarDocumento();
		driver.close();
		driver.switchTo().window(janelaPrincipalDoNavegador);

		acessarProcesso(documentoACriar.getNupSei());

		return documentoACriar;

	}

	private SolicitacaoDocumentoSEI criarDocumentoParaEdicao(SolicitacaoDocumentoSEI documentoACriar) throws InterruptedException, ElementoNaoClicavelException {
		String tipoDocumento = documentoACriar.getTipoDocumento();
		Integer numeroDocumentoModelo = documentoACriar.getNumeroDocumentoModelo();

		Wait<WebDriver> wait = gerarWait(60, 3);

		// clica em inserir documento
		driver.switchTo().frame(OperadorSistema.encontrarElemento(wait, By.id("ifrVisualizacao")));
		WebElement btnIncluirDocumento = OperadorSistema.encontrarElemento(wait,
				By.xpath("//img[@alt = 'Incluir Documento']"));
		clicar(btnIncluirDocumento);

		// clica no tipo de documento
		WebElement btnOpcaoTipoDocumento = OperadorSistema.encontrarElemento(wait,
				By.xpath("//a[text() = '" + tipoDocumento + "']"));
		clicar(btnOpcaoTipoDocumento);

		// clica em documento modelo
		WebElement lblDocumentoModelo = OperadorSistema.encontrarElemento(wait,
				By.xpath("//label[contains(text(), 'Documento Modelo')]"));
		clicar(lblDocumentoModelo);

		// preenche o código do documento modelo
		WebElement txtCodigoDocumentoModelo = OperadorSistema.encontrarElemento(wait,
				By.xpath("//input[@id = 'txtProtocoloDocumentoTextoBase']"));
		txtCodigoDocumentoModelo.sendKeys(numeroDocumentoModelo.toString());

		// seleciona nivel de acesso do documento
		WebElement lblNivelAcessoPublico = OperadorSistema.encontrarElemento(wait,
				By.xpath("//label[@id = 'lblPublico']"));
		clicar(lblNivelAcessoPublico);

		// clica em confirmar dados
		WebElement btnConfirmarDados = OperadorSistema.encontrarElemento(wait, By.xpath("//button[@id = 'btnSalvar']"));
		clicar(btnConfirmarDados);

		// esperar abrir a janela popup
		do {
			TimeUnit.SECONDS.sleep(1);
		} while (driver.getWindowHandles().size() == 1);

		// abriu janela para editar o documento, então navega até a janela
		for (String tituloJanela : driver.getWindowHandles()) {
			driver.switchTo().window(tituloJanela);
		}
		
		documentoACriar.setNumeroDocumentoGerado(driver.getTitle().split(" - ")[1]);
		
		return documentoACriar;
	}


	private void substituirMarcacoesSimples(Map<String, List<String>> mapaSubstituicoes) throws Exception {
		
		/*
		WebElement webElementConteudo = obterWebElementContendoMarcacoes();
		if(webElementConteudo == null) {
			throw new Exception("Não foi encontrado elemento na página contendo marcação");
		}
		clicar(webElementConteudo);
		*/
		clicar(By.xpath("//*[contains(text(), '{{')]"));
		
		Wait<WebDriver> wait = gerarWait(60, 3);
		
	
		// clica no botão localizar
		driver.switchTo().defaultContent();
		clicar(By.xpath("//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[@title = 'Localizar']"));		
		
		// clica na aba substituir
		driver.switchTo().defaultContent();
		/*
		 * WebElement tabSubstituir =
		 * obterElementoClicavel(By.xpath("//a[contains(text(), 'Substituir')]"), 5, 1);
		 * TimeUnit.SECONDS.sleep(2); clicar(tabSubstituir);
		 */
		clicar(By.xpath("//table[contains(@class, 'cke_dialog') and not(contains(@style, 'visibility: hidden'))]//a[contains(@id, 'cke_replace') and contains(@class, 'cke_dialog_tab') and @title = 'Substituir']"));
		
	
		// repetir este pedaço para todos os textos a serem substituídos no documento
		for (String chave : mapaSubstituicoes.keySet()) {
			List<String> valorMarcacao = mapaSubstituicoes.get(chave);
			if (valorMarcacao == null || valorMarcacao.isEmpty() || valorMarcacao.size() > 1) {
				continue;
			}
			String textoSubstituto = valorMarcacao.get(0);
			chave = "{{" + chave + "}}";
	
			// preenche o texto a ser encontrado
			WebElement txtPesquisar = OperadorSistema.encontrarElemento(wait, By.xpath(
					"(//div[@role = 'tabpanel' and @name = 'replace' and not(contains(@style, 'display: none'))]//input[@type = 'text'])[1]"));
			txtPesquisar.clear();
			txtPesquisar.sendKeys(chave);
	
			// preenche o texto para substituição
			WebElement txtSubstituir = OperadorSistema.encontrarElemento(wait, By.xpath(
					"(//div[@role = 'tabpanel' and @name = 'replace' and not(contains(@style, 'display: none'))]//input[@type = 'text'])[2]"));
			txtSubstituir.clear();
			txtSubstituir.sendKeys(textoSubstituto);
	
			// clica em substituir tudo
			WebElement btnSubstituirTudo = OperadorSistema.encontrarElemento(wait,
					By.xpath("//div[@role = 'tabpanel' and @name = 'replace' and not(contains(@style, 'display: none'))]//a[@title = 'Substituir Tudo']"));
			clicar(btnSubstituirTudo);
	
			System.out.println("Resultado da substituição de '" + chave + "' por '" + textoSubstituto + "': "
					+ driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		}
	
		// driver.switchTo().frame(OperadorSistema.encontrarElemento(wait, By.xpath("//*[@id=\"ifrEditorSalvar\"]/iframe")));
	
		// clica em fechar
		WebElement btnFechar = OperadorSistema.encontrarElemento(wait, 
				By.xpath("//div[@role = 'dialog' and not(contains(@style, 'display: none'))]//span[text() = 'Fechar']"));
		clicar(btnFechar);
		
		// volta ao conteúdo default
		driver.switchTo().defaultContent();
	}

	

	private Map<String, List<String>> obterMapaDeMarcacoesComNumeroDocumentoGerado(SolicitacaoDocumentoSEI documentoACriar) throws Exception {
		Wait<WebDriver> wait2 = gerarWait(2, 1);
		
		WebElement iframe = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[@id=\"cke_3_contents\"]/iframe"));
		driver.switchTo().frame(iframe);
		
		// WebElement tituloDocumento = OperadorSistema.encontrarElemento(wait2, By.xpath("/html/body/table/tbody/tr/td/p"));
		// clicar(tituloDocumento);
		clicar(By.xpath("/html/body/table/tbody/tr/td/p"));

		Map<String, List<String>> mapaSubstituicoes = new LinkedHashMap<String, List<String>>();
		mapaSubstituicoes.put("Documento.Numero", new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add(documentoACriar.getNumeroDocumentoGerado());
			}
		});
		return mapaSubstituicoes;
	}

	@SuppressWarnings("serial")
	private void substituirMarcacoesDeParagrafos(Map<String, List<String>> mapaSubstituicoes) throws Exception {
		WebElement webElementConteudo = obterWebElementContendoMarcacoes();
		if(webElementConteudo == null) {
			throw new Exception("Não foi encontrado elemento na página contendo marcação");
		}
		clicar(webElementConteudo);
		
		// repetir este pedaço para todos os textos a serem substituídos no documento
		for (String chave : mapaSubstituicoes.keySet()) {
			List<String> valorMarcacao = mapaSubstituicoes.get(chave);
			if (valorMarcacao == null || valorMarcacao.size() < 2) {
				continue;
			}
			String marcacao = "{{" + chave + "}}";
			WebElement paragrafo = obterParagrafoMarcacao(marcacao);
			clicar(paragrafo);

			Iterator<String> paragrafosIterator = valorMarcacao.iterator();
			while (paragrafosIterator.hasNext()) {
				String conteudoParagrafo = paragrafosIterator.next();
				paragrafo.sendKeys(conteudoParagrafo);
				paragrafo.sendKeys(Keys.chord(Keys.ENTER));
				TimeUnit.SECONDS.sleep(1);
				paragrafo = paragrafo.findElement(By.xpath("following-sibling::p"));
			}
			paragrafo.sendKeys(Keys.chord(Keys.BACK_SPACE));
			mapaSubstituicoes.put(chave, new ArrayList<String>() {
				{
					add("");
				}
			});
		}

	}

	private void salvarDocumento() throws InterruptedException, ElementoNaoClicavelException {
		Wait<WebDriver> wait = gerarWait(60, 2);

		// procura o botão salvar, conferindo que ele esteja habilitado
		WebElement btnSalvar = OperadorSistema.encontrarElemento(wait, By.xpath(
				"//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and not(@aria-disabled)]"));
		clicar(btnSalvar);

		TimeUnit.MILLISECONDS.sleep(500);

		// aguarda até que o botão de salvar esteja novamente desabilitado para fechar a
		// janela
		OperadorSistema.encontrarElemento(wait, By.xpath(
				"//div[contains(@id, 'cke_txaEditor') and contains(@class, 'cke_detached') and not(contains(@style, 'display: none'))]//a[contains(@title, 'Salvar') and @aria-disabled]"));
	}

	private WebElement obterWebElementContendoMarcacoes() throws InterruptedException {
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

		WebElement webElementConteudo = null;

		for (WebElement frmIFrame : frmIFrames) {
			System.out.println("driver.switchTo().frame(frmIFrame);");
			System.out.println("frmIFrame: " + frmIFrame.toString());
			driver.switchTo().frame(frmIFrame);

			try {
				webElementConteudo = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[contains(text(), '{{')]"));
			} catch (TimeoutException e) {
				webElementConteudo = null;
			}

			if (webElementConteudo == null) {
				driver.switchTo().defaultContent();
			} else {
			 	break;
			}
		}
		return webElementConteudo;
	}

	private WebElement obterParagrafoMarcacao(String marcacao) {
		Wait<WebDriver> wait2 = gerarWait(2, 1);
		
		// encontrar o iframe que contem o corpo do documento a ser editado
		driver.switchTo().defaultContent();

		WebElement iframe = OperadorSistema.encontrarElemento(wait2, By.xpath("//*[@id=\"cke_4_contents\"]/iframe"));
		driver.switchTo().frame(iframe);

		List<WebElement> paragrafos = OperadorSistema.encontrarElementos(wait2, By.xpath("/html/body/p"));
		for (WebElement paragrafo : paragrafos) {
			if (paragrafo.getText().contentEquals(marcacao)) {
				return paragrafo;
			}
		}
		return null;
	}

	private String obterIdentificacaoDocumentoCriado(SolicitacaoDocumentoSEI documentoACriar) {
		return "[Falta implementar OperadorSEI.obterIdentificacaoDocumentoCriado()]";
		/*
		 * Wait<WebDriver> wait = gerarWait(60, 3); WebElement iframeNumeroDocumento =
		 * OperadorSistema.encontrarElemento(wait,
		 * By.xpath("//*[@id=\"cke_3_contents\"]/iframe")); return
		 * iframeNumeroDocumento.getText(); clicar(iframeNumeroDocumento);
		 * 
		 * List<WebElement> elementosDaAreaDeIdentificaoDocumento =
		 * iframeNumeroDocumento.findElements(
		 * By.className("Texto_Alinhado_Esquerda_Maiusculo")); for (WebElement elemento
		 * : elementosDaAreaDeIdentificaoDocumento) {
		 * System.out.println(elemento.getText()); if(elemento.getText() != null &&
		 * !elemento.getText().isBlank() &&
		 * elemento.getText().toUpperCase().startsWith(documentoACriar.getTipoDocumento(
		 * ).toUpperCase())) { return elemento.getText(); } } return null;
		 */
	}

}
