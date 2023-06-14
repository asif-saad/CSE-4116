import java.math.BigInteger;

public class RSAPlainAttack {

    public static void main(String[] args){
        BigInteger p = new BigInteger("29");
        BigInteger q = new BigInteger("17");

        BigInteger n = p.multiply(q);
        BigInteger phi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger msg = new BigInteger("5");
        BigInteger e = BigInteger.ZERO;

        for (BigInteger i = new BigInteger("20"); i.compareTo(phi_n) < 0; i = i.add(BigInteger.ONE)) {
            if (i.gcd(phi_n).equals(BigInteger.ONE)) {
                e = i;
                break;
            }
        }
        
        BigInteger d = e.modInverse(phi_n);
        // Private key
        System.out.println(d);
        BigInteger C = msg.modPow(e,n);
        BigInteger decrypted = C.modPow(d,n);
        
        
        // Given plain text, ciphertext, public key and n

        for( BigInteger i = phi_n.subtract(BigInteger.ONE);;i=i.subtract(BigInteger.ONE)){
            if((C.modPow(i,n)).mod(phi_n).equals(msg) ){
                if(((e.multiply(i)).mod(phi_n)).equals(BigInteger.ONE)){
                    // Private key has been retrieved
                    System.out.println(i);
                    break;

                }
            }
        }
    }
}
