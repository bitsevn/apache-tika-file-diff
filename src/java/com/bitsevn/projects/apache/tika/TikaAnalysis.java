package com.bitsevn.projects.apache.tika;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TikaAnalysis {

    private static final String PATH = "D:\\code\\java\\ApacheTika\\resources\\docs\\";

    public static void main(String[] args) throws TikaException, IOException, SAXException {
        System.out.println(extractContentFromPdf(toInputStream("big-pdf.pdf")));
    }

    public static InputStream toInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(PATH + fileName);
    }

    public static InputStream getInputStream(String fileName) {
        return TikaAnalysis.class.getClassLoader()
                .getResourceAsStream(fileName);
    }

    public static String detectDocTypeUsingDetector(String absolutePathFile) throws IOException {
        return detectDocTypeUsingDetector(getInputStream(absolutePathFile));
    }

    public static String detectDocTypeUsingDetector(InputStream stream)
            throws IOException {
        Detector detector = new DefaultDetector();
        Metadata metadata = new Metadata();

        MediaType mediaType = detector.detect(stream, metadata);
        return mediaType.toString();
    }
    public static String extractContentUsingParser(String fileName)
            throws IOException, TikaException, SAXException {
        return extractContentUsingParser(getInputStream(fileName));
    }


    public static String extractContentUsingParser(InputStream stream)
            throws IOException, TikaException, SAXException {

        Parser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();

        parser.parse(stream, handler, metadata, context);
        return handler.toString();
    }

    public static String extractContentFromPdf(InputStream stream)
            throws IOException, TikaException, SAXException {

        Parser parser = new AutoDetectParser();
        TesseractOCRConfig config = new TesseractOCRConfig();
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        context.set(TesseractOCRConfig.class, config);
        context.set(PDFParserConfig.class, pdfConfig);
        parser.parse(stream, handler, metadata, context);
        return handler.toString();
    }
}
