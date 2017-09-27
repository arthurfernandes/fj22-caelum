package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TestaCandleStickFactorySemNegociacoes {
	
	public static void main (String[] args) {
		Calendar hoje = Calendar.getInstance();
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		System.out.println(candle);
	}
}
