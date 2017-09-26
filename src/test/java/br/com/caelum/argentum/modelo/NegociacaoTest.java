package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		
		Negociacao negociacao = new Negociacao(new BigDecimal(10), 5, c);
		c.set(Calendar.DAY_OF_MONTH, 25);
		
		negociacao.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		Assert.assertEquals(15, negociacao.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(new BigDecimal(30), 6, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComValorNulo() {
		Calendar c = Calendar.getInstance();
		new Negociacao(null, 6, c);
	}
}
