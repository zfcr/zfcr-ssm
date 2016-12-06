package cn.zfcr.demo.action;

import org.springframework.stereotype.Controller;

import cn.zfcr.common.base.action.BaseAction;

@Controller
public class DemoAction extends BaseAction{
	
	public String index(){
		return "index";
	}

}
