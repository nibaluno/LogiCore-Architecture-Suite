package org.example.parsers;

import org.example.domain.cargo.Cargo;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser implements DataParser {

    @Override
    public List<Cargo> parseCargos(String filePath) {
        List<Cargo> cargos = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            if (is == null) return cargos;

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            NodeList nList = doc.getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {
                Element el = (Element) nList.item(i);
                if ("cargo".equals(el.getAttribute("type"))) {
                    String name = el.getElementsByTagName("name").item(0).getTextContent();
                    double weight = Double.parseDouble(el.getElementsByTagName("weight").item(0).getTextContent());
                    double cost = Double.parseDouble(el.getElementsByTagName("cost").item(0).getTextContent());
                    cargos.add(new Cargo(name, weight, cost));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return cargos;
    }

    public List<String[]> parseTransports(String filePath) {
        List<String[]> transports = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            if (is == null) return transports;

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            NodeList nList = doc.getElementsByTagName("item");

            for (int i = 0; i < nList.getLength(); i++) {
                Element el = (Element) nList.item(i);
                if ("transport".equals(el.getAttribute("type"))) {
                    String name = el.getElementsByTagName("name").item(0).getTextContent();
                    String cons = el.getElementsByTagName("consumption").item(0).getTextContent();
                    String speed = el.getElementsByTagName("speed").item(0).getTextContent();
                    transports.add(new String[]{"transport", name, "0", "0", cons, speed});
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return transports;
    }
}