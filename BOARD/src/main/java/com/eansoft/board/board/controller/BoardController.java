package com.eansoft.board.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.eansoft.board.board.domain.Reply;
import com.eansoft.board.board.service.BoardService;
import com.eansoft.board.common.PageInfo;
import com.eansoft.board.common.Pagination;
import com.eansoft.board.common.SaveMultipartFile;
import com.eansoft.board.common.Search;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
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
			, @RequestParam(value="uploadFiles", required=false) List<MultipartFile> uploadFile
			, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String emplId = (String) session.getAttribute("emplId");
			board.setEmplId(emplId);
			int result = bService.boardRegister(board);
			if(uploadFile.size() > 0 && !uploadFile.get(0).getOriginalFilename().equals("")) {
				List<Map<String, String>> fileList = SaveMultipartFile.saveFile(uploadFile, request);
				for(int i = 0; i < uploadFile.size(); i++) {
					String fileName = fileList.get(i).get("fileName");
					String fileExtension = fileList.get(i).get("fileExtension");
					String fileRename = fileList.get(i).get("fileRename");
					String filePath = fileList.get(i).get("filePath");
					
					BoardFile boardFile = new BoardFile();
					boardFile.setFileName(fileName);
					boardFile.setFileExtension(fileExtension);
					boardFile.setFileRename(fileRename);
					boardFile.setFilePath(filePath);
					boardFile.setEmplId(emplId);
					
					result = bService.saveFile(boardFile);
				}
			}
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
			mv.addObject("paging", "paging");
			mv.setViewName("board/boardList");
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// POI 엑셀 다운로드(전체)
	@RequestMapping(value="/board/boardListApiView.eansoft", method=RequestMethod.GET)
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("게시판글들");
		int rowNo = 0;
		
		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("글 번호");
		headerRow.createCell(1).setCellValue("글 종류");
		headerRow.createCell(2).setCellValue("글 제목");
		headerRow.createCell(3).setCellValue("첨부파일(개수)");
		headerRow.createCell(4).setCellValue("작성자");
		headerRow.createCell(5).setCellValue("작성일");
		headerRow.createCell(6).setCellValue("조회수");
		List<Board> bList = bService.printBoard();
		for(Board board : bList) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(board.getBoardNo());
			row.createCell(1).setCellValue(board.getBoardWriteType());
			row.createCell(2).setCellValue(board.getBoardTitle());
			row.createCell(3).setCellValue(board.getBoardFileCount());
			row.createCell(4).setCellValue(board.getEmplId());
			row.createCell(5).setCellValue(board.getBoardWriteDate());
			row.createCell(6).setCellValue(board.getBoardCount());
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=boardList.xls");
		
		workbook.write(response.getOutputStream());
		workbook.close();
	}
	
	// POI 엑셀 다운로드(검색)
	@RequestMapping(value="/board/excelSearch.eansoft", method=RequestMethod.GET)
	public void downloadExcelSearch(HttpServletResponse response
			, @ModelAttribute Search search) throws IOException {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("게시판검색한글들");
		int rowNo = 0;
		
		Row headerRow = sheet.createRow(rowNo++);
		headerRow.createCell(0).setCellValue("글 번호");
		headerRow.createCell(1).setCellValue("글 종류");
		headerRow.createCell(2).setCellValue("글 제목");
		headerRow.createCell(3).setCellValue("첨부파일(개수)");
		headerRow.createCell(4).setCellValue("작성자");
		headerRow.createCell(5).setCellValue("작성일");
		headerRow.createCell(6).setCellValue("조회수");
		List<Board> bList = bService.searchBoardExcel(search);
		for(Board board : bList) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(board.getBoardNo());
			row.createCell(1).setCellValue(board.getBoardWriteType());
			row.createCell(2).setCellValue(board.getBoardTitle());
			row.createCell(3).setCellValue(board.getBoardFileCount());
			row.createCell(4).setCellValue(board.getEmplId());
			row.createCell(5).setCellValue(board.getBoardWriteDate());
			row.createCell(6).setCellValue(board.getBoardCount());
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=boardSearchList.xls");
		
		workbook.write(response.getOutputStream());
		workbook.close();
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
	@RequestMapping(value="/board/modifyView.eansoft", method=RequestMethod.GET)
	public ModelAndView boardModifyView(ModelAndView mv
			, @RequestParam("boardNo") int boardNo) {
		try {
			Board board = bService.printOneByNo(boardNo);
			if(board != null) {
				mv.addObject("board", board);
				mv.setViewName("board/boardModifyView");
			}else {
				mv.addObject("msg", "수정화면 조회 실패");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 수정
	@ResponseBody
	@RequestMapping(value="/board/boardModify.eansoft", method=RequestMethod.POST)
	public String modifyBoard(@RequestParam("boardNo") int boardNo
			, @RequestParam("boardTitle") String boardTitle
			, @RequestParam("boardContents") String boardContents) {
		try {
			Board board = new Board();
			board.setBoardNo(boardNo);
			board.setBoardTitle(boardTitle);
			board.setBoardContents(boardContents);
			int result = bService.modifyBoard(board);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 게시글 검색
	@RequestMapping(value="/board/searchBoard.eansoft", method=RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv
			, @ModelAttribute Search search
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = bService.getSearchCount(search);
			PageInfo pi = Pagination.getPageInfo(currentPage, totalCount);
			List<Board> bList = bService.searchBoard(search, pi);
			if(!bList.isEmpty()) {
				mv.addObject("bList", bList);
				mv.addObject("pi", pi);
				mv.addObject("paging", "searchPaging");
				mv.setViewName("board/boardList");
			}else {
				mv.addObject("msg", "검색조회 실패");
				mv.setViewName("common/errorPage");
			}
		}catch(Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 게시글 댓글 조회
	@ResponseBody
	@RequestMapping(value="/board/replyList.eansoft", method=RequestMethod.GET)
	public void boardReplyView(@ModelAttribute Reply reply
			, @RequestParam("boardNo") int boardNo
			, HttpServletResponse response) throws JsonIOException, IOException {
		reply.setBoardNo(boardNo);
		List<Reply> nReplyList = bService.printAllReply(reply);
		if(!nReplyList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			gson.toJson(nReplyList, response.getWriter());
		}
	}
	
	// 댓글 등록
	@ResponseBody
	@RequestMapping(value="/board/replyAdd.eansoft", method=RequestMethod.POST)
	public String registerReply(@RequestParam("boardNo") int boardNo
			, @RequestParam("replyContents") String replyContents
			, HttpServletRequest request) {
		try {
			Reply reply = new Reply();
			HttpSession session = request.getSession();
			String emplId = (String) session.getAttribute("emplId");
			reply.setBoardNo(boardNo);
			reply.setEmplId(emplId);
			reply.setReplyContents(replyContents);
			int result = bService.registerReply(reply);
			if (result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value="/board/replyDelete.eansoft", method=RequestMethod.GET)
	public String deleteReply(@RequestParam("replyNo") int replyNo) {
		try {
			int result = bService.deleteReply(replyNo);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 댓글 수정
	@ResponseBody
	@RequestMapping(value="/board/replyModify.eansoft", method=RequestMethod.POST)
	public String modifyReply(@RequestParam("replyNo") int replyNo
			, @RequestParam("replyContents") String replyContents) {
		try {
			Reply reply = new Reply();
			reply.setReplyNo(replyNo);
			reply.setReplyContents(replyContents);
			int result = bService.modifyReply(reply);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 답글 작성
	@ResponseBody
	@RequestMapping(value="/board/registerReReply.eansoft", method=RequestMethod.POST)
	public String addReReply(@ModelAttribute Reply reply
			, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String emplId = (String) session.getAttribute("emplId");
			reply.setEmplId(emplId);
			int result = bService.addReReply(reply);
			if(result > 0) {
				return "success";
			}else {
				return "fail";
			}
		}catch(Exception e) {
			return e.toString();
		}
	}
	
	// 통계 화면
	@RequestMapping(value="/board/statisticsView.eansoft", method=RequestMethod.GET)
	public ModelAndView statisticsView(ModelAndView mv) {
		mv.setViewName("board/boardStatisticsView");
		return mv;
	}
	
	// API 접속 기록
	@RequestMapping(value="/board/apiView.eansoft", method=RequestMethod.GET)
	public ModelAndView apiView(ModelAndView mv) {
		mv.setViewName("board/boardApiView");
		return mv;
	}
	
}
