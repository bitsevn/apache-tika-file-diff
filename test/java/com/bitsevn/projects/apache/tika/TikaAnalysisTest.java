package com.bitsevn.projects.apache.tika;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TikaAnalysisTest {

    @Test
    public void whenUsingDetector_thenDocumentTypeIsReturned()
            throws IOException {
        InputStream stream = this.getClass().getClassLoader()
                .getResourceAsStream("docs/file-pdf.pdf");
        String mediaType = TikaAnalysis.detectDocTypeUsingDetector(stream);

        Assert.assertEquals("application/pdf", mediaType);

        stream.close();
    }
}
