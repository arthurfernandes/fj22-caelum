package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Negociacao {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(BigDecimal valor, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Data nao pode ser nula");
		}
		if (valor == null){
			throw new IllegalArgumentException("Valor nao pode ser nulo");
		}
		if (valor.compareTo(BigDecimal.ZERO) < 0){
			throw new IllegalArgumentException("Valor nao pode ser menor que zero");
		}
		if (quantidade < 0) {
			throw new IllegalArgumentException("Quantidade nao pode ser negativo");
		}
		
		this.preco = valor;
		this.quantidade = quantidade;
		this.data = (Calendar) data.clone();
	}

	public String toString(){
		return String.format("[Preco: %s, Quantidade: %s, Data: %s]", 
				this.preco,
				this.quantidade,
				this.data);
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	public BigDecimal getVolume() {
		return preco.multiply(new BigDecimal(quantidade));
	}

	public boolean isMesmoDia(Calendar data) {
		return this.data.get(Calendar.YEAR) == data.get(Calendar.YEAR)
				&& this.data.get(Calendar.MONTH) == data.get(Calendar.MONTH)
				&& this.data.get(Calendar.DAY_OF_MONTH) == data.get(Calendar.DAY_OF_MONTH);
	}
}

