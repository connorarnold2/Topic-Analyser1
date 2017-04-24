import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;

public class ReadFile
{
	public static void read(File File1, File File2, File StopWords, boolean stopWordBoolean, boolean saveFile, File saveLocation)
	{
		try
		{
			FileInputStream input1 = new FileInputStream(File1);
			FileInputStream input2 = new FileInputStream(File2);
			
			byte[] txtDocument1 = new byte [(int) File1.length()];
			byte[] txtDocument2 = new byte [(int) File2.length()];
			
			input1.read(txtDocument1);
			input2.read(txtDocument2);
			
			input1.close();
			input2.close();
			
			String document1 = new String(txtDocument1, "UTF-8");
			String document2 = new String(txtDocument2, "UTF-8");
			String document3 = new String("UTF-8");
			
			if(stopWordBoolean == true)
			{
				FileInputStream input3 = new FileInputStream(StopWords);
				byte[] txtDoc3 = new byte [(int) StopWords.length()];
				input3.read(txtDoc3);
				input3.close();
				document3 = new String(txtDoc3, "UTF-8");
			}
			
			OverLapTopic.repeats(document1, document2, document3, stopWordBoolean, saveFile, saveLocation);
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
