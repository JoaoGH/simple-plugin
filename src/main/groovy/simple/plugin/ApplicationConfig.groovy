package simple.plugin

import grails.core.GrailsApplication
import grails.util.Holders

class ApplicationConfig {

	protected static GrailsApplication getGrailsApplication() {
		return Holders.getGrailsApplication()
	}

	static String getPublicKey() {
		return getGrailsApplication().getConfig().getProperty("rsa.public", String)
	}

	static String getPrivateKey() {
		return getGrailsApplication().getConfig().getProperty("rsa.private", String)
	}

}
