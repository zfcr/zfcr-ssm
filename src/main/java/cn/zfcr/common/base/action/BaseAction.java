package cn.zfcr.common.base.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseAction {
	
	protected Logger log = Logger.getLogger(this.getClass().getName());
	
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	protected void writeStr(String str) throws IOException {
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().getWriter().write(str);
	}
	
	protected void writeTreeJson(List<?> data, Long size) throws IOException {
		Gson gson = new GsonBuilder().create();
		String dataStr = gson.toJson(data);
		String writeValue = "{\"rows\":%s,\"data\":%s}";
		writeStr(String.format(writeValue, size, dataStr));
	}
	
}
