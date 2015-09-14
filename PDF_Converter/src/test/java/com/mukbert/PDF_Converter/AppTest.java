package com.mukbert.PDF_Converter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
	
	
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }
    
    /**
     * Rigorous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
	public void testPDF() throws IOException, XDocReportException
	{
		try 
		{
			String PATH = "src/main/res";
			
			File inputFile 		= new File(PATH + "/test.docx");
			File translatetFile = new File(PATH + "/testTranslatet.docx");
			File convertedFile 	= new File(PATH + "/testConverted.pdf");
			
			PDF_Converter.translateViaFreemarker(inputFile, translatetFile);
			PDF_Converter.convertDocxToPdf(translatetFile, convertedFile);
			
			assertTrue(true);
		} 
		catch (Exception e) 
		{
			assertTrue(false);
		}
	}
}
