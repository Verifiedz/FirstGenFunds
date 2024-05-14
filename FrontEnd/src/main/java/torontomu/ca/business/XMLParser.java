/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package torontomu.ca.business;


import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {

    private static short TEXT = 3;

    public static String convertXmlToHtmlTable(String xml) {
        StringBuilder html = new StringBuilder("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">\r\n");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            Element root = document.getDocumentElement();

            NodeList children = root.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    html.append("<tr>");
                    String text = getChildText(child);
                    html.append(text);
                    html.append("<td><button type=\"submit\">Hold</button></td>");
                    html.append("</tr>");
                }
            }

            html.append("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            return xml; // Returning the original string in case of error.
        }
        return html.toString();
    }

    private static String getChildText(Node node) {
        StringBuilder html = new StringBuilder("");
        if (node.getNodeType() == TEXT) {
            html.append("<td>").append(node.getNodeValue()).append("</td>");
            return html.toString();
        } else {
            html.append("<td><table width=\"100%\" border=\"0\" cellpadding=\"10\" cellspacing=\"1\"><tr><th>")
                .append(node.getNodeName()).append("</th></tr>");
            NodeList nodes = node.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                html.append(getChildText(nodes.item(i)));
            }
            html.append("</table></td>");
            return html.toString();
        }
    }
}
