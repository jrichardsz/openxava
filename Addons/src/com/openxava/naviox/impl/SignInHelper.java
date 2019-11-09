package com.openxava.naviox.impl;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.openxava.util.*;
import org.openxava.view.*;

import com.openxava.naviox.*;

/**
 * 
 * @author Javier Paniza
 */

public class SignInHelper {
	
	private final static String PROPERTIES_FILE = "naviox-users.properties";
	private static Log log = LogFactory.getLog(SignInHelper.class);
	private static Properties users;
	
	public static void init(HttpServletRequest request, View view) {
	}
	
	public static String refineForwardURI(HttpServletRequest request, String forwardURI) {
		return forwardURI;
	}	
	
	public static void signIn(HttpSession session, String userName) {
		session.setAttribute("naviox.user", userName);
		Modules modules = (Modules) session.getAttribute("modules");
		modules.reset();		
	}
	
	public static boolean isAuthorized(String user, String password) {
		String storedPassword = getUsers().getProperty(user, null);
		storedPassword = getEnvironmentValueIfApplicable(storedPassword);
		return password.equals(storedPassword);
	}	
	
	/**
	 * @since 5.4 
	 */
	public static boolean isAuthorized(String userName, String password, Messages errors) {  
		boolean authorized = isAuthorized(userName, password);
		if (!authorized) errors.add("unauthorized_user");
		return authorized;
	}	
	
	//TODO: Store parsed values instead of parse in each usage
    private static String getEnvironmentValueIfApplicable(String unknownValueIdentifier) {
      String regex = "(\\$\\{[\\w\\^\\$\\s]+\\})";
      Matcher m = Pattern.compile(regex).matcher(unknownValueIdentifier);
      while (m.find()) {
        String environmentKey = m.group(0).replace("${", "").replace("}", "");
  
        if (environmentKey == null || environmentKey.equals("")) {
          continue;
        }
  
        String value = System.getenv(environmentKey);
        if (value != null && !value.contentEquals("")) {
          unknownValueIdentifier = unknownValueIdentifier.replace(String.format("${%s}", new Object[] { environmentKey }), value);
        }
      }
      return unknownValueIdentifier;
    }	
	
	private static Properties getUsers() {		
		if (users == null) {
			PropertiesReader reader = new PropertiesReader(
					Users.class, PROPERTIES_FILE);
			try {
				users = reader.get();
			} catch (IOException ex) {
				log.error(XavaResources.getString("properties_file_error",
						PROPERTIES_FILE), ex);
				users = new Properties();
			}
		}
		return users;		
	}

}

