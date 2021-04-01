package com.chinamobile.cmss.bcse.search.tool;


import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;



















import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;







import org.dom4j.io.XMLWriter;



/***
 * 
 *xml  tool
 * 
 * */
public class XmlTool {
	
	public static void main(String[] args) throws Exception {
		//writeXml("a.xml");
		
		Document doc=readXML("conf//solrconfig.xml", "utf-8");
		Element rootElement = (Element) doc.getRootElement();
		Element queryElement = rootElement.addElement("cnssss");
		writeXML(doc, "conf//solrconfig.xml", "utf-8");
		//Document doc=getXmlDocumentFromString("conf//schema.xml");
		//new File("conf//schema.xml").getAbsoluteFile();
		//System.out.println(doc.getRootElement().getName());
		//readConfigXml("config.xml");
	}
	

	/**
	 * 
	 * 通过文件路径获得document对象
	 * 
	 * @param xmlFilePath
	 * @return Document
	 * 
	 * */
	public static Document getXmlDocumentFromFile(String xmlFilePath) {
		Document document = null;
		if (null != xmlFilePath) {
			SAXReader reader = new SAXReader();
			try {
				document = reader.read(new File(xmlFilePath));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return document;
	}

	/**
	 * 
	 * 通过字符串获得document对象
	 * 
	 * @param xmlString
	 * @return Document
	 * 
	 * */
	public static Document getXmlDocumentFromString(String xmlString) {
		// System.out.println(xmlString);
		Document document = null;
		if (null != xmlString) {
			try {
				document = DocumentHelper.parseText(xmlString);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return document;
	}
	/**
	 * 写XMl文件加锁
	 * @param document
	 * @param elevateXmlFile
	 * @param charset
	 * @throws IOException 
	 * 
	 * */
	@SuppressWarnings("unused")
	public static void writeXML(Document document,String elevateXmlFile,String charset)
			throws IOException {

		if (!new File(elevateXmlFile).exists()) {
			throw new FileNotFoundException(elevateXmlFile + "is not exist!");
		}
		XMLWriter writer = null;
		FileOutputStream fos=null;
		try {
			 fos = new FileOutputStream(elevateXmlFile);
			FileLock lock = null;
			
				lock = fos.getChannel().lock();
				OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
				xmlFormat.setEncoding(charset);
				writer = new XMLWriter(fos, xmlFormat);
				writer.write(document);
				lock.release();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
			fos.close();
		}

		return;
	}
	/**
	 * 读XMl文件加锁
	 * @param elevateXmlFile
	 * @param charset
	 * @throws Exception 
	 * 
	 * */
	@SuppressWarnings("unused")
	public
	static Document readXML(String elevateXmlFile,String charset) throws Exception {
         File file=new File(elevateXmlFile);
         //System.out.println(file.getName());
		if (!new File(elevateXmlFile).exists()) {
			return null;
		}
		Document document = null;
		try {
			RandomAccessFile raf = new RandomAccessFile(elevateXmlFile, "rw");
			FileLock lock = null;
			try {
				lock = raf.getChannel().lock();
				byte[] xmlContent = new byte[(int) raf.length()];
				raf.readFully(xmlContent);
				//System.out.println(new String(xmlContent));
				try {
					document =  DocumentHelper.parseText(new String(xmlContent,charset));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				lock.release();
			} finally {
				raf.close();
			}
		} catch (Exception e) {
			throw e;
		}
		return document;
	}

     
}
