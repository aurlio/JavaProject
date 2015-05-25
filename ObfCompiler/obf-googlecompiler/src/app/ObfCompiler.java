package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.rhino.Node;
import common.ObfCallChainCallback;
import common.ObfPreOrderCallback;

public class ObfCompiler {

	public static HashMap<String, ArrayList<String>> mapGlobalFunctions = new HashMap<String, ArrayList<String>>();
	public static String currentFunction = "";

	private Compiler compiler = null;
	private SourceFile external = null;
	private CompilerOptions options = null;

	public ObfCompiler() {
		if (this.compiler == null) {
			external = SourceFile.fromCode("[external]", "");
			options = new CompilerOptions();
			options.ideMode = true;
			/*
			 * options.prettyPrint = true; options.foldConstants = true;
			 * options.flowSensitiveInlineVariables = true;
			 * options.removeDeadCode = true;
			 * options.removeUnusedClassProperties = true;
			 * options.removeTryCatchFinally = true;
			 * options.removeUnusedLocalVars = true;
			 * options.removeUnusedPrototypeProperties = true;
			 * options.removeUnusedPrototypePropertiesInExterns = true;
			 * options.removeUnusedVars = true;
			 */
			options.setManageClosureDependencies(true);
			compiler = new Compiler();
		}
	}

	public Compiler getCompiler() {
		return compiler;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public Node compile(String code) {
		compiler.compile(external, SourceFile.fromCode("[primary]", code),
				options);
		return compiler.getRoot();
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

	public void traverseNodes(Node node) {
		ObfCallChainCallback cb1 = new ObfCallChainCallback();
		NodeTraversal t1 = new NodeTraversal(compiler, cb1);
		ObfPreOrderCallback cb2 = new ObfPreOrderCallback();
		cb2.init();
		NodeTraversal t2 = new NodeTraversal(compiler, cb2);
		t1.traverse(node);
		// System.out.println(mapGlobalFunctions);
		t2.traverse(node);
	}

	private static boolean compareByteArray(byte[] b1, byte[] b2){
		System.out.print("b1="+new String(b1)+"\nb2="+new String(b2));
		if (b1==null&&b2==null){
			return true;
		}
		if (b1==null||b2==null){
			return false;
		}
		if (b1.length!=b2.length){
			return false;
		}
		for(int i=0; i<b1.length; i++){
			if(b1[i]!=b2[i]){
				return false;
			}
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ObfCompiler compiler = new ObfCompiler();
		String code = compiler.readCodeFromFile(args[0]);
		Node node = compiler.compile(code);
		compiler.traverseNodes(node);
		String codeout = compiler.toSource();
		writeToFile(codeout, args[1]);

		// System.out.println(codeout);
		System.exit(0);
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

	private String toSource() {
		return this.compiler.toSource();
	}

}
