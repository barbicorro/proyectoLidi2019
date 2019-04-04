package Vista;


import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.Math.*;

import javax.swing.JPanel;

import Modelo.Configuracion;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	private double anguloRadianes=0;
	private int num_img=-1;
	private ArrayList<float []> lista = new ArrayList<float []>();
	private float [] info_en_el_tiempo; // 0->numero imagen     1->coordenada x    2->coordenada y     3->ancho del rectangulo donde se va a dibujar      4-> alto del rectangulo donde se va a dibujar	5->Transparencia 6->Angulo de rotacion
	private Configuracion miConfiguracion;
	private int actualBackground = 0;

	public Mural() {
		//setBounds(0, 0, 744, 768);
		setBounds(0,0,1024,768);
		setBackground(Color.WHITE);
	}
	
	

	public void actualizar(double x,double y, int num_id, float anguloGrados) {
		

		//this.x=(int) (x*744);
		//this.y=(int) (y*768);
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.anguloRadianes = anguloGrados*Math.PI/180;
		this.num_img=num_id;
		repaint();
		
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; //Lo casteamos para poder usar "alphaComposite"
		int color = miConfiguracion.getConfig_Fondo()[0];
		if (actualBackground != color) {
			switch(color) {
			  case 0: //blanco
			    setBackground(Color.WHITE);
			    actualBackground = 0;
			    break;
			  case 1: //rojo
				setBackground(Color.RED);
				actualBackground = 1;
				break;
			  case 2: //azul
				  setBackground(Color.BLUE);
				  actualBackground = 2;
				break;
			  case 3: //verde
				setBackground(Color.GREEN);
				actualBackground = 3;
				break;
			  case 4: //amarillo
				setBackground(Color.YELLOW);
				actualBackground = 4;
				break;
			}
		}
		
		if(num_img==-1) {
			g2d.drawImage(null, x, y, null);
		}else {
			if(! lista.isEmpty()) {
				Iterator<float []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo

				while(listaIterada.hasNext()) {
					info_en_el_tiempo=listaIterada.next();
					AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,info_en_el_tiempo[5]);
					g2d.setComposite(alcom);
					AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],imgGral.getImage((int)info_en_el_tiempo[0]).getWidth()/2,imgGral.getImage((int)info_en_el_tiempo[0]).getHeight()/2);
					g2d.setTransform(tx);
					if (info_en_el_tiempo[0]!=12) {
						g2d.drawImage(imgGral.getImage((int)info_en_el_tiempo[0]), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
					}else
						g2d.drawImage(imgGral.getImagen_gomas(actualBackground), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);

				}

			}
			if(miConfiguracion.isMural_activado()) {
				System.out.println("Asd");
				float [] info_en_el_tiempo= {num_img,this.x-150,this.y-150, (this.x + miConfiguracion.getConfig_Regla()[0]-150), (this.y + miConfiguracion.getConfig_Regla()[0]-150), miConfiguracion.getConfig_Transparencia()[0], (float)this.anguloRadianes};
				lista.add(info_en_el_tiempo);
			}
			
	
		}
		
	}
	
	
	 
}
