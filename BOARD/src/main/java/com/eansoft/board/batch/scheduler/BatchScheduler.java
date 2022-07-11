package com.eansoft.board.batch.scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eansoft.board.batch.domain.BoardApi;
import com.eansoft.board.board.controller.BoardController;
@Component
public class BatchScheduler {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Scheduled(cron="0/5 * * * * *")
	public void jobMethod() throws Exception {
//		Calendar calendar = Calendar.getInstance();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		logger.info("스케줄 실행 : " + dateFormat.format(calendar.getTime()));
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		
		String url = "jdbc:oracle:thin:@192.168.0.18:1521:xe";
		String user = "EAN";
		String password = "EAN";
		String query = "SELECT * FROM BOARD_TBL";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			List<BoardApi> bList = new ArrayList<BoardApi>();
			while(result.next()) {
				BoardApi boardApi = new BoardApi();
				boardApi.setBoardNo(result.getInt("BOARD_NO"));
				boardApi.setBoardType(result.getString("BOARD_TYPE"));
				boardApi.setBoardTitle(result.getString("BOARD_TITLE"));
				boardApi.setBoardContents(result.getString("BOARD_CONTENTS"));
				boardApi.setMemberId(result.getString("MEMBER_ID"));
				boardApi.setWriteDate(result.getString("WRITE_DATE"));
				boardApi.setBoardCount(result.getInt("BOARD_COUNT"));
				bList.add(boardApi);
				System.out.println("리스트 : " + bList);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(result != null)
				try {
					result.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			if(stmt != null)
				try {
					stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
	
}
