package torontomu.ca.frontend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map.Entry;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * Helper class for JWT authentication.
 * This class provides methods for creating and verifying JWT tokens.
 */
public class Authenticate {

    private final SignatureAlgorithm signatureAlgorithm;
    private final String secretString;

    /**
     * Constructor for Authenticate class.
     * Initializes the signature algorithm and the secret string used for token signing.
     */
    public Authenticate() {
        this.signatureAlgorithm = SignatureAlgorithm.HS256;
        this.secretString = Encoders.BASE64.encode("mysecuresecurecode".getBytes());
    }

    /**
     * Create a JWT token.
     * @param issuer The issuer of the token.
     * @param subject The subject of the token.
     * @param ttlMillis The expiration time of the token in milliseconds.
     * @return The created JWT token.
     */
    public String createJWT(String issuer, String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signingKey);

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    /**
     * Verify a JWT token.
     * @param jwt The JWT token to verify.
     * @return A map entry containing a boolean indicating the verification result and the username extracted from the token.
     * @throws UnsupportedEncodingException If an unsupported encoding is encountered.
     */
    public Entry<Boolean, String> verify(String jwt) throws UnsupportedEncodingException {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey("mysecuresecurecode")
                    .build()
                    .parseClaimsJws(jwt);

            String username = jws.getBody().getSubject();
            return new AbstractMap.SimpleEntry<>(true, username);
        } catch (JwtException ex) {
            System.out.println("Invalid JWT: " + ex.getMessage());
            return new AbstractMap.SimpleEntry<>(false, "");
        }
    }
}
