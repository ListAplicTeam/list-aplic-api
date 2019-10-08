package br.com.ufg.listaplic.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);

    private EncryptUtil() {
    }

    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
            return hash.toString(16);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }
}
