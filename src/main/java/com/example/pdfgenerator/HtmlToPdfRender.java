package com.example.pdfgenerator;

import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.OutputStream;
import java.util.List;

// second phase of pdf generation. html -> pdf
public class HtmlToPdfRender {

    // html -> pdf. generate pdf file from list of html documents. Each document can be rendered to many pages
    // if the template included links to image, they are stored as links and do not take up much space in RAM
    public static void generateToOutputStream(List<String> htmlDocuments, OutputStream os) throws Exception {
        ITextRenderer renderer = new ITextRenderer();

        // we need to create the target PDF
        // we'll create one page per input string, but we call layout for the first
        renderer.setDocumentFromString(htmlDocuments.get(0));
        renderer.layout();
        renderer.createPDF(os, false);
        // each document after the first we add using layout() followed by writeNextDocument()
        if (htmlDocuments.size() > 1) {
            for (int i = 1; i < htmlDocuments.size(); i++) {
                renderer.setDocumentFromString(htmlDocuments.get(i));
                renderer.layout();
                renderer.writeNextDocument();
            }
        }

        // complete the PDF
        renderer.finishPDF();
    }

}
