package Modelo;
import java.awt.image.BufferedImage;

import Vista.Imagenes;

public class CambioColores {
	private static Imagenes imgGral = new Imagenes();
	
	public static BufferedImage generarImagenColoreada(int numColor, int numObjeto) {
		BufferedImage img = imgGral.getImage(numObjeto); 
		int newRed=0; 
        int newGreen=0; 
        int newBlue=0;
	  
	    if (numColor == 0){
	    	return img;
	    }
	    else{
	        //agarra el ancho y alto de la imagen
	        int width = img.getWidth(); 
	        int height = img.getHeight(); 
	  
	        //convierte la imagen al color elegido 
	        for(int y = 0; y < height; y++) 
	        { 
	            for(int x = 0; x < width; x++) 
	            { 
	                int p = img.getRGB(x,y); 
	  
	                int a = (p>>24)&0xff; 
	                int R = (p>>16)&0xff; 
	                int G = (p>>8)&0xff; 
	                int B = p&0xff; 
	                
	                switch (numColor) {
	                //selecciona color rojo
	                case 1:
	                {
	                	newRed = (int)(0.7*R + 0.2*G + 0.2*B); 
		                newGreen = (int)(0*R + 0*G + 0*B); 
		                newBlue = (int)(0*R + 0*G + 0*B);
	                }
	                break;
	                //selecciona color magenta
	                case 2:
	                {
	                	newRed = (int)(0.7*R + 0.2*G + 0.7*B); 
		                newGreen = (int)(0*R + 0*G + 0*B); 
		                newBlue = (int)(0.7*R + 0.2*G + 0.7*B);
	                }
	                break;
	                //selecciona color azul
	                case 3:
	                {
	                	newRed = (int)(0*R + 0*G + 0*B); 
		                newGreen = (int)(0*R + 0*G + 0*B); 
		                newBlue = (int)(0.2*R + 0.2*G + 0.7*B);
	                }
	                break;
	                //selecciona color cian
	                case 4:
	                {
	                	newRed = (int)(0*R + 0*G + 0*B); 
		                newGreen = (int)(0.2*R + 0.7*G + 0.7*B); 
		                newBlue = (int)(0.2*R + 0.7*G + 0.7*B);
	                }
	                break;
	                //selecciona color verde
	                case 5:
	                {
	                	newRed = (int)(0*R + 0*G + 0*B); 
		                newGreen = (int)(0.2*R + 0.7*G + 0.2*B); 
		                newBlue = (int)(0*R + 0*G + 0*B);
	                }
	                break;
	                //selecciona color amarillo
	                case 6:
	                {
	                	newRed = (int)(0.7*R + 0.7*G + 0.2*B); 
		                newGreen = (int)(0.7*R + 0.7*G + 0.2*B);
		                newBlue = (int)(0*R + 0*G + 0*B);
	                }
	                break;
	             
	                

	                default:

	                	System.out.println("Fallo la seleccion de color");

	                break;

	         

	         }
	                
	               
	                //chequea que no se pasen de rango 
	                if (newRed > 255) 
	                    R = 255; 
	                else
	                    R = newRed; 
	  
	                if (newGreen > 255) 
	                    G = 255; 
	                else
	                    G = newGreen; 
	  
	                if (newBlue > 255) 
	                    B = 255; 
	                else
	                    B = newBlue; 
	  
	                //coloca los nuevos valores rgb del pixel
	                p = (a<<24) | (R<<16) | (G<<8) | B; 
	  
	                img.setRGB(x, y, p); 
	            } 
	        } 
	  
	        return img; 
	    }
	  } 
	

}
