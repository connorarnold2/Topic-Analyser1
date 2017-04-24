import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener, MouseListener
{
 private static  JButton btnStats = new JButton("Results!");
 
 private 		 JButton btnSave = new JButton("Save?");
 private 		 JButton btnNoSave = new JButton("Dont Save?");
 private 		 JButton btnFile1 = new JButton("Text File 1.");
 private 		 JButton btnFile2 = new JButton("Text File 2.");
 private 		 JButton btnFile3 = new JButton("Stop Words.");
 private 		 JButton close =    new JButton("Close");
	
 private 		 File file = new File("");
 private 		 File file1 = new File("");
 private 		 File file2 = new File("C:\\");
 private 		 File saveLocation = new File("");
 
 private boolean storeFile = false;
 private boolean stopWord = false;

 public GUI()
	{
		//The window
		JFrame window = new JFrame("TopicAnalyser!");
		window.setVisible(true);
		window.setSize(1000, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		//Creating buttons
		
		window.add(btnFile1);
		window.add(btnFile2);
		window.add(btnFile3);
		
		window.add(btnStats);
		window.add(btnSave);
		window.add(btnNoSave);
		window.add(close);
		
		//Positioning of the buttons
		
		btnFile1.setBounds(190, 70, 110, 40);
		btnFile2.setBounds(450, 70, 110, 40);
		btnFile3.setBounds(700, 70, 110, 40);
		
		btnStats.setBounds(190, 140, 110, 40);
		btnSave.setBounds(450, 140, 110, 40);
		btnNoSave.setBounds(700, 140, 110, 40);
		close.setBounds(450, 210, 110, 40);
		
	
		
		//Top Navigation Bar
		JMenuBar menu = new JMenuBar();
		window.setJMenuBar(menu);
		
		JMenu file = new JMenu("File");
		menu.add(file);
		JMenuItem findFile = new JMenuItem("File files");
		JMenuItem exit = new JMenuItem("Exit");
		file.add(findFile);
		file.add(exit);
		
		JMenu help = new JMenu("Help");
		menu.add(help);
		JMenuItem functions = new JMenuItem("Functions");
		help.add(functions);
		
		//Creating listeners	
		btnFile1.addActionListener(this);
		btnFile2.addActionListener(this);
		btnFile3.addActionListener(this);
		
		btnStats.addActionListener(this);
		btnSave.addActionListener(this);
		btnNoSave.addActionListener(this);
		close.addActionListener(new CloseListener());
		
		findFile.addActionListener(new Exit());
		exit.addActionListener(new Exit());
		
		
}
 private class CloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        //DO SOMETHING
	        System.exit(0);
	    }
	}
 
	
 public static void printLists(int[] countInt, String[] occured)
	{
		JOptionPane.showMessageDialog(btnStats, "save");
	}
	
 public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnFile1)
	{
		JFileChooser f1 = new JFileChooser();
		f1.showDialog(null, "Select");
		f1.setFocusable(true);
		file = f1.getSelectedFile();
	}
 else if (e.getSource() == btnFile2)
	{
		JFileChooser f2 = new JFileChooser();
		f2.showDialog(null, "Select");
		f2.setFocusable(true);
		file1 = f2.getSelectedFile();
	}
 else if (e.getSource() == btnFile3)
	{
		JFileChooser f3 = new JFileChooser();
		f3.showDialog(null, "Select");
		f3.setFocusable(true);
		file2 = f3.getSelectedFile();
		stopWord = true;
    }
 else if (e.getSource() == btnStats)
	{
		if (file1.exists() && file2.exists())
	{
		ReadFile.read(file, file1, file2, stopWord, storeFile, saveLocation);
	}
 else
	{
		JOptionPane.showMessageDialog(this, "Error, please select the files.");
	}
}
 else if (e.getSource() == btnSave)
	{
		JFileChooser f4 = new JFileChooser();
		f4.setFocusable(true);
		f4.showDialog(null, "Select save location!");
		
	    saveLocation = f4.getSelectedFile();
			
	    JOptionPane.showMessageDialog(this, "File saved to chosen directory!");
	    storeFile = true;
	}
 else if (e.getSource() == btnNoSave)
	{
		JOptionPane.showMessageDialog(this, "File not saved!");
		storeFile = false;
	}
 else
	{
		JOptionPane.showMessageDialog(this, "Error!!");
	}
}

@Override
 public void mouseClicked(MouseEvent arg0) 
	{
		//TODO Auto-generated method stub
	}

@Override
 public void mouseEntered(MouseEvent e) 
	{
		//TODO Auto-generated method stub	
	}

@Override
 public void mouseExited(MouseEvent e) 
	{
		//TODO Auto-generated method stub
	}

@Override
 public void mousePressed(MouseEvent e)
	{
		//TODO Auto-generated method stub	
	}

@Override
 public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub	
	}
	
}

