


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import javax.xml.transform.Transformer;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult; 

import org.w3c.dom.Element;


 class Read_Xml_File 
 {

    public static String ReadFile(String xmlname,String root,String name)
{

        String testinput = null;
        File fXmlFile = new File("C:\\Users\\schaduvu\\Desktop\\BDA_Testing\\"+xmlname);
       // System.out.println(fXmlFile);
        
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        
        
	    try 
	    {
	        dBuilder = dbFactory.newDocumentBuilder();
	    } 
	    catch (ParserConfigurationException e)
	    {
	        e.printStackTrace();
	    }
	    
	    
	    Document doc= null;
	    try
	    {
	        doc = dBuilder.parse(fXmlFile);
	    } 
	    catch (SAXException e)
	    {
	        e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    } 
	    
	    
        XPath xPath =  XPathFactory.newInstance().newXPath();
        String ele = String.format("/"+root+"/"+name);

        Node node = null;
        
	    try
	    {
	        node = (Node) xPath.compile(ele).evaluate(doc, XPathConstants.NODE);
	    } 
	    catch (XPathExpressionException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    testinput = node != null ? (node.getTextContent()) : "cannot read the test case xml file";
	        return testinput;

}

}
 class WriteXMl
 {
	 Document doc=null;
	 Element rootElement=null;
 public WriteXMl(String root)
 {
 	try
 	{
 		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
 		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 		doc = docBuilder.newDocument();
 		rootElement = doc.createElement(root);
 		createRootTag();
 	}
 	  catch (ParserConfigurationException pce) 
	  {
		pce.printStackTrace();
	  } 
	
 }
 public void XMLFormat()
 {
	 try
	 {
 		TransformerFactory transformerFactory = TransformerFactory.newInstance();
 		Transformer transformer = transformerFactory.newTransformer();
 	
 	
 		DOMSource source = new DOMSource(doc);
 		StreamResult result = new StreamResult(new File("C:\\Users\\schaduvu\\Desktop\\output.xml"));

 		transformer.transform(source, result);
 		
 		System.out.println("File saved!");
 		
 	}
	 catch(Exception e)
	 {
		 
	 }

 	}
 public void createRootTag()
 {
 	
 	doc.appendChild(rootElement);
 }
 public void createChildTags(String subtag,String status)
 {
 	Element firstname = doc.createElement(subtag);
 	firstname.appendChild(doc.createTextNode(status));
 	rootElement.appendChild(firstname);
 	}
 }
class Connection{
	
	WebDriver driver;
	public Connection() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\schaduvu\\Downloads\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver();
	}
	public  WebDriver openBrowser() throws InterruptedException
	{
	    driver.get(Read_Xml_File.ReadFile("url.xml","url" ,"BigDataAnalytics"));
	    driver.manage().window().maximize();
	    Thread.sleep(1000);
		return driver;
	}
	public void closeBrowser()
	{
		driver.close();
	}
	
}
public class BDA_Test{
	WebDriver driver;
	Connection conn;
	
	public BDA_Test(Connection conn) throws InterruptedException
	{
		this.conn=conn;
		driver=conn.openBrowser();
	}
    public String instanceValidation() throws InterruptedException
    {
		driver=conn.openBrowser();
		driver.findElement(By.id("username")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","username"));
	    driver.findElement(By.xpath("//*[@id='login']/div/div/div[2]/div[1]/input[2]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","pwd"),Keys.ENTER);
	    Thread.sleep(5000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[1]/div/div/div[7]")).click(); // Load operation
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[2]")).click(); //data source addition using + operator
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[2]/div/div/div/div[12]")).click(); //postgres selection
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[1]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Name"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[2]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Description"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[3]")).clear();
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[3]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Server"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[5]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","User"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[6]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Password"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[7]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Database"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/input[8]")).sendKeys(Read_Xml_File.ReadFile("testData.xml","Data","Options"));
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[1]/div[9]/div")).click();
	    Thread.sleep(2000);
	    
	    String validate =driver.findElement(By.xpath("/html/body/div[3]/div[7]/div[2]/div[1]/div/div")).getText();        // Test operation
	    
	    if(validate.equals("Connection test finished successfully"))
	    {
	    	
		   System.out.println("verified");
	    }
	    else
	    	
	    {
		   System.out.println("failed");
	    }
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[7]/div[2]/div[2]")).click();   // pressing ok in test operation
	    Thread.sleep(1000);
	    
	    driver.findElement(By.xpath("/html/body/div[3]/div[6]/div[2]/div[3]")).click();      //pressing ok in form
	    Thread.sleep(1000);

	    String li = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[4]/div[2]/div[2]/div/div/div[3]/div[2]/div/div[2]/div[3]/div//div[1]")).getText();   // Retrieving created instances
	    String l2[]= li.split("\n");
	    if(l2[l2.length-1].equals(Read_Xml_File.ReadFile("testData.xml","Data","Name")))
		  return "passed";
	    return "failed" ;
		 
    }
    /*public String exploreObject()
    {
    	
    	return " ";
    	
    }*/
    
    
	public static void main(String[] args) throws InterruptedException {
		Connection conn=new Connection();
	    BDA_Test testobj=new BDA_Test(conn);
		String status=testobj.instanceValidation();
		WriteXMl xmlobj=new WriteXMl("data");
		xmlobj.createChildTags("postgres",status);
		xmlobj.XMLFormat();
	}

}





