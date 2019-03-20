package Controlador;

public class Configuracion { //Esta clase deberia ser estatica? definir correctamente que vamos hacer acá
	
	private static int[] config_Regla= {190,0,0};   //0-Nivel de zoom       1-eje x      2-eje y
	
	
	public Configuracion()  {

	}



	public static int[] getConfig_Regla() {
		return config_Regla;
	}



	public static void setConfig_Regla(int[] config_Regla) {
		Configuracion.config_Regla = config_Regla;
	}
	

	
}
