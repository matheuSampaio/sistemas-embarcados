package br.edu.ifba.bsi.embarcados.regua.grafico;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import br.edu.ifba.bsi.embarcados.regua.asincexec.IListenerAcelerometro;

public class Panel extends JPanel implements IListenerAcelerometro{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double startX1=0, startY1=0, endX1, endY1;	
	private double startX2=0, startY2=0, endX2, endY2;
	
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
		double anguloXZ = Math.toRadians(xz);
		double anguloYZ = Math.toRadians(yz);
		
		endX1 = 220 + 200 * Math.cos(anguloXZ);
		endY1 = 220 + 200 * Math.sin(anguloXZ);
		startX1 = 220 - 200 * Math.cos(anguloXZ);
		startY1 = 220 - 200 * Math.sin(anguloXZ);
		
		endX2 = 650 + 200 * Math.cos(anguloYZ);
		endY2 = 220 + 200 * Math.sin(anguloYZ);
		startX2 = 650 - 200 * Math.cos(anguloYZ);
		startY2 = 220 - 200 * Math.sin(anguloYZ);
		
		
		
		removeAll();
		updateUI();
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		//retangulo
		g2d.drawRect(20, 20, 400, 400);
		
		//diagonais
		g2d.drawLine(20, 20, 420, 420);
		g2d.drawLine(20, 420, 420, 20);
		
		//vert
		g2d.drawLine(220, 20, 220, 420);
		//horizontal
		g2d.drawLine(20, 220, 420, 220);
		
		
		g2d.drawRect(450, 20, 400, 400);
		
		//diagonais
		g2d.drawLine(450, 20, 850, 420);
		g2d.drawLine(450, 420, 850, 20);
	
		//vert
		g2d.drawLine(650, 20, 650, 420);
		//horizontal
		g2d.drawLine(450, 220, 850, 220);
		
		//angulo
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine((int)startX1, (int)startY1, (int)endX1, (int)endY1);
		g2d.drawLine((int)startX2, (int)startY2, (int)endX2, (int)endY2);
	}
	

}
