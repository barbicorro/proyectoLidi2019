package Vista;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Imagenes {
	private BufferedImage image;
	
	public Imagenes()  {
		cargaImagenes();
	}
	public void cargaImagenes()  {
		try {
			
			image = ImageIO.read(new File("src/Imagenes/imagen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	


}