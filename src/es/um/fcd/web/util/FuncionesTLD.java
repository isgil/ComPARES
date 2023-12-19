package es.um.fcd.web.util;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.web.model.Notificaciones;

public class FuncionesTLD {

	public static List<String> getNotificacionesExito(Notificaciones notificaciones){
		if(notificaciones == null) return new LinkedList<String>();
		return notificaciones.popAllExito();
	}
	
	public static List<String> getNotificacionesError(Notificaciones notificaciones){
		if(notificaciones == null) return new LinkedList<String>();
		return notificaciones.popAllError();
	}
	
	public static List<String> getNotificacionesAdvertencia(Notificaciones notificaciones){
		if(notificaciones == null) return new LinkedList<String>();
		return notificaciones.popAllAdvertencia();
	}
	
	public static String redondear(Double number, Integer numDecimales) {
		String format = "#.";
		for (int i=0; i<numDecimales; i++){
			format += "#";
		}
		DecimalFormat df = new DecimalFormat(format);
		//df.setRoundingMode(RoundingMode.CEILING);
		
		return df.format(number);
	}
}