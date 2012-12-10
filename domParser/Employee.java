package domParser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Employee {
	enum EmployeeTypes {permanent, contract, intern}
	private String name;
	private int id;
	private int age;
	String type;
	//	EmployeeTypes type;

	private Employee(String name, int id, int age, String type) {
		//	private Employee(String name, Long id, Long age, EmployeeTypes type) {

		this.setName(name);
		this.setId(id);
		this.setAge(age);
		this.type= type;
	}

	public static void main(String args[]) {
		System.out.println ("This is Employee, calling parseXmlFile()");
		parseXmlFile();

	}   //end of main


	private static void parseXmlFile(){
		//Using factory get an instance of document builder
		//parse using builder to get DOM representation of the XML file
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse("emp.xml");
			System.out.println ("Employee gets, dom.getNodeName() = " + dom.getNodeName());
			NodeList nodeList = dom.getChildNodes();
			for (int i =0; i <= nodeList.getLength(); i++) {
			System.out.println ("Employee gets, dom.getNodeName() = " + nodeList.item(i));
			}
			NamedNodeMap attr = dom.getAttributes();
			NodeList nodes = dom.getChildNodes();
			Node node = dom.getFirstChild(); 
			String name = node.getNodeName();
			System.out.println ("Employee gets, dom.getFirstChild.getNodeName() = " + name);

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 */
	private static Employee getEmployee(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		String name = getTextValue(empEl,"Name");
		int id = getIntValue(empEl,"Id");
		int age = getIntValue(empEl,"Age");

		String type = empEl.getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		Employee e = new Employee(name,id,age,type);

		return e;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private static int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

}
