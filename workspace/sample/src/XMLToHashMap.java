
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.axiom.om.OMElement;

// [iRIS_INT_iRIS_INT_00349] wso2 esb new source code updation for FreemarkerMediator with synchronized keyword with mediate function
// [iRIS_INT_iRIS_INT_00344]  iRIS JSON expose Message name space changed
// [iRIS_INT_iRIS_INT_00380 ] Code optimized for memory issuse
//[iRIS_iRIS_00364] Generation Failed for XML based IP's, due to success message not binded in XMLAdapter.ftl is fixed
@SuppressWarnings("unchecked")
public class XMLToHashMap {

	HashMap<String,Object> data = null;

	public XMLToHashMap(){
		data = new HashMap<String,Object>();
	}

	public Object getAllChildren(OMElement node){
		HashMap<String,Object> data = new HashMap<String,Object>();
		boolean hasChildren = false;	
		Iterator<OMElement> nodelist = node.getChildElements();
		while(nodelist.hasNext()){
			OMElement nodes = nodelist.next();
			putData(nodes.getLocalName(), getAllChildren(nodes), data);
			hasChildren = true;
			nodes = null;
		}

		if(!hasChildren){
			return node.getText();
		}

		nodelist = null;
		return data;
	}

	public void putData(String key, Object Value, HashMap<String,Object> data){

		Object myClassList = data.get(key);
		if(myClassList == null) {
			data.put(key, Value);
		}else{

			if(myClassList instanceof ArrayList){
				((ArrayList<Object>) myClassList).add(Value); 
			}else{
				ArrayList<Object> newClassList = new ArrayList<Object>();
				newClassList.add(myClassList);
				newClassList.add(Value);
				data.remove(key);
				data.put(key, newClassList);
				newClassList = null;
			}
		}
		myClassList = null;

	}

	public Object getRootNode(OMElement node){

		data = new HashMap<String,Object>();

		boolean hasChildren = false;

		Iterator<OMElement> nodelist = node.getChildElements();

		while(nodelist.hasNext()){
			OMElement nodes = nodelist.next();
			putData(nodes.getLocalName(), getAllChildren(nodes), data);
			hasChildren = true;
			nodes = null;
		}

		if(!hasChildren){
			putData(node.getLocalName(), node.getText(), data);
		}

		nodelist = null;
		return data;
	}

	public void clear() {
		if(data != null) {
			data.clear();
			data = null;
		}
	}
}