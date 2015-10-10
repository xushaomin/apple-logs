package com.appleframework.monitor.mongodb;


import org.junit.Test;

import com.mongodb.MongoURI;


/**
 * @author Steven.Zheng
 * @date 2012-12-28
 */
@SuppressWarnings("deprecation")
public class MongoURITest {
	
	@Test
	public void testMongoURI(){
		MongoURI uri = new MongoURI("mongodb://172.16.3.82:27017,172.16.3.37:27017/monitor_test?slaveOk=true");
		uri.getOptions();
		//Assert.assertTrue(uri.getOptions().slaveOk);
	}
}
