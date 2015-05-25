package common;

class CryptoHelper {

	private final int[] BIT_MASK = { 0x00, 0x01, 0x03, 0x07, 0x0F, 0x1F,
			0x3F, 0x7F, 0xFF };

	protected void smallDecrypt(byte[] data, byte key) {
		byte tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (tmp ^ key);
			key = tmp;
		}
	}

	// ByteSwap H4L4=>L4H4
	protected void decrypt_1(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) ((data[i] >> 4) & 0x0F | data[i] << 4);
		}
	}

	// xor with key="&lt,script&gt"
	protected void decrypt_2(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[i % key.length]);
		}
	}

	// rot <<< 68
	protected void decrypt_3(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
		}
	}

	// ByteSwap L(8-index%8)H(index%8)
	protected void decrypt_4(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
	}

	// XOR, key ass index
	protected void decrypt_5(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256;
		}
	}

	// XOR, key = data[index]
	protected void decrypt_6(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key;
			key = tmp;
		}
	}

	// XOR, key ass iter keyHL->keyLH
	protected void decrypt_7(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
	}

	//
	protected void decrypt_8(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 234) >> 4) & 0x0F ^ data[i]);
		}
	}

	//
	protected void decrypt_9(byte[] data) {
		int key = 202;
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key);
			key = data[i];
		}
	}

	//
	protected void decrypt_10(byte[] data) {
		int key = 203, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp;
		}
	}

	//
	protected void decrypt_11(byte[] data) {
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
	}

	//
	protected void decrypt_12(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] & 0xF0) | ((data[i] >> 4 & 0x0F) ^ data[i])
					& 0x0F;
			data[i] = (byte) ((tmp >> 1 & 0x55) | (tmp << 1 & 0xAA));
		}
	}

	//
	protected void decrypt_13(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 2);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
	}

	//
	protected void decrypt_14(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 3);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
	}

	//
	protected void decrypt_15(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 4);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
	}

	//
	protected void decrypt_16(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = (data[i] - 5);
			data[i] = (byte) ((tmp >> 4) & 0x0F | (tmp << 4));
		}
	}

	//
	protected void decrypt_17(byte[] data) {
		byte[] key = "&lt,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
	}

	protected void decrypt_18(byte[] data) {
		byte[] key = "&1t,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 2) % key.length]);
		}
	}

	protected void decrypt_19(byte[] data) {
		byte[] key = "&it,script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
	}

	protected void decrypt_20(byte[] data) {
		byte[] key = "&1t;script&gt".getBytes();
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] ^ key[(i + 1) % key.length]);
		}
	}

	protected void decrypt_21(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 67);
		}
	}

	protected void decrypt_22(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
			if (data[i] < 0) {
				data[i] -= 1;
			}
		}
	}

	protected void decrypt_23(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 67);
			if (data[i] < 0) {
				data[i] -= 1;
			}
		}
	}

	protected void decrypt_24(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (data[i] - 68);
			if (data[i] < 0) {
				data[i] += 1;
			}
		}
	}

	protected void decrypt_25(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 2);
		}
	}

	protected void decrypt_26(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = i % 8 + 1;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
	}

	protected void decrypt_27(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			data[i] -= 1;
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
	}

	protected void decrypt_28(byte[] data) {
		int tmp;
		for (int i = 0; i < data.length; i++) {
			data[i] -= 2;
			tmp = i % 8;
			data[i] = (byte) ((data[i] >> (8 - tmp) & BIT_MASK[tmp] | data[i] << tmp) + 1);
		}
	}

	protected void decrypt_29(byte[] data) {
		int key = 53;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
	}

	protected void decrypt_30(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key;
			key = (key * i) % 256 + 1;
		}
	}

	protected void decrypt_31(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 1;
			key = (key * i) % 256;
		}
	}

	protected void decrypt_32(byte[] data) {
		int key = 54;
		for (int i = 0; i < data.length; i++) {
			data[i] ^= key + 2;
			key = (key * i) % 256;
		}
	}

	protected void decrypt_33(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 1;
			key = tmp;
		}
	}

	protected void decrypt_34(byte[] data) {
		int key = 103, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 1;
			key = tmp;
		}
	}

	protected void decrypt_35(byte[] data) {
		int key = 102, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 2;
			key = tmp;
		}
	}

	protected void decrypt_36(byte[] data) {
		int key = 103, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] ^= key + 2;
			key = tmp;
		}
	}

	protected void decrypt_37(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
	}

	protected void decrypt_38(byte[] data) {
		int key = 201;
		for (int i = 0; i < data.length; i++) {
			key = (((((key << 4) ^ key) & 0xF0) | (key >> 4))) + 1;
			data[i] = (byte) (data[i] ^ key);
		}
	}

	protected void decrypt_39(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key + 1) & 0xF0) | (key >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
	}

	protected void decrypt_40(byte[] data) {
		int key = 203;
		for (int i = 0; i < data.length; i++) {
			key = (((key << 4) ^ key) & 0xF0) | (key + 1 >> 4) & 0x0F;
			data[i] = (byte) (data[i] ^ key);
		}
	}

	protected void decrypt_41(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 235) >> 4) & 0x0F ^ data[i]);
		}
	}

	protected void decrypt_42(byte[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (((data[i] ^ 195) >> 4) & 0x0F ^ data[i]);
		}
	}

	protected void decrypt_45(byte[] data) {
		int key = 198, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (tmp ^ key);
			key = tmp - 5;
		}
	}

	protected void decrypt_46(byte[] data) {
		int key = 205, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp - 1;
		}
	}

	protected void decrypt_47(byte[] data) {
		int key = 205, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp + 1;
		}
	}

	protected void decrypt_48(byte[] data) {
		int key = 203, tmp;
		for (int i = 0; i < data.length; i++) {
			tmp = data[i];
			data[i] = (byte) (data[i] ^ key);
			key = tmp - 3;
		}
	}

	public byte obf_enc_1(byte data, byte key) {
		key = (byte) (key & 0xFF);
		data = (byte) (data ^ key);
		return (byte) (data & 0xFF);
	}

	public byte obf_dec_1(byte data, byte key) {
		key = (byte) (key & 0xFF);
		data = (byte) (data ^ key);
		return (byte) (data & 0xFF);
	}

	public byte obf_enc_2(byte data) {
		data = (byte) ((data >> 4) & 0x0F | data << 4);
		return data;
	}

	public byte obf_dec_2(byte data) {
		data = (byte) ((data >> 4) & 0x0F | data << 4);
		return data;
	}

	public byte obf_enc_3(byte data) {
		int tmp = (data & 0xF0) | ((data >> 4 & 0x0F) ^ data) & 0x0F;
		data = (byte) ((tmp >> 1 & 0x55) | (tmp << 1 & 0xAA));
		return data;

	}

	public byte obf_dec_3(byte data) {
		int tmp = (data & 0xF0) | ((data >> 4 & 0x0F) ^ data) & 0x0F;
		data = (byte) ((tmp >> 1 & 0x55) | (tmp << 1 & 0xAA));
		return data;
	}

	public byte obf_enc_4(byte data) {
		byte key = (byte) 0x7E;
		data = (byte) (key ^ data);
		return data;
	}

	public byte obf_dec_4(byte data) {
		byte key = (byte) 0x7E;
		data = (byte) (key ^ data);
		return data;
	}

	public byte obf_enc_5(byte data) {
		byte key = (byte) 120;
		data = (byte) (data ^ key);
		return data;
	}

	public byte obf_dec_5(byte data) {
		byte key = (byte) 120;
		data = (byte) (data ^ key);
		return data;
	}

	public byte obf_enc_6(byte data) {
		byte key = (byte) 35;
		data = (byte) (data - key);
		return data;
	}

	public byte obf_dec_6(byte data) {
		byte key = (byte) 35;
		data = (byte) (data + key);
		return data;
	}

	public byte obf_enc_7(byte data) {
		byte key = (byte) 22;
		data = (byte) (data - key);
		return data;
	}

	public byte obf_dec_7(byte data) {
		byte key = (byte) 22;
		data = (byte) (data + key);
		return data;
	}

	public byte obf_enc_8(byte data) {
		byte key = (byte) 19;
		data = (byte) (data - key);
		return data;
	}

	public byte obf_dec_8(byte data) {
		byte key = (byte) 19;
		data = (byte) (data + key);
		return data;
	}

	public byte obf_enc_9(byte data) {
		byte key = (byte) 17;
		data = (byte) (data - key);
		return data;
	}

	public byte obf_dec_9(byte data) {
		byte key = (byte) 17;
		data = (byte) (data + key);
		return data;
	}

	public byte obf_enc_10(byte data) {
		byte key = (byte) 13;
		data = (byte) (data - key);
		return data;
	}

	public byte obf_dec_10(byte data) {
		byte key = (byte) 13;
		data = (byte) (data + key);
		return data;
	}

	public byte obf_enc_11(byte data) {
		byte key = (byte) 0x09;
		data = (byte) (data ^ key);
		return data;
	}

	public byte obf_dec_11(byte data) {
		byte key = (byte) 0x09;
		data = (byte) (data ^ key);
		return data;
	}
}

