package Vista;


import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;

import javax.swing.JPanel;

import Modelo.Configuracion;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	private int num_img=-1;
	private ArrayList<int[]> lista = new ArrayList<int[]>();
	private int [] info_en_el_tiempo; // 0->n�mero imagen     1->coordenada x    2->coordenada y     3->ancho del rectangulo donde se va a dibujar      4-> alto del rectangulo donde se va a dibujar	5->Transparencia
	private Configuracion miConfiguracion;

	public Mural() {
		//setBounds(0, 0, 744, 768);
		setBounds(0,0,1024,768);
	}
	
	

	public void actualizar(double x,double y, int num_id) {
		

		//this.x=(int) (x*744);
		//this.y=(int) (y*768);
		this.x=(int) (x*1024);
		this.y=(int) (y*768);
		this.num_img=num_id;
		
		repaint();
		
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g; //Lo casteamos para poder usar "alphaComposite"

		if(num_img==-1) {
			g2d.drawImage(null, x, y, null);
		}else {
			if(! lista.isEmpty()) {
				Iterator<int []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo

				while(listaIterada.hasNext()) {
					info_en_el_tiempo=listaIterada.next();
					float alpha = miConfiguracion.getConfig_Transparencia()[0];
			        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			        g2d.setComposite(alcom);
					g2d.drawImage(imgGral.getImage(info_en_el_tiempo[0]), info_en_el_tiempo[1], info_en_el_tiempo[2], info_en_el_tiempo[3], info_en_el_tiempo[4], 0, 0, 744, 768, null);
				}
			}

			int [] info_en_el_tiempo= {num_img,this.x,this.y, (this.x + miConfiguracion.getConfig_Regla()[0]), (this.y + miConfiguracion.getConfig_Regla()[0]),(int) miConfiguracion.getConfig_Transparencia()[0] };

			lista.add(info_en_el_tiempo);

	
		}
		
	}
	 
}
