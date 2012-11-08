package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import play.Play;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import models.Person;

public class CustomReport extends AbstractPDFGenerator {
	
	private Person myPerson;
	private Document myDocument;
	
	public CustomReport(Person person) throws FileNotFoundException, DocumentException{
		super();
		super.generateTemplate();
		this.myPerson = person;
		this.myDocument = new Document(PageSize.A4);
	}
	
	public File createFile() throws DocumentException, IOException{
		fillPDFTable();
		fillDocument();
		
		File base_file = new File(report);
		return base_file;
	}
	
	public void fillPDFTable() throws BadElementException, IOException{
		setTemplateHeader();
		setTemplateMidSection();
		setTemplateEndSection();
		
		for(PdfPCell cell: this.page_contents){
			this.table.addCell(cell);
		}
	}
	
	public void fillDocument() throws FileNotFoundException, DocumentException{
		PdfWriter.getInstance(myDocument, new FileOutputStream(this.report));
		myDocument.setMargins(0, 0, 0, 0);
		myDocument.open();
		myDocument.add(table);
		myDocument.close();
	}
	
	public void setTemplateHeader(){
		Chunk header_content = new Chunk();
		header_content.append(this.myPerson.first_name + " " + this.myPerson.last_name);
		this.page_contents.get(0).addElement(header_content);
	}
	
	public void setTemplateMidSection(){
		Chunk mid_content = new Chunk();
		mid_content.append("Occupation: \n");
		mid_content.append(this.myPerson.occupation + "\n");
		mid_content.append("Age: \n");
		mid_content.append("" + this.myPerson.age);
		this.page_contents.get(1).addElement(mid_content);
	}
	
	public void setTemplateEndSection() throws BadElementException, IOException{
		URL img_url = this.myPerson.photo.toURI().toURL();
		this.page_contents.get(2).addElement(Image.getInstance(img_url));
	}

}
