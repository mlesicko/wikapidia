package edu.macalester.reader;


public class CosimilarityTester {
    private static String[] books = new String[]{"satyricon.txt","redbadgeofcourage.txt"};
    private static String s1 = "werewolf"; //Appears in Satyricon but not Red Badge
    private static String s2 = "gun"; //Appears in Red Badge but not Satyricon
    private static String s3 = "horse"; //Appears in both

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        for (String book : books){
            FileBuffer.read(book);
            FileBuffer.writeToFile("out-"+book);
        }


        CosimilarityMatrix matrix = new CosimilarityMatrix();
        for (String book: books){
           matrix.addWords("out-"+book);
           System.out.println(book+" read");
        }
        matrix.buildMatrix();
        System.out.println("Matrix built");

        System.out.println(matrix.sr(s1,s3));
        System.out.println(matrix.sr(s2,s3));

        System.out.println("Took "+((System.currentTimeMillis()-start)/60000)+" minutes");

    }
}
