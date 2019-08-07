package com.ibond.dcm.utils;


import com.ibond.common.service.IUserService;
import com.ibond.common.utils.ServletRequestUtil;
import com.ibond.common.utils.StringUtils;
import com.ibond.dcm.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Service
public class AnalysisRequest {

	@Resource
	private IUserService userService;

	public User getUser() {
		User user = null;
//		String uid = ServletRequestUtil.getRequest().getHeader("uid");
		String uid = "0";
		if (!StringUtils.isEmptyOrNull(uid)) {
			user = userService.selectByPrimaryId(Long.parseLong(uid));
		}
		return user;
	}

	public String getUserName() {
		User user = getUser();
		if (user != null) {
			return user.getName();
		}
		return null;
	}

	public Long getUserPrimaryKey() {
		User user = getUser();
		if (user != null) {
			return user.getId();
		}
		return null;

	}

	public boolean isLoginUser(Long vialdId) throws Exception {
		if (vialdId == null) {
			throw new Exception("用户主键不可为空！");
		}
		Long currenUser = this.getUserPrimaryKey();
		if (currenUser == null) {
			throw new Exception("用户主键不可为空！");
		}
		if (Long.valueOf(currenUser) == Long.valueOf(vialdId)) {
			return true;

		}
		return false;
	}

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static String getDomain() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		StringBuffer url = request.getRequestURL();
		String domainUrl = url
				.delete(url.length() - request.getRequestURI().length(),
						url.length()).append("/").toString();
		return domainUrl;
	}

	public static String getRequestURI() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		return request.getRequestURI();
	}

	public static String getRequestUserAgent() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		return request.getHeader("user-agent");
	}

	public static String getMethod() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		return request.getMethod();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getParamMapConvert() {
		HttpServletRequest request = ServletRequestUtil.getRequest();
		Map paramMap = request.getParameterMap();
		if (paramMap == null) {
			return null;
		}
		StringBuilder params = new StringBuilder();
		for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap)
				.entrySet()) {
			params.append(("".equals(params.toString()) ? "" : "&")
					+ param.getKey() + "=");
			String paramValue = (param.getValue() != null
					&& param.getValue().length > 0 ? param.getValue()[0] : "");
			params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(
					param.getKey(), "password") ? "" : paramValue, 100));
		}

		return params.toString();
	}

	public static String getRealURL(HttpServletRequest request) {
		// http://localhost:8080/BondWSService
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}
}
