package kr.ac.kopo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	public String getNextYear(int n) {
		// 현재 시간 가져오기
        LocalDateTime currentTime = LocalDateTime.now();

        // 1년 뒤 계산
        LocalDateTime oneYearLater = currentTime.plusYears(n);

        // 출력 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 형식에 맞춰 출력
        String nextYear = oneYearLater.format(formatter);
        
		return nextYear;
	}
}
