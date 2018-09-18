package org.baeldung.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.baeldung.persistence.model.FileEntity;
import org.baeldung.service.FileStoreAndFowardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

	@Autowired
	private ServletContext servletContext;

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

	@RequestMapping(value = FileUploadURI.POST_FORWARD_MAPPING, method = POST)
	@ResponseStatus(ACCEPTED)
	@ResponseBody
	public ResponseEntity<FileEntity> forwardFile(
			@RequestParam(value = "file", required = true) MultipartFile uploadfile,
			@RequestParam(value = "targetFolder", defaultValue = "C://TEMP//", required = true) String targetFolder,
			@RequestParam(value = "targetFileName", required = false) String targetFileName,
			@RequestHeader(value = "Authorization", required = false) final String authToken)
			throws IOException, InterruptedException {
		FileEntity fileEntity = fileStoreAndFowardService.forwardFile(uploadfile, targetFolder, targetFileName);
		return new ResponseEntity<FileEntity>(fileEntity, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = FileUploadURI.POST_FILE_DOWNLOAD_MAPPING, method = POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getFile(
			@RequestParam(value = "targetFolder", defaultValue = "C://TEMP//", required = true) String targetFolder,
			@RequestParam(value = "targetFileName", required = false) String fileName) throws IOException {
		byte[] data = fileStoreAndFowardService.getFile(targetFolder, fileName);
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
		System.out.println("fileName: " + fileName);
		System.out.println("mediaType: " + mediaType);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, HttpStatus.ACCEPTED);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				// Content-Type
				.contentType(mediaType) //
				// Content-Lengh
				.contentLength(data.length) //
				.body(resource);
	}

}
