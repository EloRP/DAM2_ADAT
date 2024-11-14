package UD2.ACTIV3UD2XPATH_RodalPerezEloy.EJ1_A3UD2;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Comparator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EJ1_A3UD2 {
    public static void main(String[] args) {
        // a)
        Document dom = crearDocumentoConXSD("UD2\\A3UD2\\EJ1_A3UD2\\BibliotecaInformatica.xml");

        // b)
        String seccion = "Programación";
        mostrarLibrosSeccion(dom, seccion);

        // c)
        visualizarLibrosSeccionOrdenados(dom);

        // d)
        String isbnLibroAModificar = "968-2224444444";
        int numCopia = 1;
        String estado = "Prestado";
        modificarEstadoCopia(dom, isbnLibroAModificar, numCopia, estado);

        // e)
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Pepe Pérez");
        autores.add("Juan Juanes");
        autores.add("Luis López");

        insertarLibro(dom, "A309", "978-84-368-0000-0", "Java 11", 500, autores, "01-01-2021",
                "Anaya", 50.0, "Programación");
        grabarDOM(dom);
    }

    private static void insertarLibro(Document dom, String id, String isbn, String titulo, int numPaginas,
            ArrayList<String> listaAutores, String fechaEdicionString, String editorial, double precio,
            String nombreSeccion) {
        NodeList librosExistentes = dom.getElementsByTagName("libro");
        for (int i = 0; i < librosExistentes.getLength(); i++) {
            Element libroExistente = (Element) librosExistentes.item(i);
            if (libroExistente.getAttribute("ID").equals(id)) {
                System.out.println("Ya existe un libro con el ID: " + id);
                return;
            }
        }

        Element libro = dom.createElement("libro");
        libro.setAttribute("ID", id);
        libro.setAttribute("isbn", isbn);
        libro.setAttribute("titulo", titulo);
        libro.setAttribute("numero_de_paginas", String.valueOf(numPaginas));

        Element autores = dom.createElement("autores");
        for (String autor : listaAutores) {
            Element autorElem = dom.createElement("autor");
            autorElem.setTextContent(autor);
            autores.appendChild(autorElem);
        }
        libro.appendChild(autores);

        Element fechaEdicion = dom.createElement("fechaEdicion");
        fechaEdicion.setTextContent(fechaEdicionString);
        libro.appendChild(fechaEdicion);

        Element editorialElem = dom.createElement("editorial");
        editorialElem.setTextContent(editorial);
        libro.appendChild(editorialElem);

        Element precioElem = dom.createElement("precio");
        precioElem.setTextContent(String.valueOf(precio));
        libro.appendChild(precioElem);

        Element copias = dom.createElement("copias");
        libro.appendChild(copias);

        Element copia = dom.createElement("copia");
        copia.setAttribute("numero", "1");
        copia.setAttribute("estado", "Disponible");
        copias.appendChild(copia);

        Node seccion = getSeccion(dom, nombreSeccion);

        if (seccion != null) {
            seccion.getFirstChild().appendChild(libro);
            System.out.println("Libro insertado con éxito.");
        } else {
            Node secciones = dom.getElementsByTagName("secciones").item(0);
            Element seccionElem = dom.createElement("seccion");
            seccionElem.setAttribute("nombre", nombreSeccion);
            Element libros = dom.createElement("libros");
            seccionElem.appendChild(libros);
            libros.appendChild(libro);
            secciones.appendChild(seccionElem);

            System.out.println("Sección y libro insertados con éxito.");
        }
    }

    private static void mostrarLibrosSeccion(Document dom, String seccionAMostrar) {
        NodeList secciones = dom.getElementsByTagName("seccion");
        Node seccion = null;

        for (int i = 0; i < secciones.getLength(); i++) {
            String nombreSeccion = ((Element) secciones.item(i)).getAttribute("nombre");
            if (nombreSeccion.equals(seccionAMostrar)) {
                seccion = secciones.item(i);
                break;
            }
        }

        if (seccion != null) {
            mostrarLibros(seccion);
        } else {
            System.out.println("No existe esa sección.");
        }
    }

    private static void mostrarLibros(Node seccion) {
        NodeList libros = seccion.getFirstChild().getChildNodes();
        for (int i = 0; i < libros.getLength(); i++) {
            Libro libro = getLibro(libros, i);
            System.out.println(libro);
        }
    }

    private static Libro getLibro(NodeList libros, int i) {
        Element libroElem;
        libroElem = (Element) libros.item(i);
        String id = libroElem.getAttribute("ID");
        String isbn = libroElem.getAttribute("isbn");
        String titulo = libroElem.getAttribute("titulo");
        int numPaginas = Integer.parseInt(libroElem.getAttribute("numero_de_paginas"));
        ArrayList<String> autores = getAutores(libroElem);
        String fechaEdicionString = libroElem.getElementsByTagName("fechaEdicion").item(0).getTextContent();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaEdicion = LocalDate.parse(fechaEdicionString, formato);
        String editorial = libroElem.getElementsByTagName("editorial").item(0).getTextContent();
        double precio = Double.parseDouble(libroElem.getElementsByTagName("precio").item(0).getTextContent());
        ArrayList<Copia> copias = getCopias(libroElem);

        Libro libro = new Libro(id, isbn, titulo, numPaginas, autores, fechaEdicion, editorial, precio,
                copias);
        return libro;
    }

    private static ArrayList<Copia> getCopias(Element libroElem) {
        ArrayList<Copia> copias = new ArrayList<>();
        NodeList copiasNodeList = libroElem.getElementsByTagName("copia");

        for (int i = 0; i < copiasNodeList.getLength(); i++) {
            int numero = Integer.parseInt(((Element) copiasNodeList.item(i)).getAttribute("numero"));
            String estado = ((Element) copiasNodeList.item(i)).getAttribute("estado");
            Copia copia = new Copia(numero, estado);
            copias.add(copia);
        }
        return copias;
    }

    private static ArrayList<String> getAutores(Element libro) {
        ArrayList<String> autores = new ArrayList<>();
        NodeList autoresNodeList = libro.getElementsByTagName("autor");

        for (int i = 0; i < autoresNodeList.getLength(); i++) {
            autores.add(autoresNodeList.item(i).getTextContent());
        }

        return autores;
    }

    private static Node getSeccion(Document dom, String seccionAObtener) {
        NodeList secciones = dom.getElementsByTagName("seccion");
        Node seccion = null;
        for (int i = 0; i < secciones.getLength(); i++) {
            String nombreSeccion = ((Element) secciones.item(i)).getAttribute("nombre");
            if (nombreSeccion.equals(seccionAObtener)) {
                seccion = secciones.item(i);
                break;
            }
        }
        return seccion;
    }

    public static Document crearDocumentoConXSD(String ruta) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        File ficheroRuta = new File(ruta);

        if (!ficheroRuta.exists()) {
            System.out.println("Se va a crear el fichero XML.");
            try {
                ficheroRuta.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el ficheor XML.");
            }
        }

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error al crear el documento xml.");
        }
        Document documento = null;

        try {
            documento = db.parse(new File(ruta));
        } catch (IOException e) {
            System.out.println("Error al crear el fichero de salida. La ruta existe?. ");
        } catch (SAXException e) {
            System.out.println(
                    "Error al crear el fichero de salida. Existe el XSD? Cumple el XSD? (si está vacío dará error)");
        }
        return documento;
    }

    private static void visualizarLibrosSeccionOrdenados(Document dom) {
        TreeMap<String, Integer> librosEnSeccion = new TreeMap<>();
        NodeList secciones = dom.getElementsByTagName("seccion");
        Node seccion = null;
        String nombreSeccion;
        Integer numLibros;

        for (int i = 0; i < secciones.getLength(); i++) {
            seccion = secciones.item(i);
            nombreSeccion = ((Element) seccion).getAttribute("nombre");
            numLibros = getNumLibrosSeccion(seccion);
            librosEnSeccion.put(nombreSeccion, numLibros);
        }

        List<Map.Entry<String, Integer>> listaLibrosEnSecciones = new ArrayList<>(librosEnSeccion.entrySet());

        Collections.sort(listaLibrosEnSecciones, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : listaLibrosEnSecciones) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        listarLibros(sortedMap);
    }

    private static Integer getNumLibrosSeccion(Node seccion) {
        return seccion.getFirstChild().getChildNodes().getLength();
    }

    private static void listarLibros(HashMap<String, Integer> listaLibrosSeccionesOrdenada) {
        for (Map.Entry<String, Integer> seccion : listaLibrosSeccionesOrdenada.entrySet()) {
            System.out.println("Sección: " + seccion.getKey());
            System.out.println("Número de libros: " + seccion.getValue());
            System.out.println("-------------------------------------");
        }
    }

    private static void grabarDOM(Document documento) {
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();

            transFact.setAttribute("indent-number", 3);

            Transformer trans;
            try {
                trans = transFact.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                DOMSource domSource = new DOMSource(documento);

                FileWriter fichero = new FileWriter("UD2\\A3UD2\\EJ1_A3UD2\\BibliotecaInformatica.xml");
                StreamResult sr = new StreamResult(fichero);
                trans.transform(domSource, sr);

            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR al crear el Transformer.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de salida.");
            } catch (TransformerException e) {
                System.out.println("Error al escribir en el archivo.");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR al crear el TransformerFactory.");
        }
    }

    public static void modificarEstadoCopia(Document dom, String isbnLibroAModificar, int numCopia,
            String estado) {
        NodeList libros = dom.getElementsByTagName("libro");
        Element libro = null;
        String isbnLibroBiblioteca;

        for (int i = 0; i < libros.getLength(); i++) {
            isbnLibroBiblioteca = ((Element) libros.item(i)).getAttribute("isbn");
            if (isbnLibroBiblioteca.equals(isbnLibroAModificar)) {
                libro = (Element) libros.item(i);
                break;
            }
        }

        if (libro != null) {
            NodeList copias = libro.getElementsByTagName("copia");
            Element copiaAModificar = (Element) copias.item(numCopia - 1);
            String estadoLibroBiblioteca = copiaAModificar.getAttribute("estado");
            if (estadoLibroBiblioteca.equals(estado)) {
                System.out.println("La copia número " + numCopia + " del libro con ISBN " + isbnLibroAModificar
                        + " ya tiene el estado " + estado + ".");
            } else {
                copiaAModificar.setAttribute("estado", estado);
                System.out.println("Estado de la copia " + numCopia + " del libro con ISBN " + isbnLibroAModificar
                        + " modificado con éxito a " + estado + ".");
            }
        } else {
            System.out.println("No se encuentra el libro con el ISBN: " + isbnLibroAModificar + ".");
        }
    }
}