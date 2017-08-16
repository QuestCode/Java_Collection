/*
Devontae Reid
CIS 292
9/13/16
*/

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
        ArrayList<String> oldmast = grabLinesInFile("oldmast.txt");
        ArrayList<String> trans = grabLinesInFile("trans.txt");
	}// End Main
	
	private static ArrayList<String> grabLinesInFile(String file) {
		FileReader fr;
		BufferedReader br;
        ArrayList<String> lines = new ArrayList<String>();
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String s;
			
			while ((s = br.readLine()) != null) {
                lines.add(s);
			}
		
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        System.out.println(lines);
		
		return lines;
	}
	
	
}
