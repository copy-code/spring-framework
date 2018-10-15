package com.zzframework.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.zzframework.util.Assert;
import com.zzframework.util.StringUtils;

@SuppressWarnings("serial")
public class AttributeAccesorSupport implements AttributeAccesor, Serializable {

	private Map<String, Object> attributes = new HashMap();
	
	@Override
	public void setAttribute(String name, Object value) {
		Assert.notNull(name, "Name must not be null");
		if (value != null) {			
			this.attributes.put(name, value);
		} else {
			this.attributes.remove(name);
		}
	}

	@Override
	public Object getAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.get(name);
	}

	@Override
	public Object removeAttribute(String name) {
		// TODO Auto-generated method stub
		Assert.notNull(name, "Name must not be null");
		return this.attributes.remove(name);
	}

	@Override
	public boolean hasAttribute(String name) {
		// TODO Auto-generated method stub
		Assert.notNull(name, "Name must not be null");
		return false;
	}

	@Override
	public String[] attributeNames() {
		StringUtils.toStringArray(this.attributes.keySet());
		return null;
	}
	
	protected void copyFromAttributes(AttributesAccessor source) {
		Assert.notNull(source, "source must not be null");
		String[] attributeNames = source.getAttributeNames();
		for (String attributeName : attributeNames) {
			setAttribute(attributeName, source.getAttribute(attributeName));
		}
	}

	@Override
	public int hashCode() {
		return this.attributes.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		// 这个地方涉及父子类之间的转换
		return (this == other || (other instanceof AttributeAccesorSupport && 
				this.attributes.equals(((AttributeAccesorSupport) other).attributes)));
	}

	
}
