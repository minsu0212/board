package com.eansoft.board.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eansoft.board.board.domain.Board;
import com.eansoft.board.board.domain.BoardFile;
import com.eansoft.board.board.service.BoardService;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService bService;
	
	// 게시글 작성페이지로 이동
	@RequestMapping(value="/board/write.eansoft", method=RequestMethod.GET)
	public ModelAndView boardWrite(ModelAndView mv) {
		mv.setViewName("board/boardWriteForm");
		return mv;
	}
	
	// 게시글 등록
	@RequestMapping(value="/board/register.eansoft", method=RequestMethod.POST)
	public ModelAndView boardRegister(ModelAndView mv
			, @ModelAttribute Board board
			, @ModelAttribute BoardFile boardFile
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String emplId = (String) session.getAttribute("emplId");
			board.setEmplId(emplId);
			boardFile.setEmplId(emplId);
			int result = bService.boardRegister(board);
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				HashMap<String, String> fileMap = saveFile(uploadFile, request);
				String filePath = fileMap.get("filePath");
				String fileRename = fileMap.get("fileName");
				String fileExtension = fileMap.get("fileExtension");
				
				if(filePath != null && !filePath.equals("")) {
					boardFile.setFileName(uploadFile.getOriginalFilename());
					boardFile.setFileExtension(fileExtension);
					boardFile.setFileRename(fileRename);
					boardFile.setFilePath(filePath);
				}
			}
			result = bService.saveFile(boardFile);
			if(result > 0) {
				mv.setViewName("redirect:/board/list.eansoft");
			}else {
				mv.addObject("msg", "게시글 등록에 실패하였습니다.");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 첨부파일 저장
	public HashMap<String, String> saveFile(MultipartFile file, HttpServletRequest request) {
		String filePath = "";
		HashMap<String, String> fileMap = new HashMap<String, String>();
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles";
		File folder = new File(savePath);
		if(!folder.exists()) folder.mkdir();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = file.getOriginalFilename();
		String extensionName = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String renameFileName = sdf.format(new Date(System.currentTimeMillis()))+"."+extensionName;
		filePath = folder + "\\" + renameFileName;
		fileMap.put("fileExtension", extensionName);
		fileMap.put("filePath", filePath);
		fileMap.put("fileName", renameFileName);
		try {
			file.transferTo(new File(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fileMap;
	}
	
	// 게시글 조회
	@RequestMapping(value="/board/list.eansoft", method=RequestMethod.GET)
	public ModelAndView selectBoardList(ModelAndView mv
			, @ModelAttribute Board board
			, @RequestParam(value="page", required=false) Integer page
			, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String emplId = (String) session.getAttribute("emplId");
			board.setEmplId(emplId);
			int currentPage = (page != null) ? page : 1;
			int totalCount = bService.getBoardCount(board);
			PageInfo pi = Pagination.getPageInfo(currentPage, totalCount);
			List<Board> bList = bService.printBoard(board, pi);
			mv.addObject("bList", bList);
			mv.addObject("pi", pi);
			mv.setViewName("board/boardList");
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 상세보기
	@RequestMapping(value="/board/detail.eansoft", method=RequestMethod.GET)
	public ModelAndView boardDetailView(ModelAndView mv
			, @RequestParam("boardNo") int boardNo) {
		try {
			bService.updateCount(boardNo);
			Board board = bService.printOneByNo(boardNo);
			if(board != null) {
				mv.addObject("board", board);
				mv.setViewName("board/boardDetailView");
			}else {
				mv.addObject("msg", "게시글 상세조회 실패");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 삭제
	@ResponseBody
	@RequestMapping(value="/board/deleteBoard.eansoft", method=RequestMethod.GET)
	public String deleteBoard(@RequestParam("boardNo") int boardNo) {
		try {
			int result = bService.deleteBoard(boardNo);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 게시글 수정 화면
	@RequestMapping(value="/board/modify.eansoft", method=RequestMethod.GET)
	public ModelAndView boardModify(ModelAndView mv) {
		mv.setViewName("board/boardModifyView");
		return mv;
	}
	
}
