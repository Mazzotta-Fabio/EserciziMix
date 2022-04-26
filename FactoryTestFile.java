package prog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Mazzotta Fabio
 *
 */
public class FactoryTestFile {

	public static void main(String[] args) throws Exception {
		//qui va inserito il path della cartella sorgente dove si tengono i file
		File f=new File("C:\\Users\\Administrator\\Desktop\\TestOrdiniFTP_Validazione");
		File [] files=f.listFiles();
		for(int i=0;i<files.length;i++) {
			File f1=files[i];
			if(f1.isFile()) {
				
				String parteFinale="";
				//controllo su parte finale nome del file
				switch(String.valueOf(i).length()) {
					case 1:
						parteFinale="008F"+i;
						break;
					case 2:
						parteFinale="10D"+i;
						break;
					case 3:
						parteFinale="6I"+i;
						break;
					case 4:
						parteFinale="p"+i;
						break;
					case 5:
						parteFinale=""+i;
						break;
					default:
						parteFinale=String.valueOf(Integer.hashCode(i)).substring(0,5);
						break;
				}
				
				//qui bisogna mettere il path del file nuovo che si vuole creare
				File newFile=new File("C:\\Users\\Administrator\\Desktop\\TestOrdiniFTP_Validazione\\IT_OZ_"+parteFinale+".xml");
				Files.copy(f1.toPath(),newFile.toPath());
				
				InputStream is=new FileInputStream(newFile);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        Document document = db.parse(is);
				
				//otteniamo i tag interessati
				NodeList nodesReceiver=document.getElementsByTagName("Receiver");
				NodeList nodesSender=document.getElementsByTagName("Sender");
				NodeList nodesId=document.getElementsByTagName("cbc:ID");
				//receiver
				for(int j=0;j<nodesReceiver.getLength();j++) {
					Node node=nodesReceiver.item(j);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element=(Element)node;
						//mettere qui il receiver desiderato
						element.getElementsByTagName("Identifier").item(0).setTextContent("790");
						System.out.println(element.getElementsByTagName("Identifier").item(0).getTextContent());
					}
				}
				//sender
				for(int j=0;j<nodesSender.getLength();j++) {
					Node node=nodesSender.item(j);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element=(Element)node;
						//mettere qui il sender desiderato
						element.getElementsByTagName("Identifier").item(0).setTextContent("782");
						System.out.println(element.getElementsByTagName("Identifier").item(0).getTextContent());
					}
				}
				//id
				Node node=nodesId.item(0);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element=(Element)node;
					//mettere l'id desiderato
					element.setTextContent("TEST_PX_"+i);
				}
				
				//scrivi tutte le modifiche effettuate. Qui metti lo stesso path usato per il nuovo file
				FileOutputStream output = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\TestOrdiniFTP_Validazione\\IT_OZ_"+parteFinale+".xml"); 
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        DOMSource source = new DOMSource(document);
		        StreamResult result = new StreamResult(output);
		        transformer.transform(source, result);
		        
		        //cancella il file originale
				Files.delete(f1.toPath());
			}
		}
	}

}
