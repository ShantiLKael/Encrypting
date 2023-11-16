/**
 * @author Ashanti NJANJA - BUT Info 2
 * @date 15/11/23
 * 
 * Affine encrypting and decryptying methods 
 */

public class Affine
{
	public static final int MIN = ' ';
	public static final int MAX = '~';
	public static final int MODULO = Affine.MAX - Affine.MIN;
	
	private int multiplier;
	private int term;
	private int m_inv;

	/**
	 * Define the encrypting function which form is : a.X + b
	 * @param a the multiplier
	 * @param b the term
	 */
	private Affine( int a,  int b )
	{
		this.multiplier = a;
		this.term = b;

		// Finds the inverse of the multiplier 
        for ( int i = 0; i < Affine.MODULO; i++ )
        {
            int tmp = ( this.multiplier * i ) % Affine.MODULO;
            if ( tmp == 1 ) this.m_inv = i;
        }
	}

	/**
	 * Define the encrypting method while verifying the multiplier's
	 * value (it needs to be an inverse element in {@code MODULO} .
	 * Return null if the value is wrong.
	 * @param a multiplier
	 * @param b term 
	 */
	public static Affine createFunction ( int a, int b )
	{
		if ( Crypto.PGCD(a, Affine.MODULO) != 1 ) return null;
		return new Affine(a, b);
	}

	public int getMultiplier() { return multiplier; }
	public int getTerm		() { return term; }
	public int getM_inv		() { return m_inv; }

	public String encrypt( String m )
	{
		String iM = "";

		for ( char c : m.toCharArray() )
		{
			int nLetter = this.multiplier * c + this.term ;

			if    ( nLetter > Affine.MAX ) nLetter = nLetter % Affine.MODULO; 
			while ( nLetter < Affine.MIN ) nLetter += Affine.MODULO;

			iM += (char) (nLetter);
		}

		return iM;
	}

    public String decrypt( String m )
	{
		String iM = "";

		for ( char c : m.toCharArray() )
		{
			int nLetter = this.m_inv * ( c - this.term ) ;

			if    ( nLetter > Affine.MAX ) nLetter = nLetter % Affine.MODULO; 
			while ( nLetter < Affine.MIN ) nLetter += Affine.MODULO;

			iM += (char) (nLetter);
		}

		return iM;
	}

	public static void main(String[] args)
    {
		String str = "bonjour";

		Affine f = new Affine(13, 3);
		String str_enc = f.encrypt(str);
		String str_dec = f.decrypt(str_enc); // must return "bonjour"

		System.out.println( "Encrypting the message "+ str   +" => "+ str_enc	);
		System.out.println( "Decrypting the message "+ str_enc +" => "+ str_dec	);
	}
}
