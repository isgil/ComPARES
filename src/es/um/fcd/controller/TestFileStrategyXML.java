package es.um.fcd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;

public class TestFileStrategyXML extends TestFileStrategy {

	@Override
	protected List<String> extractTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		/*
		System.setProperty("javax.xml.parsers.SAXParserFactory", "org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl");

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		System.out.println(documento.getIdioma().getCodigo());
		//ManejadorDocumentosXML manejador = new ManejadorDocumentosXML(documento.getIdioma());
		
		// Se utiliza la versión del parser con InputStream para que pueda liberarse el fichero tras ser procesado
		File file = new File(documento.getUri());
		InputStream in = new FileInputStream(file);
		InputSource inputSource = new InputSource(new InputStreamReader(in));
		//saxParser.parse(inputSource, manejador);
		in.close();

		System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		*/
		
		return null;
	}
}
