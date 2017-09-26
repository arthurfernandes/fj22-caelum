package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public class CandleBuilder {

	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private Calendar data;
	
	public static Candlestick getIdentity(Calendar data) {
		return new CandleBuilder().comAbertura(BigDecimal.ZERO).comFechamento(BigDecimal.ZERO)
				.comMinimo(BigDecimal.ZERO).comMaximo(BigDecimal.ZERO)
				.comVolume(BigDecimal.ZERO).comData(data).geraCandle();
	}
	
	public CandleBuilder() {
	}
	
	public CandleBuilder comAbertura(BigDecimal abertura){
		this.abertura = abertura;
		return this;
	}
	
	public CandleBuilder comFechamento(BigDecimal fechamento){
		this.fechamento = fechamento;
		return this;
	}
	
	public CandleBuilder comMinimo(BigDecimal minimo){
		this.minimo = minimo;
		return this;
	}
	
	public CandleBuilder comMaximo(BigDecimal maximo){
		this.maximo = maximo;
		return this;
	}
	
	public CandleBuilder comVolume(BigDecimal volume){
		this.volume = volume;
		return this;
	}
	
	public CandleBuilder comData(Calendar data){
		this.data = data;
		return this;
	}
	
	public Candlestick geraCandle() {
		return new Candlestick(this.abertura, this.fechamento, this.minimo, 
				this.maximo, this.volume, this.data);
	}
}
