package com.doukou.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doukou.service.BaseService;
import com.doukou.util.Sha1Util;

@Controller
public class BaseController {
	
	/**
	 * 安全性验证           .<br>
	 *
	 * @param request
	 * @return  String 
	 */
	@RequestMapping(value="api", method=RequestMethod.GET)
	@ResponseBody
	public String VerifySignature(HttpServletRequest request) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String sha1 = Sha1Util.getSha1(timestamp,nonce);
		if (sha1.equals(signature)) {
			return echostr;
		} else {
			return null;
		}
	}
	
	/**
	 * 返回消息         .<br>
	 *
	 * @param request
	 * @param response  void 
	 */
	@RequestMapping(value="api", method=RequestMethod.POST)
	public void getWeixinMessage(HttpServletRequest request, HttpServletResponse response) {
		String responseMessage = BaseService.processRequest(request);
		try {
			response.getWriter().write(responseMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
