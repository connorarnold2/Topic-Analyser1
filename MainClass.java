import java.awt.EventQueue;

 public class MainClass
{
 public static void main(String[] args)
{
		EventQueue.invokeLater (new Runnable()
	{
        public void run()
	{
		try
	{
		@SuppressWarnings("unused")
		GUI window = new GUI();
	}
 catch (Exception e)
	{
		e.printStackTrace();
	}
}
												});
}

}

