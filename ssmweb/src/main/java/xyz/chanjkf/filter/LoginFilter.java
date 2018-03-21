package xyz.chanjkf.filter;

import net.sf.json.JSONObject;
import xyz.chanjkf.entity.UserDetailEntity;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.model.IpModel;
import xyz.chanjkf.service.Impl.UserDetailService;
import xyz.chanjkf.service.Impl.UserService;
import xyz.chanjkf.service.httpClent.RestfulService;
import xyz.chanjkf.utils.HttpClientUtils;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.ToolSpring;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private RestfulService service;

	private UserService userService;

	private UserDetailService detailService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		String requestURI = request.getRequestURI();

		if (requestURI.contains("/photo/login")) {
			if (userService == null) {
				userService = (UserService)ToolSpring.getBean("UserService");
			}
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserEntity user = userService.findUserByNameAndPass(username, password);
			if (user != null) {
				ExecutorService executorService = Executors.newSingleThreadExecutor();
				if (service == null) {
					service = (RestfulService)ToolSpring.getBean("RestfulService");
				}
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							String ip = getRemoteHost(request);
							String url = "https://dm-81.data.aliyun.com/rest/160601/ip/getIpInfo.json";
							String appcode = "b3ebe78c03fd46adabe9403ca2bf73ff";
							url = url+"?ip="+ip;
							String ipMsg = HttpClientUtils.getWeather(url, appcode);
							JSONObject data = JSONObject.fromObject(ipMsg);
							IpModel model = (IpModel)JsonUtil.getObject(IpModel.class, data.getString("data"));
							if (detailService == null) {
								detailService = (UserDetailService)ToolSpring.getBean("UserDetailService");
							}
							UserDetailEntity entity =detailService.getEntityByUserId(user.getId());
							if (entity == null) {
								entity = new UserDetailEntity();
								entity.setUserId(user.getId());
								entity.setIp(ip);
								entity.setArea(model.getArea());
								entity.setCountry(model.getCountry());
								entity.setCity(model.getCity());
								entity.setRegion(model.getRegion());
								entity.setCounty(model.getCounty());
								detailService.create(entity);
							} else {
								Long id = entity.getId();
								entity = new UserDetailEntity();
								entity.setId(id);
								entity.setUserId(user.getId());
								entity.setIp(ip);
								entity.setArea(model.getArea());
								entity.setCountry(model.getCountry());
								entity.setCity(model.getCity());
								entity.setRegion(model.getRegion());
								entity.setCounty(model.getCounty());
								detailService.update(entity);
							}

						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}






		}


		arg2.doFilter(arg0, arg1);

		
	}

	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
