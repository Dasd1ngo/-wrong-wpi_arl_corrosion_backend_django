package xmlParser;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import flights.Leg;


public class xmlParserFlight {
	private NodeList nodeList;
	
	public xmlParserFlight (String xmlString, String arrivalAirportCode) {
	      
	      try {

	    	     InputSource inputSource = new InputSource();
	    	     inputSource.setCharacterStream(new StringReader(xmlString));
	         
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputSource);
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
		               newLeg.setFirstClassPrice(eElement.getElementsByTagName("FirstClass").item(0).getAttributes().item(0).getNodeValue());
		               newLeg.setCoachPrice(eElement.getElementsByTagName("Coach").item(0).getAttributes().item(0).getNodeValue());
		               newLeg.setDepartureTime(eElement.getElementsByTagName("Departure").item(0).getLastChild().getFirstChild().getNodeValue());
		               newLeg.setArrivalTime(eElement.getElementsByTagName("Arrival").item(0).getLastChild().getFirstChild().getNodeValue());
		           
		            }	
				
				legs.add(newLeg);

			}
			return legs;
			
			
		}
	}
	


