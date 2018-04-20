package com.tfe.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestFileDownloadController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String INTERNAL_FILE="Accepted.pdf";
	private static final String EXTERNAL_FILE_PATH="C:\\Users\\fred\\Desktop\\testMenu.pdf";
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String getTestPage(Model model) {
		
		return "testDownload";
	}
	
	
	@RequestMapping(value="/download/{type}", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, @PathVariable("type") String type) throws IOException {
		
		File file = null;
		
		if(type.equalsIgnoreCase("internal")) {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			file = new File(classloader.getResource(INTERNAL_FILE).getFile());
			
		} else {
			file = new File(EXTERNAL_FILE_PATH);
		}
		
		if(!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exsist";
			log.info(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
			
		}
		
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if(mimeType == null) {
			log.info("mimeType is not detectable");
			mimeType = "application/octet-stream";
		}
		
		log.info("mimeType: " + mimeType);
		response.setContentType(mimeType);
		
		response.setHeader("ContentDisposition",String.format("inline; filename=\"" + file.getName() + " \""));
		//response.setHeader("ContentDisposition",String.format("attachment; filename=\"%s\"", file.getName()));
		
		response.setContentLength((int)file.length());
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		
		
	}

	

}
