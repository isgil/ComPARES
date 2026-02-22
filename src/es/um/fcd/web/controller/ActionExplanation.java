package es.um.fcd.web.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadePares;
import es.um.fcd.web.model.Notifications;
import es.um.fcd.web.model.TopResultDetailed;

public class ActionExplanation extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {
		String id = request.getParameter("id");
		String[] campos = id.split("-"); 
		int testId = Integer.valueOf(campos[0]);
		int parId = Integer.valueOf(campos[1]);
		int topId = Integer.valueOf(campos[2]);
		String topResultId = testId + "-" + parId + "-" + topId;
		String indexType = campos[3];
		System.out.println("Test ID = " + testId);
		System.out.println("Par ID = " + parId);
		System.out.println("Top ID = " + topId);
		System.out.println("Index type = " + indexType);
		Map<String, TopResultDetailed> topResultsDetailed = (LinkedHashMap<String, TopResultDetailed>) request.getSession().getAttribute("topResultsDetailed");
		System.out.println("Top detail ID buscado = " + topResultId);
		for (String key : topResultsDetailed.keySet()) {
			System.out.println("key=" + key);
		}
		TopResultDetailed topResultDetailed = topResultsDetailed.get(topResultId);
		String explanation = "";
		switch(indexType) {
			case "order": 
				explanation = topResultDetailed.getOrderIndex().getExplanation();
				break;
			case "absence":
				explanation = topResultDetailed.getAbsenceIndex().getExplanation();
				break;
			case "gsfn":
				explanation = topResultDetailed.getGSFnIndex().getExplanation();
				break;
			case "combined":
				explanation = topResultDetailed.getCombinedIndex().getExplanation();
				break;
			default:
				break;
		}
		
		request.setAttribute("explanation", explanation);
		
		return "/WEB-INF/views/parts/explanation.jspf";	
	}
}