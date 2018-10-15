package com.zzframework.core;

import com.zzframework.lang.Nullable;

public interface AttributeAccesor {

	void setAttribute(String name, @Nullable Object value);
	
	Object getAttribute(String name);
	
	Object removeAttribute(String name);
	
	boolean hasAttribute(String name);
	
	String[] attributeNames();
}
