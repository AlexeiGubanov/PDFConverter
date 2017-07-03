package com.audatex.axn.business.pdfparsing;

import java.io.IOException;
import java.io.InputStream;

/**
 * A common interface for pdf converters.
 * The implementation should provide a conversion to some plain data structure, such as separated values list.
 */
public interface IPDFConverter {

  /**
   * Converts a pdf document into separated string values, using given separator.
   */
  String convertToSV(InputStream pdf, String separator) throws IOException;

}
