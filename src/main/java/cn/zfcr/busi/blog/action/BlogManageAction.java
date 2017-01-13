package cn.zfcr.busi.blog.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.busi.sysmanage.treetype.service.ITreeTypeManageService;
import cn.zfcr.common.base.action.BaseAction;
import cn.zfcr.system.constants.SystemConstants;
import cn.zfcr.system.utils.CommonUtils;
import sun.misc.BASE64Decoder;

/**
 * 创建博客
 * @author zf
 * @date 2016年12月3日
 *
 */
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

	public String create(){
		return "create";
	}
	
	public String createAtCkeditor() {
		DictionaryTree where = new DictionaryTree();
		where.setLevelNumber(2);
		where.setTypeCode(SystemConstants.TREETYPE_TYPECODE);
		List<DictionaryTree> dictionaryTrees = treeTypeManageService.find(where);
		List<Map<String, Object>> list = new ArrayList<>();
		for (DictionaryTree dictionaryTree : dictionaryTrees) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", dictionaryTree.getName());
			Map<String, String> childrensMap = new HashMap<>();
			if("1".equals(dictionaryTree.getIsLeaf())){
				childrensMap.put(dictionaryTree.getCode(), dictionaryTree.getName());
			}else{
				List<DictionaryTree> childrens = treeTypeManageService.queryByTreeId(dictionaryTree.getTreeId()+".", SystemConstants.TREETYPE_TYPECODE);
				for (DictionaryTree dictionaryTree2 : childrens) {
					childrensMap.put(dictionaryTree2.getCode(), dictionaryTree2.getName());
				}
			}
			map.put("childrens", childrensMap);
			list.add(map);
		}
		getRequest().setAttribute("titleTypes", list);
		return "createAtCkeditor";
	}
	
	public String show(){
		String uri = getRequest().getRequestURI();
		String blogCode = uri.substring(uri.lastIndexOf("/")+1, uri.length());
		System.out.println(blogCode);
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
		        String path = getRequest().getSession().getServletContext().getRealPath("/") + File.separator + SystemConstants.BLOG_IMAGE_DIC + File.separator + CommonUtils.generateUUID() + suffix;
	            String fileName = copyFileToDir(filePic, path);
	            entity.setImagePath(getRequest().getContextPath() + "/" + SystemConstants.BLOG_IMAGE_DIC_SYS + "/" + fileName);
			}
			
			handleBase64Image();
			
			if(StringUtils.isEmpty(entity.getImagePath())){
			    entity.setImagePath(getRequest().getContextPath() + "/" +  SystemConstants.BLOG_IMAGE_DIC_DEFAULT + "/index-blog.jpg");
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
                    String path = getRequest().getSession().getServletContext().getRealPath("/") + File.separator
                            + SystemConstants.BLOG_IMAGE_DIC + File.separator + CommonUtils.generateUUID()
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
                    
                    String sysPath = getRequest().getContextPath() + "/" + SystemConstants.BLOG_IMAGE_DIC_SYS + "/" + file.getName();
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
	            String path = getRequest().getSession().getServletContext().getRealPath("/") + File.separator + SystemConstants.BLOG_IMAGE_DIC + File.separator + CommonUtils.generateUUID() + suffix;
	            String fileName = copyFileToDir(upload, path);
	            String imagePath = getRequest().getContextPath() + "/" + SystemConstants.BLOG_IMAGE_DIC_SYS + "/" + fileName;
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
