package ph.com.alliance.jpa.functions.file.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
@PropertySource("classpath:app.properties")
@Service
public class FileServiceImp implements FileService {
	 @Value("${app.files.path}")
	    private String baseDirectory;
	 
    String basePath = System.getProperty("user.dir") + "\\";
    
    @Override
    public String downloadCsv(List<List<String>> items) throws IOException {        
        String strFilePath = basePath+"file.csv";
        
        if (null != items) {
            OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream(strFilePath));
            CSVWriter writer = new CSVWriter(stream,
                    CSVWriter.DEFAULT_SEPARATOR, 
                    CSVWriter.DEFAULT_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    "\r\n");
            ArrayList<String[]> dataToWrite = new ArrayList<String[]>();
            
           for (List<String> item : items){
                if (null != item && !item.isEmpty())
                    dataToWrite.add( item.toArray(new String[item.size()]));
            }
            writer.writeAll(dataToWrite, true);
            writer.close();
        }
        
        return strFilePath;
    }

    @Override
    public String downloadXls(List<List<String>> items) throws Exception {
        String strFilePath = basePath+"file.xlsx";
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheetname");
        sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); 
        sheet.getPrintSetup().setLandscape(true);
        
        if (null != items && !items.isEmpty()) {
            for (int iYaxis = 0; iYaxis < items.size(); iYaxis++) {
                List<String> item = items.get(iYaxis);
                Row row = sheet.createRow(iYaxis);
                for(int iXaxis = 0; iXaxis < items.size(); iXaxis++){
                    Cell cell = row.createCell(iXaxis);
                    cell.setCellValue(item.get(iXaxis));
                    sheet.autoSizeColumn(iXaxis);
                }
            }
        }
            
        try {
            FileOutputStream outputStream = new FileOutputStream(strFilePath);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            throw e;
        }
        
        return strFilePath;
    }

    @Override
    public String downloadPdf(List<List<String>> items) throws FileNotFoundException, DocumentException {
        String strFilePath = basePath+"file.pdf";
        
        Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 10f);
        PdfWriter.getInstance(document, new FileOutputStream(strFilePath));
        document.open();
        document.newPage();
        
        if (null != items && !items.isEmpty()) {
            PdfPTable table =  new PdfPTable(items.get(0).size());
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            for (int iYaxis = 0; iYaxis < items.size(); iYaxis++) {
                List<String> item = items.get(iYaxis);
                for(int iXaxis = 0; iXaxis < items.size(); iXaxis++){
                    table.addCell(item.get(iXaxis));
                }
            }
            document.add(table);
        }
        document.close();
        return strFilePath;
    }
    
    public String getBaseDirectory() {
        return baseDirectory;
    }

}
