package common;

import java.util.HashMap;

import app.CompressCompiler;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class CompressNameCollectCallback extends
		NodeTraversal.AbstractPreOrderCallback {

	String space = "";
	HashMap<Node, Integer> nodeHierarchy = new HashMap<Node, Integer>();

	public static boolean stringEquals(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 != null && s2 == null)
			return false;
		if (s1 == null && s2 != null)
			return false;
		int l1 = s1.length();
		int l2 = s2.length();
		if (l1 != l2)
			return false;
		for (int i = 0; i < l1; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
			Node parent) {
		boolean rt = nodeVisit(nodeTraversal, n, parent);
		printNode(n, parent);
		return rt;
	}

	private boolean nodeVisit(NodeTraversal nodeTraversal, Node n, Node parent) {
		switch (n.getType()) {
		case Token.NAME: {
			try {
				String name = n.getString();
				if (name != null && !name.isEmpty()
						&& !stringEquals("Array", name)
						&& !stringEquals("Object", name)
						&& !stringEquals("String", name)
						&& !stringEquals("Number", name)
						&& !stringEquals("Function", name)
						&& !stringEquals("undefined", name)
						&& !stringEquals("eval", name)
						&& !stringEquals("instanceof", name)) {
					CompressCompiler.names.put(name, name);
				}
			} catch (Exception e) {
			}
			break;
		}
		case Token.CALL: {
			Node nameNode = n.getChildAtIndex(0);
			if (nameNode.getType() == Token.NAME) {
				String name = nameNode.getString();
				if (!CompressCompiler.calls.containsKey(name)) {
					CompressCompiler.calls.put(name, 1);
				} else {
					int count = CompressCompiler.calls.get(name);
					CompressCompiler.calls.put(name, count + 1);
				}
			}
			break;
		}
		case Token.FUNCTION: {
			String name = "";
			if (parent.getType() == Token.NAME) {
				name = parent.getString();
				if (name != null && !name.isEmpty()) {
					if (!CompressCompiler.calls.containsKey(name)) {
						CompressCompiler.calls.put(name, 0);
					}
				}
			}
			break;
		}
		case Token.VAR: {
			/*
			 * VAR NAME NmI3NGUwM2M NAME MjczNTdjMjg
			 */
			Node nameSub1 = n.getChildAtIndex(0);
			if (nameSub1 != null & nameSub1.getType() == Token.NAME) {
				Node nameSub2 = nameSub1.getChildAtIndex(0);
				if (nameSub2 != null && nameSub2.getType() == Token.NAME) {
					String name = nameSub2.getString();
					if (!CompressCompiler.calls.containsKey(name)) {
						CompressCompiler.calls.put(name, 1);
					} else {
						int count = CompressCompiler.calls.get(name);
						CompressCompiler.calls.put(name, count + 1);
					}
				}
			}
			break;
		}
		case Token.INSTANCEOF:{
			
			break;
		}

		default:
			break;
		}
		return true;
	}

	private void printNode(Node n, Node parent) {
		int hierarchy = 0;
		if (parent != null) {
			Integer v = nodeHierarchy.get(parent);
			if (v != null) {
				hierarchy = v;
			} else {
				Node g = parent.getParent();
				if (g == null) {
					hierarchy = 0;
				} else {
					Integer t = nodeHierarchy.get(g);
					if (t != null) {
						hierarchy = t + 1;
					}
				}
				nodeHierarchy.put(parent, hierarchy);
			}
		}

		space = "";
		while (hierarchy != 0) {
			hierarchy--;
			space += "  ";
		}
		//System.out.println(space + n.toString(false, false, true));
	}
}
