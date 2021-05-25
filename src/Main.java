import java.util.*;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        //String str = "I love to work in global logic!";

        int sizeWithoutSpecial = countAllChars(str);

        String sentence = returnRawStr(str);
        int logicChars = countAllLogicChars(sentence.toLowerCase());

        System.out.println("The length of the string without spaces or special characters: " + sizeWithoutSpecial);
        System.out.println("Number of characters from the logic pool: " + logicChars);

        List<String> list;
        list = getRawWords(sentence);

        System.out.println("==========");
        list.sort(Comparator.comparingInt(String::length));
        Map<Integer, List<String>> map = new HashMap<>();


        for (String word : list) {
            map.computeIfAbsent(word.length(), k -> new ArrayList<>());
            map.get(word.length()).add(word);

            System.out.println("(" + word.length() + ")" + word);
        }

        System.out.println("==========");

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
            //System.out.println(entry.getKey() + ":" + entry.getValue());

            for (String word: entry.getValue()) {
                int count = countAllLogicChars(entry.getValue().get(0).toLowerCase());
                double freq = (Math.round(((double) count/(double) logicChars)*100));
                System.out.println("{" + word + "," + entry.getKey() + "} = " + freq/100 + " ("+count+"/"+logicChars+")" );
            }


        }
        System.out.println("==========");
        double TotalFrequency = (Math.round(((double) logicChars/(double) sizeWithoutSpecial)*100));
        System.out.println("Total Frequency: " + TotalFrequency/100);
    }

    public static int countAllChars(String set) {
        set = set.replaceAll("[^\\\\dA-Za-z0-9]+", "");

        return set.length();
    }

    public static int countAllLogicChars(String set) {
        int count = 0;
        for (char c : set.toCharArray()) {
            if (c == 'l') {
                count++;
            } else if (c == 'o') {
                count++;
            } else if (c == 'g') {
                count++;
            } else if (c == 'i') {
                count++;
            } else if (c == 'c') {
                count++;
            }
        }

        return count;
    }

    public static String returnRawStr(String set) {
        set = set.replaceAll("[^\\\\dA-Za-z0-9]+", " ");
        return set;
    }

    public static ArrayList<String> getRawWords(String s) {
        String[] tab = s.split(" ");

        return new ArrayList<>(Arrays.asList(tab));
    }

}