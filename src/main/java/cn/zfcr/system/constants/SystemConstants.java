package cn.zfcr.system.constants;

import java.io.File;

/**
 * 静态常量(系统中必须的常量)
 * @author zhangfeng
 * @date 2016年12月26日
 */
public class SystemConstants {

	/**
	 * 所有树形结构的根节点
	 */
	public static final String ROOT_ID = "00000000000000000000000000000000";
	
	/**
	 * 博客分类管理类型编号
	 */
	public static final String TREETYPE_TYPECODE = "0001";
	
    /**
     * 博客中图片的保存目录
     */
    public static final String BLOG_IMAGE_DIC = "blog" + File.separator + "images";
    
    /**
     * 博客中图片的程序访问路径
     */
    public static final String BLOG_IMAGE_DIC_SYS = "blog/images";
    
    /**
     * 博客中图片的默认路径
     */
    public static final String BLOG_IMAGE_DIC_DEFAULT = "blog/images/default";
    
}
