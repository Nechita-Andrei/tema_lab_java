package demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
import java.util.Map;

public class ImportUtils {

    private static final String CSV_SEPARATOR = ";";


    public static String getCsvFormat(List<Map<String, Object>> taskMaps, String fileName) {

        StringBuffer stringObject = new StringBuffer();

        for (Map<String, Object> map : taskMaps) {

            for (Map.Entry<String, Object> e : map.entrySet()) {

                stringObject.append(e.getValue());
                stringObject.append(CSV_SEPARATOR);

            }
            stringObject.append("\n");
        }
        return stringObject.toString();
    }


    public static String getXmlFormat(List<Map<String, Object>> taskMaps, String fileName) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        var doc = builder.newDocument();

        Element root = doc.createElementNS("unibuc.ro", "tasks");
        doc.appendChild(root);

        for (Map<String, Object> map : taskMaps) {
            Element task = doc.createElement("task");
            for (Map.Entry<String, Object> e : map.entrySet()) {

                if (e.getKey().equals("id"))
                    task.setAttribute(e.getKey(), e.getValue().toString());
                else {
                    task.appendChild(createUserElement(doc, e.getKey(), e.getValue().toString()));
                }
            }
            root.appendChild(task);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        transf.transform(source,result);

        return writer.toString();
    }


    private static Node createUserElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }


}
