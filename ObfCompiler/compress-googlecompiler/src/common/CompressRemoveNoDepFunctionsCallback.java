package common;

import java.util.HashMap;

import app.CompressCompiler;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class CompressRemoveNoDepFunctionsCallback extends
		NodeTraversal.AbstractPreOrderCallback {
	String space = "";
	HashMap<Node, Integer> nodeHierarchy = new HashMap<Node, Integer>();

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

	@Override
	public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
			Node parent) {
		boolean rt = nodeVisit(nodeTraversal, n, parent);
		printNode(n, parent);
		return rt;
	}

	// modify ast node here.
	private boolean nodeVisit(NodeTraversal nodeTraversal, Node n, Node parent) {
		switch (n.getType()) {
		case Token.VAR: {
			Node nameNode = n.getChildAtIndex(0);
			if (nameNode!=null&&nameNode.getType()==Token.NAME){
				Node functionNode = nameNode.getChildAtIndex(0);
				if (functionNode!=null&&functionNode.getType()==Token.FUNCTION){
					String name = nameNode.getString();
					if (CompressCompiler.calls.containsKey(name) && CompressCompiler.calls.get(name)==0){
						//System.out.println("here.");
						parent.removeChild(n);
						return false;
					}
				}
			}
			break;
		}

		default:
			break;
		}
		return true;
	}

}
