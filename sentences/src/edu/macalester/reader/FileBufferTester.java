package edu.macalester.reader;

public class FileBufferTester {

	private static String book = "huckleberry_finn.txt"; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileBuffer.read(book);
		FileBuffer.writeToFile();
	}

}
