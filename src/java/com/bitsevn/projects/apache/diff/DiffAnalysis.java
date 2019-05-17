package com.bitsevn.projects.apache.diff;

import com.bitsevn.projects.apache.tika.TikaAnalysis;
import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DiffAnalysis {

    private static final String PATH = "D:\\code\\java\\ApacheTika\\docs\\";

    public static void main(String[] args) throws IOException, DiffException, TikaException, SAXException {
        computeUnifiedDiff("docs/file-graph-original.pdf", "docs/file-graph-revised.pdf");
    }

    public static void computeUnifiedDiff(String original, String revised) throws DiffException, IOException, TikaException, SAXException {
        String originalExtract = TikaAnalysis.extractContentUsingParser(original);
        String revisedExtract = TikaAnalysis.extractContentUsingParser(revised);
        List<String> originalFileLines = Arrays.asList(originalExtract);
        List<String> revisedFileLines = Arrays.asList(revisedExtract);

        //generating diff information.
        Patch<String> diff = DiffUtils.diff(originalFileLines, revisedFileLines);

        //generating unified diff format
        List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff(original, revised, originalFileLines, diff, 0);
        System.out.println("--------------------------------------------------------");
        System.out.println("------------------- Unified Diff -----------------------");
        System.out.println("--------------------------------------------------------");
        unifiedDiff.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
    }
}
