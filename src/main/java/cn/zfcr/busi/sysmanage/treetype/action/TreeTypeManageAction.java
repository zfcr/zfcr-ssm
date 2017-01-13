package cn.zfcr.busi.sysmanage.treetype.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
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
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
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
	
	public String addOrEdit() throws Exception{
		String id = getRequest().getParameter("id");
		if(StringUtils.isEmpty(id)){
			String parentId = getRequest().getParameter("parentId");
			if(StringUtils.isEmpty(parentId)){
				parentId = SystemConstants.ROOT_ID;
			}
			entity = new DictionaryTree();
			entity.setParentId(parentId);
			entity.setCode(treeTypeManageService.queryMaxCode(SystemConstants.TREETYPE_TYPECODE));
		}else{
			entity = treeTypeManageService.getById(id);
			if(entity == null){
				throw new NullPointerException("数据已经不存在，请重新刷新！");
			}
		}
		return "addOrEdit";
	}
	
	public void save() throws Exception{
		try {
			Assert.isTrue(StringUtils.isNotEmpty(entity.getCode()), "分类编号不能为空！");
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("success", true);
			jsonObject.addProperty("msg", "保存成功！");
			
			// 如果是新增，需要校验生成的编号是否已经存在
			if(StringUtils.isEmpty(entity.getId())){
				entity.setId(null);
				boolean result = treeTypeManageService.validateCode(entity);
				if(result){
					entity.setCode(treeTypeManageService.queryMaxCode(SystemConstants.TREETYPE_TYPECODE));
					jsonObject.addProperty("msg", String.format("保存成功，已为您重新生成分类编号，编号为：%s ！",entity.getCode()));
				}
			}else{
				DictionaryTree dictionaryTree = treeTypeManageService.getById(entity.getId());
				if(entity == null){
					throw new NullPointerException("数据已经不存在，请重新刷新！");
				}
				entity.setTreeId(dictionaryTree.getTreeId());
				entity.setIsLeaf(dictionaryTree.getIsLeaf());
				entity.setTypeCode(dictionaryTree.getTypeCode());
			}
			treeTypeManageService.saveOrUpdate(entity);
			writeStr(jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void delete() throws Exception{
		try {
			String id = getRequest().getParameter("id");
			Assert.isTrue(StringUtils.isNotEmpty(id), "ID不能为空！");
			treeTypeManageService.deleteById(id);
			writeStr(true, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			writeStr(false, "删除失败，原因："+e.getMessage());
		}
	}

	public DictionaryTree getEntity() {
		return entity;
	}

	public void setEntity(DictionaryTree entity) {
		this.entity = entity;
	}
	
}
