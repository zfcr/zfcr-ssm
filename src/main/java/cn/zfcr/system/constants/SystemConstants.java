package cn.zfcr.system.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 静态常量(系统中必须的常量)
 * @author zhangfeng
 * @date 2016年12月26日
 */
public class SystemConstants {
    
private static Logger log = Logger.getLogger(SystemConstants.class);
    
    protected static Properties props = new Properties();
    static {
        InputStream is = null;
        try {
            is = SystemConstants.class.getClassLoader().getResourceAsStream("system.properties");
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("初始化文件[system.properties]异常！");
            log.error(e);
            System.exit(1);
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	/**
	 * 所有树形结构的根节点
	 */
	public static final String ROOT_ID = "00000000000000000000000000000000";
	
	/**
	 * 博客分类管理类型编号
	 */
	public static final String TREETYPE_TYPECODE = "0001";
	
    /**
     * 博客-文章中的图片的保存目录
     */
    public static final String BLOG_ARTICLE_IMAGE_DIC = props.getProperty("BLOG_ARTICLE_IMAGE_DIC");
    
    /**
     * 博客-文章中的图片的访问目录
     */
    public static final String BLOG_ARTICLE_IMAGE_DIC_VISIT = props.getProperty("BLOG_ARTICLE_IMAGE_DIC_VISIT");
    
    /**
     * 博客中默认显示图片
     */
    public static final String BLOG_IMAGE_DEFAULT = props.getProperty("BLOG_IMAGE_DEFAULT");
    
    /**
     * 留言的icon文件名集合
     */
    public static final String[] FEEDBACK_ICON_NAME = {"naruto-01.png","naruto-02.png","naruto-03.png",
    		"naruto-04.png","naruto-05.png","naruto-06.png","naruto-07.png","naruto-08.png","naruto-09.png","naruto-10.png"};
    
    /**
     * 文章的默认展示图片集合
     */
    public static final String[] BLOG_DEFAULT_PIC_NAME = {"pic-1.jpg","pic-2.jpg","pic-3.jpg","pic-4.jpg","pic-5.jpg","pic-6.jpg","pic-7.jpg"};
    
}
