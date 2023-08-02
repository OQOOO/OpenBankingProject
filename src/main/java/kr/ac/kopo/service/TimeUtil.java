package kr.ac.kopo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

	public String getNextYear(int n) {
		// ���� �ð� ��������
        LocalDateTime currentTime = LocalDateTime.now();

        // 1�� �� ���
        LocalDateTime oneYearLater = currentTime.plusYears(n);

        // ��� ���� ����
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // ���Ŀ� ���� ���
        String nextYear = oneYearLater.format(formatter);
        
		return nextYear;
	}
}
