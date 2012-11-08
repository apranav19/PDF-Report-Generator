package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import play.Play;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AbstractPDFGenerator {

	protected static final BaseColor HEADER_BG = new BaseColor(250,244,230);
	protected static final BaseColor TRACKER_SECTION_BG = new BaseColor(0,61,87);
	protected static final BaseColor MEASURE_SECTION_FONTS = new BaseColor(47,132, 163);

	protected PdfPTable table;
	protected List<PdfPCell> page_contents;
	protected String report;

	public AbstractPDFGenerator() {
		this.report = Play.tmpDir + "/results.pdf";
		this.table = new PdfPTable(1);
		this.page_contents = new ArrayList<PdfPCell>(3);
	}

	public void generateTemplate() throws FileNotFoundException,
			DocumentException {
		this.table.setWidthPercentage(100);

		this.page_contents.add(this.generateHeader());
		this.page_contents.add(this.generateMidSection());
		this.page_contents.add(this.generateBottomSection());
	}

	private PdfPCell generateHeader() {
		PdfPCell top_section = new PdfPCell();
		top_section.setBackgroundColor(HEADER_BG);
		top_section.setPadding(5);

		return top_section;
	}

	private PdfPCell generateMidSection() {
		PdfPCell mid_section = new PdfPCell();
		mid_section.setBackgroundColor(TRACKER_SECTION_BG);
		mid_section.setPadding(5);

		return mid_section;
	}

	private PdfPCell generateBottomSection() {
		PdfPCell bottom_section = new PdfPCell();
		bottom_section.setBackgroundColor(BaseColor.WHITE);
		bottom_section.setPadding(5);

		return bottom_section;
	}

}
