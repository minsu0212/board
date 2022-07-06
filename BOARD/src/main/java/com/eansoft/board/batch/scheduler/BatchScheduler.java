package com.eansoft.board.batch.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.eansoft.board.HomeController;

public class BatchScheduler {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Scheduled(cron="0/2 * * * * *")
	public void jobMethod() throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("스케줄 실행 : " + dateFormat.format(calendar.getTime()));
	}
}
