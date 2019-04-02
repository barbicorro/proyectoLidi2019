package Modelo;

import java.util.ArrayList;

public class Configuracion { //Esta clase deberia ser estatica? definir correctamente que vamos hacer ac�
	
	private static int[] config_Regla= {190,0,0};   //0-Nivel de zoom       1-eje x      2-eje y
	private static float[] config_Transparencia = {1,0,0}; //0-Nivel de opacidad   1-eje x   2-eje y
	private static int [] config_Fondo= {0,0,0};
	private static boolean mural_activado=true;

	public static boolean isMural_activado() {
		return mural_activado;
	}



	public static void setMural_activado(boolean mural_activado) {
		Configuracion.mural_activado = mural_activado;
	}



	public Configuracion()  {

	}



	public static int[] getConfig_Regla() {
		return config_Regla;
	}



	public static void setConfig_Regla(int[] config_Regla) {
		Configuracion.config_Regla = config_Regla;
	}
	
	
	
	public static float[] getConfig_Transparencia() {
		return config_Transparencia;
	}



	public static void setConfig_Transparencia(float[] config_Transparencia) {
		Configuracion.config_Transparencia = config_Transparencia;
	}
	
	public static int[] getConfig_Fondo() {
		return config_Fondo;
	}



	public static void setConfig_Fondo(int[] config_Fondo) {
		Configuracion.config_Fondo = config_Fondo;
	}




}
