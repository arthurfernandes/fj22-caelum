package br.com.caelum.argentum.reader;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list> "
							+ "<negociacao>"
							+ "   <preco>43.5</preco>"
							+ "   <quantidade>1000</quantidade>"
							+ "   <data>"
							+ "       <time>1322233344455</time>"
							+ "   </data>"
							+ "</negociacao>" +
							"</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(new BigDecimal("43.5"), negociacoes.get(0).getPreco());
		assertEquals(1000, negociacoes.get(0).getQuantidade());
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(1322233344455L));
		assertEquals(c, negociacoes.get(0).getData());
	}
	
	
}
