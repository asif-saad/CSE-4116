import java.math.BigInteger;
import java.util.Random;

import static java.math.BigInteger.ONE;


public class ElgamalSignature {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("179424673"); // The prime number to find a generator for
        BigInteger alpha = findGenerator(p);
//        System.out.println(p.subtract(BigInteger.TWO));
        BigInteger k_private =  getRandomNumberInRange(BigInteger.valueOf(2),p.subtract(BigInteger.TWO));



        //calculate Bob's public key
        assert alpha != null;
        BigInteger beta =  alpha.modPow(k_private,p);
        BigInteger k = getRandomNumberInRange(BigInteger.valueOf(2),p.subtract(BigInteger.valueOf(2)));
        while(!k.gcd(p.subtract(ONE)).equals(BigInteger.valueOf(1))){
            k = getRandomNumberInRange(BigInteger.valueOf(2),p.subtract(BigInteger.valueOf(2)));
        }
        //For Signature
        BigInteger r = alpha.modPow(k,p);
        //message
        BigInteger x = BigInteger.valueOf(52);
        BigInteger S = (x.subtract(k_private.multiply(r))).multiply(k.modInverse(p.subtract(ONE))).mod(p.subtract(ONE));
        //For verification
        BigInteger t1 = r.modPow(S, p).multiply(beta.modPow(r, p)).mod(p);
        BigInteger t2 = alpha.modPow(x, p);
        System.out.println("computed t1: "+t1);
        System.out.println("computed t2: "+t2);


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