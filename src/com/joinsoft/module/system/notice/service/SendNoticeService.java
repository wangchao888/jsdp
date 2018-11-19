package com.joinsoft.module.system.notice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.notice.dao.SysNoticedetailMapper;

@Service
public class SendNoticeService {

	private final SimpMessagingTemplate messagingTemplate;
	@Autowired
    private SysNoticedetailMapper sysNoticedetailMapper;
	
	@Autowired
	public SendNoticeService(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	/**
	 * 通知推送
	 *   
	 * @param noticeid
	 * @param usertype
	 *
	 * @author LZX
	 * @since 2018年4月8日上午11:19:55
	 */
	public void sendNoticeByTopic(String noticeid){
		if (!StringUtils.isBlank(noticeid)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("noticeid", noticeid);
			List<Map<String, Object>> detailList = sysNoticedetailMapper.getSysNoticedetail(params);
			
			if (CollectionUtils.isNotBank(detailList)) {
				for (Map<String, Object> detailMap : detailList) {
					String userid = (String)detailMap.get("USERID");
					messagingTemplate.convertAndSendToUser(userid, "/topic/notices", "1");
				}
			}
		}
	}
}
