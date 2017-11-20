
import java.lang.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros = null;

	public Parser() {
		libros = new ArrayList<Libro>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("libro");
		//Si hay al menos un elemento recorremos los que hay
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (persona)
				Element elLibro = (Element) nl.item(i);
				
				
				//Forma 1 de hacer - Más corta
				String titulo=getTexto(elLibro, "titulo");
						// Ya no hace falta utilizar estos prints porque vamos a utilizar la funcion print()
						//System.out.println("El titulo es : " + titulo);
				
				
				String anyo = getAtributo(elLibro, "titulo", "anyo");
				
				//Forma 2 de hacer - Más larga
				/*
				NodeList nl2 = elLibro.getElementsByTagName("titulo");
				
				//Recorremos el elemento titulo
				if (nl2 != null && nl.getLength() > 0) {
					//Al hacer el nl2.item(0) nos saca el primer titulo del elemenos nl2 (que es el titulo)
					Element elTitulo= (Element) nl2.item(0);
					String titulo = elTitulo.getFirstChild().getNodeValue();
					System.out.println("El titulo es : " + titulo);
					
				}*/
				//Forma 2 de hacer - Más corta
				String editor = getTexto(elLibro, "editor");
						// Ya no hace falta utilizar estos prints porque vamos a utilizar la funcion print()
						//System.out.println("El editor es : " + editor);
				//Forma 1 de hacer - Más larga
				/*
				NodeList nl3 = elLibro.getElementsByTagName("editor");
				//Recorremos el elemento editor
				if (nl3 != null && nl.getLength() > 0) {
					//Al hacer el nl3.item(0) nos saca el primer editor dentro de titulo del elemenos nl3
					Element elEditor= (Element) nl3.item(0);
					String editor = elEditor.getFirstChild().getNodeValue();
					System.out.println("El editor del libro es : " + editor);
					
				}*/
				//Forma 2 de hacer - Más corta
				String paginas = getTexto(elLibro, "paginas");
						// Ya no hace falta utilizar estos prints porque vamos a utilizar la funcion print()
						//System.out.println("Paginas vale : " + paginas);
				//Forma 1 de hacer - Más larga 
				/*
				NodeList nl4 = elLibro.getElementsByTagName("paginas");
				//Recorremos el elemento editor
				if (nl4 != null && nl.getLength() > 0) {
					//Al hacer el nl3.item(0) nos saca el primer editor dentro de titulo del elemenos nl3
					Element elPag= (Element) nl4.item(0);
					String pag = elPag.getFirstChild().getNodeValue();
					System.out.println("El editor del libro es : " + pag);
					
				}*/
				
				//Se crea un arrayList vacio
				ArrayList<String> nombres = new ArrayList<String>();
				
				
				//Para poder mostrar el arrayList tenemos que hacer lo siguiente
					//Me quedo con el elemento autor
				NodeList nl2 = elLibro.getElementsByTagName("autor");
				if (nl2 != null && nl.getLength() > 0) {
					//Como solo hay un autor, lo cojo
					Element elAutor = (Element) nl2.item(0);
					//Sobre elAutor me quedo con los elementos "nombre"
					NodeList nl3 = elAutor.getElementsByTagName("nombre");
					if(nl3 != null && nl3.getLength() > 0) {
						//Como hay varios nombres, los recorro
						for (int j=0; j<nl3.getLength(); j++) {	
							//Y me quedo con uno de ellos "j"
							Element elNombre = (Element) nl3.item(j);
							//Recupero el string de cada elemento del nombre
							String nombre = elNombre.getFirstChild().getNodeValue();
									// Ya no hace falta utilizar estos prints porque vamos a utilizar la funcion print()
										//System.out.println("El nombre es : " + nombre);
							
							//Cuando reccorre el for tenemos 3 nombres que los vamos añadiendo
							nombres.add(nombre);
					
						}
					}
				}
				
				Libro l =new Libro();
				l.setTitulo(titulo);
				l.setEditor(editor);
				
				//Para convertir las paginas a int tenemos que pasarle un Integer.parseInt()
				l.setPaginas(paginas);
				
				l.setAutores(nombres);
				l.setAnyo(Integer.parseInt(anyo));
				// obtenemos una persona
				//Persona p = getPersona(el);

				// lo añadimos al array
				libros.add(l);
			}
		}
	}
	
	private String getTexto(Element e, String etiqueta) {
		NodeList nl = e.getElementsByTagName(etiqueta);
		//Recorremos el elemento editor
		if (nl != null && nl.getLength() > 0) {
			//Al hacer el nl3.item(0) nos saca el primer editor dentro de titulo del elemenos nl3
			Element subelement= (Element) nl.item(0);
			String valor = subelement.getFirstChild().getNodeValue();
			return valor;
		}
		return null;
		
		
	}
	
	
	
	private String getAtributo(Element e, String etiqueta, String atr) {
		NodeList nl = e.getElementsByTagName(etiqueta);
		//Recorremos el elemento editor
		if (nl != null && nl.getLength() > 0) {
			//Al hacer el nl3.item(0) nos saca el primer editor dentro de titulo del elemenos nl3
			Element subelement= (Element) nl.item(0);
			String valor = subelement.getAttribute(atr);
			
			return valor;
		}
		return null;
		
		
	}
	
	/*
	private Persona getPersona(Element personaEle){
		
		//para cada elemento persona, obtenemos su nombre y su edad
		String nombre = getTextValue(personaEle,"nombre");
		int edad = getIntValue(personaEle,"edad");
		
		//Creamos una nueva persona con los elementos leídos del nodo
		Persona e = new Persona(nombre,edad);

		return e;		
		
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	private int getIntValue(Element ele, String tagName) {				
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	
	*/
	public void print(){

		Iterator it = libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}
	}
	


}