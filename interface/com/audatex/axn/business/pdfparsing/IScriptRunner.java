package com.audatex.axn.business.pdfparsing;

import java.util.Map;

public interface IScriptRunner {

  Object evaluateScript(Map<String,Object> context, String scriptName) throws ScriptException;

}
