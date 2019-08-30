package Modelo;

public class Fiduciales {
	private static int [] idMarcador= new int[17];

	public Fiduciales () {
		//version para simular
		
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
		idMarcador[10] = 10; //Transparencias
		idMarcador[11] = 11; //Circulo cromatico
		idMarcador[12] = 12; //Goma
		idMarcador[13] = 16; //Circulo cromatico
		idMarcador[14] = 20; //abrir/cerrar panel
		idMarcador[15] = 24; //reset
		idMarcador[16] = 28; //guardar mural
		
		//version para la mesa        ---->Poner -108 en linea 84 mural, vengo del futuro y ya no es la 84 ahre
		                      //   ---->cambiar el case del lapiz a 42 para la mesa y 150 para simular*/
		/*						// ---->cambiar linea 84 en la clase mural (por el 130)
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
		idMarcador[12] = 120;  //Goma
		idMarcador[13] = 123; //Circulo cromatico
		idMarcador[14] = 121;	//abrir/cerrar panel
		idMarcador[15] = 198;	//reset
		idMarcador[16] = 130; //guardar 
		*/
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
		//idMarcador[16] = 13;      //guardar
		
		*/
	} 
	
	public static int[] getIdMarcador() {
		return idMarcador;
	}

	public static void setIdMarcador(int[] idMarcador) {
		Fiduciales.idMarcador = idMarcador;
	}

	
}
