package common;

import java.util.Random;

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class GenerateNode {

    private static final String prefix       = "v_";
    private static final int    maxInt       = 1024;
    private static final int    strMaxLength = 10;
    private static final int    maxAssignVar = 10;

    public static void trave(Node root) {
        System.out.println(root);

        for (com.google.javascript.rhino.Node n : root.children()) {
            trave(n);
        }
    }

    //if(random n1 > random n2) result = random3 % random4
    public static Node geneIf() {
        Node nValue = IR.name("result");

        Node expr1 = new Node(Token.GT);
        Node number1 = Node.newNumber(new Random().nextInt(maxInt));
        Node number2 = Node.newNumber(new Random().nextInt(maxInt));
        expr1.addChildToFront(number1);
        expr1.addChildToBack(number2);
        Node number3 = IR.number(new Random().nextInt(maxInt));
        Node number4 = IR.number(new Random().nextInt(maxInt));
        Node mod = new Node(Token.MOD);
        mod.addChildToFront(number3);
        mod.addChildToBack(number4);
        Node assign = IR.assign(nValue, mod);
        Node exprResult = IR.exprResult(assign);
        Node thenBlock = IR.block(exprResult);

        Node ifBlock = IR.ifNode(expr1, thenBlock);
        return IR.block(ifBlock);

    }

    //function f(x,y){return x+y;}
    public static Node geneFunctionF() {
        Node name = IR.name("f");
        Node xParam = IR.name("x");
        Node yParam = IR.name("y");
        Node xValue = IR.name("x");
        Node yValue = IR.name("y");
        Node add = IR.add(xValue, yValue);
        Node ret = IR.returnNode(add);
        Node body = IR.block(ret);
        Node paramList = IR.paramList(xParam, yParam);
        Node function = IR.function(name, paramList, body);
        return IR.block(function);
    }

    //function name(x...)return{ statement;}
    public static Node geneFunction(String nameStr, String... params) {
        Node name = IR.name(nameStr);
        Node paramList = IR.paramList();
        for (String paramStr : params) {
            Node param = IR.name(paramStr);
            paramList.addChildrenToBack(param);
        }
        Node body = IR.block();//blank body
        Node function = IR.function(name, paramList, body);
        return IR.block(function);

    }

    //var x;
    //if(x){new y;}else{throw z;}
    public static Node geneIfElse() {
        Node x = IR.name("x");
        Node y = IR.name("y");
        Node newNode = IR.newNode(y);
        Node exprResult = IR.exprResult(newNode);
        Node thenBlock = IR.block(exprResult);
        Node z = IR.name("z");
        Node throwNode = IR.throwNode(z);
        Node elseBlock = IR.block(throwNode);
        Node ifNode = IR.ifNode(x, thenBlock, elseBlock);
        return IR.block(ifNode);
    }

    //for(var iName = 0; iName < random;){}
    //random >=1
    public static Node geneFor(String iName, int randomNumber) {
        Node iValue = IR.number(0);
        Node iVar = IR.name(iName);
        Node var = IR.var(iVar, iValue);
        //        int max = 10;
        //        Node random = IR.number(new Random().nextInt(max) + 1);
        Node random = IR.number(randomNumber);
        Node iCond = IR.name(iName);
        Node lt = new Node(Token.LT, iCond, random);
        Node body = IR.block();
        Node empty = IR.empty();
        Node forNode = IR.forNode(var, lt, empty, body);

        return IR.block(forNode);
    }

    //try - catch - finally
    //try {throw new Error(301, "an error");} catch (e) {} finally {}
    public static Node geneTryCatchFinally() {
        //throw new error
        Node error = IR.name("Error");
        Node errNO = IR.number(301);
        Node errMsg = IR.string("an error");
        Node newNode = IR.newNode(error, errNO, errMsg);
        Node throwNode = IR.throwNode(newNode);
        Node tryBlock = IR.block(throwNode);

        //catch
        Node errName = IR.name("e");
        Node catchState = IR.block();//add catch statement here
        Node catchBlock = IR.catchNode(errName, catchState);

        //finally
        Node finallyBlock = IR.block();//add finally statement here

        Node tryCatchFinallyBlock = IR.tryCatchFinally(tryBlock, catchBlock, finallyBlock);
        return IR.block(tryCatchFinallyBlock);
    }

    //try - catch
    //try {throw new Error(301, "an error");} catch (e) {}
    public static Node geneTryCatch() {
        //throw new error
        Node error = IR.name("Error");
        Node errNO = IR.number(301);
        Node errMsg = IR.string("an error");
        Node newNode = IR.newNode(error, errNO, errMsg);
        Node throwNode = IR.throwNode(newNode);
        Node tryBlock = IR.block(throwNode);

        //catch
        Node errName = IR.name("e");
        Node catchState = IR.block();//add catch statement here
        Node catchBlock = IR.catchNode(errName, catchState);

        Node tryCatchBlock = IR.tryCatch(tryBlock, catchBlock);
        return IR.block(tryCatchBlock);
    }

    public static Node geneVar(String strName) {
        Node name = IR.name(strName);
        Node var = IR.var(name);
        return IR.block(var);
    }

    public static Node geneVarString(String strName, String strValue) {
        Node name = IR.name(strName);
        Node value = IR.string(strValue);
        Node var = IR.var(name, value);
        return IR.block(var);
    }

    public static Node geneVarInt(String strName, Integer intValue) {
        Node name = IR.name(strName);
        Node value = Node.newNumber(intValue);
        Node var = IR.var(name, value);
        return IR.block(var);
    }

    //switch(n){case 1:break;case 2:break;case 3:break;...;default:break;}
    public static Node geneSwitch(String value) {
        Node switchName = IR.name(value);
        int n = new Random().nextInt(2) + 2;
        Node[] caseBody = new Node[n];
        for (int i = 0; i < n; i++) {
            caseBody[i] = geneCase(i + 1);//add case body here
        }

        Node defaultBody = IR.block(IR.breakNode());
        Node defaultCase = IR.defaultCase(defaultBody);
        Node switchBlock = IR.switchNode(switchName, caseBody);
        switchBlock.addChildToBack(defaultCase);

        /*Node var = geneVar(value);
        return IR.block(var, switchBlock);*/
        return IR.block(switchBlock);

    }

    public static Node geneCase(String value) {
        return geneCase(value, IR.block());
    }

    public static Node geneCase(int value) {
        return geneCase(value, IR.block());
    }

    public static Node geneCase(String value, Node body) {
        Node strValue = IR.string(value);
        Node brk = IR.breakNode();
        Node caseBody = IR.block(body, brk);//add statement here

        return IR.caseNode(strValue, caseBody);
    }

    public static Node geneCase(int value, Node body) {
        Node intValue = IR.number(value);
        Node brk = IR.breakNode();
        Node caseBody = IR.block(body, brk);
        return IR.caseNode(intValue, caseBody);
    }

    public static Node geneAssignAdd() {
        return geneAssignOperate(Token.ADD);
    }

    public static Node geneAssignSub() {
        return geneAssignOperate(Token.SUB);
    }

    public static Node geneAssignMul() {
        return geneAssignOperate(Token.MUL);
    }

    public static Node geneAssignDiv() {
        return geneAssignOperate(Token.DIV);
    }

    public static Node geneAssignMod() {
        return geneAssignOperate(Token.MOD);
    }

    /**
     * random1 = random2 operate random3
     * 
     * @param type
     * @return
     */
    private static Node geneAssignOperate(int type) {
        Random random = new Random();
        String name = prefix + Integer.toString(random.nextInt(maxAssignVar));
        Node target = IR.name(name);
        String para1Name = prefix + Integer.toString(random.nextInt(maxAssignVar));
        String para2Name = prefix + Integer.toString(random.nextInt(maxAssignVar));
        Node para1 = IR.name(para1Name);
        Node para2 = IR.name(para2Name);
        Node operate = new Node(type);
        operate.addChildToFront(para1);
        operate.addChildToBack(para2);
        Node exprResult = IR.exprResult(IR.assign(target, operate));
        return IR.block(exprResult);
    }

    /**
     * parameter += value
     * 
     * @param parameter
     * @param value
     * @return
     */
    public static Node geneAssignAdd(String parameter, int value) {
        Node name = IR.name(parameter);
        Node number = IR.number(value);
        Node add = IR.add(name, number);
        Node target = IR.name(parameter);
        Node assign = IR.assign(target, add);
        return IR.block(IR.exprResult(assign));

    }

    //x = value
    public static Node geneAssignX(String x, int value) {
        Node xNode = IR.name(x);
        Node valueNode = IR.number(value);
        Node assign = IR.assign(xNode, valueNode);
        return IR.block(IR.exprResult(assign));
    }

    public static Node geneIfGT(String cond, int random) {
        Node x = IR.name(cond);
        Node randomNode = IR.number(random - new Random().nextInt(maxInt) - 1);
        Node gt = new Node(Token.GT);
        gt.addChildToFront(x);
        gt.addChildToBack(randomNode);

        Node ifNode = IR.ifNode(gt, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfEQ(String cond, int random) {
        Node x = IR.name(cond);
        Node randomNode = IR.number(random);
        Node eq = new Node(Token.EQ);
        eq.addChildToFront(x);
        eq.addChildToBack(randomNode);

        Node ifNode = IR.ifNode(eq, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfGE(String cond, int random) {
        Node x = IR.name(cond);
        Node randomNode = IR.number(random - new Random().nextInt(maxInt));
        Node ge = new Node(Token.GE);
        ge.addChildToFront(x);
        ge.addChildToBack(randomNode);

        Node ifNode = IR.ifNode(ge, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfLT(String cond, int random) {
        Node x = IR.name(cond);
        Node randomNode = IR.number(1 + random + new Random().nextInt(maxInt));
        Node ge = new Node(Token.LT);
        ge.addChildToFront(x);
        ge.addChildToBack(randomNode);

        Node ifNode = IR.ifNode(ge, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfLE(String cond, int random) {
        Node x = IR.name(cond);
        Node randomNode = IR.number(random + new Random().nextInt(maxInt));
        Node ge = new Node(Token.LE);
        ge.addChildToFront(x);
        ge.addChildToBack(randomNode);

        Node ifNode = IR.ifNode(ge, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfAnd(String cond, int random) {
        Node para1 = IR.string(randomString(1 + new Random().nextInt(strMaxLength)));
        Node para2 = IR.string(randomString(1 + new Random().nextInt(strMaxLength)));
        Node and = new Node(Token.AND);
        and.addChildToFront(para1);
        and.addChildToBack(para2);

        Node ifNode = IR.ifNode(and, IR.block());
        return IR.block(ifNode);
    }

    public static Node geneIfOr(String cond, int random) {

        Node para1 = IR.string(randomString(1 + new Random().nextInt(strMaxLength)));
        Node para2 = IR.string(randomString(new Random().nextInt(strMaxLength)));
        Node or = new Node(Token.OR);
        or.addChildToFront(para1);
        or.addChildToBack(para2);

        Node ifNode = IR.ifNode(or, IR.block());
        return IR.block(ifNode);

    }

    private static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }

}
