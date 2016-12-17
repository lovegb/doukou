package com.doukou.util;

import java.util.Date;

import com.doukou.entity.TextMessage;

/**
 * 回复消息        .<br>
 *
 * @author sunkai
 * @version 2016年12月12日
 */
public class MessageResponse {

	/**
	 * 回复文本消息           .<br>
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param string
	 * @return  String 
	 */
	public static String getTextMessage(String fromUserName, String toUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setContent(content);
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return MessageUtil.textMessageToXml(textMessage);
	}

}
