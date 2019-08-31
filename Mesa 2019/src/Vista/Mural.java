package Vista;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import Modelo.*;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	private double anguloRadianes=0;
	private int num_img=-1;
	private ArrayList<float []> lista = new ArrayList<float []>();
	private float [] info_en_el_tiempo; // 0->numero imagen     1->coordenada x    2->coordenada y     3->ancho del rectangulo donde se va a dibujar      4-> alto del rectangulo donde se va a dibujar	5->Transparencia 6->Angulo de rotacion 7->Color/Textura 
	private int actualBackground = 0;
	boolean ok=true;
	boolean vaciarLista=false;
	public Mural() {
		setBounds(0,0,1024,768);
		setBackground(Color.WHITE);
		setLayout(null);
	}
	
	public void restaurarFondo() {
		int color = Configuracion.getConfig_Fondo()[0];
		if (actualBackground != color || vaciarLista) {
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
	}
	
	private void capturarLista() {
		if (lista.size() == 299) {
			setBackground(new Color(255,255,255,0));
			imgGral.imprimirCap(this);
			vaciarLista = true;
			restaurarFondo();	
		}
	}
	
	private void herramientaGuardado(JPanel msj) {
		if (num_img==Fiduciales.getIdMarcador()[16]){ //guardar
			imgGral.guardarMural(this);
			msj.setVisible(true);
			try {
				Thread.sleep(1660);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			msj.setVisible(false);
		}
	}
	
	public void actualizar(double x,double y, int num_id, float anguloGrados, JPanel msj) {
		capturarLista();
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.anguloRadianes = anguloGrados*Math.PI/180;
		this.num_img=num_id;
		herramientaGuardado(msj);
		repaint();
	}
	
	private void reiniciarLista() {
		lista.clear();
		float [] info_en_el_tiempo= {-2,this.x,this.y, (this.x + Configuracion.getConfig_Regla()[0]), (this.y + Configuracion.getConfig_Regla()[0]), 1, (float)this.anguloRadianes, Configuracion.getConfig_ColoresTexturas()[0]};
		lista.add(info_en_el_tiempo);
		vaciarLista= false;
	}
	
	private void agregarSello() {
		if(!Configuracion.isSimulando()){
			num_img=num_img-108;
		}
		float [] info_en_el_tiempo= {num_img,this.x,this.y, (this.x + Configuracion.getConfig_Regla()[0]), (this.y + Configuracion.getConfig_Regla()[0]), Configuracion.getConfig_Transparencia()[0], (float)this.anguloRadianes, Configuracion.getConfig_ColoresTexturas()[0]};
		lista.add(info_en_el_tiempo);
	}
	
	private void dibujarListaSellos(Graphics2D g2d) {
		Iterator<float []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo
		while(listaIterada.hasNext()) {
			info_en_el_tiempo=listaIterada.next();
			AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,info_en_el_tiempo[5]); //Setea la transparencia del sello a dibujar
			g2d.setComposite(alcom);
			switch((int)info_en_el_tiempo[0]) {
			  case 42:{ //Lapiz
				  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],info_en_el_tiempo[1],info_en_el_tiempo[2]);
				  g2d.setTransform(tx);
				  g2d.drawImage(imgGral.getImagen_lapiz(0), (int)info_en_el_tiempo[1], (int)info_en_el_tiempo[2], (int)info_en_el_tiempo[3], (int)info_en_el_tiempo[4], 0, 0, 744, 768, null);
				  break;
			  }
			  default:{ //Fiduciales
				  if(info_en_el_tiempo[0]==-2) {
					  g2d.drawImage(imgGral.getCap(),0,0, null);
				  } else {
					  if ((int)info_en_el_tiempo[0]>=0 && (int)info_en_el_tiempo[0]<=8) {
						  BufferedImage img=imgGral.getImage((int)((info_en_el_tiempo[0]*11)+info_en_el_tiempo[7])) ;
						  AffineTransform tx = AffineTransform.getRotateInstance(info_en_el_tiempo[6],(info_en_el_tiempo[1]),(info_en_el_tiempo[2]));
						  g2d.setTransform(tx);
						  g2d.drawImage(img, (int)info_en_el_tiempo[1]-75, (int)info_en_el_tiempo[2]-75, (int)info_en_el_tiempo[3]-60, (int)info_en_el_tiempo[4]-60, 0, 0, 744, 768, null);  
					  }
				  }
			  }
			}
		}
	}

	
	public void paintComponent(Graphics g) {
		if(!Configuracion.isSalioPanel()) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g; //Lo casteamos para poder usar "alphaComposite"
			restaurarFondo(); // De ser necesario cambia el color de fondo
			if(num_img==-1) {
				g2d.drawImage(null, x, y, null);
			}else{
				if (num_img==Fiduciales.getIdMarcador()[15]){
					lista.clear();
				} else {
					if(Configuracion.isMural_activado() && !Configuracion.isCambioConfig()) {
						//if((num_img!=99) && (num_img!=90)) { //No deberia hacer falta ----------
						if(lista.size()==300 && vaciarLista) {
							reiniciarLista();
						} else { //En este caso la lista no esta llena, y la imagen es un sello o lapiz
							if(lista.size()!=300 && ((num_img>=Fiduciales.getIdMarcador()[0] && num_img<=Fiduciales.getIdMarcador()[8]) || num_img==Fiduciales.getIdMarcador()[17])) {
								agregarSello();
							 }
						} 
					} else if (Configuracion.isCambioConfig()) {Configuracion.setCambioConfig(false);}
					if(!lista.isEmpty()) {
						dibujarListaSellos(g2d);
					}
				}
			}
		} else { //Cuando se desactiva el panel de configuracion toma este camino y logra que no se repinte un sello
			Configuracion.setSalioPanel(false);
			num_img=999;
			repaint();
		}
	}	 
}
