package Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagenes {
	private BufferedImage[] image=new BufferedImage[9] ;
	private BufferedImage[] imagen_config=new BufferedImage[13];
	private BufferedImage[] imagen_gomas=new BufferedImage[5];
	private BufferedImage fondo;
	
	
	private String [] rutaSellos= {"src/Imagenes/Objetos-01.png", //0- Mamushka
			"src/Imagenes/Objetos-02.png",  //1- Pelota 
			"src/Imagenes/Objetos-03.png",  //2- Caracol1
			"src/Imagenes/Objetos-04.png",  //3- Caracol2
			"src/Imagenes/Objetos-05.png",  //4- Caracol3
			"src/Imagenes/Objetos-06.png",  //5- Flor rosa
			"src/Imagenes/Objetos-07.png",  //6- Flor amarilla
			"src/Imagenes/Objetos-08.png",  //7- Flor roja
			"src/Imagenes/Objetos-09.png",};  //8- Flor blanca
	
	private String [] rutaObjetosConfiguracion= {"src/Imagenes/Regla.png",  //0
			"src/Imagenes/Transparencias.png",								//1
			"src/Imagenes/Selector-de-fondos.png",  						//2
			"src/Imagenes/Deshacer.png",  									//3
			"src/Imagenes/Goma.png",  										//4
			"src/Imagenes/Circulo-cromaticoBlanco.png",  					//5
			"src/Imagenes/Circulo-cromaticoRojo.png",  						//6
			"src/Imagenes/Circulo-cromaticoFucsia.png",  					//7
			"src/Imagenes/Circulo-cromaticoAzul.png",  						//8
			"src/Imagenes/Circulo-cromaticoCeleste.png",  					//9
			"src/Imagenes/Circulo-cromaticoVerde.png",  					//10
			"src/Imagenes/Circulo-cromaticoAmarillo.png",  					//11
			"src/Imagenes/Texturas.png",};  								//12
	
	private String [] rutaGomas= {"src/Imagenes/cuadradoblanco.png", //0
			"src/Imagenes/cuadradorojo.png",						 //1
			"src/Imagenes/cuadradoazul.png",						 //2
			"src/Imagenes/cuadradoverde.png",						 //3
			"src/Imagenes/cuadradoamarillo.png",};					 //4
	
	private String rutaFondo= "src/Imagenes/fondo_config.png";
	
	public Imagenes()  {
		cargaImagenes();
		cargarImagenesConfiguracion();
		cargarImagenesGomas();
		cargarFondo();
		
	}
	public BufferedImage getFondo() {
		return fondo;
	}
	public void setFondo(BufferedImage fondo) {
		this.fondo = fondo;
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
	public void cargarImagenesGomas()  {
		try {
				for(int i=0; i<imagen_gomas.length; i++) {
					imagen_gomas[i]=ImageIO.read(new File(rutaGomas[i]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void cargarFondo() {
		try {
			fondo=ImageIO.read(new File(rutaFondo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getImagen_gomas(int i) {
		return imagen_gomas[i];
	}
	public void setImagen_gomas(BufferedImage[] imagen_gomas) {
		this.imagen_gomas = imagen_gomas;
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