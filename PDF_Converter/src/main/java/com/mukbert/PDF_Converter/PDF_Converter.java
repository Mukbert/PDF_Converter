package com.mukbert.PDF_Converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.core.XWPFConverterException;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class PDF_Converter 
{
	public static void main(String[] args) throws IOException, XDocReportException 
	{
		File inputFile = new File("test.docx");
		File translatetFile = new File("test1.docx");
		File convertedFile = new File("test.pdf");
		
		PDF_Converter.translateViaFreemarker(inputFile, translatetFile);
		PDF_Converter.convertDocxToPdf(translatetFile, convertedFile);
	}
	
	public static void translateViaFreemarker(File inputFile, File outputFile) throws IOException, XDocReportException
	{
		InputStream in= new FileInputStream(inputFile);
		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Freemarker);
		
		IContext context = report.createContext();
		context.put("name", "world");
		
		OutputStream out = new FileOutputStream(outputFile);
		report.process(context, out);
		
		in.close();
		out.close();
		
		System.out.println("Done! -> PDF_Converter.convertViaFreemarker(File, File)");
	}
	
	public static void convertDocxToPdf(File inputFile, File outputFile) throws XDocConverterException, XWPFConverterException, IOException
	{
		InputStream is = new FileInputStream(inputFile);
        XWPFDocument document = new XWPFDocument(is);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(outputFile);
        PdfConverter.getInstance().convert(document, out, options);
        is.close();
		out.close();
		System.out.println("Done! -> PDF_Converter.convertToPdf(File, File)");
	}
}
