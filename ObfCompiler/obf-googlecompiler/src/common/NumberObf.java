package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

public class NumberObf {

	private int getRandom() {
		Random rd = new Random();
		int rt = rd.nextInt();
		if (rt < 0) {
			return -rt;
		}
		return rt;
	}

	public Node func_arith_make(long max, Node me) {
		List<Integer> prims = new ArrayList<Integer>();
		prims.add(2);
		for (Integer i = 3; i < max; i += 2) {
			boolean flag = true;
			for (Integer j : prims) {
				if (i % j == 0) {
					flag = false;
				}
			}
			if (flag == true) {
				prims.add(i);
			}
		}

		Random rd = new Random();
		long a = rd.nextLong();
		long b = rd.nextLong();
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;
		a = a % prims.size();
		b = b % prims.size();

		double value = me.getDouble();
		if (value == (int) value && value > 0 && value < 255
				&& value != 0x0F && value != 0xF0) {

			String make_n = "obf_make_gcd_n";
			int total = 4;
			switch (((int) a) % total) {
			case 0:
				make_n = "obf_make_gcd_n";
				break;
			case 1:
				//make_n = "obf_make_sin_n";
				break;
			case 2:
				//make_n = "obf_make_sincos_n";
				break;
			case 3:
				//make_n = "obf_make_sincos1_n";
				break;
			}
			if (CheckCycle.hasFunctionRecursiveCall(me, make_n)) {
				return null;
			}
			me.setType(Token.CALL);
			Node func_name = Node.newString(make_n);
			Node param1 = Node.newNumber(prims.get((int) a));
			Node param2 = Node.newNumber(prims.get((int) b));
			Node param3 = Node.newNumber(value);
			func_name.setType(Token.NAME);
			me.addChildrenToFront(func_name);
			me.addChildAfter(param1, func_name);
			me.addChildAfter(param2, param1);
			me.addChildAfter(param3, param2);
			return me;
		}
		return null;
	}





	

	public Node func_mod_make(Node me) {
		Double value = me.getDouble();
		if (value == value.intValue() && value >= 0) {
			int r1 = getRandom() % 31;
			int r2 = getRandom() % 11 + 1 + value.intValue();
			int total = r1 * r2 + value.intValue();
			Node param1 = Node.newNumber(total);
			Node param2 = Node.newNumber(r2);
			me.setType(Token.MOD);

			me.addChildrenToFront(param1);
			me.addChildAfter(param2, param1);

			return me;
		}
		return null;
	}

	public Node func_crypto_make(int i, Node me) {
		Double value = me.getDouble();
		byte bvalue = (byte) (value.intValue() & 0xFF);
		CryptoHelper cHelper = new CryptoHelper();
		
		if (value == value.intValue() && value > 0 && value < 255
				&& value != 0x0F && value != 0xF0) {
			int r = getRandom();
			int num = 7;
			if (r % num == 0) {
				i = i % 126;
				i += 1;
				byte key = (byte) (i & 0xFF);
				byte newValue = (byte) (bvalue ^ key);
				Node func_name = Node.newString("obf_dec_1");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_1")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				Node param2 = Node.newNumber(key);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				me.addChildAfter(param2, param1);
				return me;
			} else if (r % num == 1) {
				byte newValue = cHelper.obf_enc_2(bvalue);
				Node func_name = Node.newString("obf_dec_2");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_2")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 2) {
				byte newValue = cHelper.obf_enc_3(bvalue);
				Node func_name = Node.newString("obf_dec_3");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_3")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 3) {
				byte newValue = cHelper.obf_enc_4(bvalue);
				Node func_name = Node.newString("obf_dec_4");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_4")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 4) {
				byte newValue = cHelper.obf_enc_5(bvalue);
				Node func_name = Node.newString("obf_dec_5");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_5")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 5) {
				byte newValue = cHelper.obf_enc_6(bvalue);
				Node func_name = Node.newString("obf_dec_6");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_6")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 6) {
				byte newValue = cHelper.obf_enc_7(bvalue);
				Node func_name = Node.newString("obf_dec_7");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_7")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 7) {
				byte newValue = cHelper.obf_enc_8(bvalue);
				Node func_name = Node.newString("obf_dec_8");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_8")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 8) {
				byte newValue = cHelper.obf_enc_9(bvalue);
				Node func_name = Node.newString("obf_dec_9");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_9")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 9) {
				byte newValue = cHelper.obf_enc_10(bvalue);
				Node func_name = Node.newString("obf_dec_10");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_10")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			} else if (r % num == 10) {
				byte newValue = cHelper.obf_enc_11(bvalue);
				Node func_name = Node.newString("obf_dec_11");
				if (CheckCycle.hasFunctionRecursiveCall(me, "obf_dec_11")) {
					return func_mod_make(me);
				}
				me.setType(Token.CALL);
				func_name.setType(Token.NAME);
				Node param1 = Node.newNumber(newValue);
				me.addChildrenToFront(func_name);
				me.addChildAfter(param1, func_name);
				return me;
			}
		}
		else if (value.intValue()==0 || value.intValue()>=255){
			return func_mod_make(me);
		}

		return null;
	}
}
