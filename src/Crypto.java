/**
 * @author Ashanti NJANJA
 * @date 13/05/23
 */

public class Crypto 
{
	public static int PGCD(int v1, int v2)
	{
		if ( v2 != 0 ) return PGCD( v2, v1%v2 );
		return v1;
	}
}
