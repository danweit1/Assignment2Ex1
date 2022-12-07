package Ex1;

import java.io.*;

public class VehicleLogger {

	private FileWriter fileWrite = null;
	private PrintWriter write = null;
	private FileReader fileRead = null;
	private BufferedReader read = null;
	
	
	public VehicleLogger() {
		
	}
	
	public void write() throws IOException {
		try {
			fileWrite = new FileWriter("log.txt");
			write = new PrintWriter(fileWrite);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			fileWrite.close();
			write.close();
		}
	}
	
	public String read() throws IOException {
		String content = "";
		String str = "";
		try {
			fileRead = new FileReader("log.txt");
			read = new BufferedReader(fileRead);
			while ((str = read.readLine()) != null) {
				content += str;
				content += "\n";
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			fileWrite.close();
			write.close();
		}
		return content;
	}
	
}
