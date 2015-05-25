package common;

import java.util.Random;

import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class StringObf {

	/**
	 * 字符串节点加解密函数索引,js里的解密函数不能含有字符串
	 */
	int[] cryptoIndexs = new int[] { 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			14, 15, 16, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34,
			35, 36, 37, 38, 39, 40, 41, 42, 45, 46, 47, 48 };
	static final String cryptoJsFunName = "crypto";
	static final String decodeJsFunName = "base64DecodeToBytes";
	static final String bytesToStrJsFunName = "bytesToAsciiStr";

	int[] selCryptoIndexs = null;
	Random rand = new Random();

	public Boolean init() {
		/**
		 * 随机选择6套加密算法
		 */
		int cnt = cryptoIndexs.length;
		selCryptoIndexs = new int[cnt];
		for (int i = 0; i < cnt; i++) {
			selCryptoIndexs[i] = cryptoIndexs[Math.abs(rand.nextInt())%cnt];
		}

		return true;
	}

	public boolean strNodeObf(Node strNode, Node parent) {
		if (strNode.getType() != Token.STRING
				|| parent.getType() == Token.GETPROP
				|| parent.getType() == Token.REGEXP) {
			return false;
		}
		if (strNode.getString() != null && strNode.getString().length() == 0) {
			return false;
		}
		
		int index = selCryptoIndexs[rand.nextInt(selCryptoIndexs.length - 1)];

		Node decodeFunNameNode = IR.name(decodeJsFunName);
		Node paramNode = IR.string(encStr(strNode.getString(), index));
		Node decodeFunNode = IR.call(decodeFunNameNode, paramNode);

		String name = cryptoJsFunName + index;
		/*if(CheckCycle.hasFunctionRecursiveCall(strNode, name)) {
			System.out.println("strNode cycle check hit.");
			return false;
		}*/
		
		Node decFunNameNode = IR.name(name);
		Node decFunNode = IR.call(decFunNameNode, decodeFunNode);

		Node bytesToStrFunNameNode = IR.name(bytesToStrJsFunName);
		Node bytesToStrFunNode = IR.call(bytesToStrFunNameNode, decFunNode);

		parent.replaceChild(strNode, bytesToStrFunNode);
		return true;
	}

	private String encStr(String str, int index) {
		byte[] data = str.getBytes();
		try {
			EncryptAction.enc(data, index);
		} catch (ActionParseException e) {
			throw new RuntimeException("encrypt string exception", e);
		}

		return javax.xml.bind.DatatypeConverter.printBase64Binary(data);
	}
}
