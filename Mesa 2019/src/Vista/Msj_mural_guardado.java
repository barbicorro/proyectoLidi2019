package Vista;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Msj_mural_guardado extends JPanel {
	public Msj_mural_guardado() {
		setLayout(null);
		setBounds(0,0,394,205);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Msj_mural_guardado.class.getResource("/Imagenes/mural_guardado.png")));
		lblNewLabel.setBounds(0, 0, 393, 204);
		add(lblNewLabel);
	}
	
	

}
