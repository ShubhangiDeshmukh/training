package com.adobe.training.core.models;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;

@Model(adaptables=Resource.class)
public class PagesIteratorModel {
	
	@Inject
	private String parentPath;

	public String getParentPath() {
		return parentPath;
	}
	
	@SlingObject
    private ResourceResolver resourceResolver;
	
	private Page childPage;
	
	public Page getChildPage() {
		return childPage;
	}

	public void setChildPage(Page childPage) {
		this.childPage = childPage;
	}

	@PostConstruct
    protected void init() {
		Page childPage = resourceResolver.getResource(parentPath).adaptTo(Page.class);
		setChildPage(childPage);
	}
}
