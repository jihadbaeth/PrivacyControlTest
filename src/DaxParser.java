import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class DaxParser {
    public static ArrayList<Jobs> getDax(String path) {
        ArrayList<Jobs> listOfJobs = new ArrayList<Jobs>();

        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("job");
//			System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Jobs jobs = new Jobs();
                Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
//					System.out.println("Job ID: " + temp);
//					System.out.println("Activity ID : " + eElement.getAttribute("id"));
                    jobs.setActivityID(eElement.getAttribute("id").trim());
//					System.out.println(
//							"User's Data : " + eElement.getElementsByTagName("argument").item(0).getTextContent());
                    jobs.setUserData(eElement.getElementsByTagName("argument").item(0).getTextContent());
//					System.out.println("Operation Type : " + eElement.getAttribute("name"));
                    jobs.setOpType(eElement.getAttribute("name"));
                    NodeList nodeList = nNode.getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node childNode = nodeList.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {

                            //Element element = (Element) childNode;

                            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                if (childNode.getNodeName().equals("metadata")) {
//									System.out.println("Timestamp:" + " " + childNode.getTextContent());
                                    jobs.setTimestamp(childNode.getTextContent());
                                }
                                if (childNode.getNodeName().equals("uses") && childNode.getAttributes().getNamedItem("link").toString().contains("input")) {
                                    jobs.setTweetAffected(childNode.getAttributes().getNamedItem("name").getTextContent());
//									System.out.print(element.getAttribute("name") + " ");
//									System.out.println(element.getAttribute("link"));
//									System.out.println("Value of Link here"+ childNode.getAttributes().getNamedItem("name").getTextContent());
                                    jobs.setDerrivedFrom(childNode.getAttributes().getNamedItem("name").getTextContent());

                                }
                                if (childNode.getNodeName().equals("argument") && !jobs.getOpType().equals("like")) {
//									System.out.print(element.getAttribute("name") + " ");
//									System.out.println(element.getAttribute("link"));
//									System.out.println(childNode.getChildNodes()
//											.item(1).getAttributes().getNamedItem("name")
//											.getTextContent());
                                    jobs.setTweetID(childNode.getChildNodes().item(1)
                                            .getAttributes().getNamedItem("name")
                                            .getTextContent());
                                    //jobs.setTweetID(childNode.getAttributes().getNamedItem("name").toString());
                                }
                            }
                        }
                    }

                }
                jobs.setMetricValues();
                listOfJobs.add(jobs);

            }
//			int x=0;
//			for(Jobs job: listOfJobs)
//			{
//				System.out.println(x+"- "+job.toString());
//				x++;
//
//			}
        } catch (Exception e) {
            e.printStackTrace();
        }
//		for (Jobs tempJob : listOfJobs)
//		{
//			System.out.println(tempJob.getActivityID());
//			System.out.println(tempJob.getOpType());
//			System.out.println(tempJob.getTimestamp());
//			System.out.println(tempJob.getUserData());
//
//		}
        return listOfJobs;

    }

    public static void main(String[] args) {
        ArrayList<Jobs> listOfJobs = new ArrayList<Jobs>();

        try {
            File inputFile = new File("C:\\Users\\jehad\\Desktop\\Dax\\dax_401_1K_.dax");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("job");
//			System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Jobs jobs = new Jobs();
                Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
//					System.out.println("Job ID: " + temp);
//					System.out.println("Activity ID : " + eElement.getAttribute("id"));
                    jobs.setActivityID(eElement.getAttribute("id").trim());
//					System.out.println(
//							"User's Data : " + eElement.getElementsByTagName("argument").item(0).getTextContent());
                    jobs.setUserData(eElement.getElementsByTagName("argument").item(0).getTextContent());
//					System.out.println("Operation Type : " + eElement.getAttribute("name"));
                    jobs.setOpType(eElement.getAttribute("name"));
                    NodeList nodeList = nNode.getChildNodes();
                    for (int j = 0; j < nodeList.getLength(); j++) {
                        Node childNode = nodeList.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {

                            //Element element = (Element) childNode;

                            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                if (childNode.getNodeName().equals("metadata")) {
//									System.out.println("Timestamp:" + " " + childNode.getTextContent());
                                    jobs.setTimestamp(childNode.getTextContent());

                                }
                                if (childNode.getNodeName().equals("uses") && childNode.getAttributes().getNamedItem("link").toString().contains("input")) {
                                    System.out.println("HERE!!!!!!!!!!!!!!!!" + childNode.getAttributes().getNamedItem("name").getTextContent());
//									System.out.print(element.getAttribute("name") + " ");
//									System.out.println(element.getAttribute("link"));
//									System.out.println("Value of Link here"+ childNode.getAttributes().getNamedItem("name").getTextContent());
                                    jobs.setDerrivedFrom(childNode.getAttributes().getNamedItem("name").getTextContent());

                                }
                                if (childNode.getNodeName().equals("argument")) {
//									System.out.print(element.getAttribute("name") + " ");
//									System.out.println(element.getAttribute("link"));
//									System.out.println(childNode.getChildNodes()
//											.item(1).getAttributes().getNamedItem("name")
//											.getTextContent());
                                    jobs.setTweetID(childNode.getChildNodes().item(1)
                                            .getAttributes().getNamedItem("name")
                                            .getTextContent());

                                    //jobs.setTweetID(childNode.getAttributes().getNamedItem("name").toString());
                                }
                            }
                        }
                    }

                }
                jobs.setMetricValues();
                listOfJobs.add(jobs);

            }
//			int x=0;
//			for(Jobs job: listOfJobs)
//			{
//				System.out.println(x+"- "+job.toString());
//				x++;
//
//			}
        } catch (Exception e) {
            e.printStackTrace();
        }
//		for (Jobs tempJob : listOfJobs)
//		{
//			System.out.println(tempJob.getActivityID());
//			System.out.println(tempJob.getOpType());
//			System.out.println(tempJob.getTimestamp());
//			System.out.println(tempJob.getUserData());
//
//		}
        for (Jobs j : listOfJobs) {
            System.out.println(j.toString());
        }

    }
}