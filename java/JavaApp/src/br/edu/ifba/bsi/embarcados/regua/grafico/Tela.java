package br.edu.ifba.bsi.embarcados.regua.grafico;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Tela extends JPanel{
	
	public Tela(){
		repaint();
	}
	
	public void paint(Graphics g){
		
		//retangulo
		g.drawRect(20, 20, 400, 400);
		
		//diagonais
		g.drawLine(20, 20, 420, 420);
		g.drawLine(20, 420, 420, 20);
		
		//vert
		g.drawLine(220, 20, 220, 420);
		//horizontal
		g.drawLine(20, 220, 420, 220);
		
		
		g.drawRect(450, 20, 400, 400);
		
		//diagonais
		g.drawLine(450, 20, 850, 420);
		g.drawLine(450, 420, 850, 20);
	
		//vert
		g.drawLine(650, 20, 650, 420);
		//horizontal
		g.drawLine(450, 220, 850, 220);
		
		
	}

}
