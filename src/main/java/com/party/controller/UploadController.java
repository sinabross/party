package com.party.controller;

import com.party.dto.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping(value="/upload")
public class UploadController {
	
	@Value("D:\\uploadFile\\")
	private String filePath;
	
	/**
	 * 파일 업로드
	 * @param uploadFile
	 * @param noticeType
	 * @return
	 */
	@RequestMapping(value="/uploadFile")
	public @ResponseBody
    List<Attach> uploadFile(MultipartFile[] uploadFile, String noticeType) throws Exception {
		List<Attach> list = new ArrayList<Attach>();
		
		String uploadFolder = filePath;
		String uploadFolderPath = noticeType;
		
		File uploadPath = new File(uploadFolder , uploadFolderPath);
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for (MultipartFile multipartFile : uploadFile) {
			
			Attach attach = new Attach();
			
			String uploadFileName = multipartFile.getOriginalFilename();			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);  // IE버전 지원	
			
			attach.setReal_file_name(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			
			uploadFolderPath = noticeType;
			uploadFolderPath = uploadFolderPath + File.separator + uuid.toString();
			attach.setUpload_file_path(uploadFolderPath);
			
			if (checkImageType(saveFile)) {
				attach.setImage(true);
				Thumbnails.of(new File(uploadPath, uploadFileName)).size(100, 100).toFile(new File(uploadPath, "s_" + uploadFileName));
				Thumbnails.of(new File(uploadPath, uploadFileName)).size(350, 350).toFile(new File(uploadPath, "thumb_" + uploadFileName));
			}
			
			attach.setNotice_type(noticeType);
			list.add(attach);
			
		}
		
		return list;
	}

	/**
	 * 이미지 파일 체크
	 * @param file
	 * @return
	 */
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * x버튼 클릭시 파일 삭제
	 * @param fileName
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) throws Exception {
		File file;

		file = new File(filePath + URLDecoder.decode(fileName, "UTF-8"));
		file.delete();
		
		if(type.equals("image")) {
			String thumbFileName = file.getAbsolutePath().replace("s_", "thumb_");
			file = new File(thumbFileName);
			file.delete();
			
			String largeFileName = file.getAbsolutePath().replace("thumb_", "");
			file = new File(largeFileName);
			file.delete();
		}

		return new ResponseEntity<String>("삭제되었습니다.", HttpStatus.OK);
	}
	
	/**
	 * 이미지 파일 보여주기
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFileImage(String fileName) throws Exception {
		
		File file = new File(filePath + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			log.info("이미지 불러오기 실패");
			
			//이미지를 불러오지 못할 경우 기본 이미지 출력
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(new File(filePath + "no_image.png")), header, HttpStatus.OK);
			
		}
		
		return result;
	}
	
	/**
	 * 파일 다운로드
	 * @param userAgent
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) throws Exception {

		Resource resource = new FileSystemResource(filePath + fileName);
		
		//if(resource.exists() == false) {
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
		
		String resourceName = resource.getFilename();
		
		//remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders headers = new HttpHeaders();

		String downloadName = null;
		
		if(userAgent.contains("Trident")) {
			log.info("==================================================IE browser");
			downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
			
		}else if(userAgent.contains("Edge")) {
			log.info("==================================================Edge browser");
			downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
			
		}else if(userAgent.contains("Firefox")) {
			log.info("==================================================Firefox browser");
			downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll(" ", "");
			
		}else {
			log.info("==================================================Other browser");
			downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
		}

		log.info("==================================================downloadName: " + downloadName);
		headers.add("Content-Disposition", "attachment; filename=" + downloadName);
	
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

}

