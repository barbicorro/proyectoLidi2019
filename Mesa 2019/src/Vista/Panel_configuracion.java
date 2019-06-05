package Vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import Modelo.*;
import java.lang.Math.*;

public class Panel_configuracion extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x,y,num_img_de_configuracion,num_pieza_actual,imgActual,nivelActRegla=0,nivelActTrans=6;
	private double anguloGrados=0;
	private Configuracion miConfiguracion;
	private Fiduciales fiduciales;
	private boolean dibujar=false;
	
	public Panel_configuracion(){
		//setBackground(Color.PINK);
		setBounds(512, 0, 512, 768);
		
	}
	
	public void actualizar(int id, double x, double y, float anguloGrados ) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		if((id>=0)&&(id<=8)){
			num_pieza_actual=id;
		}
		this.num_img_de_configuracion=id;
		this.anguloGrados = anguloGrados;
		repaint();
	}
	
	public void dibujar_punto(double x, double y) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		dibujar=true; 
		
		repaint();//----->Entra al repaint() antes de asignarle el numero de configuracion... De todas formas no vamos a utilizar el punto en la mesa
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(imgGral.getFondo(), 0, 0,null);

		   if(num_img_de_configuracion == fiduciales.getIdMarcador()[9]) { // 9 es el id de la regla
				if(dibujar==false) {
					miConfiguracion.setConfig_Regla(x,y);
				}
				g.drawImage(imgGral.getImagen_config()[nivelActRegla], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+600) , (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);
				imgActual=0; //posicion de la regla en el vector
			}
			else {
				if (num_img_de_configuracion== fiduciales.getIdMarcador()[10]) { //10 es el id de las tranparencias
					if(dibujar==false) {
						miConfiguracion.setConfig_Transparencia(x,y);
					}
					g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+600) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);
					imgActual=1; //posicion de la herramienta de transparencia en el vector
				} 
				else {
					if (num_img_de_configuracion== fiduciales.getIdMarcador()[11]) { //11 es el id del selector de fondos
						if(dibujar==false) {
							miConfiguracion.setConfig_Fondo(x, y);
						}
						g.drawImage(imgGral.getImagen_config()[miConfiguracion.getConfig_Fondo()[0]+8], (int)miConfiguracion.getConfig_Fondo()[1], (int)miConfiguracion.getConfig_Fondo()[2], (int)(miConfiguracion.getConfig_Fondo()[1]+500) , (int)(miConfiguracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
						imgActual= miConfiguracion.getConfig_Fondo()[0]+8; //posicion del color seleccionado en el vector de imagenes del selector
					}
					else {
						if (num_img_de_configuracion== fiduciales.getIdMarcador()[13]) { //en la pos 13 esta el id del circulo cromatico
							if(dibujar==false) {
								miConfiguracion.setConfig_ColoresTexturas(x, y);
							}
							g.drawImage(imgGral.getImagen_config()[miConfiguracion.getConfig_ColoresTexturas()[0]+15], (int)miConfiguracion.getConfig_ColoresTexturas()[1], (int)miConfiguracion.getConfig_ColoresTexturas()[2], (int)(miConfiguracion.getConfig_ColoresTexturas()[1]+500) , (int)(miConfiguracion.getConfig_ColoresTexturas()[2] + 700), 0, 0, 512, 768, null);
							imgActual= miConfiguracion.getConfig_ColoresTexturas()[0]+15; //posicion del color seleccionado en el vector de imagenes
							
						}
					}
				}
			}
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,miConfiguracion.getConfig_Transparencia()[0]);
        g2d.setComposite(alcom);
        
        
     
		if((imgActual==0)&&(dibujar==true)){
			//g.drawImage(imgGral.getImagen_config()[nivelActRegla], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+600) , (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);
			if(((x-miConfiguracion.getConfig_Regla()[1])<104)&&((x-miConfiguracion.getConfig_Regla()[1])>0)&&(((y-miConfiguracion.getConfig_Regla()[2])<64)&&(y-miConfiguracion.getConfig_Regla()[2])>0)){ //Hizo click en el nivel 1. 
				nivelActRegla=3;
				System.out.println("Nivel 1");
				miConfiguracion.getConfig_Regla()[0]=50;

			}else {
				if(((x-miConfiguracion.getConfig_Regla()[1])<213)&&((x-miConfiguracion.getConfig_Regla()[1])>104)&&(((y-miConfiguracion.getConfig_Regla()[2])<64)&&(y-miConfiguracion.getConfig_Regla()[2])>0)){ //Hizo click en el nivel 1. 
					nivelActRegla=1;
					System.out.println("Nivel 2");
					miConfiguracion.getConfig_Regla()[0]=100;
				}else
					if(((x-miConfiguracion.getConfig_Regla()[1])<322)&&((x-miConfiguracion.getConfig_Regla()[1])>213)&&(((y-miConfiguracion.getConfig_Regla()[2])<64)&&(y-miConfiguracion.getConfig_Regla()[2])>0)){ //Hizo click en el nivel 1. 
						nivelActRegla=0;
						System.out.println("Nivel 3");
						miConfiguracion.getConfig_Regla()[0]=190;
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<432)&&((x-miConfiguracion.getConfig_Regla()[1])>322)&&(((y-miConfiguracion.getConfig_Regla()[2])<64)&&(y-miConfiguracion.getConfig_Regla()[2])>0)){ //Hizo click en el nivel 1. 	
							nivelActRegla=2;
							System.out.println("Nivel 4");
							miConfiguracion.getConfig_Regla()[0]=250;
					}else
						if(((x-miConfiguracion.getConfig_Regla()[1])<542)&&((x-miConfiguracion.getConfig_Regla()[1])>432)&&(((y-miConfiguracion.getConfig_Regla()[2])<64)&&(y-miConfiguracion.getConfig_Regla()[2])>0)){ //Hizo click en el nivel 1. 
							nivelActRegla=4;
							System.out.println("Nivel 5");
							miConfiguracion.getConfig_Regla()[0]=400;
						}
			
					}
		//	g.drawImage(imgGral.getImagen_config()[nivelActRegla], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+600) , (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);

			//g2d.drawImage(imgGral.getImage(0), 0, 0, (0 + miConfiguracion.getConfig_Regla()[0]), (0 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 744, 768, null);
		}
		else {
			if((imgActual==1)&&(dibujar==true)){
		       // g2d.drawImage(imgGral.getImage(0), 0, 0, (0 + miConfiguracion.getConfig_Regla()[0]), (0 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 1024, 768, null);
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+600) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);

		        if(((x-miConfiguracion.getConfig_Transparencia()[1])<104)&&((x-miConfiguracion.getConfig_Transparencia()[1])>0)&&(((y-miConfiguracion.getConfig_Transparencia()[2])<64)&&(y-miConfiguracion.getConfig_Transparencia()[2])>0)){ //Hizo click en el nivel 1. 
					System.out.println("Nivel 1");
					nivelActTrans=7;
					miConfiguracion.getConfig_Transparencia()[0]=0.1f;


				}else {
					if(((x-miConfiguracion.getConfig_Transparencia()[1])>104)&&((x-miConfiguracion.getConfig_Transparencia()[1])<212)&&(((y-miConfiguracion.getConfig_Transparencia()[2])<64)&&(y-miConfiguracion.getConfig_Transparencia()[2])>0)) {
						System.out.println("Nivel 2");
						nivelActTrans=5;
						miConfiguracion.getConfig_Transparencia()[0]=0.5f;


					}else
						if(((x-miConfiguracion.getConfig_Transparencia()[1])>212)&&((x-miConfiguracion.getConfig_Transparencia()[1])<321)&&(((y-miConfiguracion.getConfig_Transparencia()[2])<64)&&(y-miConfiguracion.getConfig_Transparencia()[2])>0)) {
							System.out.println("Nivel 3");
							nivelActTrans=6;
							miConfiguracion.getConfig_Transparencia()[0]=1f;


						}
				}
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+600) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);

			}
			else {
				if((imgActual>=8)&&(imgActual<=14)){
					System.out.println(imgActual);
					if (((anguloGrados>325) && (anguloGrados<=360)) || ((anguloGrados>=0)&&(anguloGrados<25))){
						System.out.println("Color blanco - sin efecto");
						miConfiguracion.setConfig_FondoNivel(0);
					} else {
						if ((anguloGrados>=25) && (anguloGrados<75)){
							System.out.println("Rojo");
							miConfiguracion.setConfig_FondoNivel(1);
						} else {
							if ((anguloGrados>=75) && (anguloGrados<125)){
								System.out.println("Magenta");
								miConfiguracion.setConfig_FondoNivel(2);
							} else {
								if ((anguloGrados>=125) && (anguloGrados<175)){
									System.out.println("Azul");
									miConfiguracion.setConfig_FondoNivel(3);
								} else {
									if ((anguloGrados>=175) && (anguloGrados<225)){
										System.out.println("Cian");
										miConfiguracion.setConfig_FondoNivel(4);
									} else {
										if ((anguloGrados>=225) && (anguloGrados<275)){
											System.out.println("Verde");
											miConfiguracion.setConfig_FondoNivel(5);
										} else {
											if ((anguloGrados>=275) && (anguloGrados<=325)){
												System.out.println("Amarillo");
												miConfiguracion.setConfig_FondoNivel(6);
											}
										} 
									} 
								} 
							} 
						}
					}
				} else {
					if((imgActual>=15)&&(imgActual<=25)){
						System.out.println(imgActual);
						if (((anguloGrados>346.5) && (anguloGrados<=360)) || ((anguloGrados>=0)&&(anguloGrados<16.5))){
							System.out.println("Color blanco - sin efecto");
							miConfiguracion.setConfig_ColoresTexturasNivel(0);
						} else {
							if ((anguloGrados>=16.5) && (anguloGrados<49.5)){
								System.out.println("Rojo");
								miConfiguracion.setConfig_ColoresTexturasNivel(1);
							} else {
								if ((anguloGrados>=49.5) && (anguloGrados<82.5)){
									System.out.println("Magenta");
									miConfiguracion.setConfig_ColoresTexturasNivel(2);
								} else {
									if ((anguloGrados>=82.5) && (anguloGrados<115.5)){
										System.out.println("Azul");
										miConfiguracion.setConfig_ColoresTexturasNivel(3);
									} else {
										if ((anguloGrados>=115.5) && (anguloGrados<148.5)){
											System.out.println("Verde");
											miConfiguracion.setConfig_ColoresTexturasNivel(4);
										} else {
											if ((anguloGrados>=148.5) && (anguloGrados<181.5)){
												System.out.println("Amarillo");
												miConfiguracion.setConfig_ColoresTexturasNivel(5);
											} else {
												if ((anguloGrados>=181.5) && (anguloGrados<=214.5)){
													System.out.println("Sepia");
													miConfiguracion.setConfig_ColoresTexturasNivel(6);
												} else {
													if ((anguloGrados>=214.5) && (anguloGrados<=247.5)){
														System.out.println("Desenfoque");
														miConfiguracion.setConfig_ColoresTexturasNivel(7);
													} else {
														if ((anguloGrados>=247.5) && (anguloGrados<=280.5)){
															System.out.println("Pixeleado");
															miConfiguracion.setConfig_ColoresTexturasNivel(8);
														} else {
															if ((anguloGrados>=280.5) && (anguloGrados<=313.5)){
																System.out.println("Blanco y negro");
																miConfiguracion.setConfig_ColoresTexturasNivel(9);
															} else {
																if ((anguloGrados>=330.5) && (anguloGrados<=346.5)){
																	System.out.println("Grises");
																	miConfiguracion.setConfig_ColoresTexturasNivel(10);
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
					}
				} imgActual=0;
			}
		}
		  BufferedImage img=imgGral.getImage((int)(miConfiguracion.getConfig_ColoresTexturas()[0]+(num_pieza_actual*7))) ;

        g2d.drawImage(img, 655, 80, (655 + miConfiguracion.getConfig_Regla()[0]), (80 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 744, 768, null);

		if(dibujar==true) { //solo lo utilizo para configurar algunas cosas no se usa en el juego final. O SI?
			g.drawImage(imgGral.getImage(1), x, y, (x+25), (y+25), 0, 0, 1024, 768, null);
			dibujar=false;
		}
	}


}
