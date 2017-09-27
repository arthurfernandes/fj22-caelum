package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class CandlestickFactory {

	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes){
		if (negociacoes == null)
			throw new IllegalArgumentException("Negociacoes nao pode ser null");
		if (negociacoes.isEmpty())
			return new ArrayList<>();
		
		negociacoes.sort(new Comparator<Negociacao>(){
			
			@Override
			public int compare(Negociacao o1, Negociacao o2) {
				return o1.isMesmoDia(o2.getData()) ? 0 :
					o1.getData().compareTo(o2.getData());
			}
		});
		
		List<Candlestick> candles = new ArrayList<>();
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		Calendar diaCorrente = negociacoes.get(0).getData();
		
		for (Negociacao n : negociacoes){
			if (n.isMesmoDia(diaCorrente)){
				negociacoesDoDia.add(n);
			}
			else{
				candles.add(constroiCandleParaData(diaCorrente, negociacoesDoDia));
				diaCorrente = n.getData();
				negociacoesDoDia = new ArrayList<>();
				negociacoesDoDia.add(n);
			}
		}
		if (!negociacoesDoDia.isEmpty()){
			candles.add(constroiCandleParaData(diaCorrente, negociacoesDoDia));
		}
		
		return candles;
	}
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		if (negociacoes.isEmpty()){
			return CandleBuilder.getIdentity(data);
		}
		
		BigDecimal abertura = negociacoes.get(0).getPreco();
		BigDecimal fechamento = negociacoes.get(negociacoes.size() - 1).getPreco();
		
		BigDecimal minimo = negociacoes
				.stream()
				.map( n -> n.getPreco())
				.reduce(
					abertura,
					(a,b) -> a.min(b));
		
		BigDecimal maximo = negociacoes
				.stream()
				.map( n -> n.getPreco())
				.reduce(
					abertura,
					(a,b) -> a.max(b));
		
		BigDecimal volume = negociacoes
				.stream()
				.map ( n -> n.getVolume())
				.reduce(
					BigDecimal.ZERO,
					(a,b) -> a.add(b));

		return new CandleBuilder().comAbertura(abertura).comFechamento(fechamento)
				.comMinimo(minimo).comMaximo(maximo).comVolume(volume).comData(data)
				.geraCandle();
	}
}
