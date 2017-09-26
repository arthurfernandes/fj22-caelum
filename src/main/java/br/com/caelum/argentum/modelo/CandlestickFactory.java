package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {

	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes){
		return null;
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
