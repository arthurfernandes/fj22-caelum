package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Candlestick {
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;
	
	public Candlestick(BigDecimal abertura, BigDecimal fechamento,
			BigDecimal minimo, BigDecimal maximo, BigDecimal volume,
			Calendar data) {
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("[Abertura %s, Fechamento %s, Minima %s, Maxima %s, Volume %s, Data %s",
				this.getAbertura().toString(),
				this.getFechamento().toString(),
				this.getMinimo().toString(),
				this.getMaximo().toString(),
				this.getVolume().toString(),
				this.getData().getTime());
	}
	
	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public boolean isAlta() {
		return this.abertura.compareTo(this.fechamento) < 0;
	}
	
	public boolean isBaixa() {
		return this.abertura.compareTo(this.fechamento) > 0;  
	}
}
