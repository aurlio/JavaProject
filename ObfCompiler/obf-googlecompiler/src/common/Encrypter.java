package common;

public class Encrypter {
	private static final String NULL_CODE = "";
	private static final String[] KEY_CODE_MAP = {
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 0 ~ 7
			"Backspace",
			"Tab",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			"Enter",
			NULL_CODE,
			NULL_CODE, // 8 ~ 15
			"Shift",
			"Ctrl",
			"Alt",
			"Pause",
			"CapsLock",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 16 ~ 23
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			"Esc",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 24 ~ 31
			"Spacebar",
			"PageUp",
			"PageDown",
			"End",
			"Home",
			"ArrowLeft",
			"ArrowUp",
			"ArrowRight", // 32 ~ 39
			"ArrowDown",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			"PrintScrn",
			"Insert",
			"Delete",
			NULL_CODE, // 40 ~ 47
			"0",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7", // 48 ~ 55
			"8",
			"9",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 56 ~ 63
			NULL_CODE,
			"A",
			"B",
			"C",
			"D",
			"E",
			"F",
			"G", // 64 ~ 71
			"H",
			"I",
			"J",
			"K",
			"L",
			"M",
			"N",
			"O", // 72 ~ 79
			"P",
			"Q",
			"R",
			"S",
			"T",
			"U",
			"V",
			"W", // 80 ~ 87
			"X",
			"Y",
			"Z",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 88 ~ 95
			"(NumPad)0",
			"(NumPad)1",
			"(NumPad)2",
			"(NumPad)3",
			"(NumPad)4",
			"(NumPad)5",
			"(NumPad)6",
			"(NumPad)7", // 96 ~ 103
			"(NumPad)8",
			"(NumPad)9",
			"(NumPad)*",
			"(NumPad)+",
			NULL_CODE,
			"(NumPad)-",
			"(NumPad).",
			"(NumPad)/", // 104 ~ 111
			"F1",
			"F2",
			"F3",
			"F4",
			"F5",
			"F6",
			"F7",
			"F8", // 112 ~ 119
			"F9",
			"F10",
			"F11",
			"F12",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 120 ~ 127
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 128 ~ 135
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 136 ~ 143
			"NumLock",
			"ScrollLock",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 144 ~ 151
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 152 ~ 159
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 160 ~ 167
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 168 ~ 175
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 176 ~ 183
			NULL_CODE,
			NULL_CODE,
			";",
			"=",
			",",
			"-",
			".",
			"/", // 184 ~ 191
			"`",
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 192 ~ 199
			NULL_CODE, NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 200 ~ 207
			NULL_CODE, NULL_CODE, NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 208 ~ 215
			NULL_CODE, NULL_CODE, NULL_CODE, "[",
			"\\",
			"]",
			"\"",
			NULL_CODE, // 216 ~ 223
			NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 224 ~ 231
			NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE,
			NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 232 ~ 239
			NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE,
			NULL_CODE,
			NULL_CODE, // 240 ~ 247
			NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE, NULL_CODE,
			NULL_CODE, NULL_CODE // 248 ~ 255
	};

	private static final String N = "";
	private static final String[] SHIFT_CODE_MAP = { N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, ")", "!", "@",
			"#", "$", "%", "^", "&", "*", "(", N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, ":", "+", "<", "_", ">", "?", "~", N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			"{", "|", "}", "\"", N, N, N, N, N, N, N, N, N, N, N, N, N, N, N,
			N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N, N };

	private static final int CTRL_KEY_FLAG = 1;
	private static final int ALT_KEY_FLAG = 2;
	private static final int SHIFT_KEY_FLAG = 4;

	private static final String KEY_CTRL = "Ctrl";
	private static final String KEY_ALT = "Alt";
	private static final String KEY_SHIFT = "Shift";
	private static final String STR_CONCATOR = "+";

	private static final int[] BIT_MASK = { 0x00, 0x01, 0x03, 0x07, 0x0F, 0x1F,
			0x3F, 0x7F, 0xFF };

	public String keyCode2Str(int keyCode, int keyControl) {
		StringBuilder keyMsg = new StringBuilder();
		if ((keyControl & CTRL_KEY_FLAG) > 0) {
			keyMsg.append(KEY_CTRL);
		}
		if ((keyControl & ALT_KEY_FLAG) > 0) {
			if (keyMsg.length() != 0) {
				keyMsg.append(STR_CONCATOR);
			}
			keyMsg.append(KEY_ALT);
		}
		if ((keyControl & SHIFT_KEY_FLAG) > 0) {
			if (keyMsg.length() != 0) {
				keyMsg.append(STR_CONCATOR);
			}
			keyMsg.append(KEY_SHIFT);
		}
		if (keyMsg.length() != 0) {
			keyMsg.append(STR_CONCATOR);
		}
		if (!NULL_CODE.equals(KEY_CODE_MAP[keyCode])) {
			if (!N.equals(SHIFT_CODE_MAP[keyCode])
					&& (keyControl & SHIFT_KEY_FLAG) > 0) {
				keyMsg.append(SHIFT_CODE_MAP[keyCode]);
			} else {
				keyMsg.append(KEY_CODE_MAP[keyCode]);
			}
		} else {
			keyMsg.append("KeyCode:").append(keyCode);
		}
		return keyMsg.toString();
	}

	public boolean checksum(String data) {
		try {
			int ck = Integer.parseInt(data.substring(0, 3));
			String dat = data.substring(3);
			int sum = ~ck & 0xFF;
			for (int i = 0; i < dat.length(); i += 4) {
				sum = (sum + ~(dat.charAt(i) & 0xFF)) & 0xFF;
			}
			return sum == 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public byte[] decrypt_1(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | data[i] << 4);
		}
		return data;
	}

	public byte[] encrypt_1(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | data[i] << 4);
		}
		return data;
	}

	public byte[] decrypt_2(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[i % key.length]);
		}
		return data;
	}

	public byte[] encrypt_2(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[i % key.length]);
		}
		return data;
	}

	public byte[] decrypt_3(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
		}
		return data;
	}

	public byte[] encrypt_3(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] + 68);
		}
		return data;
	}

	public byte[] decrypt_4(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
		return data;
	}

	public byte[] encrypt_4(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i]--;
			data[i] = (byte) ((data[i] >> tmp & BIT_MASK[8 - tmp] | data[i] << (8 - tmp)));
		}
		return data;
	}

	public byte[] decrypt_5(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] encrypt_5(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] decrypt_6(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key;
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_6(byte[] data) {
		int key = 102;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = data[i];
		}
		return data;
	}

	public byte[] decrypt_7(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] encrypt_7(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_8(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 234) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] encrypt_8(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 234) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] decrypt_9(byte[] data) {
		int key = 202;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i];
		}
		return data;
	}

	public byte[] encrypt_9(byte[] data) {
		int key = 202;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_10(byte[] data) {
		int key = 203, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_10(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i];
		}
		return data;
	}

	public byte[] decrypt_11(byte[] data) {
		int key = 212, tmp_a, tmp_b;
		for (int i = 0; i < data.length; i++) {
			tmp_a = 0;
			for (int j = 0; j < 8; j++) {
				tmp_a |= key & (0x01 << j);
				tmp_b = ((key & 0x20) << 2) ^ ((key & 0x04) << 5);
				key = tmp_b | (key >> 1) & 0x7F;
			}
			data[i] = (byte) (data[i] ^ tmp_a);
		}
		return data;
	}

	public byte[] encrypt_11(byte[] data) {
		int key = 212, tmp_a, tmp_b;
		for (int i = 0; i < data.length; i++) {
			tmp_a = 0;
			for (int j = 0; j < 8; j++) {
				tmp_a |= key & (0x01 << j);
				tmp_b = ((key & 0x20) << 2) ^ ((key & 0x04) << 5);
				key = tmp_b | (key >> 1) & 0x7F;
			}
			data[i] = (byte) (data[i] ^ tmp_a);
		}
		return data;
	}

	public byte[] decrypt_12(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] & 0xF0) | ((data[i] >> 4 & 0x0F) ^ data[i]) & 0x0F;
			data[i] = (byte) ((tmp >> 1 & 0x55) | (tmp << 1 & 0xAA));
		}
		return data;
	}

	public byte[] encrypt_12(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] & 0xF0) | ((data[i] >> 4 & 0x0F) ^ data[i]) & 0x0F;
			data[i] = (byte) ((tmp >> 1 & 0x55) | (tmp << 1 & 0xAA));
		}
		return data;
	}

	public byte[] decrypt_13(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 2);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
		return data;
	}

	public byte[] encrypt_13(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | (data[i] << 4));
			data[i] += 2;
		}
		return data;
	}

	public byte[] decrypt_14(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 3);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
		return data;
	}

	public byte[] encrypt_14(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | (data[i] << 4));
			data[i] += 3;
		}
		return data;
	}

	public byte[] decrypt_15(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 4);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
		return data;
	}

	public byte[] encrypt_15(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | (data[i] << 4));
			data[i] += 4;
		}
		return data;
	}

	public byte[] decrypt_16(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 5);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
		return data;
	}

	public byte[] encrypt_16(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | (data[i] << 4));
			data[i] += 5;
		}
		return data;
	}

	public byte[] decrypt_17(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] encrypt_17(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] decrypt_18(byte[] data) {
		byte[] key = "&1t,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 2) % key.length]);
		}
		return data;
	}

	public byte[] encrypt_18(byte[] data) {
		byte[] key = "&1t,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 2) % key.length]);
		}
		return data;
	}

	public byte[] decrypt_19(byte[] data) {
		byte[] key = "&it,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] encrypt_19(byte[] data) {
		byte[] key = "&it,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] decrypt_20(byte[] data) {
		byte[] key = "&1t;script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] encrypt_20(byte[] data) {
		byte[] key = "&1t;script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
		return data;
	}

	public byte[] decrypt_21(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 67);
		}
		return data;
	}

	public byte[] encrypt_21(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] + 67);
		}
		return data;
	}

	public byte[] decrypt_22(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
			if (data[i] < 0) {
				data[i] -= 1;
			}
		}
		return data;
	}

	public byte[] encrypt_22(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 0) {
				data[i] += 1;
			}
			data[i] = (byte) (data[i] + 68);
		}
		return data;
	}

	public byte[] decrypt_23(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 67);
			if (data[i] < 0) {
				data[i] -= 1;
			}
		}
		return data;
	}

	public byte[] encrypt_23(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 0) {
				data[i] += 1;
			}
			data[i] = (byte) (data[i] + 67);

		}
		return data;
	}

	public byte[] decrypt_24(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
			if (data[i] < 0) {
				data[i] += 1;
			}
		}
		return data;
	}

	public byte[] encrypt_24(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 1) {
				data[i] -= 1;
			}
			data[i] = (byte) (data[i] + 68);

		}
		return data;
	}

	public byte[] decrypt_25(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 2);
		}
		return data;
	}

	public byte[] encrypt_25(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i] -= 2;
			data[i] = (byte) (data[i] >> tmp & BIT_MASK[8 - tmp] | data[i] << (8 - tmp));
		}
		return data;
	}

	public byte[] decrypt_26(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8 + 1;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
		return data;
	}

	public byte[] encrypt_26(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8 + 1;
			data[i]--;
			data[i] = (byte) (data[i] >> tmp & BIT_MASK[8 - tmp] | data[i] << (8 - tmp));
		}
		return data;
	}

	public byte[] decrypt_27(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			data[i] -= 1;
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
		return data;
	}

	public byte[] encrypt_27(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i]--;
			data[i] = (byte) (data[i] >> tmp & BIT_MASK[8 - tmp] | data[i] << (8 - tmp));
			data[i]++;
		}
		return data;
	}

	public byte[] decrypt_28(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			data[i] -= 2;
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
		return data;
	}

	public byte[] encrypt_28(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i]--;
			data[i] = (byte) (data[i] >> tmp & BIT_MASK[8 - tmp] | data[i] << (8 - tmp));
			data[i] += 2;
		}
		return data;
	}

	public byte[] decrypt_29(byte[] data) {
		int key = 53;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
		return data;
	}

	public byte[] encrypt_29(byte[] data) {
		int key = 53;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
		return data;
	}

	public byte[] decrypt_30(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
		return data;
	}

	public byte[] encrypt_30(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
		return data;
	}

	public byte[] decrypt_31(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 1;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] encrypt_31(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 1;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] decrypt_32(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 2;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] encrypt_32(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 2;
			key = (key * i) % 256;
		}
		return data;
	}

	public byte[] decrypt_33(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 1;
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_33(byte[] data) {
		int key = 103;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = data[i] + 1;
		}
		return data;
	}

	public byte[] decrypt_34(byte[] data) {
		int key = 103, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 1;
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_34(byte[] data) {
		int key = 104;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = data[i] + 1;
		}
		return data;
	}

	public byte[] decrypt_35(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 2;
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_35(byte[] data) {
		int key = 104;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = data[i] + 2;
		}
		return data;
	}

	public byte[] decrypt_36(byte[] data) {
		int key = 103, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 2;
			key = tmp;
		}
		return data;
	}

	public byte[] encrypt_36(byte[] data) {
		int key = 105;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = data[i] + 2;
		}
		return data;
	}

	public byte[] decrypt_37(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] encrypt_37(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_38(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((((key << 4) ^ key) & 0xF0) | (key >> 4))) + 1;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] encrypt_38(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((((key << 4) ^ key) & 0xF0) | (key >> 4))) + 1;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_39(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] encrypt_39(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_40(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key + 1 >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] encrypt_40(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key + 1 >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
		return data;
	}

	public byte[] decrypt_41(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 235) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] encrypt_41(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 235) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] decrypt_42(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 195) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] encrypt_42(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 195) >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] decrypt_43(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 234) + 1 >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] decrypt_44(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 243) + 1 >> 4) & 0x0F ^ data[i]);
		}
		return data;
	}

	public byte[] decrypt_45(byte[] data) {
		int key = 198, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (tmp ^ key);
			key = tmp - 5;
		}
		return data;
	}

	public byte[] encrypt_45(byte[] data) {
		int key = 198;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i] - 5;
		}
		return data;
	}

	public byte[] decrypt_46(byte[] data) {
		int key = 205, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp - 1;
		}
		return data;
	}

	public byte[] encrypt_46(byte[] data) {
		int key = 205;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i] - 1;
		}
		return data;
	}

	public byte[] decrypt_47(byte[] data) {
		int key = 205, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp + 1;
		}
		return data;
	}

	public byte[] encrypt_47(byte[] data) {
		int key = 205;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i] + 1;
		}
		return data;
	}

	public byte[] decrypt_48(byte[] data) {
		int key = 203, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp - 3;
		}
		return data;
	}

	public byte[] encrypt_48(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i] - 3;
		}
		return data;
	}

}
