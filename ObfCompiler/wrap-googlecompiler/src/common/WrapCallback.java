package common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;

import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class WrapCallback extends AbstractPostOrderCallback {
    int                    methodNumber  = 4;
    Method[]               methods       = new Method[methodNumber];

    String                 space         = "";
    HashMap<Node, Integer> nodeHierarchy = new HashMap<Node, Integer>();

    public WrapCallback() {
        try {
            methods[0] = WrapNode.class.getMethod("wrapByIf", Node.class, Node.class);
            methods[1] = WrapNode.class.getMethod("wrapBySwitch", Node.class, Node.class);
            methods[2] = WrapNode.class.getMethod("wrapByTryCatch", Node.class, Node.class);
            methods[3] = WrapNode.class.getMethod("wrapByFor", Node.class, Node.class);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
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
        //        System.out.println(space + n.toString(false, false, true));
    }

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
        wrap(n, parent);
        printNode(n, parent);
    }

    private void wrap(Node n, Node parent) {
        int type = n.getType();
        switch (type) {

            case Token.STRING:
            case Token.NUMBER:
            case Token.ASSIGN:
                break;

            case Token.FUNCTION:
                //var foo = function(){}
                /*if (parent.getType() == Token.NAME) {
                    Node varNode = parent.getParent();
                    Node topNode = varNode.getParent();
                    randomWrap(varNode, topNode);
                } else {
                    //function foo(){}
                    randomWrap(n, parent);
                }*/
                break;
            case Token.VAR:
                // except var in for
                /*if (parent.getType() != Token.FOR) {
                    randomWrap(n, parent);
                }*/
                break;

            case Token.IF:
            case Token.FOR:
            case Token.EXPR_RESULT:
            case Token.WHILE:
            case Token.DO:
                randomWrap(n, parent);
                break;

            default:
                break;
        }
    }

    private void randomWrap(Node n, Node parent) {
        int maxWrapLevel = 3;
        int wrapLevel = new Random().nextInt(maxWrapLevel);
        while (wrapLevel-- > 0) {
            try {
                n = (Node) methods[new Random().nextInt(methodNumber)].invoke(null, n, parent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

}
