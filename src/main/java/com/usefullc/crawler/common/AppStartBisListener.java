/**
 * 
 */
package com.usefullc.crawler.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * app 启动业务监听器
 * @author admin
 *
 * @2015-05-31 18
 */
public class AppStartBisListener implements ServletContextListener  {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//ServletContext sc = sce.getServletContext();
		//sc.setAttribute(CommConstant.LINK_MAP,ParseResourceLink.getLinkMap());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
