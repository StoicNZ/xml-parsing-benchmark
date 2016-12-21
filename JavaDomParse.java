import org.jdom2.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.filter.ElementFilter;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;

import java.io.File;
import java.util.*;

public class JavaDomParse {
    public static void main(String[] args) {
        File xmlFile = new File("nvdcve-2.0-2011.xml");

        try {
            Date start = new Date();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            DOMBuilder domBuilder = new DOMBuilder();
            org.jdom2.Document document = domBuilder.build(doc);;

            Element rootNode = document.getRootElement();
            List<Element> list = rootNode.getChildren();
            List<HashMap> entries = new ArrayList<HashMap>();

            for (int i = 0; i < list.size(); i++) {
                Iterator<Element> elements = list.get(i).getDescendants(new ElementFilter());
                HashMap kv = new HashMap();
                while(elements.hasNext()) {
                    Element e =  elements.next();
                    String currentName = e.getName();
                    switch(currentName) {
                        case "cve-id":
                            kv.put("CVE", e.getValue()); break;
                        case "summary":
                            kv.put("Summary", e.getValue()); break;
                        case "reference":
                            kv.put("Reference", e.getValue()); break;
                        case "published-datetime":
                            kv.put("Date", e.getValue()); break;
                    }
                }
                entries.add(kv);

            }
            //System.out.println(entries.size());
            //System.out.println(entries.get(4000));
            System.out.println((new Date().getTime() - start.getTime())/1000.0);
        }
        catch(Exception e) { System.out.print(e.toString()); }

    }
}
