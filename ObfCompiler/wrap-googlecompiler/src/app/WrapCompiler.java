package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.Compiler.CodeBuilder;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.DotFormatter;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.rhino.Node;
import common.WrapCallback;

public class WrapCompiler {

    private Compiler         targetCompiler = null;
    private CompilerOptions  options        = null;

    //parser the original ast into node list
    public static List<Node> originalList   = new ArrayList<Node>();
    public static List<Node> blockList      = new ArrayList<Node>();

    public WrapCompiler() {
        if (targetCompiler == null) {
            targetCompiler = new Compiler();
            options = new CompilerOptions();
            options.setIdeMode(true);
            options.setManageClosureDependencies(true);

        }

    }

    private Node compile(String code) {
        SourceFile file = SourceFile.fromCode("[primary]", code);
        return targetCompiler.parse(file);
    }

    public String readCodeFromFile(String fileName) {
        String out = "";
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                out += strLine + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return out;
    }

    private static void writeToFile(String code, String fileName) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
            output.write(code);
            output.close();

        } catch (Exception e) {
            System.err.println("writeToFile Error: " + e.getMessage());
        }

    }

    private Node traverseNodes(Node root) {

        //traversal to wrapper
        WrapCallback wcb = new WrapCallback();
        NodeTraversal.traverse(targetCompiler, root, wcb);

        return root;
    }

    private String toSource(Node root) {
        CodeBuilder cb = new CodeBuilder();
        int inputSeqNum = 0;
        targetCompiler.toSource(cb, inputSeqNum, root);
        return cb.toString();
    }

    public String getDot(Node node) {
        try {
            String dot = DotFormatter.toDot(node);
            return dot;
        } catch (IOException e) {
            System.out.println("Get Dot Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        WrapCompiler targetCompiler = new WrapCompiler();
        String code = targetCompiler.readCodeFromFile(args[0]);
        Node targetRoot = targetCompiler.compile(code);
        targetCompiler.traverseNodes(targetRoot);

        String codeOut = targetCompiler.toSource(targetRoot);
        writeToFile(codeOut, args[1]);

        System.exit(0);

    }

}
