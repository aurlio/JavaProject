package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

public class CompressReGenerateNames {
	private static List<String> genNames(int size) {
		List<String> ret = new ArrayList<String>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Integer rnd = r.nextInt(Integer.MAX_VALUE);
			String newName = DatatypeConverter
					.printBase64Binary(Integer.toHexString(rnd).getBytes())
					.replaceAll("=", "").replaceAll("/", "").replace("+", "");
			if (newName.charAt(0) >= '0' && newName.charAt(0) <= '9') {
				newName = "_" + newName;
			}
			while (ret.contains(newName)) {
				newName += "x";
			}
			ret.add(newName);
		}

		return ret;
	}

	public static void processNames(Map<String, String> names) {
		int size = names.size();
		List<String> newNames = genNames(size);
		int i = 0;
		for (String key : names.keySet()) {
			names.put(key, newNames.get(i));
			i++;
		}
	}
}
