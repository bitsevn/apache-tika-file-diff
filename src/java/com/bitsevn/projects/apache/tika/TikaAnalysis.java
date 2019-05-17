package com.bitsevn.projects.apache.tika;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class TikaAnalysis {

    private static final String PATH = "D:\\code\\java\\ApacheTika\\docs\\";

    public static void main(String[] args) throws TikaException, IOException, SAXException {
        System.out.println(extractContentUsingParser(getInputStream("docs/file-xml.xml")));
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
}
