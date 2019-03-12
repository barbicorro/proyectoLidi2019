package Vista;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	public Mural() {
		setBackground(Color.BLACK);
		setBounds(0, 0, 744, 768);
	}
	
	

	public void actualizar(double x,double y) {
		System.out.println("x es:" + x + "y es:" + y);

		this.x=(int) (x*1000);
		this.y=(int) (y*1000);
		//System.out.println("x es:" + x + "y es:" + y);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgGral.getImage(), x, y, null);
		
	}
}
