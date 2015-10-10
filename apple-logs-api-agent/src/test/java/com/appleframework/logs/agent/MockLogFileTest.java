package com.appleframework.logs.agent;

import junit.framework.TestCase;
import com.appleframework.logs.mock.MockLogFile;

public class MockLogFileTest extends TestCase {
	
	MockLogFile mockLogFile = new MockLogFile();

	public void test_file() throws Exception {
		mockLogFile.init();
	}
}