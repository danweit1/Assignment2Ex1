package Ex1;

import java.io.*;

public class VehicleLogger {

	private FileWriter fileWrite = null;
	private PrintWriter write = null;
	private FileReader fileRead = null;
	private BufferedReader read = null;
	

	public VehicleLogger() throws IOException {
		try {
			fileWrite = new FileWriter("log.txt");
			write = new PrintWriter(fileWrite);
			fileRead = new FileReader("log.txt");
			read = new BufferedReader(fileRead);
		} catch (IOException e) {
			System.out.print(e);
		}
		
	}
	
	public void write(String str) throws IOException {
		try {
			write.write(str);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public String read() throws IOException {
		String content = "";
		String str = "";
		try {
			while ((str = read.readLine()) != null) {
				content += str;
				content += "\n";
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		return content;
	}
	
	public void closeLog() throws IOException {
		this.fileWrite.close();
		this.write.close();
		this.fileRead.close();
		this.read.close();
	}

}