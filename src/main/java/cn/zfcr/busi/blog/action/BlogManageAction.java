package cn.zfcr.busi.blog.action;

import org.springframework.stereotype.Controller;

import cn.zfcr.common.base.action.BaseAction;

/**
 * 创建博客
 * @author zf
 * @date 2016年12月3日
 *
 */
@Controller
public class BlogManageAction extends BaseAction{

	public String create(){
		return "create";
	}
	
	public String createAtCkeditor() {
		return "createAtCkeditor";
	}
	
	public String show(){
		String uri = getRequest().getRequestURI();
		String blogCode = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		System.out.println(blogCode);
		return "show";
	}
}
