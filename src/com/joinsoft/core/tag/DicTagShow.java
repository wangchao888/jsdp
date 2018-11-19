package com.joinsoft.core.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.joinsoft.core.spring.SpringContextUtil;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dictcontent.service.SysDictContentService;

/**
 * 字典内容显示
 * @author LZX 2017-06-07
 */
public class DicTagShow extends TagSupport {

	private static final long serialVersionUID = -5446693229755985488L;

	public String dictno; //字典类别
	public String dictlabel; //已选中
	private SysDictContentService sysDictContentService;
	
	public int doStartTag() throws JspException {
		try {
			String dicvalue = "";
			if(dictno != null && dictlabel != null && !"".equals(dictno) && !"".equals(dictlabel)){
				sysDictContentService = (SysDictContentService)SpringContextUtil.getBean("sysDictContentService");
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("dictno", dictno);
				params.put("delflag",  JsdpConstants.HFMP_Delflag_N);
				params.put("state", "1");
				List<Map<String, Object>> list = sysDictContentService.getEntityByList(params);
				if(list != null && list.size() > 0){
					for(int t=0; t<list.size(); t++){
						Map<String, Object> entity = list.get(t);
						if(dictlabel.equals(entity.get("DICTLABEL"))){
							dicvalue = (String)entity.get("DICTVALUE");
							break;
						}
					}
					
				}
			}
			pageContext.getOut().write(dicvalue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getDictno() {
		return dictno;
	}

	public void setDictno(String dictno) {
		this.dictno = dictno;
	}

	public String getDictlabel() {
		return dictlabel;
	}

	public void setDictlabel(String dictlabel) {
		this.dictlabel = dictlabel;
	}
	
}
