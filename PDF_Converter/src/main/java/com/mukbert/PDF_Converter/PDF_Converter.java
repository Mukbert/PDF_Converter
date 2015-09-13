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

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
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
		File test = new File("test.docx");
		
		File test1 = translateViaFreemarker(test, new File("test1.docx"));
		
		File freemarker = convertDocxToPdf(test1, new File("test.pdf"));
	}
	
	public static File convertDocxToPdf(File inputFile, File outputFile) throws XDocConverterException, XWPFConverterException, IOException
	{
		if(inputFile == null || !inputFile.exists()) throw new FileNotFoundException();
		
		InputStream is = new FileInputStream(inputFile);
        XWPFDocument document = new XWPFDocument(is);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(outputFile);
        PdfConverter.getInstance().convert(document, out, options);
		
		System.out.println("Done! -> PDF_Converter.convertToPdf(File, File)");
		
		return outputFile;
	}
	
	public static File translateViaFreemarker(File inputFile, File outputFile) throws IOException, XDocReportException
	{
		if(inputFile == null || !inputFile.exists()) throw new FileNotFoundException();
		
		InputStream in= new FileInputStream(inputFile);
		IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Freemarker);
		
		IContext context = report.createContext();
		context.put("name", "world");
		
		OutputStream out = new FileOutputStream(outputFile);
		report.process(context, out);
		
		System.out.println("Done! -> PDF_Converter.convertViaFreemarker(File, File)");
		
		return outputFile;
	}
}
