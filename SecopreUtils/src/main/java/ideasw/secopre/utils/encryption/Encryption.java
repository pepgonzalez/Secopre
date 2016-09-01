package ideasw.secopre.utils.encryption;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {

	public static String encrypByBCrypt(String chain, int strength) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
				strength);
		return passwordEncoder.encode(chain);
	}

	public static String encrypByBCrypt(String chain) {
		return encrypByBCrypt(chain, 10);
	}

	private static final char[] alphaNumericChars = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9' };
	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 11;

	public static synchronized String generateDynamicCode() {
		Random random = new Random();
		int lengthOfpassword = MIN_LENGTH
				+ random.nextInt((MAX_LENGTH - MIN_LENGTH) + 1);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lengthOfpassword; i++) {
			sb.append(alphaNumericChars[random
					.nextInt(alphaNumericChars.length)]);
		}
		return sb.toString();

	}
}
