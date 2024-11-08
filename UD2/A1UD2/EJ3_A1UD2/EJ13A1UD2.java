package UD2.A1UD2.EJ3_A1UD2;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class EJ13A1UD2 {
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        DocumentBuilderFactory dbf = creacionDocumento();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document documento = db.parse("Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD2\\A1UD2\\EJ3_A1UD2\\Empleados.xml");
        Node raiz = documento.getDocumentElement();
        anhadirId((Element) raiz);

        anhadirNodo(documento, 10, "Carmen Gil Villa", "Secretaria", "Del Mar, 4 36004 Pontevedra", "200 euros");
        reemplazarPrimerNodo(documento, 14, "Carolina Sanchez Jimenez", "Trabajador",
                "Oriente, 6 35200 Vigo Pontevedra", "100 euros");
        anhadirNodoComienzo(documento, 11, "Lucía Martín Martín", "Gerente", "Avda Vigo, 7 36911 Marin –Pontevedra-",
                "200 euros");
        borrarNodo(documento, 2);
        clonarNodo(documento, 3);

        Node primerNodo = raiz.getFirstChild();
        System.out.println("Empleados");
        visualizarDocumento(documento, primerNodo);

        grabarArbolModificado(documento);
    }

    private static void visualizarDocumento(Document documento, Node nodo) throws IOException {
        System.out.println("--------------------------------");
        imprimirAtributos(nodo);
        imprimirElementos(nodo);
        if (!(nodo.getNextSibling() == null)) {
            visualizarDocumento(documento, nodo.getNextSibling());
        }
    }

    private static void imprimirAtributos(Node nodoActual) throws IOException {
        NamedNodeMap nodos = nodoActual.getAttributes();
        for (int i = 0; i < nodos.getLength(); i++) {
            System.out.println(nodos.item(i).getNodeName() + ": " + nodos.item(i).getNodeValue() + "\n");
        }
    }

    private static void imprimirElementos(Node nodoActual) throws IOException {
        NodeList nodos = nodoActual.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            System.out.println(nodos.item(i).getNodeName() + ": " + nodos.item(i).getTextContent() + "\n");
        }
    }

    public static DocumentBuilderFactory creacionDocumento() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setValidating(true);
        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setIgnoringComments(true);
        return dbf;
    }

    // B) Crea un método para añadir a todos los nodos empleado un atributo id con
    // valores consecutivos empezando por 1.

    private static void anhadirId(Element nodo) {
        Element elemento = (Element) nodo.getFirstChild();
        for (int i = 0; i < nodo.getChildNodes().getLength(); i++) {
            elemento.setAttribute("id", String.valueOf(i + 1));
            elemento = (Element) elemento.getNextSibling();
        }
    }

    // C) Añade el siguiente nodo al principio del documento:

    private static void anhadirNodo(Document documento, int ID, String nombre, String cargo, String direccion,
            String aumento) {
        Element nuevoElemento = (Element) documento.createElement("Empleado");
        documento.getDocumentElement().appendChild(nuevoElemento);
        Attr atributo = documento.createAttribute("id");
        atributo.setValue(String.valueOf(ID));
        nuevoElemento.setAttributeNode(atributo);

        Element nombreElem = documento.createElement("Nombre");
        Text textoNombre = documento.createTextNode(nombre);
        nuevoElemento.appendChild(nombreElem);
        nombreElem.appendChild(textoNombre);

        Element cargoElem = documento.createElement("Cargo");
        Text textoCargo = documento.createTextNode(cargo);
        nuevoElemento.appendChild(cargoElem);
        cargoElem.appendChild(textoCargo);

        Element direccionElem = documento.createElement("Direccion");
        Text textoDireccion = documento.createTextNode(direccion);
        nuevoElemento.appendChild(direccionElem);
        direccionElem.appendChild(textoDireccion);

        Element aumentoElem = documento.createElement("Aumento");
        Text textoAumento = documento.createTextNode(aumento);
        nuevoElemento.appendChild(aumentoElem);
        aumentoElem.appendChild(textoAumento);
    }

    // D) Añade el siguiente nodo al principio del documento:
    private static void anhadirNodoComienzo(Document documento, int ID, String nombre, String cargo, String direccion,
            String aumento) {
        Element raiz = documento.getDocumentElement();
        Element nodo = (Element) raiz.getFirstChild();
        Element nuevoElemento = documento.createElement("Empleado");
        raiz.insertBefore(nuevoElemento, nodo);
        Attr atributo = documento.createAttribute("id");
        atributo.setValue(String.valueOf(ID));
        nuevoElemento.setAttributeNode(atributo);

        Element nombreElem = documento.createElement("Nombre");
        Text textoNombre = documento.createTextNode(nombre);
        nuevoElemento.appendChild(nombreElem);
        nombreElem.appendChild(textoNombre);

        Element cargoElem = documento.createElement("Cargo");
        Text textoCargo = documento.createTextNode(cargo);
        nuevoElemento.appendChild(cargoElem);
        cargoElem.appendChild(textoCargo);

        Element direccionElem = documento.createElement("Direccion");
        Text textoDireccion = documento.createTextNode(direccion);
        nuevoElemento.appendChild(direccionElem);
        direccionElem.appendChild(textoDireccion);

        Element aumentoElem = documento.createElement("Aumento");
        Text textoAumento = documento.createTextNode(aumento);
        nuevoElemento.appendChild(aumentoElem);
        aumentoElem.appendChild(textoAumento);
    }

    // E) Reemplaza el nodo 1 por este nodo:

    private static void reemplazarPrimerNodo(Document documento, int ID, String nombre, String cargo, String direccion,
            String aumento) {
        Element raiz = documento.getDocumentElement();
        Element nodo = (Element) raiz.getFirstChild();
        Element nuevoElemento = documento.createElement("Empleado");
        raiz.replaceChild(nuevoElemento, nodo);
        Attr atributo = documento.createAttribute("id");
        atributo.setValue(String.valueOf(ID));
        nuevoElemento.setAttributeNode(atributo);

        Element nombreElem = documento.createElement("Nombre");
        Text textoNombre = documento.createTextNode(nombre);
        nuevoElemento.appendChild(nombreElem);
        nombreElem.appendChild(textoNombre);

        Element cargoElem = documento.createElement("Cargo");
        Text textoCargo = documento.createTextNode(cargo);
        nuevoElemento.appendChild(cargoElem);
        cargoElem.appendChild(textoCargo);

        Element direccionElem = documento.createElement("Direccion");
        Text textoDireccion = documento.createTextNode(direccion);
        nuevoElemento.appendChild(direccionElem);
        direccionElem.appendChild(textoDireccion);

        Element aumentoElem = documento.createElement("Aumento");
        Text textoAumento = documento.createTextNode(aumento);
        nuevoElemento.appendChild(aumentoElem);
        aumentoElem.appendChild(textoAumento);

    }

    // F) Borra el segundo nodo.

    private static void borrarNodo(Document documento, int numNodo) {
        Node raiz = documento.getDocumentElement().getFirstChild();
        NodeList nodos = raiz.getChildNodes();
        raiz.removeChild(nodos.item(numNodo));
    }

    // G) Clona el nodo 3 y sitúalo al final del documento. Cambia el valor del
    // atributo por id=15 y el nombre por Maria Rivas Rivas.

    private static void clonarNodo(Document documento, int numNodo) {
        Element raiz = documento.getDocumentElement();
        NodeList nodos = raiz.getChildNodes();
        Node clonNodo = nodos.item(numNodo).cloneNode(true);

        Attr atributo = documento.createAttribute("id");
        atributo.setValue(String.valueOf(15));
        ((Element) clonNodo).setAttributeNode(atributo);

        ((Element) clonNodo).getFirstChild().setTextContent("Maria Rivas Rivas");

        raiz.appendChild(clonNodo);
    }

    // H) e) Haz un método para grabar el árbol modificado en un fichero XML llamado
    // EmpleadoCambio.xml. Visualízalo también por pantalla

    private static void grabarArbolModificado(Document documento) throws IOException, TransformerException {
        TransformerFactory transFact;
        try {
            transFact = TransformerFactory.newInstance();
            // añadimos sangrado
            transFact.setAttribute("indent-number", 3);
            Transformer trans;
            try {
                trans = transFact.newTransformer();
                trans.setOutputProperty(OutputKeys.INDENT, "yes");
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                DOMSource domSource = new DOMSource(documento);
                // creamos fichero para escribir en modo texto
                FileWriter sw = new FileWriter("Z:\\eloy_rodalperez\\DAM2\\Acceso a datos\\Unidades\\UD2\\A1UD2\\EJ3_A1UD2\\EmpleadoCambio.xml");
                // escribimos todo el arbol en el fichero
                StreamResult sr = new StreamResult(sw);
                // También podemos especificar el destino en la consola
                StreamResult consola = new StreamResult(System.out);
                trans.transform(domSource, sr);
                trans.transform(domSource, consola);
            } catch (TransformerConfigurationException ex) {
                System.out.println("ERROR al construir el motor de transformación ");
            }
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("ERROR a la hora de implementar la transformación");
        }
    }
}
