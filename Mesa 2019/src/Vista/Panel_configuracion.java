package Vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modelo.*;
/**

 * Esta clase se utiliza para visualizar y controlar el panel de configuracion 

 * Para crear el diseño de los JPanel se utilizo WindowBuilder
 
 * Ver comentarios en el codigo

 * @author: Franco Pirondo, Barbara Corro, Barbara Ibañez

 */
public class Panel_configuracion extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x,y,x_cursor,y_cursor,num_img_de_configuracion,num_pieza_actual,imgActual,nivelActRegla=0,nivelActTrans=6;
	private double anguloGrados=0;
	private Configuracion miConfiguracion;
	private Fiduciales fiduciales;
	private boolean dibujar=false;
	private JLabel feedback = new JLabel();
	private String[] feedbackHerramienta= {"<html><body>Esta regla te permite modificar el tamaño de los sellos</body></html>",
			"<html><body>Esta regla te permite modificar la transparencia de los sellos</body></html> ",
			"<html><body>El selector de fondos te permite cambiarle el color al fondo</body></html>",
			"<html><body>El círculo cromático te permite darle distintos colores o texturas a los sellos</body></html>"};
	
	public Panel_configuracion(){
		//setBackground(Color.PINK);
		setBounds(512, 0, 512, 768);
		setLayout(null); //el layout por defecto centra todos los elementos, asique lo saque
		feedback.setFont(new Font("Verdana",1,20));
		feedback.setForeground(Color.white);
		feedback.setBounds(new Rectangle(40, -20, 330, 500));
	    add(feedback);
		
	}
	
	public void actualizar(int id, double x, double y, float anguloGrados ) {
		
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		//if((id>=0)&&(id<=8)){
		if((id>=108)&&(id<=116)){
			num_pieza_actual=id-108;
		}
		
		this.num_img_de_configuracion=id;
		this.anguloGrados = anguloGrados;
		repaint();
	}
	
	public void eliminarHerramienta() {
		this.num_img_de_configuracion=-1;
		repaint();
	}
	
	public void dibujar_punto(double x, double y) {
		
		this.x_cursor=(int) (x*1024);
		this.y_cursor=(int) (y*768);
		dibujar=true; 
		
		repaint();//----->Entra al repaint() antes de asignarle el numero de configuracion... De todas formas no vamos a utilizar el punto en la mesa
		
	}
	private void mostrarFeedback(){
		if(Configuracion.isSimulando()) {
			switch (num_img_de_configuracion) {
	        	case 9:{ // Tamaño
	 	        	feedback.setText(feedbackHerramienta[0]);
	 	        	break;
	 	        }
	        	case 10:{
	 	        //case 10:{ //Transparencia
	 	        	feedback.setText(feedbackHerramienta[1]);
	 	        	break;
	 	        }
	        	case 11:{ //Fondo
	 	        	feedback.setText(feedbackHerramienta[2]);
	 	          break;
	 	        }
	        	case 16:{ //Colores y texturas
	 	        	feedback.setText(feedbackHerramienta[3]);
	 	        	break;
	 	        }
	 	        default:{
	 	        	feedback.setText("<html><body> Coloca la herramienta que desees utilizar </body></html>");
	 		        break;
	 	        }
	         }
		} else {
			switch (num_img_de_configuracion) {
	        	case 117:{ //Tamaño
	 	        	feedback.setText(feedbackHerramienta[0]);
	 	        	break;
	 	        }
	        	case 118:{ //Transparencia
	 	        	feedback.setText(feedbackHerramienta[1]);
	 	        	break;
	 	        }
	        	case 119:{ //Fondo
	 	        	feedback.setText(feedbackHerramienta[2]);
	 	          break;
	 	        }
	        	case 142:{ //Colores y texturas
	 	        	feedback.setText(feedbackHerramienta[3]);
	 	        	break;
	 	        }
	 	        default:{
	 	        	feedback.setText("<html><body> Coloca la herramienta que desees utilizar </body></html>");
	 		        break;
	 	        }
	         }
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(imgGral.getFondo(), 0, 0,null);
		
		 //Feedback simulador ----------------------------------------------------------------------------------------
	     mostrarFeedback();

	      

	//Reconocimiento de la herramienta ------------------------------------------------------------------------
	   if(num_img_de_configuracion == fiduciales.getIdMarcador()[9]) { // 9 es el id de la regla
			if(dibujar==false) {
				miConfiguracion.setConfig_Regla(x,y);
			}
			g.drawImage(imgGral.getImagen_config()[nivelActRegla], (int)miConfiguracion.getConfig_Regla()[1]+90,(int) miConfiguracion.getConfig_Regla()[2]-40,(int) (miConfiguracion.getConfig_Regla()[1]+325) ,(int) (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);

			imgActual=0; //posicion de la regla en el vector
		}
		else {
			if (num_img_de_configuracion== fiduciales.getIdMarcador()[10]) { //10 es el id de las tranparencias
				if(dibujar==false) {
					miConfiguracion.setConfig_Transparencia(x,y);
				}
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1]+60, (int)miConfiguracion.getConfig_Transparencia()[2]-40, (int)(miConfiguracion.getConfig_Transparencia()[1])+485 , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);

				imgActual=1; //posicion de la herramienta de transparencia en el vector
			} 
			else {
				if (num_img_de_configuracion== fiduciales.getIdMarcador()[11]) { //11 es el id del selector de fondos
					if(dibujar==false) {
						miConfiguracion.setConfig_Fondo(x, y);
					}

					g.drawImage(imgGral.getImagen_config()[miConfiguracion.getConfig_Fondo()[0]+8], (int)miConfiguracion.getConfig_Fondo()[1]-128, (int)miConfiguracion.getConfig_Fondo()[2]-120, (int)(miConfiguracion.getConfig_Fondo()[1]+157) , (int)(miConfiguracion.getConfig_Fondo()[2] + 300), 0, 0, 512, 768, null);
					imgActual= miConfiguracion.getConfig_Fondo()[0]+8; //posicion del color seleccionado en el vector de imagenes del selector
				}
				else {
					if (num_img_de_configuracion== fiduciales.getIdMarcador()[13]) { //en la pos 13 esta el id del circulo cromatico
						if(dibujar==false) {
							miConfiguracion.setConfig_ColoresTexturas(x, y);
						}
						g.drawImage(imgGral.getImagen_config()[miConfiguracion.getConfig_ColoresTexturas()[0]+15], (int)miConfiguracion.getConfig_ColoresTexturas()[1]-130, (int)miConfiguracion.getConfig_ColoresTexturas()[2]-125, (int)(miConfiguracion.getConfig_ColoresTexturas()[1])+155 , (int)(miConfiguracion.getConfig_ColoresTexturas()[2] )+295, 0, 0, 512, 768, null);
						imgActual= miConfiguracion.getConfig_ColoresTexturas()[0]+15; //posicion del color seleccionado en el vector de imagenes
						
					}
				}
			}
		}
		//AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,miConfiguracion.getConfig_Transparencia()[0]);
       // g2d.setComposite(alcom);
	 
        
     
        //Acción de la herramienta -----------------------------------------------------------------------------
     
		if((imgActual==0)&&(dibujar==true)){
			//g.drawImage(imgGral.getImagen_config()[nivelActRegla], miConfiguracion.getConfig_Regla()[1], miConfiguracion.getConfig_Regla()[2], (miConfiguracion.getConfig_Regla()[1]+600) , (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);
			if(((x_cursor-miConfiguracion.getConfig_Regla()[1])<110)&&((x_cursor-miConfiguracion.getConfig_Regla()[1])>0)&&(((y_cursor-miConfiguracion.getConfig_Regla()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Regla()[2])>-15)){ //Hizo click en el nivel 1. 
				nivelActRegla=3;
				System.out.println("Nivel 1");
				miConfiguracion.getConfig_Regla()[0]=50;


			}else {
				if(((x_cursor-miConfiguracion.getConfig_Regla()[1])<150)&&((x_cursor-miConfiguracion.getConfig_Regla()[1])>100)&&(((y_cursor-miConfiguracion.getConfig_Regla()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Regla()[2])>-15)){ //Hizo click en el nivel 1. 
					nivelActRegla=1;
					System.out.println("Nivel 2");
					miConfiguracion.getConfig_Regla()[0]=100;

				}else
					if(((x_cursor-miConfiguracion.getConfig_Regla()[1])<200)&&((x_cursor-miConfiguracion.getConfig_Regla()[1])>150)&&(((y_cursor-miConfiguracion.getConfig_Regla()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Regla()[2])>-15)){ //Hizo click en el nivel 1. 
						nivelActRegla=0;
						System.out.println("Nivel 3");
						miConfiguracion.getConfig_Regla()[0]=190;

					}else
						if(((x_cursor-miConfiguracion.getConfig_Regla()[1])<250)&&((x_cursor-miConfiguracion.getConfig_Regla()[1])>200)&&(((y_cursor-miConfiguracion.getConfig_Regla()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Regla()[2])>-15)){ //Hizo click en el nivel 1. 	
							nivelActRegla=2;
							System.out.println("Nivel 4");
							miConfiguracion.getConfig_Regla()[0]=250;

					}else
						if(((x_cursor-miConfiguracion.getConfig_Regla()[1])<310)&&((x_cursor-miConfiguracion.getConfig_Regla()[1])>250)&&(((y_cursor-miConfiguracion.getConfig_Regla()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Regla()[2])>-15)){ //Hizo click en el nivel 1. 
							nivelActRegla=4;
							System.out.println("Nivel 5");
							miConfiguracion.getConfig_Regla()[0]=400;

						}
			
					}
		//FUNCIONANDO A MEDIASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS	
			g.drawImage(imgGral.getImagen_config()[nivelActRegla], (int)miConfiguracion.getConfig_Regla()[1]+90,(int) miConfiguracion.getConfig_Regla()[2]-40,(int) (miConfiguracion.getConfig_Regla()[1]+325) ,(int) (miConfiguracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);

			//g2d.drawImage(imgGral.getImage(0), 0, 0, (0 + miConfiguracion.getConfig_Regla()[0]), (0 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 744, 768, null);
		}
		else {
			if((imgActual==1)&&(dibujar==true)){
		       // g2d.drawImage(imgGral.getImage(0), 0, 0, (0 + miConfiguracion.getConfig_Regla()[0]), (0 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 1024, 768, null);
				//g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1], (int)miConfiguracion.getConfig_Transparencia()[2], (int)(miConfiguracion.getConfig_Transparencia()[1]+600) , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);

		        if(((x_cursor-miConfiguracion.getConfig_Transparencia()[1])<120)&&((x_cursor-miConfiguracion.getConfig_Transparencia()[1])>0)&&(((y_cursor-miConfiguracion.getConfig_Transparencia()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Transparencia()[2])>-15)){ //Hizo click en el nivel 1. 
					System.out.println("Nivel 1");
					nivelActTrans=7;
					miConfiguracion.getConfig_Transparencia()[0]=0.1f;


				}else {
					if(((x_cursor-miConfiguracion.getConfig_Transparencia()[1])>120)&&((x_cursor-miConfiguracion.getConfig_Transparencia()[1])<212)&&(((y_cursor-miConfiguracion.getConfig_Transparencia()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Transparencia()[2])>-15)) {
						System.out.println("Nivel 2");
						nivelActTrans=5;
						miConfiguracion.getConfig_Transparencia()[0]=0.5f;


					}else
						if(((x_cursor-miConfiguracion.getConfig_Transparencia()[1])>212)&&((x_cursor-miConfiguracion.getConfig_Transparencia()[1])<321)&&(((y_cursor-miConfiguracion.getConfig_Transparencia()[2])<40)&&(y_cursor-miConfiguracion.getConfig_Transparencia()[2])>-15)) {
							System.out.println("Nivel 3");
							nivelActTrans=6;
							miConfiguracion.getConfig_Transparencia()[0]=1f;


						}
				}
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)miConfiguracion.getConfig_Transparencia()[1]+75, (int)miConfiguracion.getConfig_Transparencia()[2]-40, (int)(miConfiguracion.getConfig_Transparencia()[1])+500 , (int)(miConfiguracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);

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
																System.out.println("Blanco y_cursor negro");
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
		//Imagen de previsualizacion --------------------------------------------------------
		
		BufferedImage img=imgGral.getImage((int)(miConfiguracion.getConfig_ColoresTexturas()[0]+(num_pieza_actual*11))) ;
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,miConfiguracion.getConfig_Transparencia()[0]);
	    g2d.setComposite(alcom);
		g2d.drawImage(img, 655, 80, (655 + miConfiguracion.getConfig_Regla()[0]), (80 + miConfiguracion.getConfig_Regla()[0]), 0, 0, 744, 768, null);
		alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
	    g2d.setComposite(alcom);
		if(dibujar==true) { //solo lo utilizo para configurar algunas cosas no se usa en el juego final. O SI?
			g.drawImage(imgGral.getImage(1), x_cursor, y_cursor, (x_cursor+25), (y_cursor+25), 0, 0, 1024, 768, null);
			dibujar=false;
		}
	}


}
