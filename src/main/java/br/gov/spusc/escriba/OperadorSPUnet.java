package br.gov.spusc.escriba;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import br.gov.spusc.escriba.pojo.Imovel;
import br.gov.spusc.escriba.pojo.ObjetivoRequerimento;
import br.gov.spusc.escriba.pojo.Requerente;
import br.gov.spusc.escriba.pojo.RequerimentoSPUnet;



public class OperadorSPUnet extends OperadorSistema {
	
	
	private String url_login = "http://spunet.planejamento.gov.br";
	
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
	
	RequerimentoSPUnet obterRequerimentoPorNumeroAtendimento(String numeroAtendimento) {		
		JSONObject json =  obterJsonObject(montarURLRequerimentoPorNumeroAtendimento(numeroAtendimento));
		JSONArray jsonResposta = (JSONArray) json.get("resposta");
		JSONObject jsonRespostaMap = (JSONObject) jsonResposta.get(0);
		JSONArray jsonContent = (JSONArray) jsonRespostaMap.get("content");
		RequerimentoSPUnet requerimento = montarRequerimento((JSONObject) jsonContent.get(0));
		
		String urlRequerimento = montarURLRequerimento(requerimento);		
		JSONObject jsonRequerimento = (JSONObject) ((JSONArray) obterJsonObject(urlRequerimento).get("resposta")).get(0);
		
		JSONObject jsonRequerente = (JSONObject) jsonRequerimento.get("requerente");
		Requerente requerente = montarRequerente(jsonRequerente);
		requerimento.setRequerente(requerente);
		
		JSONObject jsonImovel = (JSONObject) jsonRequerimento.get("imovel");
		Imovel imovel = montarImovel(jsonImovel);
		requerimento.setImovel(imovel);
		
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

	private RequerimentoSPUnet montarRequerimento(JSONObject json) {
		RequerimentoSPUnet requerimento = new RequerimentoSPUnet();
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

	private Imovel montarImovel(JSONObject json) {
		Imovel imovel = new Imovel();
		
		if(!json.isNull("nuMatriculaTransicao")) {
			imovel.setNuMatriculaTransicao(json.getString("nuMatriculaTransicao"));			
		}
		if(!json.isNull("classificacaoImovel")) {
			imovel.setClassificacaoImovel(json.getString("classificacaoImovel"));			
		}
		if(!json.isNull("nuProcesso")) {
			imovel.setNuProcesso(json.getString("nuProcesso"));			
		}
		if(!json.isNull("nuInscricao")) {
			imovel.setNuInscricao(json.getString("nuInscricao"));			
		}
		if(!json.isNull("tipoImovel")) {
			imovel.setTipoImovel(json.getString("tipoImovel"));			
		}
		if(!json.isNull("areaTerreno")) {
			double areaTerreno = json.getDouble("areaTerreno");			
			imovel.setAreaTerreno(areaTerreno);
		}
		
		if(!json.isNull("endereco")) {
			JSONObject jsonEndereco = (JSONObject) json.get("endereco");
			if(!jsonEndereco.isNull("cep")) {
				imovel.setCep(jsonEndereco.getString("cep"));				
			}
			if(!jsonEndereco.isNull("tipoLogradouro")) {
				imovel.setTipoLogradouro(jsonEndereco.getString("tipoLogradouro"));				
			}
			if(!jsonEndereco.isNull("logradouro")) {
				imovel.setLogradouro(jsonEndereco.getString("logradouro"));				
			}
			if(!jsonEndereco.isNull("numero")) {
				imovel.setNumero(jsonEndereco.getString("numero"));				
			}
			if(!jsonEndereco.isNull("complemento")) {
				imovel.setComplemento(jsonEndereco.getString("complemento"));				
			}
			if(!jsonEndereco.isNull("municipio")) {
				imovel.setMunicipio(jsonEndereco.getString("municipio"));				
			}
			if(!jsonEndereco.isNull("bairro")) {
				imovel.setBairro(jsonEndereco.getString("bairro"));				
			}
			if(!jsonEndereco.isNull("uf")) {
				imovel.setUf(jsonEndereco.getString("uf"));							
			}
		}
		
		
		return imovel;
	}

	private String montarURLRequerimentoPorNumeroAtendimento(String numeroAtendimento) {
		return "http://spunet.planejamento.gov.br/servicos/api/requerimento/search?limit=5&offset=0&tipoPesquisaAtendimento=RECEBIDOS_NO_PERIODO&nuAtendimento=" + encodeValue(numeroAtendimento);
	}
	
	private String montarURLRequerimento(RequerimentoSPUnet requerimento) {
		// Requerimento
		// http://spunet.planejamento.gov.br//servicos/api/requerimento/codigoIdentificacao/53b2b01b43879786f322fc6837f411f2cbb5833726c5ee44f99c381258d0e7a1?cacheBuster=1563902929842
		StringBuilder _url = new StringBuilder("http://spunet.planejamento.gov.br//servicos/api/requerimento/codigoIdentificacao/");
		_url.append(requerimento.getCodigoIdentificacao());
		return _url.toString();
	}

}
