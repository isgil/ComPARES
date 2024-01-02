package es.um.fcd.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeSources;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;

public class ActionNew extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		if (request.getMethod().equalsIgnoreCase("POST")) {
			String sourceName = request.getParameter("source");
			// Creating new source
			if (sourceName != null) {
				FacadeSources fcSources = FacadeSources.getInstancia();
				System.out.println("Source to create: " + sourceName);
				Source source;
				try {
					source = fcSources.get(sourceName);
					if (source == null) {
						System.out.println("Success");
						fcSources.add(sourceName);
						response.setStatus(200);
					} else {
						System.out.println("Fail");
						response.setStatus(409);
					}
				} catch (DAOException e) {
					e.printStackTrace();
					response.setStatus(500);
				}
			}
			// Creating new Test
			else {
				/*
				FacadeSources fcSources = FacadeSources.getInstancia();
				// Informacion del documento
				doc = FachadaDocumentos.getInstancia().getDocumento(Integer.parseInt(idDocumento));
				peticion.setAttribute("documento", doc);
				*/
			}
			return "";
		} else {
			FacadeSources fcSources = FacadeSources.getInstancia();
			try {
				List<Source> sources = (List<Source>) fcSources.getAll();
				request.setAttribute("sources", sources);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "/WEB-INF/views/new.jsp";
		}
	}

}