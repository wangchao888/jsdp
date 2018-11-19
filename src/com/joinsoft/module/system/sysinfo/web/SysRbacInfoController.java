/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： SysInfoController.java
 *              (C) Copyright shandong joinsoft Corporation 2018
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.module.system.sysinfo.web;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.bean.UserBean;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.application.entity.SysRbacApplication;
import com.joinsoft.module.system.sysinfo.entity.SysInfo;
import com.joinsoft.module.system.sysinfo.service.SysInfoService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
/**
 * 系统信息
 * @author scy
 * @since  2018年3月27日
 */
@Controller
@RequestMapping(value="/system/sysinfo/")
public class SysRbacInfoController {
	private String url ="system/sysinfo/";
	
	@Autowired
	private SysInfoService sysInfoService;
	@Autowired
	private IUserService userService;
	
	
	/**
	 * 系统信息
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月27日下午1:52:16
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request, String menuid){
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月27日下午1:52:38
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(SysRbacApplication entity, HttpServletRequest request) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("delFlag", "0");
        params.put("order", "createTime desc");
        params.put("page", page);
        List<Map<String, Object>> dataList = sysInfoService.getEntityByPage(params);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	/**
	 * 进入修改界面
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月27日下午2:18:49
	 */
	@RequestMapping(value="initUpdate")
	public String initUpdate(Model model,HttpServletRequest request, String sid){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		Map<String, Object> dataMap = sysInfoService.getEntity(params);
		model.addAttribute("dataMap", dataMap);
		return url+"edit";
	}
	/**
	 * 保存修改的数据 (包括替换图片)
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月27日下午2:19:52
	 */
	@RequestMapping(value="update")
	public String update(Model model,HttpServletRequest request, SysInfo entity){
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		try {
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());
			String  ftpLocalPath = request.getSession().getServletContext().getRealPath("static"+File.separator+"img"+File.separator+"log"+File.separator+"logo"+File.separator);
			MultipartRequest multipartRequest = (MultipartRequest) request;
		    MultipartFile file = multipartRequest.getFile("file");
			sysInfoService.updateEntity(entity,file,ftpLocalPath);
			model.addAttribute("message", JsdpConstants.HFMP_RESULT_SUCCESS);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}   
		return url+"edit";
	}
	/**
	 * 下载图片
	 * @param request
	 * @param response
	 *
	 * @author scy
	 * @since 2018年3月27日下午4:17:33
	 */
	@RequestMapping("downloadImg")
	public void downloadReg(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getSession().getServletContext().getRealPath("static"+File.separator+"img"+File.separator+"log"+File.separator+"logo.png"); 
		String filename = "logo.png";
        try {  
        	String iso_filename = URLEncoder.encode(filename, "UTF-8");		
            response.setContentType("multipart/form-data");  
            response.setHeader("Content-Disposition", "attachment;fileName="+iso_filename);  
            File file = new File(path);  
            java.io.OutputStream os = response.getOutputStream();
            java.io.FileInputStream fis = new java.io.FileInputStream(file);
            byte[] buff = new byte[10240];
            int bytesRead;
            while (-1 != (bytesRead = fis.read(buff, 0, buff.length))) {
            	os.write(buff, 0, bytesRead);
            }  
            fis.close();  
            os.close();  
            os.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	
	
	
	
	
	
	
	
	
	
}

