package com.joinsoft.core.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.SessionConstants;


/**
 * Servlet implementation class TokonServlet
 */
public class TokonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(TokonServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter("url");
		HttpSession session = request.getSession();
		log.info("url=: " + url);
    	String sso = "0";
    	if (session != null) {
			sso = "1";
		}
    	if (url.indexOf("?")>0) {
    		url = url + "&sso=" + sso + "&sid=" + (String)session.getAttribute(SessionConstants.JSDP_USER_SESSIONID);
    	} else {
    		url = url + "?sso=" + sso + "&sid=" + (String)session.getAttribute(SessionConstants.JSDP_USER_SESSIONID);
    	}
    	try {
    		log.info("url=: " + url);
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
