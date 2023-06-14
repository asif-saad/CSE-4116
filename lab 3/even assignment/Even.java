import java.math.BigInteger;
import java.util.Scanner;
class mod_inverse {
    static BigInteger x1;
    static BigInteger x2;
    static BigInteger BetterGCD(BigInteger x, BigInteger y)
    {
        if (x.equals(new BigInteger("0"))) {
            x1 = new BigInteger("0");
            x2 = new BigInteger("1");
            return y;
        }
        BigInteger gcdExtended = BetterGCD(y.mod(x), x);
        BigInteger x3 = x1;
        BigInteger x4 = x2;
        BigInteger tmp = y.divide(x);
        x1 = x4.subtract( tmp.multiply(x3));
        x2 = x3;
        return gcdExtended;
    }
    static BigInteger Modular_Inversion(BigInteger a, BigInteger b)
    {
        BigInteger val=null, gcdextend = BetterGCD(a, b);
        if (gcdextend.equals(new BigInteger("1"))) {
            val = (x1.mod(b).add(b)).mod(b);
        }
        else {
            System.out.println("Mod Inverse not possible!");
        }
        return val;
    }
}
public class Even {
    static BigInteger L_function(BigInteger a , BigInteger b)
    {
        BigInteger k;
        k=a.subtract(new BigInteger("1"));
        k=k.divide(b);
        return k;
    }
    static BigInteger encryption(BigInteger a, BigInteger b, BigInteger c)
    {
        mod_inverse object=new mod_inverse();
        BigInteger d = new BigInteger("11");
        if(object.BetterGCD(c,d).equals(new BigInteger("1")))
        {
            BigInteger x= a.modPow(b,c.multiply(c));
            BigInteger y = d.modPow(c,c.multiply(c));
            BigInteger cipher = x.multiply(y).mod(c.multiply(c));
            return cipher;
        }
        else
        {
            System.out.println("Incorrect value for r");
            return null;
        }
    }
    static BigInteger decryption(BigInteger a, BigInteger lambda, BigInteger b,BigInteger c)
    {
        BigInteger msg = null, t1= a.modPow(lambda,b.multiply(b));
        t1= L_function(t1,b);
        msg = t1.multiply(c).mod(b);
        return msg;
    }
    public static void main(String[] args) {
        BigInteger x=new BigInteger("1005481"),y=new BigInteger("1758719");
        mod_inverse object=new mod_inverse();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your message: ");
        BigInteger message = scanner.nextBigInteger();
        BigInteger n=x.multiply(y);
        BigInteger phi_phi= x.subtract(new BigInteger("1")).multiply(y.subtract(new BigInteger("1")));
        if(object.BetterGCD(n,phi_phi).equals(new BigInteger("1")))
        {
            BigInteger lambda = x.subtract(new BigInteger("1")).multiply(y.subtract(new BigInteger("1")));
            lambda = lambda.divide(object.BetterGCD(x.subtract(new BigInteger("1")),y.subtract(new BigInteger("1"))));
            BigInteger g= new BigInteger("13");
            if(object.BetterGCD(n,g).equals(new BigInteger("1")))
            {
                BigInteger t1=L_function(g.modPow(lambda,n.multiply(n)),n);
                BigInteger encryptedMessage = encryption(g,message,n);
                System.out.println("Message afer Encryption: "+encryptedMessage);
                BigInteger decryptedMessage = decryption(encryptedMessage,lambda,n,object.Modular_Inversion(t1,n));
                System.out.println("Message after Decryption: "+ decryptedMessage);
            }
            else {
                System.out.println("Incorrect");
            }
        }
        else
        {
            System.out.println("Incorrect");
        }
        scanner.close();
    }
}