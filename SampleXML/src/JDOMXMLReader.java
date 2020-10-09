import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
//import org.jdom.input.StAXEventBuilder;
//import org.jdom.input.StAXStreamBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//import com.journaldev.xml.Employee;


public class JDOMXMLReader {

    public static void main(String[] args) {
        final String fileName = "C:\\Users\\SHUBHI\\Documents\\workspace-spring-tool-suite-4-4.4.0.RELEASE\\SampleXML";
        org.jdom.Document jdomDoc;
        try {
            //we can create JDOM Document from DOM, SAX and STAX Parser Builder classes
            jdomDoc = useDOMParser(fileName);
            Element root = jdomDoc.getRootElement();
            List<Element> empListElements = root.getChildren("Employee");
            List<Employee> empList = new ArrayList<>();
            for (Element empElement : empListElements) {
                Employee emp = new Employee();
                emp.setId(Integer.parseInt(empElement.getAttributeValue("id")));
                emp.setAge(Integer.parseInt(empElement.getChildText("age")));
                emp.setName(empElement.getChildText("name"));
                emp.setRole(empElement.getChildText("role"));
                emp.setGender(empElement.getChildText("gender"));
                empList.add(emp);
            }
            //lets print Employees list information
            for (Employee emp : empList)
                System.out.println(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //Get JDOM document from DOM Parser
    private static org.jdom.Document useDOMParser(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        //creating DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);

    }
}