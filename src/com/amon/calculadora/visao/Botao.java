package com.amon.calculadora.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
		setFont(new Font("courrier", Font.BOLD, 25));
		setText(texto);
		setOpaque(true);
		setBackground(cor);
		setForeground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
