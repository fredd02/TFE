package com.tfe.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfe.exceptions.StorageFileNotFoundException;
import com.tfe.service.IStorageService;

@Controller
@RequestMapping("/file")
public class FileUploadController {
	
	//logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final IStorageService storageService;
	
	@Autowired
	public FileUploadController(IStorageService storageService) {
		this.storageService = storageService;
	}
	
	//methode GET pour uploader un fichier
	@GetMapping("/upload")
	public String listUploadedFiles(Model model) throws IOException {
		
		log.info("methode GET pour uploader des fichiers");
		
		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toString())
				.collect(Collectors.toList()));
		
		return "storage/uploadForm";
		
	}
	
	//methode POST pour uploader un fichier
	@RequestMapping(value="/upload", method=RequestMethod.POST, headers = "content-type=multipart/form-data")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, 
			RedirectAttributes redirectAttributes) throws IOException {
		
		log.info("methode POST pour uploader des fichiers");
		
		if(!file.getContentType().equals("application/pdf")) {
			redirectAttributes.addFlashAttribute("messageError","Vous ne pouvez uploader que des fichiers pdf");
			return "redirect:/file/upload";
		}
		log.info(file.getContentType());
		
		storageService.store(file);
		redirectAttributes.addFlashAttribute("messageSuccess", "Fichier uploadé avec succès: " + file.getOriginalFilename());
		
		return "redirect:/file/upload";
	}
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	
	
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){
		return ResponseEntity.notFound().build();
	}

}
