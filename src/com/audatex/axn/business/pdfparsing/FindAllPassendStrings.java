package com.audatex.axn.business.pdfparsing;

import com.audatex.axn.business.calculation.manualcalculationitem.ManualCalculationItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 02.07.2017.
 */
public class FindAllPassendStrings {

    public List<ManualCalculationItem> findStringsAndMakeObjekt(String dokument){

        List<ManualCalculationItem> manualCalculationItems = new ArrayList<ManualCalculationItem>();

        String[]allLines = dokument.split("\n");


        String typeOperation="";
        for (int i = 0; i <allLines.length ; i++) {
            String nextLine = allLines[i];
nextLine.trim();
           String[]lineContens= nextLine.split(" ");
 String lastWordOfLine=lineContens[lineContens.length-1];

            //find first String with contaning at the end Double without $
            if (lastWordOfLine.matches("-?[0-9]+\\.[\\d]+")){

                ManualCalculationItem manualCalculationItem=new ManualCalculationItem();
//defining Type of Operation
                String beforeLine=allLines[i-1];
                beforeLine.trim();
                String[]lineContensbeforeLine= beforeLine.split(" ");
                String lastWordofbeforeLine=lineContensbeforeLine[lineContensbeforeLine.length-1];
                if (!lastWordofbeforeLine.matches("-?[0-9]+\\.[\\d]+")){
                     typeOperation=beforeLine;
                }

                manualCalculationItem.setRepairOperation(typeOperation);
                manualCalculationItem.setAmount(new BigDecimal(lastWordOfLine));

                String description;
//description is all Text before Number
                if (!typeOperation.contains("Part No")||!typeOperation.contains("PartNo")){
                     description = nextLine.replaceAll("-?\\d\\.?","").trim();
                    manualCalculationItem.setDescription(description);
                }
//defining description for Strings with Type Operation contaning "Part No"
                if (typeOperation.contains("Part No")||typeOperation.contains("PartNo")){
                    String otherDescription="";
                    String partNumber="";
                    for (int j = 0; j <lineContens.length ; j++) {

                        if (!(lineContens[j] == " ") && lineContens[j].matches("\\D+")) {
                            otherDescription = otherDescription+" "+lineContens[j];
                        }
                        // "Part No" may contain mix letter and number
                        if (lineContens[j].matches("[\\D]+?[0-9]+?\\.?[0-9]+?")&&!(j==lineContens.length-1)){
                            partNumber = lineContens[j];
                        }else if (lineContens[j].matches("\\d+")){
                            partNumber=lineContens[j];
                        }
                    }

                    manualCalculationItem.setDescription(otherDescription.trim());
                    manualCalculationItem.setPartNumber(partNumber);
                }

                manualCalculationItems.add(manualCalculationItem);
            }
        }


        return manualCalculationItems ;
        }



 }
