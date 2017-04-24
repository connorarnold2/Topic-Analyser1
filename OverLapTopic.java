
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.Arrays;

public class OverLapTopic
{
	//public static Set<String> stopWordSet = new HashSet<String>(Arrays.asList(stopWords));
	//public static Set<String> stemmedStopWordSet = stemStringSet(stopWordSet);
	
	public static void repeats(String words, String words1, String stopWords, boolean stopWordsOption, boolean saveFile, File saveLocation) throws IOException
	{
		stopWords = stopWords.toLowerCase();
		
		words = words.toLowerCase();
		words = words.replaceAll("[\",.()-;:%\n\r]", "");
		words = words.replace("\\]", "");
		words = words.replace("\\[", "");
		words = words.replaceAll("[0-9]", "");
		
		words1 = words1.toLowerCase();
		words1 = words1.replaceAll("[\",.()-;:%\n\r]", "");
		words1 = words1.replaceAll("\\]", "");
		words1 = words1.replaceAll("\\[", "");
		words1 = words1.replaceAll("[0-9]", "");
		
		String[] wordList1 = words.split(" ");
		String[] wordList2 = words1.split(" ");
		
		String[] finalList1 = new String[wordList1.length];
		String[] finalList2 = new String[wordList2.length];
		
		if(stopWordsOption == true)
		{
			
		}
		
		
		
		String[] ocrd1 = new String[wordList1.length];
		String[] ocrd2 = new String[wordList2.length];
		
		int[] countInt1 = new int[wordList1.length];
		int[] countInt2 = new int[wordList2.length];
		
		int[][] corr;
		int[] top ;

		ocrd1[0] = finalList1[0];
		ocrd2[0] = finalList2[0];
		countInt1[0] = 1 ;
		countInt2[0] = 1 ;
		
		if(finalList1.length > 0)
		{
			for(int i = 1, l = 0 ; i < finalList1.length ; i ++)
			{
				boolean found = false;
				
				for (int j = 0 ; j < i ; j ++)
				{
					if (finalList1[i].equals(ocrd1[j]))
					{
						countInt1[j] ++ ;
						found = true;
					}
				}
				
				if (found == false)
				{
					l ++ ;
					ocrd1[l] = finalList1[i];
					countInt1[l] = 1;
				}
			}
		}
		
		if(finalList2.length > 0)
		{
			for(int i = 1, l = 0 ; i < finalList2.length ; i ++)
			{
				boolean found = false;
				
				for (int j = 0 ; j < i ; j ++)
				{
					if (finalList2[i].equals(ocrd2[j]))
					{
						countInt2[j] ++ ;
						found = true;
					}
				}
				
				if (found == false)
				{
					l ++ ;
					ocrd2[l] = finalList2[i];
					countInt2[l] = 1;
				}
			}
		}
		
		int end1 = 0;
		int end2 = 0;
		
		for(int i = 0 ; i < ocrd1.length ; i ++ )
		{
			if (ocrd1[i] == null)
			{
				end1 = i ;
				break;
			}
		}
		for(int i = 0 ; i < ocrd2.length ; i ++ )
		{
			if (ocrd2[i] == null)
			{
				end2 = i ;
				break;
			}
		}
		
		String[] temp1 = new String[end1];
		String[] temp2 = new String[end2];
		
		for (int i = 0 ; i < ocrd1.length ; i ++ )
		{
			if(ocrd1[i] != null && i < end1)
			{
				temp1[i] = ocrd1[i];
			}
		}
		
		for (int i = 0 ; i < ocrd2.length ; i ++ )
		{
			if(ocrd2[i] != null && i < end2)
			{
				temp2[i] = ocrd2[i];
			}
		}
		
		int max = 0 ;
		
		if (temp1.length > temp2.length)
		{
			corr = new int[temp1.length][4];
			int l = 0;
			
			if(temp1.length > 0 && temp2.length > 0)
			{
				for(int i = 0 ; i < temp1.length ; i ++)
				{					
					for (int j = 0 ; j < temp2.length ; j ++)
					{
						if (temp1[i].equals(temp2[j]))
						{
							corr[l][0] = i;
							corr[l][1] = j;
							corr[l][2] = countInt1[i];
							corr[l][3] = countInt2[j];
							l ++;
							break;
						}
					}
				}
				max = (l - 1) ;
			}
		}
		else
		{
			corr = new int[temp2.length][4];
			int l = 0;
			
			if(temp1.length > 0 && temp2.length > 0)
			{
				for(int i = 0 ; i < temp2.length ; i ++)
				{					
					for (int j = 0 ; j < temp1.length ; j ++)
					{
						if (temp2[i].equals(temp1[j]))
						{
							corr[l][0] = j;
							corr[l][1] = i;
							corr[l][2] = countInt1[j];
							corr[l][3] = countInt2[i];
							l ++;
							break;
						}
					}
				}
				max = (l - 1) ;
			}
		}
		
		top = topTen(corr, max);
		
		
		System.out.println("Top 10 words common to both files:");
		System.out.printf("%-17s %5s %5s\n", "Word", "File 1", "File 2");
		for( int i = 0 ; i < 10 ; i ++ )
		{
			System.out.printf( "%-12s %6d %6d\n", ocrd1[ corr[ top[ i ] ] [0] ], corr[top[i]][2], corr[top[i]][3] );
		}
		
		if (saveFile == true)
		{
			Store.saveTopList( corr, top, ocrd1, saveLocation);
		}
		
		JFrame window = new JFrame("Top 10 words common to both");
		JOptionPane.showMessageDialog( window, "Word     Text File 1    Text File 2\n" 
						+ ocrd1[ corr[ top[ 0 ] ] [0] ] + "      " + corr[top[0]][2] + "   " + corr[top[0]][3] + "\n"
						+ ocrd1[ corr[ top[ 1 ] ] [0] ] + "      " + corr[top[1]][2] + "   " + corr[top[1]][3] + "\n"
						+ ocrd1[ corr[ top[ 2 ] ] [0] ] + "      " + corr[top[2]][2] + "   " + corr[top[2]][3] + "\n"
						+ ocrd1[ corr[ top[ 3 ] ] [0] ] + "      " + corr[top[3]][2] + "   " + corr[top[3]][3] + "\n"
						+ ocrd1[ corr[ top[ 4 ] ] [0] ] + "      " + corr[top[4]][2] + "   " + corr[top[4]][3] + "\n"
						+ ocrd1[ corr[ top[ 5 ] ] [0] ] + "      " + corr[top[5]][2] + "   " + corr[top[5]][3] + "\n"
						+ ocrd1[ corr[ top[ 6 ] ] [0] ] + "      " + corr[top[6]][2] + "   " + corr[top[6]][3] + "\n"
						+ ocrd1[ corr[ top[ 7 ] ] [0] ] + "      " + corr[top[7]][2] + "   " + corr[top[7]][3] + "\n"
						+ ocrd1[ corr[ top[ 8 ] ] [0] ] + "      " + corr[top[8]][2] + "   " + corr[top[8]][3] + "\n"
						+ ocrd1[ corr[ top[ 9 ] ] [0] ] + "      " + corr[top[9]][2] + "   " + corr[top[9]][3]	) ;
	}
	
	
/*
	public static boolean isStopword(String word) {
		if(word.length() < 2) return true;
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9') return true; //remove numbers, "25th", etc
		if(stopWordSet.contains(word)) return true;
		else return false;
	}
	*/
	/*
	public static String removeStopWords(String string) {
		String result = "";
		String[] words = string.split("\\s+");
		for(String word : words) {
			if(word.isEmpty()) continue;
			if(isStopword(string)) continue; //remove stopwords
			result += (word+" ");
		}
		return result;
	}
	*/
	
	private static int[] topTen(int[][] corolation, int max)
	{
		int[] highest = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,};
		int[] highLocations = {0,0,0,0,0,0,0,0,0,0};
		int score = 0 ;
		
		boolean flag = false ;
		
		
			
			
			
			
		
		
		return highLocations;
	}
}
