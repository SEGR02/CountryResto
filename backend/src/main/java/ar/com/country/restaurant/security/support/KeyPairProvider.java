package ar.com.country.restaurant.security.support;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public interface KeyPairProvider {

    RSAPublicKey getAccessTokenPublicKey();

    RSAPrivateKey getAccessTokenPrivateKey();

    RSAPublicKey getRefreshTokenPublicKey();

    RSAPrivateKey getRefreshTokenPrivateKey();

}
