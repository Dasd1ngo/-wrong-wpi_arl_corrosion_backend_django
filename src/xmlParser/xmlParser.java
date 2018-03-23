package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import application.Leg;

public class xmlParser {
    private NodeList nodeList;
    
	public xmlParser(String xmlFile, String arrivalAirportCode) {
      
      try {
    	  	

    	  		// "flights_departing_BOS_2017_12_05.xml"
    	  	 File inputFile = new File(xmlFile);
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbFactory.newDocumentBuilder();

         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();

         XPath xPath =  XPathFactory.newInstance().newXPath();

         String expression = "/Flights/Flight/Arrival[Code='"+ arrivalAirportCode +"']";	        
         nodeList = (NodeList) xPath.compile(expression).evaluate(
            doc, XPathConstants.NODESET);
         

      }
      
            catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (XPathExpressionException e) {
         e.printStackTrace();
      }
   }
	
	public ArrayList<Leg> getResultAsLegList() {
		ArrayList<Leg> legs = new ArrayList<Leg>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Leg newLeg = new Leg();
			Node nNode = nodeList.item(i).getParentNode();			

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               newLeg.setFlightNumber(eElement.getAttribute("Number"));
	               newLeg.setCoachPrice(eElement.getElementsByTagName("Coach").item(0).getAttributes().item(0).getNodeValue());
	            }
			
			legs.add(newLeg);

		}
		return legs;
		
		
	}
}