package br.edu.ifba.bsi.embarcados.regua.grafico;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	Tela t;
	
	public Frame(){
		setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		setSize(900, 500);
		setResizable(false);
		setTitle("Régua de nivelamento");
		
		init();
	}
	
	public void init(){
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 1, 0, 0));
		
		t = new Tela();
		add(t);
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Frame();
	}

}
