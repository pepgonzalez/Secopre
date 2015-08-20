package ideasw.secopre.utils.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {

	public static String encrypByBCrypt(String chain, int strength) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
		return passwordEncoder.encode(chain);
	}
	public static String encrypByBCrypt(String chain) {
		return encrypByBCrypt(chain, 10);
	}
}
