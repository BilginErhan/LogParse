import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameClass extends JFrame{
	
	private JPanel contentPane;
	public String filepath;
	
	public FrameClass(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,450,300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton filebtn = new JButton("Dosya Seç");
		filebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filepath = FindFilePath();
				try {
					LogController fileparse = new LogController(filepath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		filebtn.setBounds(156, 216, 141, 23);
		contentPane.add(filebtn);
				
	}
	
	public String FindFilePath(){
//		File file = null;
		JFileChooser fileopen = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Log Files" , "log");
		fileopen.addChoosableFileFilter(filter);
		
		int answer = fileopen.showDialog(null, "Dosya Seç");
		if(answer == JFileChooser.APPROVE_OPTION){
			return fileopen.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

}
