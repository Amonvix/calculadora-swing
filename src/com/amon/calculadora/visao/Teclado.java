package com.amon.calculadora.visao;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.amon.calculadora.modelo.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener{
	
	private final Color cinza = new Color(68,68,68);
	private final Color claro = new Color(99,99,99); 
	private final Color laranja = new Color(242,163,60);
	private final Color verde = Color.GREEN;
	
	
	
	public Teclado() {
		
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		c.weightx=1;
		c.weighty=1;
		
		c.fill= GridBagConstraints.BOTH;
		
		c.gridwidth = 3;
		adicionarBotao("CLEAR", cinza, c, 0, 0);		
		c.gridwidth = 1;
		
		adicionarBotao("/", laranja, c, 3, 0);
		
		adicionarBotao("7", claro, c, 0, 1);
		adicionarBotao("8", claro, c, 1, 1);
		adicionarBotao("9", claro, c, 2, 1);
		adicionarBotao("*", laranja, c, 3, 1);
		
		adicionarBotao("4", claro, c, 0, 2);
		adicionarBotao("5", claro, c, 1, 2);
		adicionarBotao("6", claro, c, 2, 2);
		adicionarBotao("-", laranja, c, 3, 2);
		
		adicionarBotao("1", claro, c, 0, 3);
		adicionarBotao("2", claro, c, 1, 3);
		adicionarBotao("3", claro, c, 2, 3);
		adicionarBotao("+", laranja, c, 3, 3);
		
		c.gridwidth = 2;
		adicionarBotao("0", claro, c, 0, 4);
		c.gridwidth = 1;
		
		adicionarBotao(",", claro, c, 2, 4);
		adicionarBotao("=", verde, c, 3, 4);
		
	}



	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao,c);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton botao= (JButton) e.getSource();
			Memoria.getInstacia().processarComando(botao.getText());
			
		}
		
	}

}
