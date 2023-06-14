import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;


public class RSA_signature {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner=new Scanner(System.in);
        String input = scanner.nextLine();

        // Generate the SHA-256 hash
        byte[] hash = generateSHA256Hash(input.getBytes(StandardCharsets.UTF_8));

        // Convert the hash to a decimal representation
        BigInteger plaintext = convertHashToBigInteger(hash);

        // Generate RSA keys
        KeyPair keyPair = generateKeys(512); // Specify the desired key size (in bits)

        BigInteger signature=encrypt(plaintext, keyPair.getPrivateKey());


        //signature  validation
        BigInteger decrypted_signature=decrypt(signature, keyPair.getPublicKey());
        if(decrypted_signature.equals(plaintext)){
            System.out.println("both are same");
        }
        
        scanner.close();
    }

    public static byte[] generateSHA256Hash(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(input);
    }

    public static BigInteger convertHashToBigInteger(byte[] hash) {
        BigInteger hashBigInt = new BigInteger(1, hash);
        return hashBigInt;
    }


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
    public static BigInteger encrypt(BigInteger plaintext, PrivateKey privateKey) {
        return plaintext.modPow(privateKey.getExponent(), privateKey.getModulus());
    }

    // Decrypt ciphertext using the private key
    public static BigInteger decrypt(BigInteger ciphertext, PublicKey publicKey) {
        return ciphertext.modPow(publicKey.getExponent(), publicKey.getModulus());
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
