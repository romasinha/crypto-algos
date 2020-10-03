import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
 public class Main
{
  
public static void main (String[]args) throws Exception
  {
    
String plainText = "Hello World";
    
SecretKey secKey = getSecretEncryptionKey ();
    
byte[] cipherText = encryptText (plainText, secKey);
    
String decryptedText = decryptText (cipherText, secKey);
    

System.out.println ("Original Text:" + plainText);
    
System.out.println ("AES Key (Hex Form):" + secKey.getEncoded ());
    
System.out.println ("Encrypted Text (Hex Form):" + cipherText);
    
System.out.println ("Descrypted Text:" + decryptedText);
  

} 
public static SecretKey getSecretEncryptionKey () throws Exception
  {
    
KeyGenerator generator = KeyGenerator.getInstance ("AES");
    
generator.init (128);	// The AES key size in number of bits
    SecretKey secKey = generator.generateKey ();
     
return secKey;
   
}
   
public static byte[] encryptText (String plainText,
				      SecretKey secKey) throws Exception
  {
    
      // AES defaults to AES/ECB/PKCS5Padding in Java 7
    Cipher aesCipher = Cipher.getInstance ("AES");
    
aesCipher.init (Cipher.ENCRYPT_MODE, secKey);
    
byte[] byteCipherText = aesCipher.doFinal (plainText.getBytes ());
    
return byteCipherText;
  
}
   
public static String decryptText (byte[]byteCipherText,
				      SecretKey secKey) throws Exception
  {
    
      // AES defaults to AES/ECB/PKCS5Padding in Java 7
    Cipher aesCipher = Cipher.getInstance ("AES");
    
aesCipher.init (Cipher.DECRYPT_MODE, secKey);
    
byte[] bytePlainText = aesCipher.doFinal (byteCipherText);
    
return new String (bytePlainText);
  
}
 
}
 
