package br.com.caelum.argentum.ws;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;

public class ClientWebService {
	private static final String URL_WEB_SERVICE = "http://argentumws.caelum.com.br/negociacoes";
	
	public List<Negociacao> getNegociacoes(){
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL_WEB_SERVICE);
			connection = (HttpURLConnection) url.openConnection();
			
			return new LeitorXML().carrega(connection.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if(connection != null) {
				connection.disconnect();
			}
		}
	}
}
