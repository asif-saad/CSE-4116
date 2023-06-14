public class CaesarCipher {
    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                char shiftedChar = (char) ((ch - 'A' + shift) % ALPHABET_SIZE + 'A');
                ciphertext.append(shiftedChar);
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int shift) {
        // To decrypt, we just negate the shift
        return encrypt(ciphertext, -shift);
    }

    public static void main(String[] args) {
        String plaintext = "HELLO";
        int shift = 3;

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, shift);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, shift);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
