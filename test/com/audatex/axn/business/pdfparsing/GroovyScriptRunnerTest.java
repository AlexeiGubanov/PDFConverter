package com.audatex.axn.business.pdfparsing;


import com.audatex.axn.business.calculation.manualcalculationitem.ManualCalculationItem;
import com.audatex.axn.valueholders.cases.TaskType;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GroovyScriptRunnerTest {

  @Test
  public void testEvaluateScript() throws ScriptException {
    GroovyScriptRunner gsr = getGroovyScriptRunner();
    HashMap<String,Object> params = new HashMap<String,Object>();
    List<ManualCalculationItem> r = (List<ManualCalculationItem>) gsr.evaluateScript(params, "examplescripts/ppgLines.txt");
    assertEquals(1, r.size());
    assertEquals("11", r.get(0).getLineNumber());

  }

  private GroovyScriptRunner getGroovyScriptRunner() {
    return new GroovyScriptRunner(new IResourceScriptProvider() {
        @Override
        public String getResource(String fileName) {
          try {
            return getTestResource(fileName);
          } catch (IOException e) {
            return "";
          }
        }
      });
  }

  @Test
  public void testResolveInterpretator() throws IOException, ScriptException {
    GroovyScriptRunner gsr = getGroovyScriptRunner();
    HashMap<String,Object> params = new HashMap<String,Object>();
    params.put("pdfContent", parsePdf("examplepdf/samplePdfPpg.pdf"));
    System.out.println(gsr.evaluateScript(params, "examplescripts/resolveInterpretator.txt"));
  }

  private static String getTestResource(String name) throws IOException {
    InputStream is= GroovyScriptRunnerTest.class.getResourceAsStream(name);
    return IOUtils.toString(is);
  }

  private String parsePdf(String name) throws IOException {
    InputStream pdf = GroovyScriptRunnerTest.class.getResourceAsStream(name);
    return new PDFBoxPDFConverter().convertToSV(pdf, ";");
  }
}
