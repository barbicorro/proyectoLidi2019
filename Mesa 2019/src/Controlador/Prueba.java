package Controlador;

import javax.swing.JFrame;

import java.io.*;
import TUIO.TuioBlob;
import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioSimulator;
import TUIO.TuioTime;
import Vista.Msj_mural_guardado;
import Vista.Mural;
import Vista.Panel_configuracion;
import TUIO.TuioListener;
import TUIO.TuioObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Modelo.*;


public class Prueba  extends JFrame implements TuioListener, ActionListener {

	private static TuioClient cliente;
	private Mural mural = new Mural();;
	private Panel_configuracion panel_configuracion = new Panel_configuracion();
	private Configuracion miConfiguracion;
	private JButton btnActivarPanel;
	private Fiduciales fiduciales = new Fiduciales();
	private Msj_mural_guardado msj = new Msj_mural_guardado();
	private final JButton btnDesactivarPanel = new JButton("Desactivar Panel");

	public static void main(String[] args) {   
		//TuioSimulator simulador = new TuioSimulator();
		File archconfig = new File("config.xml");
		String[] argv = { "-host", "127.0.0.1", "-port", "3333", "-config", archconfig.getAbsolutePath() };
		//simulador.main(argv);
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
		this.setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		panel_configuracion.setSize(1024, 768);
		panel_configuracion.setLocation(0, 0);
		getContentPane().add(msj);
		getContentPane().add(panel_configuracion);
		getContentPane().add(mural);
		msj.setVisible(false);
		
		
		btnActivarPanel = new JButton("Activar panel de configuraci\u00F3n");
		btnActivarPanel.setSize(400, 60);
		//mural.add(btnActivarPanel);
		btnActivarPanel.addActionListener(this);
		panel_configuracion.setVisible(false);
		panel_configuracion.setOpaque(false);
		btnDesactivarPanel.setVisible(false);
		btnDesactivarPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnDesactivarPanel) {
					panel_configuracion.setVisible(false);
					btnDesactivarPanel.setVisible(false);
					btnActivarPanel.setVisible(true);
					miConfiguracion.setMural_activado(true);
				}
			}
		});
		panel_configuracion.add(btnDesactivarPanel);
	}
	
	


	@Override
	//Se llama cuando un objeto se hace visible
	public void addTuioObject(TuioObject tobj) { 
		
		if(tobj.getSymbolID()==fiduciales.getIdMarcador()[14] && !panel_configuracion.isVisible()) {
				miConfiguracion.setMural_activado(false);
				panel_configuracion.setVisible(true);
		} else {
			if(((!panel_configuracion.isVisible()) && ((tobj.getSymbolID()>=fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=fiduciales.getIdMarcador()[8]))||(tobj.getSymbolID()==fiduciales.getIdMarcador()[12])||(tobj.getSymbolID())==fiduciales.getIdMarcador()[15])||(tobj.getSymbolID())==fiduciales.getIdMarcador()[16]) {
				mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID(),tobj.getAngleDegrees(), msj);
				
			}else {
				if((panel_configuracion.isVisible())) {	
					panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
				}
			}
		}
	}
	
	@Override
	// Se llama cuando un objeto fue movido(arrastrado) sobre la superficie de la mesa
	public void updateTuioObject(TuioObject tobj) {
		
		if(panel_configuracion.isVisible()) {
			panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
		} else {
			if((((tobj.getSymbolID()>=fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=fiduciales.getIdMarcador()[8]))||(tobj.getSymbolID()==fiduciales.getIdMarcador()[12])||(tobj.getSymbolID())==fiduciales.getIdMarcador()[15])) {
				mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID(),tobj.getAngleDegrees(),msj);
			}
		}
	}


	@Override
	// Se llama cuando un objeto es removido de la mesa
	public void removeTuioObject(TuioObject tobj) {
		if(tobj.getSymbolID()>=fiduciales.getIdMarcador()[9] && panel_configuracion.isVisible()) {
			/*miConfiguracion.setMural_activado(true);
			miConfiguracion.setSalioPanel(true);
			panel_configuracion.setVisible(false);*/
			panel_configuracion.eliminarHerramienta();
			if(tobj.getSymbolID()==fiduciales.getIdMarcador()[14]) {
				panel_configuracion.setVisible(false);
			}
		}
	}


	@Override
	// Se llama cuando se detecta un nuevo objeto(puede ser un dedo o el marcador) en la mesa
	public void addTuioCursor(TuioCursor tcur) {
		/*if(panel_configuracion.isVisible()) {
			//panel_configuracion.actualizar(666, tcur.getX(), tcur.getY());
			panel_configuracion.dibujar_punto(tcur.getX(), tcur.getY());
		}
		else {
			//el cursor tiene id = 0 siempre, pero mando 150 para que no se confunda con la mamushka
			mural.actualizar(tcur.getX(), tcur.getY(), 150, 0,msj);
		}*/
	}


	@Override
	public void updateTuioCursor(TuioCursor tcur) {
		if(panel_configuracion.isVisible()) {
			//panel_configuracion.actualizar(666, tcur.getX(), tcur.getY());
			panel_configuracion.dibujar_punto(tcur.getX(), tcur.getY());
		}
		else {
			//el cursor tiene id = 0 siempre, pero mando 150 para que no se confunda con la mamushka
			mural.actualizar(tcur.getX(), tcur.getY(), 150, 0,msj);
		}
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
			miConfiguracion.setMural_activado(false);

			panel_configuracion.setVisible(true);
			btnActivarPanel.setVisible(false);
			btnDesactivarPanel.setVisible(true);
		}
		
	}
}
