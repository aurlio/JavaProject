package common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;

public class WrapNode {
    private static final String globalPrefix = "global_";
    private static final int    maxInt       = 1024;
    private static final int    maxVar       = 1024;

    private static String[]     globalVars   = { globalPrefix + 0, globalPrefix + 1,
            globalPrefix + 2, globalPrefix + 3, globalPrefix + 4, globalPrefix + 5,
            globalPrefix + 6, globalPrefix + 7, globalPrefix + 8, globalPrefix + 9 };

    /**
     * generate code: 
     * <p>
     * <code>
     * try{ <b>target</b> } catch(e){e.message}
     * </code>
     * 
     * @param target
     * @param parent
     * @return
     */
    public static Node wrapByTryCatch(Node target, Node parent) {
        Node tryCatchBlock = GenerateNode.geneTryCatch();
        Node tryNode = tryCatchBlock.getFirstChild();
        Node tryBlock = tryNode.getFirstChild();
        tryBlock.removeChildren();
        Node newChild = target.cloneTree();
        tryBlock.addChildToFront(newChild);
        parent.replaceChild(target, tryCatchBlock);

        return tryCatchBlock;
    }

    /**
     * generate code:
     * <p>
     * <code>
     * v_random = random1;
     * <br/>if(v_random > random2){<strong>target</strong>}
     * <br/>if(v_random >= random2){<strong>target</strong>}
     * <br/>if(v_random == random2){<strong>target</strong>}
     * <br/>if(v_random < random2){<strong>target</strong>}
     * <br/>if(v_random <= random2){<strong>target</strong>}
     * <br/>if('str1'&&'str2'){<strong>target</strong>}
     * <br/>if('str1'||'str2'){<strong>target</strong>}
     * 
     * </code>
     * 
     * @param target
     * @param parent
     * @return
     */
    public static Node wrapByIf(Node target, Node parent) {
        int random = new Random().nextInt(maxInt);
        String x = globalVars[new Random().nextInt(globalVars.length)];
        Node var = GenerateNode.geneAssignX(x, random);

        int methodNumber = 5;

        //if
        Method[] methods = new Method[methodNumber];
        try {
            methods[0] = GenerateNode.class.getMethod("geneIfGT", String.class, int.class);
            methods[1] = GenerateNode.class.getMethod("geneIfGE", String.class, int.class);
            methods[2] = GenerateNode.class.getMethod("geneIfLT", String.class, int.class);
            methods[3] = GenerateNode.class.getMethod("geneIfLE", String.class, int.class);
            methods[4] = GenerateNode.class.getMethod("geneIfEQ", String.class, int.class);
            //            methods[5] = GenerateNode.class.getMethod("geneIfAnd", String.class, int.class);
            //            methods[6] = GenerateNode.class.getMethod("geneIfOr", String.class, int.class);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Node ifBlock = IR.block();
        try {
            ifBlock = (Node) methods[new Random().nextInt(methodNumber)].invoke(null, x, random);
            Node thenBlock = ifBlock.getFirstChild().getLastChild();
            //then
            thenBlock.addChildToFront(target.cloneTree());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Node varIfBlock = IR.block(var, ifBlock);
        parent.replaceChild(target, varIfBlock);
        return varIfBlock;
    }

    /**
     * generate code: <br/>
     * <p>
     * <code>
     * var v_random = k;<br/>
     * switch(v_random){<br/>
     *      case 1:break;<br/>
     *      case 2:break;<br/>
     *      ...<br/>
     *      case 3:<b>target;</b>break;<br/>
     *      ...<br/>
     *      case 4:break;<br/>
     *      default:break;<br/>
     *  }<br/>
     * </code>
     * 
     * @param target
     * @param parent
     * @return
     */
    public static Node wrapBySwitch(Node target, Node parent) {
        String x = globalVars[new Random().nextInt(globalVars.length)];

        //switch
        Node switchBlock = GenerateNode.geneSwitch(x);
        Node switchBody = switchBlock.getLastChild();
        int n = switchBody.getChildCount();
        int select = new Random().nextInt(n - 2) + 1;
        Node selectedCase = switchBody.getChildAtIndex(select);

        Node[] otherCases = new Node[n - 3];

        //other case
        int j = 0;
        for (int i = 1; i < n - 1; i++) {
            if (i != select) {
                otherCases[j++] = switchBody.getChildAtIndex(i);
            }
        }
        for (int i = 0; i < otherCases.length; i++) {
            //            String value = otherCases[i].getFirstChild().getString();
            int value = (int) otherCases[i].getFirstChild().getDouble();

            int methodNumber = 5;
            Method[] methods = new Method[methodNumber];
            try {
                methods[0] = GenerateNode.class.getMethod("geneAssignAdd");
                methods[1] = GenerateNode.class.getMethod("geneAssignSub");
                methods[2] = GenerateNode.class.getMethod("geneAssignMul");
                methods[3] = GenerateNode.class.getMethod("geneAssignDiv");
                methods[4] = GenerateNode.class.getMethod("geneAssignMod");
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            //assign_add, assign_sub, assign_mutil, assign_div
            try {
                Node assign = (Node) methods[new Random().nextInt(methodNumber)].invoke(null);
                Node newNode = GenerateNode.geneCase(value, assign);
                switchBody.replaceChild(otherCases[i], newNode);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        int intValue = select;
        Node globalVar = GenerateNode.geneAssignX(x, intValue);

        //replace a case
        //        Node thisCase = GenerateNode.geneCase(Integer.toString(intValue), target.cloneTree());
        Node thisCase = GenerateNode.geneCase(intValue, target.cloneTree());
        switchBody.replaceChild(selectedCase, thisCase);

        Node varSwitch = IR.block(globalVar, switchBlock);

        parent.replaceChild(target, varSwitch);

        return varSwitch;

    }

    /**
     * generate code:
     * <p>
     * <code>
     * for(var _i_random = 0; _i_random < random_int; _i_random++){<br/>
     * <b>target;</b><br/>
     * _i_random += random_int;}
     * </code>
     * 
     * @param target
     * @param parent
     * @return
     */
    public static Node wrapByFor(Node target, Node parent) {
        int max = 10;
        int randomNumber = new Random().nextInt(max) + 1;
        String varName = "_i" + "_" + new Random().nextInt(maxVar);
        Node forBlock = GenerateNode.geneFor(varName, randomNumber);
        Node forBody = forBlock.getLastChild().getLastChild();
        forBody.detachChildren();
        forBody.addChildToBack(target.cloneTree());
        forBody.addChildToBack(GenerateNode.geneAssignAdd(varName, randomNumber));

        parent.replaceChild(target, forBlock);

        return forBlock;

    }
}
