package cn.zfcr.common.codeautogenerate.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class CodeAutoGenerateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String goadd() {
		return "success";
	}
	
	public String indexJson() throws Exception {
		return "generate";
	}

	public String index(){
		return "success";
	}
	

}
