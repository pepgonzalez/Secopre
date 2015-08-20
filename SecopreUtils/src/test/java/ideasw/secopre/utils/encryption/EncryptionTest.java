package ideasw.secopre.utils.encryption;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionTest {
	static final Logger LOG = LoggerFactory.getLogger(EncryptionTest.class);
	@Test
	public void test(){
		String pass = "123456";
		LOG.debug("Encriptando cadena ===>" + pass);
		String encrypt = Encryption.encrypByBCrypt(pass);
		LOG.debug("Cadena encriptada ===>" + encrypt);
	}
}
