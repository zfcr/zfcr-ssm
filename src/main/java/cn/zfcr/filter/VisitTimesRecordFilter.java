package cn.zfcr.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.zfcr.busi.sysmanage.entity.SystemVisitTimes;
import cn.zfcr.busi.sysmanage.units.SysManageConstants;
import cn.zfcr.system.utils.CommonUtils;

/**
 * 访问次数记录的过滤器
 * @author zhangfeng
 * @date 2017年02月15日
 * 
 */
public class VisitTimesRecordFilter implements Filter{
    
    private Logger log = Logger.getLogger(VisitTimesRecordFilter.class);

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        
        String uri = request.getRequestURI();
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpeg")
                || uri.endsWith(".jpg") || uri.endsWith(".eot")) {
            arg2.doFilter(request, response);
            return;
        }
        Set<String> yetVisitUrlSet = (Set<String>) session.getAttribute("yetVisitUrlMap"); // 当前会话中已经访问的url记录下来
        if(yetVisitUrlSet==null){
            yetVisitUrlSet = new HashSet<>();
        }
        if(yetVisitUrlSet.contains(uri)){
            arg2.doFilter(request, response);
            return;
        }
        yetVisitUrlSet.add(uri);
        session.setAttribute("yetVisitUrlMap", yetVisitUrlSet);
        SystemVisitTimes systemVisitTimes = new SystemVisitTimes();
        systemVisitTimes.setVisitIp(CommonUtils.getIpAddr(request));
        systemVisitTimes.setVisitTime(new Date());
        
        if(uri.startsWith(request.getContextPath() + "/blog/show")){
            String blogId = uri.substring(uri.lastIndexOf("/")+1, uri.length());
            systemVisitTimes.setVisitTitleId(blogId);
        }
        systemVisitTimes.setVisitUrl(uri);
        SysManageConstants.putOrRemoveSystemConfigMap(systemVisitTimes, 1);
        log.info("记录url访问次数:"+uri);
        arg2.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}
