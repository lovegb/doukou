package com.doukou.service;

import java.security.MessageDigest;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.doukou.Tuling.TulingProcess;
import com.doukou.commom.GlobalConstant;
import com.doukou.util.MessageResponse;
import com.doukou.util.MessageUtil;
import com.doukou.util.StringUtil;

public class BaseService {
	
	private static final String CONTENT = "Content";
	private static final String CREATETIME = "CreateTime";
	private static final String TO_USERNAME = "ToUserName";
	private static final String FROM_USERNAME = "FromUserName";
	private static final String MSGTYPE = "MsgType";
	private static final String MSGID = "MsgId";
	private static final String EVENTTYPE = "Event";
	
	/**
	 * 处理微信发来的消息           .<br>
	 *
	 * @param request
	 * @return  String 
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		Map<String, String>	 resultMap = MessageUtil.parseXml(request);
		//发送方账户（openId）
		String fromUserName = resultMap.get(FROM_USERNAME);
		//公众账号
		String toUserName =	resultMap.get(TO_USERNAME);
		//发送内容
		String content = resultMap.get(CONTENT);
		//消息类型
		String msgType = resultMap.get(MSGTYPE);
		System.err.println("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType+"content is :"+content);
		//判断消息类型
		//文本
		if (MessageUtil.RESP_MESSAGE_TYPE_TEXT.equals(msgType)) {
			String respContent = TulingProcess.getTulingResult(content);
			if(StringUtil.isEmpty(respContent)){
				respMessage = MessageResponse.getTextMessage(fromUserName , toUserName , GlobalConstant.ERROR_OUTAGE);
			} else {
				respMessage = MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
				System.err.println(respMessage);
			}
		} else if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {//事件推送
			String eventType = resultMap.get(EVENTTYPE);
			//订阅
			if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {
				respMessage = MessageResponse.getTextMessage(fromUserName , toUserName , GlobalConstant.SUBSCRIBE_CONTENT);
			} else if (MessageUtil.EVENT_TYPE_CLICK.equals(eventType)) { //自定义菜单点击事件
				respMessage = MessageResponse.getTextMessage(fromUserName , toUserName , "success"); 
			}
		} else if (MessageUtil.RESP_MESSAGE_TYPE_VOICE.equals(msgType)) {//语音消息
			
		} else {
				respMessage = MessageResponse.getTextMessage(fromUserName , toUserName , GlobalConstant.ERROR_OUTAGE); 
		}
		return respMessage;
	}

}
