package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickFactoryTest {
	@Test
	public void paraNegociacoesTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_YEAR, 1);
		
		Negociacao negociacao5 = new Negociacao(new BigDecimal("48.8"), 100, amanha);
		Negociacao negociacao6 = new Negociacao(new BigDecimal("49.3"), 100, amanha);
		
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_YEAR, 1);
		
		Negociacao negociacao7 = new Negociacao(new BigDecimal("51.8"), 100, depois);
		Negociacao negociacao8 = new Negociacao(new BigDecimal("51.9"), 100, depois);
	
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3,
				negociacao4, negociacao5, negociacao5, negociacao6, negociacao7, negociacao8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles =  fabrica.constroiCandles(negociacoes);
		
		assertEquals(3, candles.size());
		assertEquals(new BigDecimal("40.5"), candles.get(0).getAbertura());
		assertEquals(new BigDecimal("42.3"), candles.get(0).getFechamento());
		assertEquals(new BigDecimal("48.8"), candles.get(1).getAbertura());
		assertEquals(new BigDecimal("49.3"), candles.get(1).getFechamento());
		assertEquals(new BigDecimal("51.8"), candles.get(2).getAbertura());
		assertEquals(new BigDecimal("51.9"), candles.get(2).getFechamento());	
	}
	
	@Test
	public void aceitaNegociacoesForaDeOrdemEmTresDiasDistintos() {
		
		Calendar hoje = Calendar.getInstance();
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_YEAR, 1);
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_YEAR, 1);
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, depois);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, amanha);
		Negociacao negociacao5 = new Negociacao(new BigDecimal("48.8"), 100, amanha);
		Negociacao negociacao6 = new Negociacao(new BigDecimal("49.3"), 100, hoje);
		Negociacao negociacao7 = new Negociacao(new BigDecimal("51.8"), 100, depois);
		Negociacao negociacao8 = new Negociacao(new BigDecimal("51.9"), 100, hoje);
	
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3,
				negociacao4, negociacao5, negociacao5, negociacao6, negociacao7, negociacao8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles =  fabrica.constroiCandles(negociacoes);
		
		assertEquals(3, candles.size());
		assertEquals(new BigDecimal("40.5"), candles.get(0).getAbertura());
		assertEquals(new BigDecimal("51.9"), candles.get(0).getFechamento());
		assertEquals(new BigDecimal("42.3"), candles.get(1).getAbertura());
		assertEquals(new BigDecimal("48.8"), candles.get(1).getFechamento());
		assertEquals(new BigDecimal("39.8"), candles.get(2).getAbertura());
		assertEquals(new BigDecimal("51.8"), candles.get(2).getFechamento());	
	}
	
	@Test
	public void paraNenhumaNegociacaoGeraListaCandlesVazia(){
		List<Negociacao> negociacoes = new ArrayList<>();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles =  fabrica.constroiCandles(negociacoes);
		
		assertEquals(candles.size(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void paraNegociacoesNullArgumentoInvalido(){
		List<Negociacao> negociacoes = null;
		
		CandlestickFactory fabrica = new CandlestickFactory();
		fabrica.constroiCandles(negociacoes);
	}
	
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
