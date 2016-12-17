package com.doukou.commom;

import com.doukou.util.Global;

/**
 * 全局常量                 .<br>
 *
 * @author sunkai
 * @version 2016年12月12日
 */
public class GlobalConstant {
	/**
	 * 订阅号的展示信息。
	 */
	public static final String SUBSCRIBE_CONTENT = Global.getConfig("SUBSCRIBE_CONTENT");
	/**
	 * 服务暂时无法回复。
	 */
	public static final String ERROR_OUTAGE = Global.getConfig("ERROR_OUTAGE");
}
