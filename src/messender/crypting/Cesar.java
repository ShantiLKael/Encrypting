package messender.crypting;


/**
 * @author Ashanti NJANJA - BUT Info 2
 * Date of creation : 21/10/23
 * 
 * Cesar encrypting and decryptying methods 
 */

public class Cesar
{
	private static final int MIN = ' ';
	private static final int MAX = '~';
	private static final int MODULO = Cesar.MAX - Cesar.MIN;

	public static String encrypt( String m, char key )
	{
		String iM = "";
		for ( char c : m.toCharArray() )
		{
			int nLetter = c + key;
			
			if    ( nLetter > Cesar.MAX ) nLetter = nLetter % Cesar.MODULO; 
			while ( nLetter < Cesar.MIN ) nLetter += Cesar.MODULO;

			iM += (char) (nLetter);
		}

		return iM;
	}

	public static String decrypt( String m, char key )
	{
		String iM = "";
		for ( char c : m.toCharArray() )
		{
			int nLetter = c - key;

			if    ( nLetter > Cesar.MAX ) nLetter = nLetter % Cesar.MODULO; 
			while ( nLetter < Cesar.MIN ) nLetter += Cesar.MODULO;

			iM += (char) (nLetter);
		}

		return iM;
	}

	public static void main(String[] args)
    {
		String str = "bonjour";
		String str_enc = Cesar.encrypt(str, ',');
		String str_dec = Cesar.decrypt(str_enc, ','); // must return "bonjour"

		System.out.println( "Encrypting the message "+ str   +" => "+ str_enc	);
		System.out.println( "Decrypting the message "+ str_enc +" => "+ str_dec	);
	}
}