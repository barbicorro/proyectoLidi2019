package Vista;
import java.awt.image.BufferedImage;
import Modelo.CambioColores;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.lang.Math.*;

import javax.swing.JPanel;

import Modelo.Configuracion;
import Modelo.Fiduciales;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	private double anguloRadianes=0;
	private int num_img=-1;
	private Panel_configuracion panel_configuracion;
	private ArrayList<float []> lista = new ArrayList<float []>();
	private float [] info_en_el_tiempo; // 0->numero imagen     1->coordenada x    2->coordenada y     3->ancho del rectangulo donde se va a dibujar      4-> alto del rectangulo donde se va a dibujar	5->Transparencia 6->Angulo de rotacion 7->Color/Textura 8->Color lapiz
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
		//CAMBIO DE COLOR DEL FONDO
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
			  case 2: //magenta
				  setBackground(Color.MAGENTA);
				  actualBackground = 2;
				break;
			  case 3: //azul
				  setBackground(Color.BLUE);
				  actualBackground = 3;
				break;
			  case 4: //cyan
				  setBackground(Color.CYAN);
				  actualBackground = 4;
				break;
			  case 5: //verde
				setBackground(Color.GREEN);
				actualBackground = 5;
				break;
			  case 6: //amarillo
				setBackground(Color.YELLOW);
				actualBackground = 6;
				break;
			}
		}
		
		if(num_img==-1) {
			g2d.drawImage(null, x, y, null);
		}else {
			if(miConfiguracion.isMural_activado() && !miConfiguracion.isCambioConfig()) {
				//ACA se resta 108 ------------------------------------------------------------
				//if(num_img!=99) {
					float [] info_en_el_tiempo= {num_img,this.x,this.y, (this.x + miConfiguracion.getConfig_Regla()[0]), (this.y + miConfiguracion.getConfig_Regla()[0]), miConfiguracion.getConfig_Transparencia()[0], (float)this.anguloRadianes, miConfiguracion.getConfig_ColoresTexturas()[0], miConfiguracion.getConfig_ColoresTexturas()[3]};
					lista.add(info_en_el_tiempo);
				//}
				System.out.println("No cambio la configuracion");
			} else {
				if (miConfiguracion.isCambioConfig()) {
					miConfiguracion.setCambioConfig(false);
					System.out.println("Cambio la configuracion y la pase a falso");
				}
			}
			if((! lista.isEmpty())&&(num_img!=Fiduciales.getIdMarcador()[15])) {
				Iterator<float []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo
				while(listaIterada.hasNext()) {
					info_en_el_tiempo=listaIterada.next();
					AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,info_en_el_tiempo[5]);
					g2d.setComposite(alcom);
					switch((int)info_en_el_tiempo[0]) {
					  case 99:{ //lapiz
						  g2d.drawImage(imgGral.getImagen_lapiz((int)info_en_el_tiempo[8]), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
						  break;
					  }
					  case 12:{ //goma
						  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],info_en_el_tiempo[1],info_en_el_tiempo[2]);
						  g2d.setTransform(tx);
						  g2d.drawImage(imgGral.getImagen_gomas(actualBackground), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
						  break;
					  } 
					  default:{ //fiduciales
						  BufferedImage img=imgGral.getImage((int)((info_en_el_tiempo[0]*11)+info_en_el_tiempo[7])) ;
						  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],(info_en_el_tiempo[1]),(info_en_el_tiempo[2]));
						  g2d.setTransform(tx);
						  g2d.drawImage(img, (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
					  }
					}
				}
			}
		}	
		if(num_img==Fiduciales.getIdMarcador()[15]) {
			lista.clear();
			
		}
	}	 
}
