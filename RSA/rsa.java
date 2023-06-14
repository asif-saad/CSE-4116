import java.math.BigInteger;

public class rsa {

    public static void main(String[] args) {
        BigInteger p = new BigInteger("656692050181897513638241554199181923922955921760928836766304161790553989228223793461834703506872747071705167995972707253940099469869516422893633357693");
        BigInteger q = new BigInteger("533791764536500962982816454877600313815808544134584704665367971790938714376754987723404131641943766815146845004667377003395107827504566198008424339207");

        BigInteger n = p.multiply(q);
        BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger msg = new BigInteger("5");
        BigInteger e = getEncryptionExponent(phi_n);
        BigInteger d = modInverse(e, phi_n);
        BigInteger encrypted = msg.modPow(e, n);

        BigInteger decrypted = encrypted.modPow(d, n);
        System.out.println(decrypted);
    }

    public static BigInteger getEncryptionExponent(BigInteger phi_n) {
        BigInteger e = BigInteger.ZERO;
        BigInteger i = new BigInteger("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        while (i.compareTo(phi_n) < 0) {
            if (gcd(i, phi_n).equals(BigInteger.ONE)) {
                e = i;
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        return e;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger[] gcdExtended = extendedEuclidean(a, m);
        BigInteger x = gcdExtended[1];
        if (x.compareTo(BigInteger.ZERO) < 0) {
            x = x.add(m);
        }
        return x;
    }

    public static BigInteger[] extendedEuclidean(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new BigInteger[]{a, BigInteger.ONE, BigInteger.ZERO};
        } else {
            BigInteger[] result = extendedEuclidean(b, a.mod(b));
            BigInteger gcd = result[0];
            BigInteger x1 = result[2];
            BigInteger y1 = result[1].subtract(a.divide(b).multiply(result[2]));
            return new BigInteger[]{gcd, x1, y1};
        }
    }
}