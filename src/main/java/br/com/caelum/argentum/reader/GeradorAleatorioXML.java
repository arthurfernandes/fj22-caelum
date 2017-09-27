package br.com.caelum.argentum.reader;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.caelum.argentum.modelo.Negociacao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeradorAleatorioXML {
	public static void main(String[] args) throws IOException {
		Calendar data = Calendar.getInstance();
		Random random = new Random(123);
		
		List<Negociacao> negociacoes = new ArrayList<>();
		
		BigDecimal valor = new BigDecimal("40");
		int quantidade = 1000;
		
		for(int dias = 0; dias < 30; dias++) {
			int quantidadeNegociacoesDoDia = random.nextInt(20);
			
			for(int negociacao = 0; negociacao < quantidadeNegociacoesDoDia; negociacao++) {
				valor = valor.add(new BigDecimal((random.nextInt(200) - 100)/200));
				if (valor.compareTo(new BigDecimal("5")) < 0){
					valor = new BigDecimal("5");
				}
				
				quantidade = 1000 - random.nextInt(500);
				
				Negociacao n = new Negociacao(valor, quantidade, data);
				negociacoes.add(n);
			}
			
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}		
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		stream.setMode(XStream.NO_REFERENCES);
		
		PrintStream out = new PrintStream(new File("negociacao.xml"));
		out.println(stream.toXML(negociacoes));
	}
}
