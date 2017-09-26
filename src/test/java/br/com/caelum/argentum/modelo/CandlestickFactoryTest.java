package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("42.3"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("39.8"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("45.0"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("16760.0"), candle.getVolume());
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(BigDecimal.ZERO, candle.getAbertura());
		Assert.assertEquals(BigDecimal.ZERO, candle.getFechamento());
		Assert.assertEquals(BigDecimal.ZERO, candle.getMinimo());
		Assert.assertEquals(BigDecimal.ZERO, candle.getMaximo());
		Assert.assertEquals(BigDecimal.ZERO, candle.getVolume());
	}
	
	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);
		CandlestickFactory fabrica = new CandlestickFactory();
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		Assert.assertEquals(new BigDecimal("40.5"), candle.getAbertura());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getFechamento());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMinimo());
		Assert.assertEquals(new BigDecimal("40.5"), candle.getMaximo());
		Assert.assertEquals(new BigDecimal("4050.0"), candle.getVolume());
	}
}
