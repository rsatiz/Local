
// [iRIS_iRIS_00097] The formatter threw an exception while trying to deserialize the message: There was an error while trying to deserialize parameter http://www.ramco.com/VirtualWorks30/Services:uom_pro1_srsubmit__uommlml_in. The InnerException message was 'There was an error deserializing the object of type Ramco.VW.Types.UOM_Pro1_SrSubmit__UOMmlML_in[]. The value ' ' cannot be parsed as the type 'DateTime'.'. Please see InnerException for more details. - This error was thrown in output file when Transform function 'currentdate' was used in mapping ds to es for ip_63.  
// [iRIS_INT_iRIS_INT_00349] wso2 esb new source code updation for FreemarkerMediator with synchronized keyword with mediate function
// [iRIS_INT_iRIS_INT_00344]  iRIS JSON expose Message name space changed

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class AttachNode implements TemplateMethodModel {

	@Override
	public Object exec(List input) throws TemplateModelException {
		
		if (input.size() != 3) {
            throw new TemplateModelException("Wrong arguments");
        }
		
		String nodeName = input.get(0).toString();
		String nodeNamespace = input.get(1).toString();
		Object nodeValue = input.get(2);
		
		String returnData = "";
		
		
		if(nodeValue==null){
			
			returnData = "<"+nodeName+" i:nil=\"true\" xmlns=\""+nodeNamespace+"\" ></"+nodeName+">";
			
			//returnData = "<"+nodeName+" xmlns=\""+nodeNamespace+"\" ></"+nodeName+">";
			
			return new SimpleScalar(returnData);
			
		}else{
			String nodeStringValue = nodeValue.toString();
			nodeStringValue = nodeStringValue.trim();
			if(nodeStringValue.length() > 0){
				
				returnData = "<"+nodeName+" xmlns=\""+nodeNamespace+"\" >" + StringEscapeUtils.escapeXml(nodeStringValue)  + "</"+nodeName+">";
				
				return new SimpleScalar(returnData);
				
			}else{
				
				returnData = "<"+nodeName+" i:nil=\"true\" xmlns=\""+nodeNamespace+"\" >" + "</"+nodeName+">";
				
				//returnData = "<"+nodeName+" xmlns=\""+nodeNamespace+"\" ></"+nodeName+">";
				
				return new SimpleScalar(returnData);
			}
		}	
		
	}
}  