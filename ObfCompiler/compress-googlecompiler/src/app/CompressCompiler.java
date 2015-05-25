package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.InputStreamReader;


import java.util.HashMap;


import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.NodeTraversal;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.rhino.Node;

import common.CompressNameCollectCallback;
import common.CompressPreOrderCallback;
import common.CompressReGenerateNames;
import common.CompressRemoveNoDepFunctionsCallback;

public class CompressCompiler {

	public static HashMap<String, String> names = new HashMap<String, String>();
	public static HashMap<String, Integer> calls = new HashMap<String,Integer>();

	private Compiler compiler = null;
	private SourceFile external = null;
	private CompilerOptions options = null;

	public CompressCompiler() {
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
			options.removeDeadCode = true;
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
		CompressNameCollectCallback cb1 = new CompressNameCollectCallback();
		NodeTraversal t1 = new NodeTraversal(compiler, cb1);
		CompressPreOrderCallback cb2 = new CompressPreOrderCallback();
		NodeTraversal t2 = new NodeTraversal(compiler, cb2);
		CompressRemoveNoDepFunctionsCallback cb3 = new CompressRemoveNoDepFunctionsCallback();
		NodeTraversal t3 = new NodeTraversal(compiler, cb3);
		t1.traverse(node);
		CompressReGenerateNames.processNames(names);
		//System.out.println(names);
		//System.out.println(calls);
		t3.traverse(node);
		t2.traverse(node);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompressCompiler compiler = new CompressCompiler();
		String code = compiler.readCodeFromFile(args[0]);
		
		Node node = compiler.compile(code);
		compiler.traverseNodes(node);
		
		String codeout = compiler.toSource();
		
		codeout = codeout.replaceAll("\\r\\n", "");
		codeout = codeout.replaceAll("\\n", "");
		codeout = codeout.replaceAll("\\r", "");
		//System.out.println(codeout);
		codeout = codeout.replaceAll("instanceof", "instanceof ");
		writeToFile(codeout, args[1]);
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
