import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			   FrameClass show = new FrameClass();
			   show.setVisible(true);
			}
		});
		
		
		
		
		
		
	}

}
