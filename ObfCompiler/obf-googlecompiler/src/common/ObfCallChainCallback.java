package common;

import java.util.ArrayList;

import app.ObfCompiler;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class ObfCallChainCallback extends NodeTraversal.AbstractPreOrderCallback {

	@Override
	public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
			Node parent) {
		if (n.getType() == Token.FUNCTION) {
			// currentFunction = n.getChildAtIndex(0).getString();
			Node next = n.getChildAtIndex(0);
			if (next.getType() == Token.NAME) {
				ObfCompiler.currentFunction = next.getString();
				if (!ObfCompiler.mapGlobalFunctions.containsKey(ObfCompiler.currentFunction)
						&& ObfCompiler.currentFunction.length() > 0) {
					ArrayList<String> callto = new ArrayList<String>();
					ObfCompiler.mapGlobalFunctions.put(ObfCompiler.currentFunction, callto);
				}
			}
			if (parent.getType() == Token.NAME) {
				ObfCompiler.currentFunction = parent.getString();
				if (!ObfCompiler.mapGlobalFunctions.containsKey(ObfCompiler.currentFunction)
						&& ObfCompiler.currentFunction.length() > 0) {
					ArrayList<String> callto = new ArrayList<String>();
					ObfCompiler.mapGlobalFunctions.put(ObfCompiler.currentFunction, callto);
				}
			}
		}
		if (n.getType() == Token.CALL) {
			Node next = n.getChildAtIndex(0);
			if (next.getType() == Token.NAME) {
				if (ObfCompiler.mapGlobalFunctions.containsKey(ObfCompiler.currentFunction)
						&& ObfCompiler.currentFunction.length() > 0) {
					ArrayList<String> callto = ObfCompiler.mapGlobalFunctions
							.get(ObfCompiler.currentFunction);
					if (callto != null) {
						callto.add(n.getChildAtIndex(0).getString());
					}
				}
			}
		}
		return true;
	}
}
