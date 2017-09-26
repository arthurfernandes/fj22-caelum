package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	public List<Negociacao> carrega(InputStream is){
		XStream xs = new XStream(new DomDriver());
		xs.alias("negociacao", Negociacao.class);
		return (List<Negociacao>) xs.fromXML(is);
	}
}
