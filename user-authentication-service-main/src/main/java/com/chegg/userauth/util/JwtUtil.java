package com.chegg.userauth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Date;

public class JwtUtil {

    // The secret key. This should be in a property file NOT under source
    // control and not hard coded in real life. We're putting it here for
    // simplicity.
    private static final String jwtSecret = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

    //Sample method to construct a JWT
    public static String createJWT(String subject, long ttlMillis) {
        // The JWT signature algorithm
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // The current time
        long nowMillis = System.currentTimeMillis();

        // Sign the JWT with the ApiKey secret
        byte[] apiKeySecretBytes = jwtSecret.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Build the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .signWith(signingKey, signatureAlgorithm)
        .claim("UserName",subject);
        return builder.compact();
    }


}