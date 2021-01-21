//Imports the necessary classes
import java.text.BreakIterator;
import java.util.*;
import java.nio.charset.*;
//Begins program
public class TextSummarizer
{
    /**Designed to score sentences
    @author Brennan Jacobs
    @version 1.0 (1/18/2021)
     */
    //Main method begins here
    public static void main(String[] args)
    {
        String file = "";
        //Fully sets up the scanner.
        Scanner input = new Scanner(System.in);
        //Asks the user for the title of the text
        System.out.print("What is the title? ");
        String title = input.nextLine();
        //Asks the user for the length of summary
        System.out.print("What is the length of the summary between 0 and 99? ");
        int length = input.nextInt();
        input.nextLine();
                System.out.printf("Please specify how many lines you want to enter: ");
            String[] temp = new String[input.nextInt()];
            input.nextLine(); //consuming the <enter> from input above

            for (int i = 0; i < temp.length; i++) {
                temp[i] = input.nextLine();
            }

            for (String s : temp) {
                file += s;
            }
        ArrayList <Sentence> sentences = new ArrayList();
        //Creates a space for the needed BreakIterator
        BreakIterator iteratorSentence =
            BreakIterator.getSentenceInstance(Locale.US);
        //Sets the iteratorSentence to file
        iteratorSentence.setText(file);
        //Sets start to first
        int start = iteratorSentence.first();
        for (int end = iteratorSentence.next();
            end != BreakIterator.DONE;
            start = end, end = iteratorSentence.next())
        {
            //Fills sentences appropriately
            sentences.add(new Sentence(file.substring(start, end), title));
        }
        int currentScore = 0;
        int highScore = 0;
        for(int i = 1; i < (int)((length/100) * sentences.size()); i++)
        {
            //Decides the ranking
            for (int j = 0; j < sentences.size(); j++)
            {
                currentScore = sentences.get(j).getScore();
                if (currentScore > sentences.get(highScore).getScore() &&
                    sentences.get(j).notRanked() == true)
                {
                    highScore = j;
                }
            }
            //Prints the current rank
            System.out.println(i + ". " + sentences.get(highScore).toString());
            sentences.get(highScore).setRank(false);
            highScore = 0;
        }
    }
}
