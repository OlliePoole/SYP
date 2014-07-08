// Students: don't touch this file!

/**
 *
 * @author Dr Sharon Curtis
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Xrefs {

    /** Allows the user to choose a text file and then produces
     *  a cross-reference of it.
     *
     *  @param args    command-line arguments, unused
     */
    public static void main(String args[]) {

        // setting up a little dialogue box,
        // to select the file to cross-reference
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose the file to cross-reference");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int fcReturnValue = fc.showOpenDialog(null);

        // now, which file did the user select?
        if (fcReturnValue != JFileChooser.APPROVE_OPTION) {
            // user must have cancelled, or an error occurred
            System.out.println("No file selected. Program terminating.");
        }
        else {
            // user selected a file ok
            System.out.println("************************************\n"
                    + "Producing a cross-reference for the file:\n"
                    + fc.getSelectedFile().getName()
                    + "\n************************************\n");
            try {
                BufferedReader fileReader = new BufferedReader
                        (new FileReader(fc.getSelectedFile()));

                String line = "";
                int lineCount = 0;
                int wordCount = 0;
                WordTree wt = null;
                // now to read in the file, a line at a time
                while ((line = fileReader.readLine()) != null) {

                    lineCount++;
                    System.out.println(lineCount + ": " + line);

                    // now to split the line up into separate words
                    // using a StringTokenizer object
                    StringTokenizer st = new StringTokenizer
                            (line, " ,./|?!()[]{};:<>*^\\\"\t\n\r\f");
                    while (st.hasMoreTokens()) {
                        String nextWord = st.nextToken();
                        wordCount++;
                        if (wt == null) {
                            wt = new WordTree(nextWord, lineCount);
                        } else {
                            wt.recordWord(nextWord, lineCount);
                        }
                    }


                }
                fileReader.close();

                System.out.println("\n");
                wt.display();
                System.out.println("\nnumber of words: " + wordCount);
                System.out.println("number of different words: "
                        + wt.numberOfEntries());

            } catch (Exception e) {
                System.out.println("An exception occurred:\n" + e.toString());
                e.printStackTrace();
            }
        }
    }
}
