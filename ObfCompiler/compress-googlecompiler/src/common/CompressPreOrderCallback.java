package common;

import java.util.HashMap;

import app.CompressCompiler;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class CompressPreOrderCallback extends
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
		case Token.NAME: {
			try{
			String name = n.getString();
			if (name!=null&& !name.isEmpty()){
				n.setString(CompressCompiler.names.get(name));
			}
			}catch(Exception e){}
			return true;
		}

		default:
			break;
		}
		return true;
	}

}
