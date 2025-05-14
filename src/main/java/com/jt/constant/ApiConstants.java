package com.jt.constant;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface ApiConstants {
	Timestamp FROM_TODAY_DATE = Timestamp.valueOf(LocalDateTime.now());
	Timestamp LAST_MONTH_TRANSACTION_FROM_TODAY_TO_30_DAYS_BACK = Timestamp.valueOf(LocalDateTime.now().minusDays(30));
	Timestamp LAST_SECOND_MONTH_TRANSACTION_FROM_TODAY_TO_60_DAYS_BACK = Timestamp.valueOf(LocalDateTime.now().minusDays(60));
	Timestamp LAST_THIRD_MONTH_TRANSACTION_FROM_TODAY_TO_90_DAYS_BACK = Timestamp.valueOf(LocalDateTime.now().minusDays(90));
	
}
