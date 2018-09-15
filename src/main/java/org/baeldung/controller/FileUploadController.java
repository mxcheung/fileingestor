package org.baeldung.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import org.baeldung.service.FileStoreAndFowardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = FileUploadURI.FILEINGESTOR_BASE_CONTEXT)
public class FileUploadController {

	@Autowired
	private FileStoreAndFowardService fileStoreAndFowardService;

	@RequestMapping(value = FileUploadURI.POST_UPLOAD_MAPPING, method = POST)
	@ResponseStatus(ACCEPTED)
	@ResponseBody
	public ResponseEntity<Void> uploadHoldingFile(
			@RequestParam(value = "file", required = true) MultipartFile uploadfile,
			@RequestParam(value = "name", defaultValue = "portfolioHoldingDef", required = true) String fileSpec,
			@RequestHeader(value = "Authorization", required = false) final String authToken)
			throws IOException, InterruptedException {
		fileStoreAndFowardService.storeAndFwd(uploadfile, 1L, true);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

	}

	@RequestMapping(value = FileUploadURI.POST_MULTI_FILEUPLOAD_MAPPING, method = POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(ACCEPTED)
	@ResponseBody
	public ResponseEntity<Void> multiuploadFileStoreAndFwd(
			@RequestParam(value = "inputFiles", required = true) MultipartFile[] inputFiles,
			@RequestParam(value = "batchId", required = false) Long batchId,
			@RequestHeader(value = "Content-Type", required = false) final String contentType,
			@RequestHeader(value = "Authorization", required = false) final String authToken)
			throws IOException, InterruptedException {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
