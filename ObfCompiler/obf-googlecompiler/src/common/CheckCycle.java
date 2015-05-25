package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import app.ObfCompiler;

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class CheckCycle {
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

	public static Object clone(Object src) throws IOException, ClassNotFoundException {
		if (src == null)
			return src;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(src);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}

	public static boolean hasFunctionRecursiveCall(Node me, String name) {
		Node tmp = me;
		while (tmp.getParent() != null
				&& tmp.getParent().getType() != Token.SCRIPT) {
			Node parent = tmp.getParent();
			if (parent.getType() == Token.FUNCTION) {
				String meFunctionName = parent.getChildAtIndex(0).getString();
				if (meFunctionName.isEmpty()) {
					meFunctionName = parent.getParent().getType() == Token.NAME ? parent
							.getParent().getString() : "";
				}
				if (meFunctionName.isEmpty()) {
					return false;
				}
				if (stringEquals(meFunctionName, "getObject")) {
					return true;
				}
				// a call b, make b call a error;
				if (hasFunctionCall(name, meFunctionName)) {
					return true;
				} else {
					return false;
				}
			}
			tmp = parent;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private static boolean hasFunctionCall(String name, String meFunctionName) {
		// meFunctionName 中增加call name后，看是否会形成圈，图算法
		boolean rt = false;
		if (stringEquals(name, meFunctionName)) {
			return true;
		}
		ArrayList<String> oldCallTo = ObfCompiler.mapGlobalFunctions
				.get(meFunctionName);

		//System.out.println((oldCallTo == null ? "0" : oldCallTo.size()) + "|" + meFunctionName);

		ArrayList<String> newCallTo = null;
		try {
			newCallTo = (ArrayList<String>) clone(oldCallTo);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newCallTo.add(name);
		ObfCompiler.mapGlobalFunctions.put(meFunctionName, newCallTo);
		if (checkCycle()) {
			rt = true;
			ObfCompiler.mapGlobalFunctions.put(meFunctionName, oldCallTo);
		}
		//System.out.println(ObfCompiler.mapGlobalFunctions);
		return rt;
	}

	@SuppressWarnings("unchecked")
	private static boolean checkCycle() {
		HashMap<String, ArrayList<String>> newMap = null;
		try {
			newMap = (HashMap<String, ArrayList<String>>) clone(ObfCompiler.mapGlobalFunctions);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (newMap.size() < 1)
			return false;
		// 拓扑排序，判断是否存在环
		// E<=N*N
		int size = newMap.size() * newMap.size();
		for (int i = 0; i < size; i++) {
			Iterator<Map.Entry<String, ArrayList<String>>> it = newMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, ArrayList<String>> entry = it.next();
				String key = entry.getKey();
				if (newMap.get(key) != null
						&& newMap.get(key).contains(key) && !newMap.get(key).contains("eval")) {
					return true;
				}
				if (newMap.get(key) != null && newMap.get(key).size() == 0) {
					it.remove();
					// del list of it;
					for (String func : newMap.keySet()) {
						if (newMap.get(func) != null
								&& newMap.get(func).contains(key)) {
							newMap.get(func).remove(key);
						}
					}

				}
			}
		}

		Iterator<Map.Entry<String, ArrayList<String>>> it = newMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<String>> entry = it.next();
			String key = entry.getKey();
			if (newMap.get(key) != null && newMap.get(key).contains("eval")) {
				while(newMap.get(key).contains("eval")) {
					newMap.get(key).remove("eval");
				}
			}
		}
		if (newMap.size() > 0) {
			if (newMap.size() == 1) {
				for (String func : newMap.keySet()) {
					// {"varxxx":[]}
					if (newMap.get(func).size() == 0) {
						return false;
					}
					if (stringEquals(newMap.get(func).get(0), func)){
						return false;
					}
				}

			}
			return true;
		}

		return false;
	}
}
