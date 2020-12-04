package days;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

public class part2 {

	private static ArrayList<String> passwordList;
	
	public static ArrayList<String> createList(String inputFile) throws IOException {
		
		String everything = "";
		
		FileInputStream inputStream = new FileInputStream(inputFile);
		try {
		    everything = IOUtils.toString(inputStream);
		} finally {
		    inputStream.close();
		}
		
		ArrayList<String> output = new ArrayList<String>();
		
		for (String piece : everything.split("\n")) {
			output.add(piece);
		}
		
		return output;
		
	}
	
	public static boolean checkPass(String input) {
		
		int count = 0;
		
		ArrayList<String> cake = new ArrayList<String>();
		
		for (String slice : input.split(" ")) {
			cake.add(slice);
		}
		
		// Step 1: Number restriction
		
		ArrayList<Integer> bounds = new ArrayList<Integer>();
		
		for (String number : cake.get(0).split("-")) {
			
			bounds.add(Integer.parseInt(number));
			
		}
		
		// Step 2: Letter restriction
		
		String letterRes = cake.get(1).substring(0,1);
		System.out.println("The target letter is " + letterRes + "!");
		
		// Step 3: Iterate
		
		/** --- PART 1 SOLUTION ---
		  
		for (int i = 0; i < cake.get(2).length(); i++){
		    String c = cake.get(2).substring(i,i+1);        
		    
		    if (c.equals(letterRes)) {
		    	count++;
		    }
		}
		
		**/
		int lowerBound = bounds.get(0);
		int upperBound = bounds.get(1);
		
		System.out.println("It must be found in either position " + Integer.toString(lowerBound) + " or in position " + Integer.toString(upperBound) + ".");
		
		if (cake.get(2).substring(lowerBound-1,lowerBound).equals(letterRes) && cake.get(2).substring(upperBound-1,upperBound).equals(letterRes)) {
			System.out.println(input + " = FALSE, both matches");
			return false;
		}
		
		else if (cake.get(2).substring(lowerBound-1,lowerBound).equals(letterRes) || cake.get(2).substring(upperBound-1,upperBound).equals(letterRes)) {
			System.out.println(input + " = TRUE, one match");
			return true;
		}
		
		else {
			System.out.println(input + " = FALSE, no matches");
			return false;
		}
		
		// Step 4: Check and return
		
		
		/** --- PART 1 SOLUTION ---
		  
		if (count >= lowerBound && count <= upperBound) {
			return true;
		}
		else {
			return false;
		}
		
		**/
		
	}
	
	public static int checkPasswords(ArrayList<String> inputList) {
		
		int result = 0;
		
		for (String password : inputList) {
			if (checkPass(password)) {
				result ++;
			}
		}
		
		return result;
		
	}
	
	
	
	public static void main (String[] args) throws IOException {
		
		passwordList = createList("C:\\Users\\Alex\\eclipse-workspace\\AdventOfCode2020\\src\\days\\day2input.txt");
		
		System.out.println(checkPasswords(passwordList));
		
	}
	
}
