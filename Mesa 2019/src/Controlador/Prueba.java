package Controlador;

import javax.swing.JFrame;
import java.io.*;
import TUIO.TuioBlob;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioSimulator;
import TUIO.TuioTime;
import Vista.Imagenes;
import Vista.Mural;
import Vista.Panel_configuracion;
import TUIO.TuioListener;
import TUIO.TuioObject;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;


public class Prueba  extends JFrame implements TuioListener, ActionListener {

	private static TuioClient cliente;
	private Mural mural = new Mural();;
	private Panel_configuracion panel_configuracion = new Panel_configuracion();
	private JButton btnActivarPanel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TuioSimulator simulador = new TuioSimulator();
		File archconfig = new File("config.xml");
		String[] argv = { "-host", "127.0.0.1", "-port", "3333", "-config", archconfig.getAbsolutePath() };
		simulador.main(argv);
		final TuioClient cliente = new TuioClient();
		cliente.connect();
		Prueba carga = new Prueba(cliente);
	}
	
	
	public Prueba(TuioClient cliente) {
		this.cliente = cliente;
		cliente.addTuioListener(this);
		getContentPane().setLayout(null);
		setBounds(0, 0, 1024, 768);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().add(panel_configuracion);
		getContentPane().add(mural);
		
		btnActivarPanel = new JButton("Activar panel de configuraci\u00F3n");
		btnActivarPanel.setSize(400, 60);
		mural.add(btnActivarPanel);
		btnActivarPanel.addActionListener(this);
		panel_configuracion.setVisible(false);
		
		
	}
	
	


	@Override
	//Se llama cuando un objeto se hace visible
	public void addTuioObject(TuioObject tobj) { 
		// TODO Auto-generated method stub
		//System.out.println(tobj.getSymbolID());      
		if((tobj.getX()>0.5)&&(panel_configuracion.isVisible())) {
			panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY());
		}
		//Falta un tratamiento para aquellos fiducial que no se reconozcan(que no esten dentro del vector de imagenes)
	}


	@Override
	// Se llama cuando un objeto fue movido(arrastrado) sobre la superficie de la mesa
	public void updateTuioObject(TuioObject tobj) {
		// TODO Auto-generated method stub
		if((tobj.getX()>0.5)&&(panel_configuracion.isVisible())) {
			panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY());
			
		}else {
			if((tobj.getSymbolID()>=0)&&(tobj.getSymbolID()<=8)) {
				panel_configuracion.setVisible(false);
				mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID());
			}
		}
			
			
	}


	@Override
	// Se llama cuando un objeto es removido de la mesa
	public void removeTuioObject(TuioObject tobj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	// Se llama cuando se detecta un nuevo objeto(puede ser un dedo o el marcador) en la mesa
	public void addTuioCursor(TuioCursor tcur) {
		// TODO Auto-generated method stub
	
		if((tcur.getX()>0.5)&&(panel_configuracion.isVisible())) {
			//panel_configuracion.actualizar(666, tcur.getX(), tcur.getY());
			panel_configuracion.dibujar_punto(tcur.getX(), tcur.getY());
		}
	}


	@Override
	public void updateTuioCursor(TuioCursor tcur) {
		// TODO Auto-generated method stub
	}


	@Override
	public void removeTuioCursor(TuioCursor tcur) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addTuioBlob(TuioBlob tblb) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateTuioBlob(TuioBlob tblb) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeTuioBlob(TuioBlob tblb) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void refresh(TuioTime ftime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnActivarPanel) {
			panel_configuracion.setVisible(true);
			
		}
		
	}
}
