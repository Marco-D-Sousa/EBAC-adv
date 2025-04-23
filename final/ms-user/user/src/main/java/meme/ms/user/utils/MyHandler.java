package meme.ms.user.utils;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class MyHandler extends StreamHandler {

	public void publish(LogRecord record) {
		super.publish(record);
	}

}
