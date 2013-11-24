package edu.macalester.reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileBuffer {
	
	private static Pattern spec_char = Pattern.compile("[\"*_,;:^%+=()\\[\\]~`\\{}]");
	private static Pattern spaces = Pattern.compile("\\s+|\\t+");
	private static Pattern strip = Pattern.compile("^\\s+|\\s+$");
	private static ArrayList<String> buf_lines;
	private static String output_file = "output.txt";	// Default output file
	
	public static void read(String fname) {
		
		ArrayList<String> prev_lines = new ArrayList<String>();
		buf_lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			String line = null;
			reader = new BufferedReader(new FileReader(fname));
			
			while ((line = reader.readLine()) != null) {
				// DEBUG: System.out.println("RAW LINE: \t " + line);
				
				// Split line into 'sentences' by end-of-line markers
				String[] lines = line.split("[?!.]");
				int num_lines = lines.length;
				
				if (num_lines == 1) {
					line = clean(line);
					if (line.length() != 0)
						prev_lines.add(line);
					
				// Sentence end somewhere in there
				} else {
					// No matter what happens we always add the first statement 
					// to the ArrayList and create a sentence
					prev_lines.add(lines[0]);
					String sentence = makeSentence(prev_lines);
					buf_lines.add(sentence);
					// DEBUG: System.out.println("SENTENCE: \t " + sentence);
					
					prev_lines = new ArrayList<String>();
					
					// Skip the first sentence
					for (int i = 1; i < num_lines; i++) {
						sentence = clean(lines[i]);
						
						if (i < (num_lines - 1)) {
							if (sentence.length() != 0) {
								buf_lines.add(sentence);
								// DEBUG: System.out.println("SENTENCE: \t " + sentence);
							}
							
						/* The last line of the sentence is not nothing
						 * This assumes that if a sentence ends at the end of the line 
						 * the split will return spaces/tabs or the empty string
						 */
						} else if (sentence.length() != 0) {
							prev_lines.add(sentence);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					if (prev_lines.size() > 0) {
						buf_lines.add(makeSentence(prev_lines));
					}
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void writeToFile(String fname) {
		output_file = fname;
		writeToFile();
	}
	
	public static void writeToFile() {
		try {
			
			File f_out = new File(output_file);
			if (!f_out.exists())
				f_out.createNewFile();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(f_out.getAbsoluteFile()));
			
			ArrayList<String> content = getBufferLines(output_file);
			for (String line : content) {
				writer.write(line);
				writer.newLine();
			}
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getBufferLines(String fname) {
		if (buf_lines == null) {
			read(fname);
		}
		return buf_lines;
	}
	
	public static void setOutputFile(String fname) {
		output_file = fname;
	}
	
	private static String makeSentence(ArrayList<String> lines) {
		String sentence = "";
		
		for (String line : lines)
			sentence += line + " ";
		
		return clean(sentence);
	}
	
	private static String clean(String str) {
		// Remove special characters
		String w_spaces = spec_char.matcher(str).replaceAll("");
		
		// Remove extra spaces + tabs and return
		w_spaces = spaces.matcher(w_spaces).replaceAll(" ");
		
		// Remove spaces at the beginning and end of sentence
		return strip.matcher(w_spaces).replaceAll("");
	}
}
