package com.amon.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando{
		ZERAR, NUMERO, DIV, MULT, SUB, SOMA, IGUAL, VIRG;
	};
	
	private static final Memoria instancia = new Memoria();
	
	private List<MemoriaObservador> observadores =  new ArrayList<>();
	
	private TipoComando ultimaOperacao = null;		
	private boolean substituir = false;		
	private String textoAtual = "";		
	private String textoBuffer = "";
	

	private Memoria() {

	}
	
	public static Memoria getInstacia() {
		return instancia;
		
	}
	
	public void adicionarObservador(MemoriaObservador o) {
		observadores.add(o);
	}	
	
	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}
	
	public void processarComando(String texto) {
		
		TipoComando tipoComando = detectarTipoComando(texto);
		
		if(tipoComando == null) {
			return;
		}else if (tipoComando == TipoComando.ZERAR) {
			ultimaOperacao = null;		
			substituir = false;		
			textoAtual = "";		
			textoBuffer = "";
		}else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRG) {
			textoAtual = substituir ? texto : textoAtual + texto;		
			substituir = false;		
		}else {
			substituir = true;
			textoAtual = obterResultadoOperação();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;		
		}
		
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperação() {
		if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;			
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(",", "."));
		
		double resultado = 0;
		
		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
			
		}else if (ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
			
		}else if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
			
		}else if (ultimaOperacao == TipoComando.MULT) {
			resultado = numeroBuffer * numeroAtual;
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
		
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		
		try {
			Integer.parseInt(texto);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			
			if("CLEAR".equals(texto)) {
				return TipoComando.ZERAR;
			}else if ("/".equals(texto)) {
				return TipoComando.DIV;
			}else if ("+".equals(texto)) {
				return TipoComando.SOMA;
			}else if ("-".equals(texto)) {
				return TipoComando.SUB;
			}else if ("*".equals(texto)) {
				return TipoComando.MULT;
			}else if ("=".equals(texto)) {
				return TipoComando.IGUAL;
			}else if (",".equals(texto) && !textoAtual.contains(",")) {
				return TipoComando.VIRG;
			}
			
		}
		
		return null;
	}

}
