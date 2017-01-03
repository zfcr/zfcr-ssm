package cn.zfcr.busi.sysmanage.treetype.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;

import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.busi.sysmanage.treetype.service.ITreeTypeManageService;
import cn.zfcr.common.base.action.BaseAction;
import cn.zfcr.system.constants.SystemConstants;

/**
 * 树形分类管理-博客分类管理
 * @author zhangfeng
 * @date 2016年12月9日
 *
 */
@Controller
public class TreeTypeManageAction extends BaseAction{
	
	@Resource
	private ITreeTypeManageService treeTypeManageService;
	
	private DictionaryTree entity = new DictionaryTree();

	public String index() throws Exception{
		Map<String, String> dictionaryTypes = new HashMap<String, String>();
		dictionaryTypes.put("0001", "博客分类");
		getRequest().setAttribute("dictionaryTypes", dictionaryTypes);
		return "index";
	}
	
	public void indexTreeJson() throws Exception{
		try {
			String id = getRequest().getParameter("id");
			if(StringUtils.isEmpty(id)){
				id = SystemConstants.ROOT_ID;
			}
			List<DictionaryTree> dictionaryTrees = treeTypeManageService.find(id, SystemConstants.TREETYPE_TYPECODE);
			writeTreeJson(dictionaryTrees, 0l);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String add() throws Exception{
		String parentId = getRequest().getParameter("parentId");
		if(StringUtils.isEmpty(parentId)){
			parentId = SystemConstants.ROOT_ID;
		}
		entity.setParentId(parentId);
		entity.setCode(treeTypeManageService.queryMaxCode(SystemConstants.TREETYPE_TYPECODE));
		return "add";
	}
	
	public void save() throws Exception{
		try {
			Assert.isTrue(StringUtils.isNotEmpty(entity.getCode()), "分类编号不能为空！");
			boolean result = treeTypeManageService.validateCode(entity);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("success", true);
			jsonObject.addProperty("msg", "保存成功！");
			if(result){
				entity.setCode(treeTypeManageService.queryMaxCode(SystemConstants.TREETYPE_TYPECODE));
				jsonObject.addProperty("msg", String.format("保存成功，已为您重新生成分类编号，编号为：%s ！",entity.getCode()));
			}
			treeTypeManageService.saveOrUpdate(entity);
			writeStr(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public DictionaryTree getEntity() {
		return entity;
	}

	public void setEntity(DictionaryTree entity) {
		this.entity = entity;
	}
	
}
