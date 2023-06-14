import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Paillier {

    public static void main(String[] args) {


        // key generation

        Integer keySize=512;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Type message: ");
        BigInteger m=scanner.nextBigInteger();
        Random random=new Random();
        BigInteger p=BigInteger.probablePrime(keySize/2, random);
        BigInteger q;
        do{
            q=BigInteger.probablePrime(keySize/2, random);
        }
        while(!((p.multiply(q)).gcd(p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)))).equals(BigInteger.ONE));
        BigInteger n,g,lambda,mu;
        n=p.multiply(q);
        g=n.add(BigInteger.ONE);
        lambda=(p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
        mu=lambda.modInverse(n);
        System.out.println("The public keys:\nn="+n+"\ng="+g);
        System.out.println("The public keys:\nLambda="+lambda+"\nMu="+mu);





        // encryption
        BigInteger r;
        do{
            r=BigInteger.probablePrime(m.bitLength()/2, random);
        }
        while(!(r.gcd(n)).equals(BigInteger.ONE));
        BigInteger c=((g.modPow(m,n.multiply(n))).multiply(r.modPow(n, n.multiply(n)))).mod(n.multiply(n));





        // decryption
        BigInteger arg=c.modPow(lambda, n.multiply(n));
        BigInteger L=(arg.subtract(BigInteger.ONE)).divide(n);
        BigInteger decyrpt=(L.multiply(mu)).mod(n);
        System.out.println("Decrypted message: "+decyrpt);

        scanner.close();
    }
    
}
