import java.math.BigInteger;
import java.util.Random;

public class RSA_algo {
    public static void main(String[] args) {
        // Generate RSA keys
        KeyPair keyPair = generateKeys(512); // Specify the desired key size (in bits)

        // Original message to be encrypted
        BigInteger plaintext = new BigInteger("12345");

        // Encrypt the plaintext using the public key
        BigInteger ciphertext = encrypt(plaintext, keyPair.getPublicKey());

        // Decrypt the ciphertext using the private key
        BigInteger decryptedText = decrypt(ciphertext, keyPair.getPrivateKey());

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    // Generate RSA key pair
    public static KeyPair generateKeys(int keySize) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(keySize / 2, random);
        BigInteger q = BigInteger.probablePrime(keySize / 2, random);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent (commonly 65537)
        BigInteger e;
        
        BigInteger coprime;

        do {
            coprime = new BigInteger(phiN.bitLength(), random);
            e=coprime;
        } while (!(phiN.gcd(coprime)).equals(BigInteger.ONE));


        BigInteger d = e.modInverse(phiN);


        return new KeyPair(new PublicKey(n, e), new PrivateKey(n, d));
    }

    // Encrypt plaintext using the public key
    public static BigInteger encrypt(BigInteger plaintext, PublicKey publicKey) {
        return plaintext.modPow(publicKey.getExponent(), publicKey.getModulus());
    }

    // Decrypt ciphertext using the private key
    public static BigInteger decrypt(BigInteger ciphertext, PrivateKey privateKey) {
        return ciphertext.modPow(privateKey.getExponent(), privateKey.getModulus());
    }

    // Key pair class
    public static class KeyPair {
        private PublicKey publicKey;
        private PrivateKey privateKey;

        public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }
    }

    // Public key class
    public static class PublicKey {
        private BigInteger modulus;
        private BigInteger exponent;

        public PublicKey(BigInteger modulus, BigInteger exponent) {
            this.modulus = modulus;
            this.exponent = exponent;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public BigInteger getExponent() {
            return exponent;
        }
    }

    // Private key class
    public static class PrivateKey {
        private BigInteger modulus;
        private BigInteger exponent;

        public PrivateKey(BigInteger modulus, BigInteger exponent) {
            this.modulus = modulus;
            this.exponent = exponent;
        }

        public BigInteger getModulus() {
            return modulus;
        }

        public BigInteger getExponent() {
            return exponent;
        }
    }
}

