package simple.plugin


import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
class RSATests extends Specification {

    void "Encrypt"() {
        given:
        byte[] crypt = RSA.encrypt("Simple string here")

        expect:
        crypt != null
    }

}