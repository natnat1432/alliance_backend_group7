package ph.com.alliance.jpa.functions.file.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.functions.file.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
	ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/csv", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadCsv(@RequestBody String items) throws Exception {        
        
        List<List<String>> list = convertToTableData (items);
        
        Path resultFile = new File(fileService.downloadCsv(list)).toPath();
        byte[] contents =  Files.readAllBytes(resultFile);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/csv"));
        headers.setContentDispositionFormData(resultFile.getFileName().toString(), resultFile.getFileName().toString());
        
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        
        return response;
    }
    
    @RequestMapping(value = "/xlsx", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadXls(@RequestBody String items) throws Exception {

        List<List<String>> list = convertToTableData (items);
        Path resultFile = new File(fileService.downloadXls(list)).toPath();
        byte[] contents =  Files.readAllBytes(resultFile);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/xlsx"));
        headers.setContentDispositionFormData(resultFile.getFileName().toString(), resultFile.getFileName().toString());
        
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        
        return response;
    }
    
    @RequestMapping(value = "/pdf", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadPdf(@RequestBody String items) throws Exception {

        List<List<String>> list = convertToTableData (items);
        Path resultFile = new File(fileService.downloadPdf(list)).toPath();
        byte[] contents =  Files.readAllBytes(resultFile);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setContentDispositionFormData(resultFile.getFileName().toString(), resultFile.getFileName().toString());
        
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        
        return response;
    }
    
    @SuppressWarnings("unchecked")
    private List<List<String>> convertToTableData (String itemsList) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
        List<List<String>> dataList = new ArrayList<List<String>>();
        Object[] itemList = mapper.readValue(URLDecoder.decode(itemsList, "UTF-8"), Object[].class); 
        int i = 0;
        List<String> lines = new ArrayList<>();
        for (Object item: itemList){            
            if(i==0){
                for (String key : ((LinkedHashMap<String, String>)item).keySet() ) {
                    lines.add(key);
                }              
                dataList.add(lines);
            }
            lines = new ArrayList<>(); 
            for (String value : ((LinkedHashMap<String, String>)item).values()) {
                lines.add(value);
            }
            dataList.add(lines);
            i++;
        }        
        return dataList;
    }

}
