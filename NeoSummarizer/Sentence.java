import java.text.BreakIterator;
import java.util.*;
//Writes the Sentence class
public class Sentence
{
    //Writes the necessary private variables
    private static final String[] COMMONWORDS =
    {"the","of","and","a","to","in","is","you","that","it","he","was","for","on","are","as","with","his","they","I","at","be","this","have","from","or","one","had","by","word","but","not","what","all","were","we","when","your","can","said","there","use","an","each","which","she","do","how","their","if","will","up","other","about","out","many","then","them","these","so","some","her","would","make","like","him","into","time","has","look","two","more","write","go","see","number","no","way","could","people","my","than","first","water","been","call","who","oil","its","now","find","long","down","day","did","get","come","made","may","part"};
    private int score = 0;
    private String sentence = "";
    private String title = "";
    private boolean notUsed = true;
    public Sentence(String a, String b)
    {
        sentence = a;
        title = b;
        setScore();
    }
    public void setScore()
    {
        ArrayList <String> titleWords = new ArrayList();
        String tempWord = "";
        boolean isCommon = false;
        BreakIterator iteratorWords = BreakIterator.getWordInstance(Locale.US);
        iteratorWords.setText(title);
        int start = iteratorWords.first();
        for (int end = iteratorWords.next();
            end != BreakIterator.DONE;
            start = end, end = iteratorWords.next())
        {
           tempWord = title.substring(start, end).toLowerCase();
           for (int i = 0; i < COMMONWORDS.length; i++)
           {
               if (tempWord == COMMONWORDS[i])
               {
                   isCommon = true;
                   break;
               }
           }
           if (isCommon == false)
           {
               titleWords.add(tempWord);
           }
           isCommon = false;
        }
        iteratorWords.setText(sentence);
        start = iteratorWords.first();
        for (int end = iteratorWords.next();
            end != BreakIterator.DONE;
            start = end, end = iteratorWords.next())
        {
            tempWord = sentence.substring(start, end).toLowerCase();
            for (int j = 0; j < titleWords.size(); j++)
            {
                if (tempWord == titleWords.get(j))
                {
                    score++;
                }
            }
        }
    }
    public int getScore()
    {
        return score;
    }
    public boolean notRanked()
    {
        return notUsed;
    }
    public String toString()
    {
        return sentence;
    }
    public void setRank(boolean a)
    {
        notUsed = a;
    }
}
