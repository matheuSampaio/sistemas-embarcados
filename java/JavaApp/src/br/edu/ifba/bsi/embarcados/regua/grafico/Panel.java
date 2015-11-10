package br.edu.ifba.bsi.embarcados.regua.grafico;

import java.awt.Graphics;

import javax.swing.JPanel;

import br.edu.ifba.bsi.embarcados.regua.asincexec.IListenerAcelerometro;

public class Panel extends JPanel implements IListenerAcelerometro{
	
	public Panel(){
		repaint();
	}
//	
//	public void paint(Graphics g){
//		
//		Graphics2D g2d = (Graphics2D) g.create();
//		
//		//retangulo
//		g2d.drawRect(20, 20, 400, 400);
//		
//		//diagonais
//		g2d.drawLine(20, 20, 420, 420);
//		g2d.drawLine(20, 420, 420, 20);
//		
//		//vert
//		g2d.drawLine(220, 20, 220, 420);
//		//horizontal
//		g2d.drawLine(20, 220, 420, 220);
//		
//		
//		g2d.drawRect(450, 20, 400, 400);
//		
//		//diagonais
//		g2d.drawLine(450, 20, 850, 420);
//		g2d.drawLine(450, 420, 850, 20);
//	
//		//vert
//		g2d.drawLine(650, 20, 650, 420);
//		//horizontal
//		g2d.drawLine(450, 220, 850, 220);
//		
//		g2d.rotate(Math.PI/4);
//		
//		
//		
//		
//	}

	@Override
	public void notificarInclinacao(double xz, double yz) {
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		g.drawRect(20, 20, 400, 400);
	}
	

}
