package Vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Modelo.*;


public class Panel_configuracion extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x,y,num_img_de_configuracion,imgActual;
	private Configuracion miConfiguracion;
	private Fiduciales fiduciales;
	private int actualizando;
	public Panel_configuracion(){
		//setBackground(Color.PINK);
		setBounds(512, 0, 512, 768);
		
	}
	
	public void actualizar(int id, double x, double y ) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.num_img_de_configuracion=id;
		repaint();
	}
	
	public void dibujar_punto(double x, double y) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.num_img_de_configuracion=666; //ID del punto es 666 
		
		
		repaint();//----->Entra al repaint() antes de asignarle el numero de configuracion... De todas formas no vamos a utilizar el punto en la mesa
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g.drawImage(imgGral.getFondo(), 0, 0,null);
		if((num_img_de_configuracion >= fiduciales.getIdMarcador()[0])&&(num_img_de_configuracion <= fiduciales.getIdMarcador()[8])) {
			
			AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,miConfiguracion.getConfig_Transparencia()[0]);
	        g2d.setComposite(alcom);
			g2d.drawImage(imgGral.getImage(num_img_de_configuracion), this.x-150, this.y-150, (this.x + miConfiguracion.getConfig_Regla()[0]-150), (this.y + miConfiguracion.getConfig_Regla()[0]-150), 0, 0, 744, 768, null);
		}

		
		if(num_img_de_configuracion == fiduciales.getIdMarcador()[9]) { // 9 es el id de la regla
			miConfiguracion.getConfig_Regla()[1]=x;
			miConfiguracion.getConfig_Regla()[2]=y;
			g.drawImage(imgGral.getImagen_config()[0], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+500) , (miConfiguracion.getConfig_Regla()[2] + 700), 0, 0, 512, 768, null);
			imgActual=0; //posicion de la regla en el vector


		}
		else {
			if (num_img_de_configuracion== fiduciales.getIdMarcador()[10]) { //10 es el id de las tranparencias
				miConfiguracion.getConfig_Transparencia()[1]=x;
				miConfiguracion.getConfig_Transparencia()[2]=y;
				g.drawImage(imgGral.getImagen_config()[1], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+500) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 700), 0, 0, 512, 768, null);
				imgActual=1; //posicion de la herramienta de transparencia en el vector
			} 
			else {
				if (num_img_de_configuracion== fiduciales.getIdMarcador()[11]) { //11 es el id del selector de fondos
					miConfiguracion.getConfig_Fondo()[1]=x;
					miConfiguracion.getConfig_Fondo()[2]=y;
					g.drawImage(imgGral.getImagen_config()[1], (int)miConfiguracion.getConfig_Fondo()[1], (int)miConfiguracion.getConfig_Fondo()[2], (int)(miConfiguracion.getConfig_Fondo()[1]+500) , (int)(miConfiguracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
					imgActual=2; //posicion de la herramienta de fondos en el vector
				}
			}
		}
		if((imgActual==0)&&(num_img_de_configuracion==666)){
			g.drawImage(imgGral.getImagen_config()[imgActual], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+500) , (miConfiguracion.getConfig_Regla()[2] + 700), 0, 0, 512, 768, null);
			if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&((y-miConfiguracion.getConfig_Regla()[2])<86)){ //Hizo click en el nivel 1. 
				System.out.println("Nivel 1");
				miConfiguracion.getConfig_Regla()[0]=50;

			}else {
				if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>90)&&(y-miConfiguracion.getConfig_Regla()[2])<185)){
					System.out.println("Nivel 2");
					miConfiguracion.getConfig_Regla()[0]=100;
				}else
					if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>185)&&(y-miConfiguracion.getConfig_Regla()[2])<280)){
						System.out.println("Nivel 3");
						miConfiguracion.getConfig_Regla()[0]=190;
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>280)&&(y-miConfiguracion.getConfig_Regla()[2])<375)){
							System.out.println("Nivel 4");
							miConfiguracion.getConfig_Regla()[0]=250;
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>375)&&(y-miConfiguracion.getConfig_Regla()[2])<470)){
							System.out.println("Nivel 5");
							miConfiguracion.getConfig_Regla()[0]=400;
						}
			
					}
		}
		else {
			if((imgActual==1)&&(num_img_de_configuracion==666)){
				g.drawImage(imgGral.getImagen_config()[imgActual], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+500) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 700), 0, 0, 512, 768, null);
				if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&((y-miConfiguracion.getConfig_Transparencia()[2])<86)){ //Hizo click en el nivel 1. 
					System.out.println("Nivel 1");
					miConfiguracion.getConfig_Transparencia()[0]=1f;
				}else {
					if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&(((y-miConfiguracion.getConfig_Transparencia()[2])>90)&&(y-miConfiguracion.getConfig_Transparencia()[2])<185)){
						System.out.println("Nivel 2");
						miConfiguracion.getConfig_Transparencia()[0]=0.5f;
					}else
						if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&(((y-miConfiguracion.getConfig_Transparencia()[2])>185)&&(y-miConfiguracion.getConfig_Transparencia()[2])<280)){
							System.out.println("Nivel 3");
							miConfiguracion.getConfig_Transparencia()[0]=0.1f;
						}
				}
			}
			else {
				if((imgActual==2)&&(num_img_de_configuracion==666)){
					g.drawImage(imgGral.getImagen_config()[imgActual], (int)miConfiguracion.getConfig_Fondo()[1], (int)miConfiguracion.getConfig_Fondo()[2], (int)(miConfiguracion.getConfig_Fondo()[1]+500) , (int)(miConfiguracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
					if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&((y-miConfiguracion.getConfig_Fondo()[2])<86)){ //Hizo click en el nivel 1. 
						System.out.println("Nivel 1");
						miConfiguracion.getConfig_Fondo()[0]=0; //blanco
					}else {
						if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>90)&&(y-miConfiguracion.getConfig_Fondo()[2])<185)){
							System.out.println("Nivel 2");
							miConfiguracion.getConfig_Fondo()[0]=1; //rojo
						}else
							if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>185)&&(y-miConfiguracion.getConfig_Fondo()[2])<280)){
								System.out.println("Nivel 3");
								miConfiguracion.getConfig_Fondo()[0]=2; //azul
							}else
								if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>280)&&(y-miConfiguracion.getConfig_Fondo()[2])<375)){
									System.out.println("Nivel 4");
									miConfiguracion.getConfig_Fondo()[0]=3; //verde
								}else
									if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>375)&&(y-miConfiguracion.getConfig_Fondo()[2])<470)){
										System.out.println("Nivel 5");
										miConfiguracion.getConfig_Fondo()[0]=4; //amarillo
									}
					}
				}
			}
		}

		if(num_img_de_configuracion==666) { //solo lo utilizo para configurar algunas cosas no se usa en el juego final. O SI?
			g.drawImage(imgGral.getImage(1), x, y, (x+25), (y+25), 0, 0, 1024, 768, null);
			
		}
	}
	

}
