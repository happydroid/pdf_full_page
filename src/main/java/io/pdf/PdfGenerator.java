package io.pdf;

import io.pdf.util.DataToHtmlGenerator;
import io.pdf.util.HtmlToPdfRender;
import org.thymeleaf.context.Context;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PdfGenerator {

    public static void main(String[] args) throws Exception {
        String template = DataToHtmlGenerator.loadTemplateAndGenerateHtml("report", new Context());

        OutputStream outputStream = Files.newOutputStream(Paths.get("output.pdf"));
        HtmlToPdfRender.generateToOutputStream(List.of(template), outputStream);
        outputStream.close();
    }

}
