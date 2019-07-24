package com.github.tanxinzheng;

import com.google.common.collect.Lists;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.util.List;

public class MargePdf {

    public static void main(String[] args) {
        String root = "/Users/jeng/Documents/学习/RDR/高级操作系统/";
        List<String> list = Lists.newArrayList();
        for (int i = 1; i < 12; i++) {
            list.add(root + i + ".pdf");
        }
        String[] files = list.toArray(new String[list.size()]);
        String savepath = "/Users/jeng/Documents/学习/RDR/高级操作系统/高级操作系统汇总版.pdf";
        mergePdfFiles(files, savepath);
    }


    public static boolean mergePdfFiles(String[] files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files[0]).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.length; i++) {
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return retValue;
    }
}
