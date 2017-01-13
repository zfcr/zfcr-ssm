package cn.zfcr.demo.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.zfcr.common.base.action.BaseAction;

@Controller
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class DemoAction extends BaseAction{
	
	public String index(){
		return "index";
	}
	
	public String indexTreeGrid(){
		return "indexTreeGrid";
	}
	
	public String indexTreeGridDn(){
		return "indexTreeGridDn";
	}
	
	public void indexTreeGridJson() throws Exception{
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().write("{\"data\":[{\"id\":\"1\",\"name\":\"zf\",\"age\":27},{\"id\":\"10\",\"name\":\"zf-10\",\"age\":37,\"parentId\":\"1\",\"isLeaf\":true},{\"id\":\"11\",\"name\":\"zf-11\",\"age\":47,\"parentId\":\"1\",\"isLeaf\":true},{\"id\":\"3\",\"name\":\"cr\",\"age\":26},{\"id\":\"4\",\"name\":\"cr-23\",\"age\":3,\"parentId\":\"3\",\"isLeaf\":true},{\"id\":\"5\",\"name\":\"zhc\",\"age\":1,\"isLeaf\":true}]}");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void indexTreeGridDnJson() throws Exception{
		try {
			String id = getRequest().getParameter("id");
			if(StringUtils.isNotEmpty(id)){
				if("1".equals(id)){
					writeStr("{\"data\":[{\"id\":\"10\",\"name\":\"zf-10\",\"age\":37,\"parentId\":\"1\",\"isLeaf\":true},{\"id\":\"11\",\"name\":\"zf-11\",\"age\":47,\"parentId\":\"1\",\"isLeaf\":true}]}");
				}else if("3".equals(id)){
					writeStr("{\"data\":[{\"id\":\"4\",\"name\":\"cr-23\",\"age\":3,\"parentId\":\"3\",\"isLeaf\":true},{\"id\":\"13\",\"name\":\"cr-24\",\"age\":27,\"parentId\":\"3\",\"isLeaf\":true}]}");
				}
				return;
			}
			getResponse().getWriter().write("{\"data\":[{\"id\":\"1\",\"name\":\"zf\",\"age\":27},{\"id\":\"3\",\"name\":\"cr\",\"age\":26},{\"id\":\"5\",\"name\":\"zhc\",\"age\":1,\"isLeaf\":true}]}");
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
