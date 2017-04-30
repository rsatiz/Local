

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.io.IOUtils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

// [iRIS_INT_iRIS_INT_00349] wso2 esb new source code updation for FreemarkerMediator with synchronized keyword with mediate function
// [iRIS_INT_iRIS_INT_00344]  iRIS JSON expose Message name space changed
// [iRIS_INT_iRIS_INT_00380 ] Code optimized for memory issuse
// [iRIS_INT_iRIS_INT_00371] Code change for memory issuse 
// [iRIS_INT_iRIS_INT_00380 ] Code optimized for memory issue
// [IRIS-41 ] Support for dynamic Transaction ID in VW 3.0 services

public class FreemarkerMediator {

	/* init Parameters */
	private Configuration cfg;
	private FileTemplateLoader fileTemplateLoader;
	private Template template;
	private String currentdir = System.getProperty("user.dir");
	Map<String,Object> root = null;

	/* input Parameters */
	private String templateDir = "";
	private String templateName = "";
	private String operationname = "";
	private String datasourcetype = "";
	private Writer writer = null;
	private String ipid = "";	
	private String fileName ="" ;
	//Trancation ID for service , Set oldmessageid property from message context to stranID
	private String stranId ="" ;


	@SuppressWarnings("deprecation")
	public FreemarkerMediator() {
		// TODO Auto-generated constructor stub
		try{

			cfg = new Configuration();
			File f = new File("D:/work/samples/freemarker/");

			fileTemplateLoader = new FileTemplateLoader(f);
			cfg.setTemplateLoader(fileTemplateLoader);
			template = cfg.getTemplate("transform.ftl");
			writer = new StringWriter();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


	public static void main(String[] args) {
		FreemarkerMediator fm = new FreemarkerMediator();
		fm.mediate();

	}
	@SuppressWarnings("unchecked")
	public boolean mediate() {

		long startTime = System.currentTimeMillis();
		try {

			InputStream is = new FileInputStream(new File("D:\\work\\samples\\freemarker\\Out_response_soap.txt"));
			String resultData = "<xml>nothing</xml>";
			String child = IOUtils.toString(is);;
			OMElement xmlroot = AXIOMUtil.stringToOM("<xml>"+child+"</xml>");
			XMLToHashMap xmlToMapConverter = new XMLToHashMap();
			resultData = transformXMLfileToSoapXML((HashMap<String,Object>)xmlToMapConverter.getRootNode(xmlroot));

			System.out.println(resultData.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}finally{

			try {

				if(writer != null) {
					writer.close();
					writer = null;
				}

				if(root != null) {
					root.clear();
					root = null;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long endTime = System.currentTimeMillis();
		System.out.println("("+getIpid()+") Total execution time for FreemarkerMediator.execute() is : "+ (endTime-startTime));
		return true;
	}

	public String transformXMLfileToSoapXML(HashMap<String,Object> data) throws Exception{

		data.put("filename",getFileName());
		data.put("stranId",getStranId());
		root = new HashMap<String,Object>();
		root.put("XML",data);
		root.put("maxunbounded", new maxunboundedDirective());
		root.put("operation", getOperationname());
		root.put("attachNode", new AttachNode());
		template.process(root, writer);

		return writer.toString();
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getOperationname() {
		return operationname;
	}

	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}

	public String getDatasourcetype() {
		return datasourcetype;
	}

	public void setDatasourcetype(String datasourcetype) {
		this.datasourcetype = datasourcetype;
	}

	public String getIpid() {
		return ipid;
	}

	public void setIpid(String ipid) {
		this.ipid = ipid;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the stranId
	 */
	public String getStranId() {
		return stranId;
	}

	/**
	 * @param stranId the stranId to set
	 */
	public void setStranId(String stranId) {
		this.stranId = stranId;
	}

}