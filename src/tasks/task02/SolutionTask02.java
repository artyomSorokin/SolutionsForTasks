package tasks.task02;


import java.io.*;

public class SolutionTask02 {

    public SolutionTask02(String nameFileIn, String nameFileOut) throws Exception {
        FileLoader fileLoader = new FileLoader();
        File fileIn, fileOut = null;

        try {
            fileIn = new File(nameFileIn);
            if (fileIn.exists()) {
                fileLoader.setFileNameIn(fileIn);
            } else {
                throw new FileNotFoundException("The file " + fileIn + " was not found.");
            }

            fileOut = new File(nameFileOut);
            if (fileOut.exists()) {
                if (!fileOut.delete()) {
                    throw new IOException("The file " + fileOut.getName() + " is not delate.");
                }
            }

            if (!fileOut.createNewFile()) {
                throw new IOException("The file " + fileOut.getName() + " is not created.");
            }
            fileLoader.setFileNameOut(fileOut);

            fileLoader.fileLoad();
            fileLoader.solution();
            fileLoader.writeToFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file name of input");
        String nameFileIn = reader.readLine();
        System.out.println("Enter file name of output");
        String nameFileOut = reader.readLine();

        new SolutionTask02(nameFileIn, nameFileOut);
    }
}
