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
		if (data == null){
			throw new IllegalArgumentException("A data nao pode ser nula");
		}
		if (abertura.compareTo(BigDecimal.ZERO) < 0 ||
				fechamento.compareTo(BigDecimal.ZERO) < 0 ||
				minimo.compareTo(BigDecimal.ZERO) < 0 || 
				maximo.compareTo(BigDecimal.ZERO) < 0 ||
				volume.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Nao pode atribuir valores negativos para abertura, fechamento, minimo, maximo e volume");
		}
		if (minimo.compareTo(abertura) > 0 || minimo.compareTo(fechamento) > 0 || minimo.compareTo(maximo) > 0){
			throw new IllegalArgumentException("O valor minimo nao pode ser maior que abertura, fechamento e minimo");
		}
		if (maximo.compareTo(abertura) < 0 || maximo.compareTo(fechamento) < 0 || maximo.compareTo(minimo) < 0){
			throw new IllegalArgumentException("O valor maximo nao pode ser menor que abertura, fechamento e minimo");
		}
		
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = (Calendar) data.clone();
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
		return (Calendar) this.data.clone();
	}
	
	public boolean isAlta() {
		return this.abertura.compareTo(this.fechamento) < 0;
	}
	
	public boolean isBaixa() {
		return this.abertura.compareTo(this.fechamento) > 0;  
	}
}
