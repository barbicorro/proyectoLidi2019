package Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagenes {
	private BufferedImage[] image=new BufferedImage[63] ;
	private BufferedImage[] imagen_config=new BufferedImage[13];
	private BufferedImage[] imagen_gomas=new BufferedImage[5];
	private BufferedImage[] imagen_lapiz=new BufferedImage[7];
	private BufferedImage fondo;
	
	
	private String [] rutaSellos= {
			"src/Imagenes/Objeto0/0-0.png", //0- Mamushka
			"src/Imagenes/Objeto0/0-1.png",   
			"src/Imagenes/Objeto0/0-2.png",  
			"src/Imagenes/Objeto0/0-3.png",  
			"src/Imagenes/Objeto0/0-4.png",  
			"src/Imagenes/Objeto0/0-5.png",  
			"src/Imagenes/Objeto0/0-6.png",  
			"src/Imagenes/Objeto1/1-0.png",  //1- Pelota
			"src/Imagenes/Objeto1/1-1.png",
			"src/Imagenes/Objeto1/1-2.png",
			"src/Imagenes/Objeto1/1-3.png",
			"src/Imagenes/Objeto1/1-4.png",
			"src/Imagenes/Objeto1/1-5.png",
			"src/Imagenes/Objeto1/1-6.png",
			"src/Imagenes/Objeto2/2-0.png",  //2- Caracol1
			"src/Imagenes/Objeto2/2-1.png",
			"src/Imagenes/Objeto2/2-2.png",
			"src/Imagenes/Objeto2/2-3.png",
			"src/Imagenes/Objeto2/2-4.png",
			"src/Imagenes/Objeto2/2-5.png",
			"src/Imagenes/Objeto2/2-6.png",
			"src/Imagenes/Objeto3/3-0.png",  //3- Caracol2
			"src/Imagenes/Objeto3/3-1.png",
			"src/Imagenes/Objeto3/3-2.png",
			"src/Imagenes/Objeto3/3-3.png",
			"src/Imagenes/Objeto3/3-4.png",
			"src/Imagenes/Objeto3/3-5.png",
			"src/Imagenes/Objeto3/3-6.png",
			"src/Imagenes/Objeto4/4-0.png",  //4- Caracol3
			"src/Imagenes/Objeto4/4-1.png",
			"src/Imagenes/Objeto4/4-2.png",
			"src/Imagenes/Objeto4/4-3.png",
			"src/Imagenes/Objeto4/4-4.png",
			"src/Imagenes/Objeto4/4-5.png",
			"src/Imagenes/Objeto4/4-6.png",
			"src/Imagenes/Objeto5/5-0.png",  //5- Flor rosa
			"src/Imagenes/Objeto5/5-1.png",
			"src/Imagenes/Objeto5/5-2.png",
			"src/Imagenes/Objeto5/5-3.png",
			"src/Imagenes/Objeto5/5-4.png",
			"src/Imagenes/Objeto5/5-5.png",
			"src/Imagenes/Objeto5/5-6.png",
			"src/Imagenes/Objeto6/6-0.png",  //6- Flor amarilla
			"src/Imagenes/Objeto6/6-1.png",
			"src/Imagenes/Objeto6/6-2.png",
			"src/Imagenes/Objeto6/6-3.png",
			"src/Imagenes/Objeto6/6-4.png",
			"src/Imagenes/Objeto6/6-5.png",
			"src/Imagenes/Objeto6/6-6.png",
			"src/Imagenes/Objeto7/7-0.png",  //7- Flor Naranja
			"src/Imagenes/Objeto7/7-1.png",
			"src/Imagenes/Objeto7/7-2.png",
			"src/Imagenes/Objeto7/7-3.png",
			"src/Imagenes/Objeto7/7-4.png",
			"src/Imagenes/Objeto7/7-5.png",
			"src/Imagenes/Objeto7/7-6.png",
			"src/Imagenes/Objeto8/8-0.png",  //8- Flor Blanca
			"src/Imagenes/Objeto8/8-1.png",
			"src/Imagenes/Objeto8/8-2.png",
			"src/Imagenes/Objeto8/8-3.png",
			"src/Imagenes/Objeto8/8-4.png",
			"src/Imagenes/Objeto8/8-5.png",
			"src/Imagenes/Objeto8/8-6.png",};  
	
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
	
	private String [] rutaLapiz= {"src/Imagenes/CirculoBlanco.png", //0
			"src/Imagenes/CirculoRojo.png",							//1
			"src/Imagenes/CirculoMagenta.png",						//2
			"src/Imagenes/CirculoAzul.png",							//3
			"src/Imagenes/CirculoCian.png",							//4
			"src/Imagenes/CirculoVerde.png",						//5
			"src/Imagenes/CirculoAmarillo.png",};					//6
	
						
	private String rutaFondo= "src/Imagenes/fondo_config.png";
	
	public Imagenes()  {
		cargaImagenes();
		cargarImagenesConfiguracion();
		cargarImagenesGomas();
		cargarImagenesLapiz();
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
			e.printStackTrace();
		}
		
		
	}
	public void cargarImagenesConfiguracion()  {
		try {
			for(int i=0; i<imagen_config.length; i++) {
				imagen_config[i]=ImageIO.read(new File(rutaObjetosConfiguracion[i]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void cargarImagenesGomas()  {
		try {
				for(int i=0; i<imagen_gomas.length; i++) {
					imagen_gomas[i]=ImageIO.read(new File(rutaGomas[i]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public void cargarImagenesLapiz()  {
		try {
				for(int i=0; i<imagen_lapiz.length; i++) {
					imagen_lapiz[i]=ImageIO.read(new File(rutaLapiz[i]));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public void cargarFondo() {
		try {
			fondo=ImageIO.read(new File(rutaFondo));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImagen_gomas(int i) {
		return imagen_gomas[i];
	}
	public void setImagen_gomas(BufferedImage[] imagen_gomas) {
		this.imagen_gomas = imagen_gomas;
	}
	
	public BufferedImage getImagen_lapiz(int i) {
		return imagen_lapiz[i];
	}
	public void setImagen_lapiz(BufferedImage[] imagen_lapiz) {
		this.imagen_lapiz = imagen_lapiz;
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