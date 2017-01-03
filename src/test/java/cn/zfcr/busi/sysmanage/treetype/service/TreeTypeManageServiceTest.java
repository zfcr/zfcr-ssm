package cn.zfcr.busi.sysmanage.treetype.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.system.constants.SystemConstants;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class TreeTypeManageServiceTest {
	
	@Resource
	private ITreeTypeManageService treeTypeManageService;

	@Test
	public void testFind(){
		treeTypeManageService.find(SystemConstants.ROOT_ID, SystemConstants.TREETYPE_TYPECODE);
	}
	
	@Test
	public void testSaveOrUpdate(){
		DictionaryTree dictionaryTree = new DictionaryTree();
		dictionaryTree.setCode("0001");
		dictionaryTree.setName("博客分类");
		dictionaryTree.setParentId(SystemConstants.ROOT_ID);
		dictionaryTree.setTreeId("1");
		dictionaryTree.setTypeCode(SystemConstants.TREETYPE_TYPECODE);
		treeTypeManageService.saveOrUpdate(dictionaryTree);
	}
	
	@Test
	public void testQueryMaxCode(){
		String value = treeTypeManageService.queryMaxCode(SystemConstants.TREETYPE_TYPECODE);
		System.out.println(value);
	}
}
