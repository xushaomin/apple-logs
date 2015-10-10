package com.appleframework.logs.agent;

import org.apache.commons.io.input.Tailer;
import org.springframework.stereotype.Component;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author hill.hu
 */
@Component
public class DogMain {
	
	@Resource
	private LogFileTailerListener listener;
	
	public void setListener(LogFileTailerListener listener) {
		this.listener = listener;
	}

	@PostConstruct
	public void init() {
		File file = new File(listener.getFileName());
		//Tailer tailer = Tailer.create(file, listener, 1000, true, true);
		Tailer.create(file, listener, 1000, true, true);
	}
}
