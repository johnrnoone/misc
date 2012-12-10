package javaIO;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader; 
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MyFileReader {

	public static void main (String[] args) {

		Scanner in = new Scanner(System.in);

		fileCreateDelete (in);
		fileReaderPrinter (in);
		bufferWrite();
		bufferRead();
		fileIO();
		fileReadIO();

	}

	static void fileCreateDelete (Scanner in) {
		StringBuffer fileName = new StringBuffer ("textfile.txt2");
		String answer = new String();

		File file  = new File(fileName.toString());
		System.out.println ("fileCreateDelete, file.exists() = " + file.exists());
		System.out.println ("fileCreateDelete, file.getAbsolutePath() = " + file.getAbsolutePath());
		try {
			if (!file.exists()) {
				System.out.print("Do you wish to create " + fileName + ":"); answer = in.nextLine();
				if (answer.equalsIgnoreCase("y")) {
					file.createNewFile(); System.out.println ("fileCreateDelete, " +fileName + " is created"); }
				fileWrite(file);
			} else {
				System.out.print("Do you wish to delete " + fileName + ":"); answer = in.nextLine();
				if (answer.equalsIgnoreCase("y")) {
				file.delete(); System.out.println ("fileCreateDelete " + fileName + " is deleted"); }
			}
			System.out.print("Do you wish to continue?:"); answer = in.nextLine();
		} catch (IOException ioe) {
			System.out.println ("IOexception in file.createNewFile() = "); ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println ("Exception in file.createNewFile() = "); e.printStackTrace();
		}
	}

	static void fileReaderPrinter (Scanner in) {
		StringBuffer fileName = new StringBuffer ("textfile.txt2");
		char [] charBuf = new char[50];

		File file  = new File(fileName.toString());
		System.out.println ("fileReaderPrinter, file.exists() = " + file.exists());
		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				int size = fr.read(charBuf);
				System.out.print ("fileReaderPrinter, ");
				for (int idx = 0;  idx<size; ++idx) {
					System.out.print(charBuf[idx] + " ");
				}
				System.out.println(); 
				System.out.print("Do you wish to continue?:"); String answer = in.nextLine();
				fr.close();
			}
		} catch (IOException ioe) {
			System.out.println ("IOexception in file.createNewFile() = "); ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println ("Exception in file.createNewFile() = "); e.printStackTrace();
		}
	}
	
	
	static void fileIO () {
		StringBuffer fileName = new StringBuffer ("textfile.txt2");
		File file  = new File(fileName.toString());
		System.out.println ("fileIO, file.exists() = " + file.exists());
		try {
			if (!file.exists()) {
				file.createNewFile(); System.out.println ("fileIO, " +fileName + " is created"); 
				fileWrite(file);
			} else {
				file.delete(); System.out.println ("fileIO, " + fileName + " is deleted"); 
			}
		} catch (IOException e) {
			System.out.println ("exception in file.createNewFile() = "); 
		}

	}

	static void fileWrite (File file) {		
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("record"); fw.close();
		} catch (IOException e) {
			System.out.println ("exception in file.createNewFile() = "); e.printStackTrace();		
		}
	}

	static int fileReadIO () {
		char[] in = new char[50];
		try {
			StringBuffer fileName = new StringBuffer ("textfile.txt2");
			File file  = new File(fileName.toString());
			FileReader fr = new FileReader(file);
			return fr.read(in);

		} catch (FileNotFoundException fnfe) {
			System.out.println ("fileReadIO, FileNotFoundException in file.createNewFile()");
			return 0;
		} catch (Exception e) {
			System.out.println ("fileReadIO, exception in file.createNewFile() = "); e.printStackTrace();
			return 0;
		}
	}

	static void bufferRead () {
		try{
			
			String fileName = new String ("textfile.txt3");
			System.out.println ("bufferRead reads " + fileName);
			
			File f = new File (fileName);
			BufferedReader br    = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			
			//Read File Line By Line
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				System.out.println ("bufferRead, " + strLine);	
				if (strLine.contains("Yahoo")) System.out.println ("Found Yahoo");				
			}

			br.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("bufferRead, FileNotFoundException in streamIO()");
		} catch (Exception e) {
			System.out.println("bufferRead, Exception in streamIO()"); e.printStackTrace();
		}	
	}
	
	static void bufferWrite () {
		try{
			
			String fileName = new String ("textfile.txt3");
			System.out.println ("bufferWrite reads " + fileName);
			
			File f = new File (fileName);
			BufferedWriter bw    = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));

			//Read File Line By Line
			StringBuffer strLine = new StringBuffer("record1");
			bw.write(strLine.toString()); System.out.println ("bufferWrite, " + strLine); bw.newLine();
		    strLine = new StringBuffer("record2");
			bw.write(strLine.toString()); System.out.println ("bufferWrite, " + strLine); bw.newLine();
			bw.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("bufferWrite, FileNotFoundException in streamIO()");
      } catch (NullPointerException npe) {
         System.out.println("bufferWrite, NullPointerException in streamIO()");
		} catch (Exception e) {
			System.out.println("bufferWrite, Exception in streamIO()"); e.printStackTrace();
		}	
	}
}
