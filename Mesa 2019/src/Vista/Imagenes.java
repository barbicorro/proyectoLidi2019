package Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagenes {
	private BufferedImage[] image=new BufferedImage[8] ;
	
	private String [] rutaSellos= {"src/Imagenes/Objetos-01.png", //0- mamushka
			"src/Imagenes/Objetos-02.png",  //1- Pelota 
			"src/Imagenes/Objetos-03.png",  //2- Concha
			"src/Imagenes/Objetos-04.png",  //3- ???
			"src/Imagenes/Objetos-05.png",  //4- Caracol
			"src/Imagenes/Objetos-06.png",  //5- Rosa
			"src/Imagenes/Objetos-07.png",  //6- Flor amarilla
			"src/Imagenes/Objetos-08.png",  //7- Flor roja
			"src/Imagenes/Objetos-09.png",};  //8- Flor blanca
	
	
	
	public Imagenes()  {
		cargaImagenes();
	}
	public void cargaImagenes()  {
		try {
			for(int i=0; i<image.length; i++) {
				image[i]=ImageIO.read(new File(rutaSellos[i]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public BufferedImage getImage(int i) {
		return image[i];
	}
	public void setImage(BufferedImage image, int i) {
		this.image[i] = image;
	}
	
	
	


}