package Vista;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Mural extends JPanel {
	private Imagenes imgGral = new Imagenes();
	private int x=0;
	private int y=0;
	private int num_img=-1;
	private ArrayList<int[]> lista = new ArrayList<int[]>();
	private int [] info_en_el_tiempo; // 0->número imagen     1->coordenada x    2->coordenada y

	public Mural() {
		//setBackground(Color.BLACK);
		setBounds(0, 0, 744, 768);
	
	}
	
	

	public void actualizar(double x,double y, int num_id) {
		

		this.x=(int) (x*744);
		this.y=(int) (y*768);

		this.num_img=num_id;
		
		repaint();
		
	}
	
	
	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(num_img==-1) {
			g.drawImage(null, x, y, null);
		}else {
			if(ok==true) {
				g.drawImage(imgGral.getImage(num_img), this.x, this.y, (this.x + 100), (this.y + 106), 0, 0, 744, 768, null);
				ok=false;
			}else {
				g.copyArea(0,0, 1024, 763, Math.abs((old_x-this.x)), Math.abs((old_y-this.y)));
				
			}
		//	System.out.println("x es:" + this.x + " y es:" + this.y);
			
			g.drawImage(imgGral.getImage(num_img), this.x, this.y, (this.x + 100), (this.y + 106), 0, 0, 744, 768, null);
			
			//g.copyArea(old_x, old_y, 543, 763, Math.abs((old_x-this.x)), Math.abs((old_y-this.y)));
			System.out.println((old_x) + " " + this.x);
			old_x=this.x+10;
			old_y=this.y+10;
			//System.out.println(Math.abs((old_x-this.x)) + "   " + (old_x-this.x));
		}
		
	}*/

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	

		if(num_img==-1) {
			g.drawImage(null, x, y, null);
		}else {
			if(! lista.isEmpty()) {
				Iterator<int []> listaIterada=lista.iterator();  //iteramos el ArrayList para poder recorrerlo

				while(listaIterada.hasNext()) {
					info_en_el_tiempo=listaIterada.next();
					g.drawImage(imgGral.getImage(info_en_el_tiempo[0]), info_en_el_tiempo[1], info_en_el_tiempo[2], (info_en_el_tiempo[1] + 100), (info_en_el_tiempo[2] + 106), 0, 0, 744, 768, null);

				}
			}

			g.drawImage(imgGral.getImage(num_img), this.x, this.y, (this.x + 100), (this.y + 106), 0, 0, 744, 768, null);
			int [] info_en_el_tiempo= {num_img,this.x,this.y};

			lista.add(info_en_el_tiempo);

	
		}
		
	}
	 
}
