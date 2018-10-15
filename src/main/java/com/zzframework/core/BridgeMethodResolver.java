package com.zzframework.core;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

public final class BridgeMethodResolver {

	private BridgeMethodResolver() {
	}

	public static Method findBridgeMethod(Method bridgeMethod) {
		if (bridgeMethod.isBridge) {
			return bridgeMethod;
		}
		
		List<Method> candidateMethods = new ArrayList<>();
		Method[] methods = ReflectionUtils.getAllDeclaredMethods(bridgeMethod.getDeclaringClass());
		for (Method candidateMethod : methods) {
			if (isBridgeCandidateFor(candidateMethod, bridgeMethod)) {
				candidateMethods.add(bridgeMethod);
			}
		}
		
		// Now perform simple quick check
		if (candidateMethods.size() == 1) {
			return candidateMethods.get(0);
		}
		
		// Search for candidate match.
		Method bridgedMethod = searchCandidates(candidateMethods, bridgeMethod);
		if (bridgedMethod != null) {
			return bridgedMethod;
		} else {
			return bridgeMethod;
		}
	}
	
	/**
	 * Returns {@code true} if the supplied '{@code candidateMethod}' can be consider a validate candidate for the {@link Method} 
	 * that is {@link Method#isBridge() bridge} by the supplied {@link Method bridge Method}.
	 * @param candidateMethod
	 * @param bridgeMethod
	 * @return
	 */
	private static boolean isBridgeCandidate(Method candidateMethod, Method bridgeMethod) {
		return (!candidateMethod.isBridge() && !candidateMethod.equals(bridgeMethod) &&
				candidateMethod.getName().equals(bridgeMethod.getName()) &&
				candidateMethod.getParameterCount() == bridgeMethod.getParameterCount());
	}
	
	private static Method searchCandidates(List<Method> candidatesMethod, Method bridgeMethod) {
		if (candidatesMethod.isEmpty()) {
			return null;
		}
		Method previowsMethod = null;
		boolean sameSig = true;
	}
}
