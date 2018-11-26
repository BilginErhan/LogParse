import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class LogController {

	private String filepath;
	LogModel log = new LogModel();
	LogProcessor logProcessor;
	private BufferedReader reader;
	private FileReader readFile;
	
	
	public LogController(String file) throws IOException {
		filepath = file;
		OpenFileAndAddArrayList();
	}

	private void OpenFileAndAddArrayList() throws IOException {
		if (filepath != null) {
			double sayac= 0;
			try {
				String line;
				readFile = new FileReader(filepath);
				reader = new BufferedReader(readFile);
				
				while ((line = reader.readLine()) != null) {
					log.parcala(line);
					logProcessor = logProcessor.getInstance(log);
					sayac++;			
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LogResult logResult = logProcessor.result(sayac);
			System.out.println(logProcessor.logResult.toString());
			logResult.PushDatabase(filepath);
			
			
			
			

		}	
	}


}
