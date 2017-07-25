package cn.zfcr.busi.blog.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import cn.zfcr.busi.blog.service.BlogManageService;
import cn.zfcr.busi.entity.BlogComment;
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.busi.sysmanage.treetype.service.ITreeTypeManageService;
import cn.zfcr.common.base.action.BaseAction;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;
import cn.zfcr.system.constants.SystemConstants;
import cn.zfcr.system.utils.CommonUtils;
import sun.misc.BASE64Decoder;

/**
 * 创建博客
 * @author zf
 * @date 2016年12月3日
 *
 */
@SuppressWarnings("restriction")
@Controller
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class BlogManageAction extends BaseAction{
	
	@Resource
	private ITreeTypeManageService treeTypeManageService;
	
	@Resource
	private BlogManageService blogManageService;
	
	private File filePic;
	
	private String filePicFileName;
	
	private String filePicContentType;
	
	private File upload;
	
	private String uploadFileName;
	
	private String uploadContentType;
	
	private BlogInfo entity = new BlogInfo();

	public String main() throws UnsupportedEncodingException{
	    String uri = getRequest().getRequestURI();
        String pageIndex = uri.substring(uri.lastIndexOf("/")+1, uri.length());
        int i = 1;
        if(NumberUtils.isNumber(pageIndex)){
            i = NumberUtils.toInt(pageIndex, 1);
        }
        entity.setBlogStatus("1");
	    PageList<BlogInfo> list = blogManageService.queryPaing(entity, new PageInfo(i, 10, "visitTimes desc"));
	    if(i != 1 && (list == null || list.size() <= 0)){
	        i = 1;
	        list = blogManageService.queryPaing(entity, new PageInfo(i, 10, "visitTimes desc"));
	    }
	    getRequest().setAttribute("blogInfos", list);
	    
	    setNewBlogInfo();
	    
	    // 推荐文章
	    BlogInfo blogInfo = new BlogInfo();
	    blogInfo.setBlogStatus("2");
	    PageList<BlogInfo> recBlogInfos = blogManageService.queryPaing(blogInfo, new PageInfo(1, 3, "visitTimes desc"));
	    getRequest().setAttribute("recBlogInfos", recBlogInfos);
	    
	    List<Map<String, Object>> titleTypeNums = blogManageService.countTitleTypeNum();
		getRequest().setAttribute("titleTypeNums", titleTypeNums);
        return "main";   
    }

	/**
	 * 设置最新文章到请求中
	 */
    private void setNewBlogInfo() {
      
        // 最新文章(10条)
	    List<BlogInfo> newBlogInfos = blogManageService.queryNewTitle(entity);
	    getRequest().setAttribute("newBlogInfos", newBlogInfos);
    }
	
	public String createAtCkeditor() {
		setTitleTypes();
		return "createAtCkeditor";
	}

	/**
	 * 设置文章编辑页面待选分类
	 */
    private void setTitleTypes() {
        DictionaryTree where = new DictionaryTree();
		where.setLevelNumber(2);
		where.setTypeCode(SystemConstants.TREETYPE_TYPECODE);
		List<DictionaryTree> dictionaryTrees = treeTypeManageService.find(where);
		getRequest().setAttribute("titleTypes", dictionaryTrees);
    }
	
	public String updateAtCkeditor() throws Exception {
	    String uri = getRequest().getRequestURI();
        String blogId = uri.substring(uri.indexOf("/blog/update/") + 13, uri.length());
        Assert.hasLength(blogId, "编辑文章，文章编号不能为空！");
        
        String str = "";
        if(NumberUtils.isNumber(blogId)){
            str = NumberUtils.toInt(blogId, 0) + "";
        }
        entity = blogManageService.findById(str+"");
        Assert.isTrue(entity != null, "文章不存在！");
        
        setTitleTypes();
	    return "createAtCkeditor";
	}
	
	public String show() throws Exception{
		String uri = getRequest().getRequestURI();
		String blogId = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		entity = blogManageService.findById(blogId);
		Assert.isTrue(entity != null, "访问的博客不存在，请检查！");
		
		List<BlogComment> blogComments = blogManageService.queryBlogComments(blogId);
		getRequest().setAttribute("blogComments", blogComments);
		
		setNewBlogInfo();
		
		List<Map<String, Object>> titleTypeNums = blogManageService.countTitleTypeNum();
		getRequest().setAttribute("titleTypeNums", titleTypeNums);
		return "show";
	}
	
	public String saveOrUpdate() throws Exception{
		try {
			Assert.isTrue(StringUtils.isNotEmpty(entity.getTitle()), "文章标题不能为空！");
			
			if(filePic != null){
			    String suffix = ".jpg";
		        if(StringUtils.isNotEmpty(filePicContentType)){
		            suffix = "." + (filePicContentType.lastIndexOf("/") != -1 ? filePicContentType.substring(filePicContentType.lastIndexOf("/") + 1, filePicContentType.length()) : filePicContentType);
		        }
		        String path = SystemConstants.BLOG_ARTICLE_IMAGE_DIC + File.separator + CommonUtils.generateUUID() + suffix;
	            String fileName = copyFileToDir(filePic, path);
	            entity.setImagePath(SystemConstants.BLOG_ARTICLE_IMAGE_DIC_VISIT + "/" + fileName);
			}
			
			handleBase64Image();
			
			if(StringUtils.isEmpty(entity.getImagePath())){
				Random random = new Random();
				int index = random.nextInt(SystemConstants.BLOG_DEFAULT_PIC_NAME.length);
			    entity.setImagePath(SystemConstants.BLOG_ARTICLE_IMAGE_DIC_VISIT + "/" + SystemConstants.BLOG_DEFAULT_PIC_NAME[index]);
			}
			blogManageService.saveOrUpdate(entity);
			writeStr(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
		return "success";
	}
	
	public String search() throws Exception {
		String keywords = getRequest().getParameter("keywords");
		Assert.hasLength(keywords, "搜索关键词不能为空！");
		keywords = new String(URLDecoder.decode(keywords, "utf-8"));
		String index = getRequest().getParameter("index");
		int i = NumberUtils.toInt(index, 1);
		PageList<BlogInfo> list = blogManageService.queryFullText(keywords, new PageInfo(i, 10, "visitTimes desc"));
		getRequest().setAttribute("blogInfos", list);
	    
	    setNewBlogInfo();
	    
	    List<Map<String, Object>> titleTypeNums = blogManageService.countTitleTypeNum();
		getRequest().setAttribute("titleTypeNums", titleTypeNums);
		return "search";
	}
	
	private void init() {
        setNewBlogInfo();
	    
	    List<Map<String, Object>> titleTypeNums = blogManageService.countTitleTypeNum();
		getRequest().setAttribute("titleTypeNums", titleTypeNums);
    }
	
	public String category() throws Exception {
	    String uri = getRequest().getRequestURI();
        String code = uri.substring(uri.lastIndexOf("/")+1, uri.length());
        DictionaryTree dictionaryTree = treeTypeManageService.getByCode(code);
        Assert.isTrue(dictionaryTree != null, "文章分类不存在！");
        PageList<BlogInfo> blogInfos = blogManageService.listByBlogType(dictionaryTree.getTreeId(), new PageInfo(1, 10, "visitTimes desc"));
        getRequest().setAttribute("blogInfos", blogInfos);
        
        init();
	    return "category";
	}

	/**
	 * 处理前台通过粘贴的图片，将图片保存到服务器
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void handleBase64Image() throws IOException, FileNotFoundException {
        if(StringUtils.isNotEmpty(entity.getContent())){
           Document document = Jsoup.parse(entity.getContent());
           Elements elements = document.select("img");
           for (Element element : elements) {
                String src = element.attr("src");
                if (StringUtils.isNotEmpty(src) && src.startsWith("data:image")) {
                    String suffix = src.substring(0, src.indexOf(";"));
                    suffix = "." + (suffix.lastIndexOf("/") != -1
                            ? suffix.substring(suffix.lastIndexOf("/") + 1, suffix.length()) : suffix);
                    src = src.substring(src.indexOf(",") + 1);
                    byte[] imageData = new BASE64Decoder().decodeBuffer(src);
                    String path = SystemConstants.BLOG_ARTICLE_IMAGE_DIC + File.separator + CommonUtils.generateUUID()
                            + suffix;
                    File file = new File(path);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(imageData);
                    fos.flush();
                    fos.close();
                    
                    String sysPath = SystemConstants.BLOG_ARTICLE_IMAGE_DIC_VISIT + "/" + file.getName();
                    element.attr("src", sysPath);
                    
                    // 如果首页显示的图片没有上传，取第一张
                    if(filePic == null && StringUtils.isEmpty(entity.getImagePath())){
                        entity.setImagePath(sysPath);
                    }
                }
           }
           entity.setContent(document.toString());
        }
    }
	
	public void ckeditorUpload() throws IOException{
	    String uploadResult = "<script type=\"text/javascript\">%s</script>";
	    String funcNum = getRequest().getParameter("CKEditorFuncNum");
	    String msg = "";
	    try {
	        if ("image/pjpeg".equals(uploadContentType) || "image/jpeg".equals(uploadContentType) 
	                || "image/png".equals(uploadContentType) || "image/x-png".equals(uploadContentType) 
	                || "image/gif".equals(uploadContentType) || "image/bmp".equals(uploadContentType)){  
	            
	            if(upload.length() > 1024*1024){  
	                msg = "window.parent.CKEDITOR.tools.callFunction(" + funcNum + ",''," + "'文件大小不得大于1MB');";   
	                writeStr(String.format(uploadResult, msg));
	                return;
	            }  
	            
	            String suffix = ".jpg";
	            if(StringUtils.isNotEmpty(uploadContentType)){
	                suffix = "." + (uploadContentType.lastIndexOf("/") != -1 ? uploadContentType.substring(uploadContentType.lastIndexOf("/") + 1, uploadContentType.length()) : uploadContentType);
	            }
	            String path = SystemConstants.BLOG_ARTICLE_IMAGE_DIC + File.separator + CommonUtils.generateUUID() + suffix;
	            String fileName = copyFileToDir(upload, path);
	            String imagePath = SystemConstants.BLOG_ARTICLE_IMAGE_DIC_VISIT + "/" + fileName;
	            msg = "window.parent.CKEDITOR.tools.callFunction(" + funcNum + ",'" + imagePath + "','')";
	            writeStr(String.format(uploadResult, msg));
	            return;
	        }else{  
	            msg = "window.parent.CKEDITOR.tools.callFunction(" + funcNum + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');";   
	            writeStr(String.format(uploadResult, msg));
	            return;
	        }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            msg = "window.parent.CKEDITOR.tools.callFunction(" + funcNum + ",''," + "'文件上传异常');";   
            writeStr(String.format(uploadResult, msg));
        }
	}
	
	/**
	 * 根据上级分类编号，查询文章的自定义分类
	 * @throws IOException 
	 */
	public void queryCustomType() throws IOException{
		try {
			String code = getParameter("code");
			if(log.isDebugEnabled()){
				log.debug("queryCustomType() request paramter code " + code); 
			}
			if(StringUtils.isEmpty(code)){
				writeStr("");
			}else{
				DictionaryTree dictionaryTree = treeTypeManageService.getByCode(code);
				if(dictionaryTree == null){
					writeStr("");
					return;
				}
				List<DictionaryTree> dictionaryTrees = treeTypeManageService.listByTreeId(dictionaryTree.getTreeId());
				writeJson(dictionaryTrees);
			}
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
			writeStr("");
		}
	}

	/**
	 * 保存文件到指定目录
	 * @param path
	 * @return 返回保存的文件名
	 * @throws IOException 
	 */
    private String copyFileToDir(File sourceFile, String path) throws IOException {
        File file = new File(path);
	    if(!file.getParentFile().exists()){
	        file.getParentFile().mkdirs();
	    }
	    file.createNewFile();
        FileCopyUtils.copy(sourceFile, file);
        return file.getName();
    }
    
	public BlogInfo getEntity() {
		return entity;
	}

	public void setEntity(BlogInfo entity) {
		this.entity = entity;
	}

    public File getFilePic() {
        return filePic;
    }

    public void setFilePic(File filePic) {
        this.filePic = filePic;
    }

    public String getFilePicFileName() {
        return filePicFileName;
    }

    public void setFilePicFileName(String filePicFileName) {
        this.filePicFileName = filePicFileName;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getFilePicContentType() {
        return filePicContentType;
    }

    public void setFilePicContentType(String filePicContentType) {
        this.filePicContentType = filePicContentType;
    }

}
