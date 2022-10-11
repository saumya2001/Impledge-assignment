import java.util.*;
import java.io.*;

public class LongestCompounded {
    public static void main(String[] args) throws FileNotFoundException {


        long initial_Time = System.currentTimeMillis();

        // to read input from a file
        File file = new File("src/Input_02.txt");
        //Trie Creation
        Trie tri = new Trie();
        LinkedList<Pair<String>> que = new LinkedList<Pair<String>>();
        //created to calculate the total amount of compound words
        HashSet<String> compound_Word = new HashSet<>();

        //Scan the file
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(file);

        String ss;
        List<Integer> sufix_indices;

        while(sc.hasNext()) {
            ss = sc.next();
            sufix_indices = tri.getSuffixesStartIndices(ss);

            for(int i: sufix_indices){
                if(i>=ss.length()) {
                    break;
                }
                que.add(new Pair<String>(ss,ss.substring(i)));

            }
            tri.insert(ss);

        }

        Pair<String> pr;        // a pair of word and its remaining suffix
        int max_Length = 0;      // longest compound word length
        String largest = "";
        String sec_largest = "";

        while(!que.isEmpty()){
            pr = que.removeFirst();
            ss = pr.second();
            sufix_indices = tri.getSuffixesStartIndices(ss);

            if(sufix_indices.isEmpty())
                continue;

            for(int i: sufix_indices) {
                if(i>ss.length())
                    break;
                if(i == ss.length()){
                    if(pr.first().length() > max_Length){
                        sec_largest = largest;
                        max_Length = pr.first().length();
                        largest = pr.first();
                    }
                    compound_Word.add(pr.first());
                } else {
                    que.add(new Pair<>(pr.first(),ss.substring(i)));
                }
            }
        }


        long last_Time = System.currentTimeMillis();


        System.out.println("Longest Compound Word: "+ largest);
        System.out.println("Second Longest Compound Word: "+ sec_largest);
        System.out.println("Total Number of Compound Words: "+ compound_Word.size());
        System.out.println("Total time taken to process the file: "+ (last_Time - initial_Time) + " milliseconds");

    }
}
