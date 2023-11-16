/**
 * @author Ashanti NJANJA - BUT Info 2
 * @date 15/11/23
 * 
 * Vigenère encrypting and decryptying methods 
 */

public class Vigenere
{
	public static String encrypt( String m, String key )
	{
		String iM = "";
		for ( int i = 0;  i < m.length(); i++ )
		{
			String nLetter = Cesar.encrypt( m.charAt(i) + "", key.charAt(i) );

			iM += nLetter;
		}

		return iM;
	}

	public static String decrypt( String m, String key )
	{
		String iM = "";
		for ( int i = 0; i < m.length(); i++ )
		{
			String nLetter = Cesar.decrypt( m.charAt(i) + "", key.charAt(i) );
			iM += nLetter;
		}

		return iM;
	}

	public static void main(String[] args)
    {
		String str = "bonjour";
        String key = "mvfo4@*"; // all char need to be between 32 and 127 (ASCII)
		String str_enc = Vigenere.encrypt(str, key);
		String str_dec = Vigenere.decrypt(str_enc, key); // must return "bonjour"

		System.out.println( "Encrypting the message "+ str   +" => "+ str_enc	);
		System.out.println( "Decrypting the message "+ str_enc +" => "+ str_dec	);
	}
}