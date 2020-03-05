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
import Modelo.*;

/**

 * Esta clase se utiliza para inicar el programa (contiene el metodo main). 
 * Para utilizar el reacTIVsion se recomienda consultar los tutoriales y ejemplos en su pagina web 
 * El proyecto usa la version 1.4 del reacTIVsion (las versiones mas actuales cambian los números de fiduciales).
 * Para crear el diseño de los JPanel se utilizo WindowBuilder 
 * @see <a href = "http://reactivision.sourceforge.net" /> Web oficial </a>
 * Este trabajo tambien utiliza en gran medida la clase graphics y JComponents junto con sus derivados. Para consultar su funcionamiento ir a la api de oracle
 * @see <a href = "https://docs.oracle.com/javase/7/docs/api/" /> Web oficial </a>
 * Por ultimo, para activar el simulador descomentar las lineas 55, 58 y  activar la simulación en el constructor de la clase configuración (linea 30)

	
 * @author: Franco Pirondo, Barbara Corro, Barbara Ibañez




 */
public class Prueba  extends JFrame implements TuioListener, ActionListener {

	private static TuioClient cliente;
	private Mural mural = new Mural();
	private Panel_configuracion panel_configuracion = new Panel_configuracion();
	private Configuracion miConfiguracion = new Configuracion();
	private Fiduciales fiduciales = new Fiduciales();
	private Msj_mural_guardado msj = new Msj_mural_guardado();
	private int numId;
	
	/**

     * El main se encarga de configurar el Reactivision. 
     * Para utilizar el simulador en la pc descomentar las lineas 55 y 58

     * @param args

     */
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
		panel_configuracion.setVisible(false);
		panel_configuracion.setOpaque(false);
	}
	
	


	
	   /**
	
     * Este Metodo se llama automaticamente por reacTIVsion cuando un objeto se hace visible
     * Para mas informacion consultar la interfaz TuioListener
     * @Override
		
     */
	public void addTuioObject(TuioObject tobj) {
		//if(Configuracion.isSimulando()) {
			if(tobj.getSymbolID()==Fiduciales.getIdMarcador()[14] && !panel_configuracion.isVisible()) {
				Configuracion.setMural_activado(false);
				panel_configuracion.setVisible(true);
			} else {
				if(((!panel_configuracion.isVisible()) && ((tobj.getSymbolID()>=Fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=Fiduciales.getIdMarcador()[8]))||(tobj.getSymbolID()==Fiduciales.getIdMarcador()[12])||(tobj.getSymbolID())==Fiduciales.getIdMarcador()[15])||(tobj.getSymbolID())==Fiduciales.getIdMarcador()[16]) {
					mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID(),tobj.getAngleDegrees(), msj);
				} else {
					if((panel_configuracion.isVisible())) {	
						if((tobj.getSymbolID()>=Fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=Fiduciales.getIdMarcador()[8])) {
							panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), 0);
						} else {
							panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
						}
					}
				}
			}
		/*} else {							//esto sirve para simular la mesa en nuestras casas
			numId=tobj.getSymbolID()+108;
			if(tobj.getSymbolID()==24) {
				numId=198;
			}
			
			if(numId==Fiduciales.getIdMarcador()[14] && !panel_configuracion.isVisible()) {
				Configuracion.setMural_activado(false);
				panel_configuracion.setVisible(true);
			} else {
				if(((!panel_configuracion.isVisible()) && ((numId>=Fiduciales.getIdMarcador()[0])&&(numId<=Fiduciales.getIdMarcador()[8]))||(numId==Fiduciales.getIdMarcador()[12])||(numId)==Fiduciales.getIdMarcador()[15])||(numId)==Fiduciales.getIdMarcador()[16]) {
					mural.actualizar(tobj.getX(), tobj.getY(),numId,tobj.getAngleDegrees(), msj);
				} else {
					if((panel_configuracion.isVisible())) {	
						if((numId>=Fiduciales.getIdMarcador()[0])&&(numId<=Fiduciales.getIdMarcador()[8])) {
							panel_configuracion.actualizar(numId, tobj.getX(), tobj.getY(), 0);
						} else {
							panel_configuracion.actualizar(numId, tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
						}
					}
				}
			}
		}*/
	}
	
  /**
	
     * Este Metodo se llama automaticamente por reacTIVsion cuando un objeto fue movido(arrastrado) sobre la superficie de la mesa
     * Para mas informacion consultar la interfaz TuioListener
     * @Override
		
     */
	
	public void updateTuioObject(TuioObject tobj) {
		//if(Configuracion.isSimulando()) {
			if(panel_configuracion.isVisible()) {
				if((tobj.getSymbolID()>=Fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=Fiduciales.getIdMarcador()[8])) {
					panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), 0);
				} else {
					panel_configuracion.actualizar(tobj.getSymbolID(), tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
				}
			} else {
				if((((tobj.getSymbolID()>=Fiduciales.getIdMarcador()[0])&&(tobj.getSymbolID()<=Fiduciales.getIdMarcador()[8]))||(tobj.getSymbolID()==Fiduciales.getIdMarcador()[12])||(tobj.getSymbolID())==Fiduciales.getIdMarcador()[15])) {
					mural.actualizar(tobj.getX(), tobj.getY(),tobj.getSymbolID(),tobj.getAngleDegrees(),msj);
				}
			}
		/*} else {                //esto sirve para simular la mesa en nuestras casas
			numId=tobj.getSymbolID()+108;
			if(panel_configuracion.isVisible()) {
				if((numId>=Fiduciales.getIdMarcador()[0])&&(numId<=Fiduciales.getIdMarcador()[8])) {
					panel_configuracion.actualizar(numId, tobj.getX(), tobj.getY(), 0);
				} else {
					panel_configuracion.actualizar(numId, tobj.getX(), tobj.getY(), tobj.getAngleDegrees());
				}
			} else {
				if((((numId>=Fiduciales.getIdMarcador()[0])&&(numId<=Fiduciales.getIdMarcador()[8]))||(numId==Fiduciales.getIdMarcador()[12])||(numId)==Fiduciales.getIdMarcador()[15])) {
					mural.actualizar(tobj.getX(), tobj.getY(),numId,tobj.getAngleDegrees(),msj);
				}
			}
		}*/
	}


  /**
	
     * Este Metodo se llama automaticamente por reacTIVsion cuando un objeto es removido de la mesa
     * Para mas informacion consultar la interfaz TuioListener
     * @Override
		
     */
	
	public void removeTuioObject(TuioObject tobj) {
		
		if(tobj.getSymbolID()==Fiduciales.getIdMarcador()[14] && panel_configuracion.isVisible() ) {
			panel_configuracion.eliminarHerramienta();
			panel_configuracion.setVisible(false);
			Configuracion.setMural_activado(true);
			Configuracion.setSalioPanel(true);
		}
	}


 /**
	
     * Este Metodo se llama automaticamente por reacTIVsion cuando se detecta un nuevo objeto(puede ser un dedo o el marcador) en la mesa.
     * Para mas informacion consultar la interfaz TuioListener
     * @Override
		
     */
	
	public void addTuioCursor(TuioCursor tcur) {
		/*if(panel_configuracion.isVisible()) {
			//panel_configuracion.actualizar(666, tcur.getX(), tcur.getY());
			panel_configuracion.dibujar_punto(tcur.getX(), tcur.getY());
		}
		else {
			mural.actualizar(tcur.getX(), tcur.getY(), Fiduciales.getIdMarcador()[17], 0,msj);
		}*/
		if(!panel_configuracion.isVisible()) {
			mural.actualizar(tcur.getX(), tcur.getY(), Fiduciales.getIdMarcador()[17], 0,msj);
		}
	}


	@Override
	public void updateTuioCursor(TuioCursor tcur) {
		if(panel_configuracion.isVisible()) {
			//panel_configuracion.actualizar(666, tcur.getX(), tcur.getY());
			panel_configuracion.dibujar_punto(tcur.getX(), tcur.getY());
		}
		else {
			mural.actualizar(tcur.getX(), tcur.getY(), Fiduciales.getIdMarcador()[17], 0,msj);
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
		
	}
	
}
