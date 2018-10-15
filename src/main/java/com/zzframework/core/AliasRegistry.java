package com.zzframework.core;

public interface AliasRegistry {

	void registerAlias(String name, String alias);
	
	void removeAlias(String alias);
	
	boolean isAligs(String name);
	
	String getAlias(String name);
}
