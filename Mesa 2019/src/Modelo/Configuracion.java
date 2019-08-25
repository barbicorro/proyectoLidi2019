package Modelo;

import java.util.ArrayList;

public class Configuracion { //Esta clase deberia ser estatica? definir correctamente que vamos hacer ac�
	
	private static int[] config_Regla= {190,0,0};   //0-Nivel de zoom       1-eje x      2-eje y
	private static float[] config_Transparencia = {1,0,0}; //0-Nivel de opacidad   1-eje x   2-eje y
	private static int [] config_Fondo= {0,0,0};
	private static int [] config_ColoresTexturas= {0,0,0}; //0-ColorOTextura  1-eje x  2-eje y 
	private static boolean cambioConfig;
	private static boolean salioPanel;

	private static boolean mural_activado=true;

	public static boolean isMural_activado() {
		return mural_activado;
	}



	public static void setMural_activado(boolean mural_activado) {
		Configuracion.mural_activado = mural_activado;
	}



	public Configuracion()  {
		cambioConfig = false;
		salioPanel = false;
	}



	public static boolean isSalioPanel() {
		return salioPanel;
	}



	public static void setSalioPanel(boolean salioPanel) {
		Configuracion.salioPanel = salioPanel;
	}



	public static int[] getConfig_Regla() {
		return config_Regla;
	}



	public static void setConfig_Regla(int x,int y) {
		Configuracion.config_Regla[1] = x;
		Configuracion.config_Regla[2] = y;
	}
	
	public static void setConfig_ReglaNivel(int nivel) {
		Configuracion.config_Regla[0] = nivel;
		cambioConfig = true;
	}
	
	
	
	public static float[] getConfig_Transparencia() {
		return config_Transparencia;
	}



	public static void setConfig_Transparencia(float x, float y) {
		Configuracion.config_Transparencia[1] = x;
		Configuracion.config_Transparencia[2] = y;
	}
	
	public static void setConfig_TransparenciaNivel(float nivel) {
		Configuracion.config_Transparencia[0] = nivel;
		cambioConfig = true;
	}
	
	public static int[] getConfig_Fondo() {
		return config_Fondo;
	}
	
	public static void setConfig_Fondo(int x, int y) {
		Configuracion.config_Fondo[1] = x;
		Configuracion.config_Fondo[2] = y;
	}

	public static void setConfig_FondoNivel(int nivel) {
		Configuracion.config_Fondo[0] = nivel;
		cambioConfig = true;
	}
	
	
	public static int[] getConfig_ColoresTexturas() {
		return config_ColoresTexturas;
	}

	public static void setConfig_ColoresTexturas(int x, int y) {
		Configuracion.config_ColoresTexturas[1] = x;
		Configuracion.config_ColoresTexturas[2] = y;
	}
	
	public static void setConfig_ColoresTexturasNivel(int nivel) {
		Configuracion.config_ColoresTexturas[0] = nivel;
		cambioConfig = true;
	}



	public static boolean isCambioConfig() {
		return cambioConfig;
	}



	public static void setCambioConfig(boolean cambioConfig) {
		Configuracion.cambioConfig = cambioConfig;
	}


}
