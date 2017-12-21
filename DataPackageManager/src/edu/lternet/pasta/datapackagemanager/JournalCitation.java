package edu.lternet.pasta.datapackagemanager;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.xpath.CachedXPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class JournalCitation {
    
    /*
     * Class variables
     */
    
    private static Logger logger = Logger.getLogger(JournalCitation.class);

    
    /*
     * Instance variables
     */
    
    int journalCitationId;
    String articleTitle;
    String articleDoi;
    String principalOwner;
    LocalDateTime dateCreated;
    String packageId;
    String journalTitle;
    

    
    public static void main(String[] args) {
        String dirPath = "WebRoot/WEB-INF/conf";
        boolean includeDeclaration = true;
        try {
            ConfigurationListener configurationListener = new ConfigurationListener();
            configurationListener.initialize(dirPath);
            DataPackageManager dpm = new DataPackageManager();
            DataPackageRegistry dpr = DataPackageManager.makeDataPackageRegistry();
            String userId = "uid=LNO,o=LTER,dc=ecoinformatics,dc=org";
            StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            sb.append("<journalCitation>\n");    
            sb.append("    <packageId>edi.1000.1</packageId>\n"); 
            sb.append("    <articleDoi>10.5072/FK2/06dccc7b0cb2a2d5f6fef62cb4b36dae</articleDoi>\n"); 
            sb.append("    <articleTitle>Tree Survey in Rio Rico, Arizona</articleTitle>\n"); 
            sb.append("    <journalTitle>Arizona Highways</journalTitle>\n"); 
            sb.append("</journalCitation>\n");
            String requestXML = sb.toString();
            JournalCitation journalCitation = dpm.createJournalCitation(userId, requestXML);
            System.out.println(journalCitation.toXML(includeDeclaration));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /*
     * Constructors
     */
    
    /**
     * Create a new JournalCitation object. The empty constructor.
     * 
      */
    public JournalCitation() {
        super();
    }
    
    
    /**
     * Create a new JournalCitation object by parsing the journal citation XML string.
     * 
     * @param xml   an XML string that conforms to the journal citation format, typically sent in
     *              a web service request body
     */
    public JournalCitation(String xml) {
        parseDocument(xml);
    }
    
    

    
    /*
     * Instance methods
     */
    
    
    /**
     * Parses an EML document.
     * 
     * @param   xml          The XML string representation of the EML document
     * @return  dataPackage  a DataPackage object holding parsed values
     */
    private void parseDocument(String xml) {
      if (xml != null) {
        try {
          InputStream inputStream = IOUtils.toInputStream(xml, "UTF-8");
          parseDocument(inputStream);
        }
        catch (Exception e) {
          logger.error("Error parsing journal citation metadata: " + e.getMessage());
        }
      }
    }

 
    /**
     * Parses an EML document.
     * 
     * @param   inputStream          the input stream to the EML document
     * @return  dataPackage          a DataPackage object holding parsed values
     */
    private void parseDocument(InputStream inputStream) 
            throws Exception {
      
      DocumentBuilder documentBuilder = 
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
      CachedXPathAPI xpathapi = new CachedXPathAPI();

      Document document = null;

      try {
        document = documentBuilder.parse(inputStream);
        
        if (document != null) {

            Node journalCitationIdNode = xpathapi.selectSingleNode(document, "//journalCitationId");
            if (journalCitationIdNode != null) {
              String journalCitationIdStr = journalCitationIdNode.getTextContent();
              setJournalCitationId(Integer.parseInt(journalCitationIdStr));
            }
            
            Node packageIdNode = xpathapi.selectSingleNode(document, "//packageId");
            if (packageIdNode != null) {
              String packageId = packageIdNode.getTextContent();
              setPackageId(packageId);
            }
            
            Node articleDoiNode = xpathapi.selectSingleNode(document, "//articleDoi");
            if (articleDoiNode != null) {
              String articleDoi = articleDoiNode.getTextContent();
              setArticleDoi(articleDoi);
            }

            Node articleTitleNode = xpathapi.selectSingleNode(document, "//articleTitle");
            if (articleTitleNode != null) {
              String articleTitle = articleTitleNode.getTextContent();
              setArticleTitle(articleTitle);
            }

            Node journalTitleNode = xpathapi.selectSingleNode(document, "//journalTitle");
            if (journalTitleNode != null) {
              String journalTitle = journalTitleNode.getTextContent();
              setJournalTitle(journalTitle);
            }

        }
      }
      catch (SAXException e) {
          logger.error("Error parsing document: SAXException");
          e.printStackTrace();
          throw(e);
        } 
        catch (IOException e) {
          logger.error("Error parsing document: IOException");
          e.printStackTrace();
          throw(e);
        }
        catch (TransformerException e) {
          logger.error("Error parsing document: TransformerException");
          e.printStackTrace();
          throw(e);
        }
    }
    
    
    /**
     * Composes the XML representation of this JournalCitation object 
     * 
     * @return  an XML string representation
     */
    public String toXML(boolean includeDeclaration) {
        String firstLine = includeDeclaration ? "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" : "";
        StringBuilder sb = new StringBuilder(firstLine);
        sb.append("<journalCitation>\n");
        
        if (this.journalCitationId > 0)
            { sb.append(String.format("    <journalCitationId>%d</journalCitationId>\n", this.journalCitationId)); } 
        
        sb.append(String.format("    <packageId>%s</packageId>\n", this.packageId)); 
        sb.append(String.format("    <principalOwner>%s</principalOwner>\n", this.principalOwner)); 
        sb.append(String.format("    <dateCreated>%s</dateCreated>\n", getDateCreatedStr())); 
        sb.append(String.format("    <articleDoi>%s</articleDoi>\n", this.articleDoi));
        
        if (this.articleTitle != null)
            { sb.append(String.format("    <articleTitle>%s</articleTitle>\n", this.articleTitle)); } 
        
        if (this.journalTitle != null)
            { sb.append(String.format("    <journalTitle>%s</journalTitle>\n", this.journalTitle)); } 
        
        sb.append("</journalCitation>\n");

        String xml = sb.toString();
        return xml;
    }
    
    
    private String getDateCreatedStr() {
        String dateCreatedStr = "";
        if (this.dateCreated != null) {
            dateCreatedStr = dateCreated.toString();
        }
        
        return dateCreatedStr;
    }
    
    
    /*
     * Accessors
     */
    
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDoi() {
        return articleDoi;
    }

    public void setArticleDoi(String articleDoi) {
        this.articleDoi = articleDoi;
    }
    
    public int getJournalCitationId() {
        return journalCitationId;
    }
    
    public void setJournalCitationId(int val) {
        this.journalCitationId = val;
    }

    public String getPrincipalOwner() {
        return principalOwner;
    }

    public void setPrincipalOwner(String principalOwner) {
        this.principalOwner = principalOwner;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime localDateTime) {
        this.dateCreated = localDateTime;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

}
