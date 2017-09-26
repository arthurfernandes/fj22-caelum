package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TesteCandlestickFactory {
	
	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao negociacao1 = new Negociacao(new BigDecimal("40.5"), 100, hoje);
		Negociacao negociacao2 = new Negociacao(new BigDecimal("45.0"), 100, hoje);
		Negociacao negociacao3 = new Negociacao(new BigDecimal("39.8"), 100, hoje);
		Negociacao negociacao4 = new Negociacao(new BigDecimal("42.3"), 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);
		
		CandlestickFactory fabrica = new CandlestickFactory();

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		System.out.println(candle);

	}
}
