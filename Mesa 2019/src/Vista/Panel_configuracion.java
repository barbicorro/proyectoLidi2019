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

public class Panel_configuracion extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x, y, num_img_de_configuracion, num_pieza_actual=0, imgActual, nivelActRegla=0, nivelActTrans=6;
	private double anguloGrados=0;
	private boolean dibujar=false;
	private JLabel feedback = new JLabel();
	private String[] feedbackHerramienta= {"<html><body>Esta regla te permite modificar el tamaño de los sellos</body></html>",
			"<html><body>Esta regla te permite modificar la transparencia de los sellos</body></html> ",
			"<html><body>El selector de fondos te permite cambiarle el color al fondo</body></html>",
			"<html><body>El círculo cromático te permite darle distintos colores o texturas a los sellos</body></html>"};
	
	public Panel_configuracion(){
		setBounds(512, 0, 512, 768);
		setLayout(null);
		feedback.setFont(new Font("Verdana",1,20));
		feedback.setForeground(Color.white);
		feedback.setBounds(new Rectangle(40, -20, 330, 500));
	    add(feedback);
		
	}
	
	public void actualizar(int id, double x, double y, float anguloGrados ) {
		if(Configuracion.isSimulando()){
			if((id>=Fiduciales.getIdMarcador()[0])&&(id<=Fiduciales.getIdMarcador()[8])){
				num_pieza_actual=id;
			} else {
				this.x=(int) (x*1024);
				this.y=(int) (y*768);
				this.anguloGrados = anguloGrados;
				num_img_de_configuracion=id;
			}
		} else {
			if((id>=Fiduciales.getIdMarcador()[0])&&(id<=Fiduciales.getIdMarcador()[8])){
				num_pieza_actual=id-108;
			} else {
				this.x=(int) (x*1024);
				this.y=(int) (y*768);
				this.anguloGrados = anguloGrados;
				num_img_de_configuracion=id-108;
			}
		}
		repaint();
	}
	
	public void eliminarHerramienta() {
		this.num_img_de_configuracion=-1;
		repaint();
	}
	
	public void dibujar_punto(double x, double y) {
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
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
	
	private void seleccionTamaño() {
		if(((x-Configuracion.getConfig_Regla()[1])<110)&&((x-Configuracion.getConfig_Regla()[1])>0)&&(((y-Configuracion.getConfig_Regla()[2])<40)&&(y-Configuracion.getConfig_Regla()[2])>-15)){ //Selecciono el nivel 1. 
			nivelActRegla=3;
			Configuracion.getConfig_Regla()[0]=50;
		} else {
			if(((x-Configuracion.getConfig_Regla()[1])<150)&&((x-Configuracion.getConfig_Regla()[1])>100)&&(((y-Configuracion.getConfig_Regla()[2])<40)&&(y-Configuracion.getConfig_Regla()[2])>-15)){ //Selecciono el nivel 2. 
				nivelActRegla=1;
				Configuracion.getConfig_Regla()[0]=100;
			} else
				if(((x-Configuracion.getConfig_Regla()[1])<200)&&((x-Configuracion.getConfig_Regla()[1])>150)&&(((y-Configuracion.getConfig_Regla()[2])<40)&&(y-Configuracion.getConfig_Regla()[2])>-15)){ //Selecciono el nivel 3. 
					nivelActRegla=0;
					Configuracion.getConfig_Regla()[0]=190;
				} else
					if(((x-Configuracion.getConfig_Regla()[1])<250)&&((x-Configuracion.getConfig_Regla()[1])>200)&&(((y-Configuracion.getConfig_Regla()[2])<40)&&(y-Configuracion.getConfig_Regla()[2])>-15)){ //Selecciono el nivel 4. 	
						nivelActRegla=2;
						Configuracion.getConfig_Regla()[0]=250;
				} else
					if(((x-Configuracion.getConfig_Regla()[1])<310)&&((x-Configuracion.getConfig_Regla()[1])>250)&&(((y-Configuracion.getConfig_Regla()[2])<40)&&(y-Configuracion.getConfig_Regla()[2])>-15)){ //Selecciono el nivel 5. 
						nivelActRegla=4;
						Configuracion.getConfig_Regla()[0]=400;
					}
		}
	}
	
	private void seleccionTransparencia() {
		if(((x-Configuracion.getConfig_Transparencia()[1])<120)&&((x-Configuracion.getConfig_Transparencia()[1])>0)&&(((y-Configuracion.getConfig_Transparencia()[2])<40)&&(y-Configuracion.getConfig_Transparencia()[2])>0)){
			nivelActTrans=7;
			Configuracion.getConfig_Transparencia()[0]=0.1f;
		} else {
			if(((x-Configuracion.getConfig_Transparencia()[1])>120)&&((x-Configuracion.getConfig_Transparencia()[1])<212)&&(((y-Configuracion.getConfig_Transparencia()[2])<40)&&(y-Configuracion.getConfig_Transparencia()[2])>0)) {
				nivelActTrans=5;
				Configuracion.getConfig_Transparencia()[0]=0.5f;
			} else
				if(((x-Configuracion.getConfig_Transparencia()[1])>212)&&((x-Configuracion.getConfig_Transparencia()[1])<321)&&(((y-Configuracion.getConfig_Transparencia()[2])<40)&&(y-Configuracion.getConfig_Transparencia()[2])>0)) {
					nivelActTrans=6;
					Configuracion.getConfig_Transparencia()[0]=1f;
				}
		}
	}
	
	private void seleccionFondo() {
		if (((anguloGrados>325) && (anguloGrados<=360)) || ((anguloGrados>=0)&&(anguloGrados<25))){
			Configuracion.setConfig_FondoNivel(0); //Blanco
		} else 
			if ((anguloGrados>=25) && (anguloGrados<75)){
				Configuracion.setConfig_FondoNivel(1); //Rojo
			} else 
				if ((anguloGrados>=75) && (anguloGrados<125)){
					Configuracion.setConfig_FondoNivel(2); //Magenta
				} else 
					if ((anguloGrados>=125) && (anguloGrados<175)){
						Configuracion.setConfig_FondoNivel(3); //Azul
					} else 
						if ((anguloGrados>=175) && (anguloGrados<225)){
							Configuracion.setConfig_FondoNivel(4); //Cian
						} else 
							if ((anguloGrados>=225) && (anguloGrados<275)){
								Configuracion.setConfig_FondoNivel(5); //Verde
							} else 
								if ((anguloGrados>=275) && (anguloGrados<=325)){
									Configuracion.setConfig_FondoNivel(6); //Amarillo
								}
	}
	
	private void seleccionColorTextura() {
		if (((anguloGrados>346.5) && (anguloGrados<=360)) || ((anguloGrados>=0)&&(anguloGrados<16.5))){
			Configuracion.setConfig_ColoresTexturasNivel(0); //Blanco
		} else 
			if ((anguloGrados>=16.5) && (anguloGrados<49.5)){
				Configuracion.setConfig_ColoresTexturasNivel(1); //Rojo
			} else 
				if ((anguloGrados>=49.5) && (anguloGrados<82.5)){
					Configuracion.setConfig_ColoresTexturasNivel(2); //Magenta
				} else 
					if ((anguloGrados>=82.5) && (anguloGrados<115.5)){
						Configuracion.setConfig_ColoresTexturasNivel(3); //Azul
					} else 
						if ((anguloGrados>=115.5) && (anguloGrados<148.5)){
							Configuracion.setConfig_ColoresTexturasNivel(4); //Verde
						} else 
							if ((anguloGrados>=148.5) && (anguloGrados<181.5)){
								Configuracion.setConfig_ColoresTexturasNivel(5); //Amarillo
							} else 
								if ((anguloGrados>=181.5) && (anguloGrados<=214.5)){
									Configuracion.setConfig_ColoresTexturasNivel(6); //Sepia
								} else 
									if ((anguloGrados>=214.5) && (anguloGrados<=247.5)){
										Configuracion.setConfig_ColoresTexturasNivel(7); //Desenfoque
									} else 
										if ((anguloGrados>=247.5) && (anguloGrados<=280.5)){
											Configuracion.setConfig_ColoresTexturasNivel(8); //Pixeleado
										} else 
											if ((anguloGrados>=280.5) && (anguloGrados<=313.5)){
												Configuracion.setConfig_ColoresTexturasNivel(9); //Blanco y negro
											} else 
												if ((anguloGrados>=330.5) && (anguloGrados<=346.5)){
													Configuracion.setConfig_ColoresTexturasNivel(10); //Grises
												} 
	}
	
	private void reconocerHerramienta(Graphics g){
		if (num_img_de_configuracion == Fiduciales.getIdMarcador()[9]) { // Tamaño
			if(dibujar==false) {Configuracion.setConfig_Regla(x,y);}
			g.drawImage(imgGral.getImagen_config()[nivelActRegla], (int)Configuracion.getConfig_Regla()[1]+90,(int) Configuracion.getConfig_Regla()[2]-40,(int) (Configuracion.getConfig_Regla()[1]+325) ,(int) (Configuracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);
			imgActual=0;
		} else {
			if (num_img_de_configuracion == Fiduciales.getIdMarcador()[10]) { //Tranparencias
				if(dibujar==false) {Configuracion.setConfig_Transparencia(x,y);}
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)Configuracion.getConfig_Transparencia()[1]+75, (int)Configuracion.getConfig_Transparencia()[2]-40, (int)(Configuracion.getConfig_Transparencia()[1])+500 , (int)(Configuracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);
				imgActual=1;
			} else {
				if (num_img_de_configuracion == Fiduciales.getIdMarcador()[11]) { //Selector de fondos
					if(dibujar==false) {Configuracion.setConfig_Fondo(x,y);}
					g.drawImage(imgGral.getImagen_config()[Configuracion.getConfig_Fondo()[0]+8], (int)Configuracion.getConfig_Fondo()[1], (int)Configuracion.getConfig_Fondo()[2], (int)(Configuracion.getConfig_Fondo()[1]+500) , (int)(Configuracion.getConfig_Fondo()[2] + 700), 0, 0, 512, 768, null);
					imgActual= Configuracion.getConfig_Fondo()[0]+8; //Posicion del color seleccionado en el vector de imagenes del selector
				} else {
					if (num_img_de_configuracion == Fiduciales.getIdMarcador()[13]) { //Circulo cromatico
						if(dibujar==false) {Configuracion.setConfig_ColoresTexturas(x,y);}
						g.drawImage(imgGral.getImagen_config()[Configuracion.getConfig_ColoresTexturas()[0]+15], (int)Configuracion.getConfig_ColoresTexturas()[1], (int)Configuracion.getConfig_ColoresTexturas()[2], (int)(Configuracion.getConfig_ColoresTexturas()[1]+500) , (int)(Configuracion.getConfig_ColoresTexturas()[2] + 700), 0, 0, 512, 768, null);
						imgActual= Configuracion.getConfig_ColoresTexturas()[0]+15; //Posicion del color seleccionado en el vector de imagenes
					}
				}
			}
		}
	}
	
	private void accionHerramienta(Graphics g){
		if((imgActual==0)&&(dibujar==true)){
			seleccionTamaño();
			//FUNCIONANDO A MEDIASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS	
			g.drawImage(imgGral.getImagen_config()[nivelActRegla], (int)Configuracion.getConfig_Regla()[1]+90,(int) Configuracion.getConfig_Regla()[2]-40,(int) (Configuracion.getConfig_Regla()[1]+325) ,(int) (Configuracion.getConfig_Regla()[2] + 300), 0, 0, 1024, 768, null);
		} else {
			if((imgActual==1)&&(dibujar==true)){
				seleccionTransparencia();
				g.drawImage(imgGral.getImagen_config()[nivelActTrans], (int)Configuracion.getConfig_Transparencia()[1]+75, (int)Configuracion.getConfig_Transparencia()[2]-40, (int)(Configuracion.getConfig_Transparencia()[1])+500 , (int)(Configuracion.getConfig_Transparencia()[2] + 300), 0, 0, 1024, 768, null);
			} else {
				if((imgActual>=8)&&(imgActual<=14)){
					seleccionFondo();
				} else {
					if((imgActual>=15)&&(imgActual<=25)){
						seleccionColorTextura();
					}
				} imgActual=0;
			}
		}
	}
	
	private void imagenPrevisualizacion(Graphics2D g2d) {
		BufferedImage img=imgGral.getImage((int)(Configuracion.getConfig_ColoresTexturas()[0]+(num_pieza_actual*11))) ;
		AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,Configuracion.getConfig_Transparencia()[0]);
	    g2d.setComposite(alcom);
		g2d.drawImage(img, 655, 80, (655 + Configuracion.getConfig_Regla()[0]), (80 + Configuracion.getConfig_Regla()[0]), 0, 0, 744, 768, null);
		alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1); //Restaura la transparencia del panel
	    g2d.setComposite(alcom);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(imgGral.getFondo(),0,0,null);
		mostrarFeedback();  //Feedback simulador
		reconocerHerramienta(g);
	    accionHerramienta(g);
	    imagenPrevisualizacion(g2d);
		if(dibujar==true) { //solo lo utilizo para configurar algunas cosas no se usa en el juego final. O SI?
			g.drawImage(imgGral.getImage(1), x, y, (x+25), (y+25), 0, 0, 1024, 768, null);
			dibujar=false;
		}
	}


}
