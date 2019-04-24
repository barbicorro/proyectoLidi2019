package Vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Modelo.*;
import java.lang.Math.*;

public class Panel_configuracion extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x,y,num_img_de_configuracion,imgActual;
	private double anguloGrados=0;
	private Configuracion miConfiguracion;
	private Fiduciales fiduciales;
	private int actualizando;
	public Panel_configuracion(){
		//setBackground(Color.PINK);
		setBounds(512, 0, 512, 768);
		
	}
	
	public void actualizar(int id, double x, double y, float anguloGrados ) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.num_img_de_configuracion=id;
		this.anguloGrados = anguloGrados;
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
			miConfiguracion.setConfig_Regla(x,y);
			g.drawImage(imgGral.getImagen_config()[0], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+500) , (miConfiguracion.getConfig_Regla()[2] + 700), 0, 0, 512, 768, null);
			imgActual=0; //posicion de la regla en el vector
		}
		else {
			if (num_img_de_configuracion== fiduciales.getIdMarcador()[10]) { //10 es el id de las tranparencias
				miConfiguracion.setConfig_Transparencia(x,y);
				g.drawImage(imgGral.getImagen_config()[1], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+500) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 700), 0, 0, 512, 768, null);
				imgActual=1; //posicion de la herramienta de transparencia en el vector
			} 
			else {
				if (num_img_de_configuracion== fiduciales.getIdMarcador()[11]) { //11 es el id del selector de fondos
					miConfiguracion.setConfig_Fondo(x, y);
					g.drawImage(imgGral.getImagen_config()[1], (int)miConfiguracion.getConfig_Fondo()[1], (int)miConfiguracion.getConfig_Fondo()[2], (int)(miConfiguracion.getConfig_Fondo()[1]+500) , (int)(miConfiguracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
					imgActual=2; //posicion de la herramienta de fondos en el vector
				}
				else {
					if (num_img_de_configuracion== fiduciales.getIdMarcador()[13]) { //13 es el id del circulo cromatico
						miConfiguracion.setConfig_Colores(x, y);
						switch (miConfiguracion.getConfig_Colores()[0]) {
						  case 0:{ //blanco
							  g.drawImage(imgGral.getImagen_config()[5], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=5; //posicion seleccionado blanco en el vector de imagenes
							  break;
						  }
						  case 1:{ //rojo
							  g.drawImage(imgGral.getImagen_config()[6], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=6; //posicion seleccionado rojo en el vector de imagenes
							  break;
						  }
						  case 2:{ //fucsia
							  g.drawImage(imgGral.getImagen_config()[7], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=7; //posicion seleccionado fucsia en el vector de imagenes
							  break;
						  }
						  case 3:{ //azul
							  g.drawImage(imgGral.getImagen_config()[8], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=8; //posicion seleccionado azul en el vector de imagenes
							  break;
						  }
						  case 4:{ //celeste
							  g.drawImage(imgGral.getImagen_config()[9], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=9; //posicion seleccionado celeste en el vector de imagenes
							  break;
						  }
						  case 5:{ //verde
							  g.drawImage(imgGral.getImagen_config()[10], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=10; //posicion seleccionado verde en el vector de imagenes
							  break;
						  }
						  case 6:{ //amarillo
							  g.drawImage(imgGral.getImagen_config()[11], (int)miConfiguracion.getConfig_Colores()[1], (int)miConfiguracion.getConfig_Colores()[2], (int)(miConfiguracion.getConfig_Colores()[1]+500) , (int)(miConfiguracion.getConfig_Colores()[2] + 700), 0, 0, 512, 768, null);
							  imgActual=11; //posicion seleccionado amarillo en el vector de imagenes
							  break;
						  }
						}
					}
				}
			}
		}
		if((imgActual==0)&&(num_img_de_configuracion==666)){
			g.drawImage(imgGral.getImagen_config()[imgActual], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+500) , (miConfiguracion.getConfig_Regla()[2] + 700), 0, 0, 512, 768, null);
			if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&((y-miConfiguracion.getConfig_Regla()[2])<86)){ //Hizo click en el nivel 1. 
				System.out.println("Nivel 1");
				miConfiguracion.setConfig_ReglaNivel(50);

			}else {
				if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>90)&&(y-miConfiguracion.getConfig_Regla()[2])<185)){
					System.out.println("Nivel 2");
					miConfiguracion.setConfig_ReglaNivel(100);
				}else
					if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>185)&&(y-miConfiguracion.getConfig_Regla()[2])<280)){
						System.out.println("Nivel 3");
						miConfiguracion.setConfig_ReglaNivel(190);
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>280)&&(y-miConfiguracion.getConfig_Regla()[2])<375)){
							System.out.println("Nivel 4");
							miConfiguracion.setConfig_ReglaNivel(250);
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<90)&&(((y-miConfiguracion.getConfig_Regla()[2])>375)&&(y-miConfiguracion.getConfig_Regla()[2])<470)){
							System.out.println("Nivel 5");
							miConfiguracion.setConfig_ReglaNivel(400);
						}
			
					}
		}
		else {
			if((imgActual==1)&&(num_img_de_configuracion==666)){
				g.drawImage(imgGral.getImagen_config()[imgActual], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+500) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 700), 0, 0, 512, 768, null);
				if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&((y-miConfiguracion.getConfig_Transparencia()[2])<86)){ //Hizo click en el nivel 1. 
					System.out.println("Nivel 1");
					miConfiguracion.setConfig_TransparenciaNivel(1f);
				}else {
					if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&(((y-miConfiguracion.getConfig_Transparencia()[2])>90)&&(y-miConfiguracion.getConfig_Transparencia()[2])<185)){
						System.out.println("Nivel 2");
						miConfiguracion.setConfig_TransparenciaNivel(0.5f);
					}else
						if(((x-miConfiguracion.getConfig_Transparencia()[1])<90)&&(((y-miConfiguracion.getConfig_Transparencia()[2])>185)&&(y-miConfiguracion.getConfig_Transparencia()[2])<280)){
							System.out.println("Nivel 3");
							miConfiguracion.setConfig_TransparenciaNivel(0.1f);
						}
				}
			}
			else {
				if((imgActual==2)&&(num_img_de_configuracion==666)){
					g.drawImage(imgGral.getImagen_config()[imgActual], (int)miConfiguracion.getConfig_Fondo()[1], (int)miConfiguracion.getConfig_Fondo()[2], (int)(miConfiguracion.getConfig_Fondo()[1]+500) , (int)(miConfiguracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
					if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&((y-miConfiguracion.getConfig_Fondo()[2])<86)){ //Hizo click en el nivel 1. 
						System.out.println("Nivel 1");
						miConfiguracion.setConfig_FondoNivel(0); //blanco
					}else {
						if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>90)&&(y-miConfiguracion.getConfig_Fondo()[2])<185)){
							System.out.println("Nivel 2");
							miConfiguracion.setConfig_FondoNivel(1); //rojo
						}else
							if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>185)&&(y-miConfiguracion.getConfig_Fondo()[2])<280)){
								System.out.println("Nivel 3");
								miConfiguracion.setConfig_FondoNivel(2); //azul
							}else
								if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>280)&&(y-miConfiguracion.getConfig_Fondo()[2])<375)){
									System.out.println("Nivel 4");
									miConfiguracion.setConfig_FondoNivel(3); //verde
								}else
									if(((x-miConfiguracion.getConfig_Fondo()[1])<90)&&(((y-miConfiguracion.getConfig_Fondo()[2])>375)&&(y-miConfiguracion.getConfig_Fondo()[2])<470)){
										System.out.println("Nivel 5");
										miConfiguracion.setConfig_FondoNivel(4); //amarillo
									}
					}
				} else {
					if((imgActual>=5)&&(imgActual<=11)){
						if (((anguloGrados>325) && (anguloGrados<=360)) || ((anguloGrados>=0)&&(anguloGrados<25))){
							System.out.println("Blanco");
							miConfiguracion.setConfig_ColoresNivel(0);
						} else {
							if ((anguloGrados>=25) && (anguloGrados<75)){
								System.out.println("Rojo");
								miConfiguracion.setConfig_ColoresNivel(1);
							} else {
								if ((anguloGrados>=75) && (anguloGrados<125)){
									System.out.println("Fucsia");
									miConfiguracion.setConfig_ColoresNivel(2);
								} else {
									if ((anguloGrados>=125) && (anguloGrados<175)){
										System.out.println("Azul");
										miConfiguracion.setConfig_ColoresNivel(3);
									} else {
										if ((anguloGrados>=175) && (anguloGrados<225)){
											System.out.println("Celeste");
											miConfiguracion.setConfig_ColoresNivel(4);
										} else {
											if ((anguloGrados>=225) && (anguloGrados<275)){
												System.out.println("Verde");
												miConfiguracion.setConfig_ColoresNivel(5);
											} else {
												if ((anguloGrados>=275) && (anguloGrados<=325)){
													System.out.println("Amarillo");
													miConfiguracion.setConfig_ColoresNivel(6);
												}
											} 
										} 
									} 
								} 
							}
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
