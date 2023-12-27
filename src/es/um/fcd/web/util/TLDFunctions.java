package es.um.fcd.web.util;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.web.model.Notifications;

public class TLDFunctions {

	public static List<String> getNotificacionesExito(Notifications notifications){
		if(notifications == null) return new LinkedList<String>();
		return notifications.popAllSuccess();
	}
	
	public static List<String> getNotificacionesError(Notifications notifications){
		if(notifications == null) return new LinkedList<String>();
		return notifications.popAllError();
	}
	
	public static List<String> getNotificacionesAdvertencia(Notifications notifications){
		if(notifications == null) return new LinkedList<String>();
		return notifications.popAllWarning();
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