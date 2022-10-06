package simple.plugin

import spock.lang.Specification

class RSATests extends Specification {

	void "Encrypt"() {
		given:
		GroovySpy(ApplicationConfig, global: true)
		1 * ApplicationConfig.getPublicKey() >> "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzLeWnkIP6KubeLR2uv7MHGsCvMeXNSdyQoJxWPcBTPJQwqwNIcBCuQHq44+crnJ1qLzSxt3jHq6fVd5vdL9rAXb44OO4eR0z+Y2EjczPhqOrWW+xAz4IiIs5tlHiE+vvSiXdWoYlRDMbWKawm8m4eDvqZzRdlKw2IOOXwWVqAP0f8Axr+ssu63DPkbmb9XYQXTsxHtzaTaGURgT2aM/Ma8crvwwlsahChARXDfp59IuyjH43M74xQ072W75NP8tXf94q3eNkXBsaOqttxu0hf35EW/p8oWuf+WYOJr8m4lC0drTeqGDVZdSJ6pSjI+Co/R5+JADQ15qw68qTMEw49QIDAQAB"
		byte[] crypt = RSA.encrypt("Simple string here")

		expect:
		crypt != null
	}

}
