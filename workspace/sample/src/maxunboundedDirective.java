

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * FreeMarker user-defined directive for repeating a section of a template,
 * optionally with separating the output of the repetations with
 * <tt>&lt;hr></tt>-s.
 *
 * 
 * <p><b>Directive info</b></p>
 * 
 * <p>Parameters:
 * <ul>
 *   <li><code>count</code>: The number of repetations. Required!
 *       Must be a non-negative number. If it is not a whole number then it will
 *       be rounded <em>down</em>.
 *   <li><code>hr</code>: Tells if a HTML "hr" element could be printed between
 *       repetations. Boolean. Optional, defaults to <code>false</code>. 
 * </ul>
 *
 * <p>Loop variables: One, optional. It gives the number of the current
 *    repetation, starting from 1.
 * 
 * <p>Nested content: Yes
 */
 
 // [iRIS_INT_iRIS_INT_00349] wso2 esb new source code updation for FreemarkerMediator with synchronized keyword with mediate function
 // [iRIS_INT_iRIS_INT_00344]  iRIS JSON expose Message name space changed
 // [iRIS_INT_iRIS_INT_00380 ] Code optimized for memory issuse
public class maxunboundedDirective implements TemplateDirectiveModel {
    
  /*  private static final String PARAM_NAME_COUNT = "query";
    private static final String PARAM_NAME_HR = "hr";    
  */  
    public void readBuffer(){
    	
    }
    
    @SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)throws TemplateException, IOException {
        
        boolean single = true;
        
        SimpleSequence templateCollectionModel = null;
        SimpleHash TemplateHashModel = null;
       
        
        Iterator paramIter = params.entrySet().iterator();
        
        while (paramIter.hasNext()) {
            Map.Entry ent = (Map.Entry) paramIter.next();
            String paramName = (String) ent.getKey();
            if(paramName.equals("node")){
            	TemplateModel paramValue = (TemplateModel) ent.getValue();
            	if(paramValue instanceof SimpleSequence){
            		templateCollectionModel = (SimpleSequence) paramValue;
            		single = false;
            	}else if(paramValue instanceof SimpleHash){
            		TemplateHashModel = (SimpleHash) paramValue;
            		single = true;
            	}else{
            		
            	}
            }
        }
        
        if (loopVars.length > 1) {
            throw new TemplateModelException("At most one loop variable is allowed.");
        }
        
        if (body != null) {
        	
        	if(single){
        		
        		 if (loopVars.length > 0) {
                     loopVars[0] = TemplateHashModel;
                 }
                 
                 body.render(env.getOut());
                 
            }else{
            	
            	for(int i=0; i<templateCollectionModel.size(); i++){
            		 if (loopVars.length > 0) {
                         loopVars[0] = templateCollectionModel.get(i);
                     }
                     
                     body.render(env.getOut());
            	}
            }     
        	
        }
        
        
    }   

}  