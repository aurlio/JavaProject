package common;

import java.util.HashMap;
import java.util.Random;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class ObfPreOrderCallback extends NodeTraversal.AbstractPreOrderCallback {
	String space = "";
	HashMap<Node, Integer> nodeHierarchy = new HashMap<Node, Integer>();
	StringObf sobf = new StringObf();
	
	public void init() {
	    sobf.init();
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

	private int getRandom() {
		Random rd = new Random();
		return rd.nextInt();
	}

	@Override
	public boolean shouldTraverse(NodeTraversal nodeTraversal, Node n,
			Node parent) {
		boolean rt = nodeVisit(nodeTraversal, n, parent);
		printNode(n, parent);
		return rt;
	}

	// modify ast node here.
	private boolean nodeVisit(NodeTraversal nodeTraversal, Node n,
			Node parent) {
		switch (n.getType()) {
		case Token.STRING: {
			return sobf.strNodeObf(n,parent);
		}
		case Token.NUMBER: {
			NumberObf nobf = new NumberObf();
			int ticket = getRandom();
			Node rt = null;
			int total = 4;
			if (ticket % total == 1) {
				rt = nobf.func_arith_make(255, n);
			} else if (ticket % total == 2) {
				rt = nobf.func_mod_make(n);
			} else {
				rt = nobf.func_crypto_make(ticket, n);
			}
			return rt == null;
		}
		default:
			break;
		}
		return true;
	}
	
	
}

