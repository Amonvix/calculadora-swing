package com.amon.calculadora.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.amon.calculadora.modelo.Memoria;
import com.amon.calculadora.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador{
	
	private final JLabel label;

	public Display() {
		Memoria.getInstacia().adicionarObservador(this);
		
		setBackground(new Color(45,45,45));
		label = new JLabel(Memoria.getInstacia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courrier", Font.PLAIN, 40));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 25));
		
		add(label);
	}
	
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}
}

