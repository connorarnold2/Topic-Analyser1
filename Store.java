
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Store
{
	static void saveTopList( int[][] corolation, int[] top, String[] occured, File location ) throws IOException
	{
		PrintWriter write = new PrintWriter(location);
		
		for( int i = 0 ; i < 10 ; i ++ )
		{
			write.print( occured[ corolation[ top[ i ] ] [0] ] + ", ");
			write.print( (corolation[top[i]][2]) + ", ");
			write.println( corolation[top[i]][3] );
		}
		
		write.close();
	}
}
