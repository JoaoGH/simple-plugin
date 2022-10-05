package simple.plugin

import spock.lang.Specification

class RSATests extends Specification {

	void "Encrypt"() {
		given:
		byte[] crypt = RSA.encrypt("Simple string here")

		expect:
		crypt != null
	}

}
