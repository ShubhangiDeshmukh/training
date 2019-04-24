/*
 * Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.training.core.servlets;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */

@SlingServlet(paths = "/bin/pageIterator")

public class PageIterator extends SlingAllMethodsServlet {
	@Reference
    static ResourceResolverFactory resolverFactory;
    static Session session;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		/*String pagePath = request.getParameter("pagePathValue");
		System.out.println("Page Path =" + pagePath);
		ResourceResolver resolver = request.getResourceResolver();
		Page page = resolver.getResource(pagePath).adaptTo(Page.class);
		System.out.println(page.getTitle());*/
		
		/*Node mynode = page.adaptTo(Node.class);
		try {
			mynode.setProperty("approved", "OK");
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
		
		Session session= null;
        String path = "/content/we-retail/ca/en";
        try {
        	/*ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);*/
            session = (Session) request.getSession();
            Node node = session.getNode(path);
            node.setProperty("approved", "ok");
            session.save();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.logout();
        }
		
		/*Iterator<Page> childPageIterator = page.listChildren();
		ChilldPagesIterator childPagesIterator = new ChilldPagesIterator();
		
		ArrayList<Page> childPagesList = new ArrayList<Page>();
		
		while (childPageIterator.hasNext()) {
			Page childPages = childPageIterator.next();
			childPagesList.add(childPages);
			System.out.println("Child Pages Title = " + childPages.getTitle());
		}
		childPagesIterator.setChildPage(childPagesList);
		System.out.println(childPagesList.size());*/
	}
	
}
