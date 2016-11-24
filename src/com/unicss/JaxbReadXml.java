package com.unicss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class JaxbReadXml {
//xml  --->  bean
@SuppressWarnings("unchecked")
   public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
       try {
           JAXBContext jc = JAXBContext.newInstance(clazz);
           Unmarshaller u = jc.createUnmarshaller();
           return (T) u.unmarshal(new File(context));
       } catch (JAXBException e) {
           // logger.trace(e);
           throw e;
       }
   }

   @SuppressWarnings("unchecked")
   public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
           JAXBException {
       InputStream is = null;
       try {
           if (arguments.length > 0) {
               config = MessageFormat.format(config, arguments);
           }
           // logger.trace("read configFileName=" + config);
           JAXBContext jc = JAXBContext.newInstance(clazz);
           Unmarshaller u = jc.createUnmarshaller();
           is = new FileInputStream(config);
           return (T) u.unmarshal(is);
       } catch (IOException e) {
           // logger.trace(config, e);
           throw e;
       } catch (JAXBException e) {
           // logger.trace(config, e);
           throw e;
       } finally {
           if (is != null) {
               is.close();
           }
       }
   }

   @SuppressWarnings("unchecked")
   public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
       try {
           JAXBContext jc = JAXBContext.newInstance(clazz);
           Unmarshaller u = jc.createUnmarshaller();
           return (T) u.unmarshal(dataStream);
       } catch (JAXBException e) {
           // logger.trace(e);
           throw e;
       }
   }
   //bean  -->  xml
  public static <T> String gernateConfigXml(Class<T> clazz, T orgs){
           if (orgs != null) { 
                 try { 
                     JAXBContext context = JAXBContext.newInstance(clazz); 
                     Marshaller m = context.createMarshaller(); 
                     //m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
                    // m.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                     m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT , true); 
                     //m.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "amzn-envelope.xsd");
                     Writer writer = new StringWriter(); 
                     m.marshal(orgs, writer); 
                     try { 
                         String xml = writer.toString(); 
                         writer.flush(); 
                         writer.close(); 
                         return xml; 
                     } catch (IOException e) { 
                         return ""; 
                     } 
                 } catch (Exception e) { 
                     return null;
                 } 
             } else { 
                 return null; 
             } 
         }
   public static void main(String[] args) throws Exception {
   /* BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("g:/test.xml"),"GBK"));
    PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("g:/tests.xml"),"UTF-8"));
    for(String line = reader.readLine();line != null;line=reader.readLine()){
       writer.println(line);
    }
    writer.close();
    reader.close();*/
	   /*TestOrgs orgs = new TestOrgs();
	   orgs.setBatchNumber(20150418001L);
	   orgs.setErrmsg("no error");
	   orgs.setSize(1);
	   List<TestOrg> orgList = new ArrayList<TestOrg>();
	   TestOrg org = new TestOrg();
	   org.setAttribute("name");
	   org.setEndDate(new Date());
	   org.setInsertTime(new Date());
	   org.setOrgCode("001");
	   org.setOrgId(10001L);
	   org.setOrgName("lenzhao");
	   org.setOrgType("male");
	   org.setParentId(100L);
	   org.setStartDate(new Date());
	   orgList.add(org);
	   orgs.setOrgs(orgList);
      // TestOrgs testOrgs = JaxbReadXml.readString(TestOrgs.class, "/home/lenzhao/data/jaxb.xml");
       String xml = gernateConfigXml(TestOrgs.class,orgs);
       PrintWriter wt = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/home/lenzhao/data/jaxb.xml"),"UTF-8"));
       wt.write(xml);
       wt.flush();
       wt.close();
       System.out.println(xml);*/
	   Message msg = readString(Message.class, "/home/lenzhao/data/jaxb.xml");
	   System.out.println(msg.remainpoint);
   }
   
   @XmlRootElement(name = "returnsms" )
	@XmlAccessorType(XmlAccessType.FIELD )
	public static class Message{
		@XmlElement(name = "returnstatus")
		public String returnstatus;
		@XmlElement(name="remainpoint")
		public String remainpoint;
		@XmlElement(name="taskID")
		public String taskID;
		@XmlElement(name="successCounts")
		public String successCounts;
	}
}
