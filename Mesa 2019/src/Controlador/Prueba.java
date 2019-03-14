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


public class Prueba  extends JFrame implements TuioListener {

	private static TuioClient cliente;
	private Imagenes imgGral = new Imagenes();
	private Mural mural = new Mural();;
	private Panel_configuracion panel_configuracion = new Panel_configuracion();

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
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().add(mural);
		getContentPane().add(panel_configuracion);
	}
	
	


	@Override
	//Se llama cuando un objeto se hace visible
	public void addTuioObject(TuioObject tobj) { 
		// TODO Auto-generated method stub
		//System.out.println(tobj.getSymbolID());      
		
			//mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID());

		//Falta un tratamiento para aquellos fiducial que no se reconozcan(que no esten dentro del vector de imagenes)
	}


	@Override
	// Se llama cuando un objeto fue movido(arrastrado) sobre la superficie de la mesa
	public void updateTuioObject(TuioObject tobj) {
		// TODO Auto-generated method stub
			
			mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID());
			
		//Falta un tratamiento para aquellos fiducial que no se reconozcan(que no esten dentro del vector de imagenes)
	}


	@Override
	// Se llama cuando un objeto es removido de la mesa
	public void removeTuioObject(TuioObject tobj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	// Se llama cuando se detecta un nuevo objeto en la mesa
	public void addTuioCursor(TuioCursor tcur) {
		// TODO Auto-generated method stub
		
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
}
