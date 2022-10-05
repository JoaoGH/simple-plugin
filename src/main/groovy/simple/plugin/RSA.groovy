package simple.plugin

import javax.crypto.Cipher
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec

class RSA {

	static final String ALGORITHM = "RSA"

	/**
	 * Gera um par de chaves assimétricas no padrão RSA usando 2048 bytes.
	 * Retorna um mapa com os valores das chaves em base64.
	 *
	 * @return Map<String, String>
	 * */
	static Map generateKey() {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM)
		keyGen.initialize(2048)
		KeyPair keyPair = keyGen.generateKeyPair()

		PublicKey publicKey = keyPair.getPublic()
		PrivateKey privateKey = keyPair.getPrivate()

		String pk = Base64.getEncoder().encodeToString(publicKey.getEncoded())
		String pr = Base64.getEncoder().encodeToString(privateKey.getEncoded())

		return [publicKey: pk, privateKey: pr]
	}

	/**
	 * Criptografa um texto puro usando a chave pública.
	 *
	 * @param text : texto original
	 * @return Valor criptografado em bytes.
	 * */
	static byte[] encrypt(String text) {
		byte[] pkByte = Base64.getDecoder().decode(ApplicationConfig.getPublicKey())
		X509EncodedKeySpec spec = new X509EncodedKeySpec(pkByte)
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM)
		PublicKey publicKey = factory.generatePublic(spec)

		Cipher cipher = Cipher.getInstance(ALGORITHM)
		cipher.init(Cipher.ENCRYPT_MODE, publicKey)

		byte[] cipherText = cipher.doFinal(text.getBytes())

		return cipherText
	}

	/**
	 * Descriptografa um texto, em array de bytes, usando a chave private.
	 *
	 * @param text : texto criptografado
	 * @return Texto puro.
	 * */
	static String decrypt(byte[] text) {
		byte[] pkByte = Base64.getDecoder().decode(ApplicationConfig.getPrivateKey())
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(pkByte)
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM)
		PrivateKey privateKey = factory.generatePrivate(spec)

		Cipher cipher = Cipher.getInstance(ALGORITHM)
		cipher.init(Cipher.DECRYPT_MODE, privateKey)

		byte[] plainText = cipher.doFinal(text)

		return new String(plainText)
	}

}
