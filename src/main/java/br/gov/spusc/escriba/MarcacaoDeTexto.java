package br.gov.spusc.escriba;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarcacaoDeTexto {
	
	private static final String SEPARADOR_CHAVE = ".";

	public Map<String, List<String>> preencherMapaDeMarcacoesValores() {
		return preencherMapaDeMarcacoesValores(null, null);
	}
	
	private Map<String, List<String>> preencherMapaDeMarcacoesValores(Map<String, List<String>> mapa, String prefixoSuperClasse) {
		
		if(mapa == null) {
			mapa = new LinkedHashMap<String, List<String>>();
		}
		
		if(prefixoSuperClasse == null || prefixoSuperClasse.isBlank()) {
			prefixoSuperClasse = "";
		} else {
			prefixoSuperClasse += SEPARADOR_CHAVE;
		}
		
		String prefixoClasse = prefixoSuperClasse + this.getClass().getSimpleName();
		
		try {
			for(PropertyDescriptor propertieDescriptor : Introspector.getBeanInfo(getClass()).getPropertyDescriptors()) {
				String propriedade = propertieDescriptor.getName();
				if(propriedade != "class") {
					propriedade = propriedade.substring(0, 1).toUpperCase() + propriedade.substring(1);
					
					String chave = prefixoClasse + SEPARADOR_CHAVE +  propriedade;
					Object valor = propertieDescriptor.getReadMethod().invoke(this);
					
					if(valor == null) {
						valor = "";
					}
										
					if(valor instanceof MarcacaoDeTexto) {
						mapa = ((MarcacaoDeTexto) valor).preencherMapaDeMarcacoesValores(mapa, prefixoClasse);
					} else {
						List<String> valorList = new ArrayList<String>();
						if(valor instanceof String) {
							valorList.add((String) valor);							
						} else if (propertieDescriptor.getPropertyType().getName() == "double") {
							valorList.add(valor.toString());
						}
						mapa.put(chave, valorList);
					}
					
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mapa;
	}

}
