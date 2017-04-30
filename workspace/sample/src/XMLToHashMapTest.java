import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.axiom.om.OMElement;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

    public class XMLToHashMapTest {
    public static void main(String[] args) throws Exception {

    InputStream is = new FileInputStream(new File("e:\\jagannathan\\personal\\java-projects\\secondtest.xml"));

    }

    public static void printJSON(String jsonString) {
    ObjectMapper mapper = new ObjectMapper();

    try {

    Map<String, Object> jsonInMap = mapper.readValue(jsonString,
    new TypeReference<Map<String, Object>>() {
    });

    List<String> keys = new ArrayList<String>(jsonInMap.keySet());

    for (String key : keys) {
    System.out.println(key +";" + jsonInMap.get(key));
    }

    } catch (JsonGenerationException e) {
    e.printStackTrace();
    } catch (JsonMappingException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }

	public HashMap<String, Object> getRootNode(OMElement xmlroot) {
		
		    XMLSerializer xmlSerializer = new XMLSerializer();
		    JSON json = xmlSerializer.read(xmlroot.toString());

		    System.out.println(json.toString(2));

		    printJSON(json.toString(2));

		return null;
	}
    }
