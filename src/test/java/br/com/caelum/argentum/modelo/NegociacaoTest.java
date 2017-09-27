package br.com.caelum.argentum.modelo;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia(){
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao negociacao = new Negociacao(new BigDecimal("5.6"), 100, agora);
		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}
	
	@Test
	public void comHorariosDiferentesEhDoMesmoDia(){
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("7.9"), 200, manha);
		assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar diaAgosto = new GregorianCalendar(2011, 8, 20, 8, 30);
		Calendar diaMaio = new GregorianCalendar(2011, 5, 20, 8, 30);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("12.56"), 1, diaAgosto);
		assertFalse(negociacao.isMesmoDia(diaMaio));
	}
	
	@Test
	public void mesmoDiaMesmoMesAnosDiferentesNaoSaoMesmoDia() {
		Calendar dia2011 = new GregorianCalendar(2011, 10, 25, 13, 23);
		Calendar dia1011 = new GregorianCalendar(1011, 10, 25, 13, 23);
		
		Negociacao negociacao = new Negociacao(new BigDecimal("190"), 180, dia2011);
		assertFalse(negociacao.isMesmoDia(dia1011));
	}
}
