package com.appleframework.monitor.action;

import com.appleframework.monitor.model.Alert;
import com.appleframework.monitor.model.WebResult;
import com.appleframework.monitor.service.HttpAlertNotifier;
import com.appleframework.monitor.util.SerializableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author Hill.Hu
 */
@Controller
public class HttpAlertAction {
	
    @Resource
    private HttpAlertNotifier httpAlertNotifier;
    
    @Resource
    private SerializableResourceBundleMessageSource messageBundle;

    @RequestMapping(value = "/projects/{projectName}/notifier/http/test")
    public @ResponseBody
		WebResult notify(HttpServletRequest request,
        	@RequestBody Properties properties, @PathVariable String projectName) {
        WebResult result=new WebResult();
        try {
			Alert alert = new Alert();
			alert.setTitle("test");
			alert.setContent("this is a alert test");
			alert.setIp(request.getLocalAddr());
			String data = httpAlertNotifier.notify(alert, properties);
            String url = properties.getProperty(HttpAlertNotifier.HTTP_NOTIFY_CONFIG_URL, null);
            result.setMessage("send alert to "+url+",data:"+data);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
       return result;
    }
}
