package Modelo;
import Modelo.Configuracion;
/**

 * Esta clase se utiliza solo para cambiar el valor de los fiduciales segundo el ambito donde se utilicen.
 * Tambien estan implementados los fiduciales pequeños (linea 58 a 77) pero no se utilizaron debido a una baja en el rendimiento en la deteccion de objetos.

	
 * @author: Franco Pirondo, Barbara Corro, Barbara Ibañez




 */
public class Fiduciales {
	private static int [] idMarcador= new int[18];

	public Fiduciales () {
		if(Configuracion.isSimulando()) { //version para simular
			idMarcador[0] = 0;   //Mamushka
			idMarcador[1] = 1;   //Pelota
			idMarcador[2] = 2;   //Caracol1
			idMarcador[3] = 3;   //Caracol2
			idMarcador[4] = 4;   //Caracol3
			idMarcador[5] = 5;   //Flor rosa
			idMarcador[6] = 6;   //Flor amarilla
			idMarcador[7] = 7;   //Flor roja
			idMarcador[8] = 8;   //Flor blanca
			idMarcador[9] = 9;   //Regla
			idMarcador[10] = 10; //Transparencia
			idMarcador[11] = 11; //Circulo cromatico
			//el puesto 12 esta libre, era la goma
			idMarcador[13] = 16; //Circulo cromatico
			idMarcador[14] = 20; //abrir/cerrar panel
			idMarcador[15] = 24; //reset
			idMarcador[16] = 28; //guardar mural
			idMarcador[17] = 42; //lapiz
		} else { //version para la mesa
			idMarcador[0] = 108;   //Mamushka
			idMarcador[1] = 109;   //Pelota
			idMarcador[2] = 110;   //Caracol1
			idMarcador[3] = 111;   //Caracol2
			idMarcador[4] = 112;   //Caracol3
			idMarcador[5] = 113;   //Flor rosa
			idMarcador[6] = 114;   //Flor amarilla
			idMarcador[7] = 115;   //Flor roja
			idMarcador[8] = 116;   //Flor blanca
			idMarcador[9] = 117;   //Regla
			idMarcador[10] = 118;  //Transparencias
			idMarcador[11] = 119;  //Selector de fondos
			//el puesto 12 esta libre, era la goma
			idMarcador[13] = 123; //Circulo cromatico
			idMarcador[14] = 121;	//abrir/cerrar panel
			idMarcador[15] = 124;	//reset
			idMarcador[16] = 130; //guardar
			idMarcador[17] = 150; //lapiz 
		}
		
		//Fiducial chicos
		/*
		idMarcador[0] = 0;   //Mamushka
		idMarcador[1] = 1;   //Pelota
		idMarcador[2] = 2;   //Caracol1
		idMarcador[3] = 3;   //Caracol2
		idMarcador[4] = 4;   //Caracol3
		idMarcador[5] = 5;   //Flor rosa
		idMarcador[6] = 6;   //Flor amarilla
		idMarcador[7] = 7;   //Flor roja
		idMarcador[8] = 8;   //Flor blanca
		idMarcador[9] = 9;   //Regla
		idMarcador[10] = 10;  //Transparencias
		idMarcador[11] = 11;  //Selector de fondos
		idMarcador[12] = 12;  //Goma
		idMarcador[13] = 14; //Circulo cromatico
		idMarcador[14] = 15;	//abrir/cerrar panel
		idMarcador[15] = 16;	//reset
		idMarcador[16] = 13;      //guardar
		
		*/
	} 
	
	public static int[] getIdMarcador() {
		return idMarcador;
	}
}
