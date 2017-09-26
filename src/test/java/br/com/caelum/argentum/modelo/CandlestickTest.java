package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		Calendar c = Calendar.getInstance();
		BigDecimal abertura = new BigDecimal("12");
		BigDecimal fechamento = new BigDecimal("20");
		BigDecimal minimo = new BigDecimal("10");
		BigDecimal maximo = new BigDecimal("5");
		BigDecimal volume = new BigDecimal("1200");
		
		new Candlestick(abertura, fechamento, minimo, maximo, volume, c);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaCandleComValorNulo() {
		BigDecimal abertura = new BigDecimal("12");
		BigDecimal fechamento = new BigDecimal("8");
		BigDecimal minimo = new BigDecimal("8");
		BigDecimal maximo = new BigDecimal("5");
		BigDecimal volume = new BigDecimal("1200");
		
		new Candlestick(abertura, fechamento, minimo, maximo, volume, null);
	}
}
