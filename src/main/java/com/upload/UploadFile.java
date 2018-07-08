package com.upload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Servlet implementation class UploadFile
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String saveFile="D:/upload/";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();

try {
boolean ismultipart=ServletFileUpload.isMultipartContent(request);
if(!ismultipart){

}else{
FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
List items = null;

try{

items = upload.parseRequest(request);
}catch(Exception e){
}
Iterator itr = items.iterator();
while(itr.hasNext()){
FileItem item = (FileItem)itr.next();
if(item.isFormField()){

}else{
String itemname = item.getName();
if((itemname==null || itemname.equals(""))){
continue;
}
String filename = FilenameUtils.getName(itemname);
File f = checkExist(filename);
item.write(f);

InputStream inputStream= new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(f)));

Workbook wb = WorkbookFactory.create(inputStream);
Sheet mySheet = wb.getSheetAt(0);
Iterator<Row> rowIter = mySheet.rowIterator();
rowIter.next();
}
}
}

}catch(Exception e){

}
finally {
out.close();
}
}

private File checkExist(String fileName) {
File f = new File(saveFile+"/"+fileName);

if(f.exists()){
StringBuffer sb = new StringBuffer(fileName);
sb.insert(sb.lastIndexOf("."),"-"+new Date().getTime());
f = new File(saveFile+"/"+sb.toString());
}
return f;
}

	
	
    public UploadFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
