package Vista;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.geom.AffineTransform;

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
	private float [] info_en_el_tiempo; // 0->numero imagen     1->coordenada x    2->coordenada y     3->ancho del rectangulo donde se va a dibujar      4-> alto del rectangulo donde se va a dibujar	5->Transparencia 6->Angulo de rotacion 7->Color/Textura 
	private Configuracion miConfiguracion;
	private int actualBackground = 0;
	private JPanel paintPane;
	boolean ok=true;
	private Mural m;
	public Mural() {
		//setBounds(0, 0, 744, 768);
		setBounds(0,0,1024,768);
		setBackground(Color.WHITE);
		m=this;
		

	}
	
	
	public void actualizar(double x,double y, int num_id, float anguloGrados) {

		
		if(lista.size()==299) {
			//archivo.delete();
			imgGral.imprimirCap(this);
		
		}
		//this.x=(int) (x*744);
		//this.y=(int) (y*768);
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.anguloRadianes = anguloGrados*Math.PI/180;
		System.out.println(this.anguloRadianes);
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
				if(num_img!=99) {
					
					if(lista.size()==300) {
	
						lista.clear();
						//imgGral.imprimirCap(this); --->linea 44
						float [] info_en_el_tiempo= {-2,this.x,this.y, (this.x + miConfiguracion.getConfig_Regla()[0]), (this.y + miConfiguracion.getConfig_Regla()[0]), miConfiguracion.getConfig_Transparencia()[0], (float)this.anguloRadianes, miConfiguracion.getConfig_ColoresTexturas()[0]};
						lista.add(info_en_el_tiempo);
					}else {
						float [] info_en_el_tiempo= {num_img,this.x,this.y, (this.x + miConfiguracion.getConfig_Regla()[0]), (this.y + miConfiguracion.getConfig_Regla()[0]), miConfiguracion.getConfig_Transparencia()[0], (float)this.anguloRadianes, miConfiguracion.getConfig_ColoresTexturas()[0]};
						lista.add(info_en_el_tiempo);
					}
				}
			} else {
				if (miConfiguracion.isCambioConfig()) {
					miConfiguracion.setCambioConfig(false);
				}
			}
			if((! lista.isEmpty())&&(num_img!=Fiduciales.getIdMarcador()[15])) {
			
				Iterator<float []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo
				while(listaIterada.hasNext()) {
					
					
					info_en_el_tiempo=listaIterada.next();
					AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,info_en_el_tiempo[5]);
					g2d.setComposite(alcom);

					switch((int)info_en_el_tiempo[0]) {
						//Para el case del lapiz utilizar 42 para la mesa y 150 para el simulador.
					  case 150:{ //lapiz
						  //System.out.println("info 1 " + info_en_el_tiempo[1]  + "info 2 " + info_en_el_tiempo[2] +"info 3 " + info_en_el_tiempo[3] + "info 4 " + info_en_el_tiempo[4]);
						  System.out.println("info 1 " + info_en_el_tiempo[2] );
						  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],info_en_el_tiempo[1],info_en_el_tiempo[2]);
						  g2d.setTransform(tx);
						  g2d.drawImage(imgGral.getImagen_lapiz(0), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
						  break;
					  }
					  case 12:{ //goma
						  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],info_en_el_tiempo[1],info_en_el_tiempo[2]);
						  g2d.setTransform(tx);
						  g2d.drawImage(imgGral.getImagen_gomas(actualBackground), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
						  break;
					  } 
					  
					  default:{ //fiduciales
						  if(info_en_el_tiempo[0]==-2) {
							  g2d.drawImage(imgGral.getCap(), 0, 0, null);
						  }else {
							  
							  BufferedImage img=imgGral.getImage((int)((info_en_el_tiempo[0]*11)+info_en_el_tiempo[7])) ;
							  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],(info_en_el_tiempo[1]),(info_en_el_tiempo[2]));
							  g2d.setTransform(tx);
							  //CENTRADO DE IMAGENES
							  g2d.drawImage(img, (int)info_en_el_tiempo[1]-150, (int)info_en_el_tiempo[2]-150, (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
							  //g2d.drawImage(img, (int)info_en_el_tiempo[1]-150, (int)info_en_el_tiempo[2]-150, (int)info_en_el_tiempo[3]-150, (int)info_en_el_tiempo[4]-150, 0, 0, 744, 768, null);
							  //g2d.drawImage(img, (int)info_en_el_tiempo[1]-400, (int)info_en_el_tiempo[2]-400, (int)info_en_el_tiempo[3]-400, (int)info_en_el_tiempo[4]-400, 0, 0, 744, 768, null);

						  }
						  
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
