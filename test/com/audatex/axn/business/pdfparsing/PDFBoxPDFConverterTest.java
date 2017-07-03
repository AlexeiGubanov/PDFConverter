package com.audatex.axn.business.pdfparsing;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class PDFBoxPDFConverterTest {

  @Test
  public void testParse() {
    InputStream pdf = getResource("examplepdf/samplePdfPpg.pdf");
    //InputStream pdf = getResource("examplepdf/PPG Maybe - Quote 15.pdf");
    try {
      String result = new PDFBoxPDFConverter().convertToSV(pdf, ";");
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  private static InputStream getResource(String name) {
    return PDFBoxPDFConverterTest.class.getResourceAsStream(name);
  }


}
