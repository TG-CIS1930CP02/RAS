package co.edu.javeriana.RAS.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KeyStore {
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private static final Logger log = LoggerFactory.getLogger(KeyStore.class);
	
	
	public KeyStore(){
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();
			
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
			
			log.info("Private key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
			log.info("Public key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}


	public PublicKey getPublicKey() {
		return publicKey;
	}


	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}


	public PrivateKey getPrivateKey() {
		return privateKey;
	}


	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
