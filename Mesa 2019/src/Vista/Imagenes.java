package Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagenes {
	private BufferedImage[] image=new BufferedImage[9] ;
	private BufferedImage[] imagen_config=new BufferedImage[6];
	
	
	private String [] rutaSellos= {"src/Imagenes/Objetos-01.png", //0- mamushka
			"src/Imagenes/Objetos-02.png",  //1- Pelota 
			"src/Imagenes/Objetos-03.png",  //2- Concha
			"src/Imagenes/Objetos-04.png",  //3- ???
			"src/Imagenes/Objetos-05.png",  //4- Caracol
			"src/Imagenes/Objetos-06.png",  //5- Rosa
			"src/Imagenes/Objetos-07.png",  //6- Flor amarilla
			"src/Imagenes/Objetos-08.png",  //7- Flor roja
			"src/Imagenes/Objetos-09.png",};  //8- Flor blanca
	
	private String [] rutaObjetosConfiguracion= {"src/Imagenes/Regla.png",  //0
			"src/Imagenes/Transparencias.png",								//1
			"src/Imagenes/Selector-de-fondos.png",  						//2
			"src/Imagenes/Deshacer.png",  									//3
			"src/Imagenes/Goma.png",  										//4
			"src/Imagenes/Circulo-cromatico.png",  							//5
			"src/Imagenes/Texturas.png",};  								//6
	
	
	
	public Imagenes()  {
		cargaImagenes();
		cargarImagenesConfiguracion();
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
	public void cargarImagenesConfiguracion()  {
		try {
			for(int i=0; i<imagen_config.length; i++) {
				imagen_config[i]=ImageIO.read(new File(rutaObjetosConfiguracion[i]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public BufferedImage[] getImagen_config() {
		return imagen_config;
	}
	public void setImagen_config(BufferedImage[] imagen_config) {
		this.imagen_config = imagen_config;
	}
	public BufferedImage getImage(int i) {
		return image[i];
	}
	public void setImage(BufferedImage image, int i) {
		this.image[i] = image;
	}
	
	
	


}