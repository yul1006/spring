package spring.utils;

import javax.servlet.http.HttpSession;

import spring.domain.User;

public class HttpSessionUtils {
	private static final String LOGIN_USER = "loginUser";

	public static boolean isLoginUser(HttpSession session){
		return session.getAttribute(LOGIN_USER) !=null;
	}

	public static User getUserFromSession(HttpSession session){
		if(isLoginUser(session)){
			return null;
		}
		return (User)session.getAttribute(LOGIN_USER);
	}
}
