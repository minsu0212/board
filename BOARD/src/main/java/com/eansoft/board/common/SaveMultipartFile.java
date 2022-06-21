package com.eansoft.board.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class SaveMultipartFile {

	// 첨부파일 저장
	public static List<Map<String, String>> saveFile(List<MultipartFile> multipartfile
			, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("resources");
		String root = path + "\\" + "uploadFiles";
		File fileCheck = new File(root);
		if(!fileCheck.exists()) fileCheck.mkdirs();
		List<Map<String, String>> fileList = new ArrayList();
		
		for(int i = 0; i < multipartfile.size(); i++) {
			String originFileName = multipartfile.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String fileRename = UUID.randomUUID().toString() + ext;
			String filePath = fileCheck + "\\" + fileRename;
			
			Map<String, String> map = new HashMap();
			map.put("fileName", originFileName);
			map.put("fileExtension", ext);
			map.put("fileRename", fileRename);
			map.put("filePath", filePath);
			fileList.add(map);
		}
		try {
			for(int i = 0; i < multipartfile.size(); i++) {
				File file = new File(root + "\\" + fileList.get(i).get("fileRename"));
				multipartfile.get(i).transferTo(file);
			}
		}catch(Exception e) {
			for(int i = 0; i < multipartfile.size(); i++) {
				new File(root + "\\" + fileList.get(i).get("fileRename")).delete();
			} e.printStackTrace();
		}
		return fileList;
	}
	
	// 첨부파일 삭제
	public static void deleteFile(String filePath, HttpServletRequest request) {
		File deleteFile = new File(filePath);
		if(deleteFile.exists()) {
			deleteFile.delete();
		}
	}
	
}
