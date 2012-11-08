package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Params;
import util.CustomReport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import models.*;

public class Application extends Controller {

	public static void index() throws DocumentException, IOException, Exception {
		render();
	}

	/**
	 * @throws DocumentException
	 * @throws Exception
	 * @throws IOException
	 */
	public static void generateTemplate() throws DocumentException, Exception,
			IOException {
		Person person = new Person("Pranav", "Angara", 21, "Occupation", Play.getFile("/public/images/result-chart.png"));
		CustomReport myReport = new CustomReport(person);
		renderBinary(myReport.createFile());
	}

	/**
	 * A callback method that receives the SVG element from the main page and converts it into a PNG image
	 * @throws DocumentException
	 * @throws Exception
	 */
	public static void convertSVGToPNG() throws DocumentException, Exception {
		File base_file = Play.getFile("/public/images/result-chart.png");
		String svg_element = params.all().get("svg")[0];

		PNGTranscoder transcoder = new PNGTranscoder();

		InputStream instream = new ByteArrayInputStream(svg_element.getBytes());
		OutputStream outstream = new FileOutputStream(base_file);

		TranscoderInput tinput = new TranscoderInput(instream);
		TranscoderOutput toutput = new TranscoderOutput(outstream);

		transcoder.transcode(tinput, toutput);

		instream.close();
		outstream.flush();
		outstream.close();

		generateTemplate();
	}
}
