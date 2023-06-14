import java.math.BigInteger;
import java.util.Random;


public class ElgamalEncryption {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("179424673"); // The prime number to find a generator for
        BigInteger g = findGenerator(p);
        //Bob's private key
        BigInteger k_private =  getRandomNumberInRange(BigInteger.valueOf(2),p.subtract(BigInteger.valueOf(2)));

        //calculate Bob's public key
        BigInteger beta =  g.modPow(k_private,p);

        //Alice  private
        BigInteger i = getRandomNumberInRange(BigInteger.valueOf(2),p.subtract(BigInteger.valueOf(2)));

        //calculate alice's public key
        BigInteger ephemoral = g.modPow(i,p);
        BigInteger masking = beta.modPow(i,p);
        //message
        BigInteger x = BigInteger.valueOf(554652);
        //Encrypted
        BigInteger encrypted= x.multiply(masking.mod(p));

        //Bobs maasking key
        BigInteger bob_mask = ephemoral.modPow(k_private,p);
        BigInteger decryp = encrypted.multiply(bob_mask.modInverse(p)).mod(p);
        System.out.println("decrypted: " + decryp);

    }
    private static BigInteger getRandomNumberInRange(BigInteger min, BigInteger max) {
        Random rnd = new Random();
        BigInteger range = max.subtract(min).add(BigInteger.ONE);
        BigInteger randBigInt = new BigInteger(range.bitLength(), rnd);
        while (randBigInt.compareTo(range) >= 0) {
            randBigInt = new BigInteger(range.bitLength(), rnd);
        }
        return randBigInt.add(min);
    }
    public static BigInteger findGenerator(BigInteger p) {
        BigInteger pMinusOne = p.subtract(BigInteger.ONE);
        BigInteger[] factors = factor(pMinusOne);

        // Try values of g from 2 to p-1
        for (BigInteger g = BigInteger.valueOf(2); g.compareTo(p) < 0; g = g.add(BigInteger.ONE)) {
            boolean isGenerator = true;
            // Check if g is a primitive root modulo p
            for (BigInteger factor : factors) {
                if (g.modPow(pMinusOne.divide(factor), p).equals(BigInteger.ONE)) {
                    isGenerator = false;
                    break;
                }
            }
            if (isGenerator) {
                return g;
            }
        }
        // No generator was found
        return null;
    }

    public static BigInteger[] factor(BigInteger n) {
        // Returns the prime factors of n
        BigInteger[] factors = new BigInteger[32]; // Assumes n has no more than 32 prime factors
        int count = 0;
        BigInteger i = BigInteger.valueOf(2);
        while (i.multiply(i).compareTo(n) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                factors[count++] = i;
                n = n.divide(i);
            } else {
                i = i.add(BigInteger.ONE);
            }
        }
        if (n.compareTo(BigInteger.ONE) > 0) {
            factors[count++] = n;
        }
        BigInteger[] result = new BigInteger[count];
        System.arraycopy(factors, 0, result, 0, count);
        return result;
    }
}