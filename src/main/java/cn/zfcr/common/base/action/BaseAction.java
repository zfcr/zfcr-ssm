package cn.zfcr.common.base.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class BaseAction {

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
}
