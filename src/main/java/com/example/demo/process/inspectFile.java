package com.example.demo.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class inspectFile {

    private static int largestWordLength = 0;

    private static Map<String, String[]> allBadWords = new HashMap<String, String[]>();

    /**
     * Iterates over a String input and checks whether any curse words were found
     */
    public static String getCensoredText(final String input) {
        getCurseWords();
        if (input == null) {
            return "";
        }

        String modifiedInput = input;

        // remove leetspeak
      //  modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");

        // ignore any character that is not a letter
       modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

        ArrayList<String> badWordsFound = new ArrayList<>();

        for (int start = 0; start < modifiedInput.length(); start++) {
            for (int offset = 1; offset < (modifiedInput.length() + 1 - start) && offset < largestWordLength; offset++) {
                String wordToCheck = modifiedInput.substring(start, start + offset);
                if (allBadWords.containsKey(wordToCheck)) {
                    String[] ignoreCheck = allBadWords.get(wordToCheck);
                    boolean ignore = false;
                    for (int stringIndex = 0; stringIndex < ignoreCheck.length; stringIndex++) {
                        if (modifiedInput.contains(ignoreCheck[stringIndex])) {
                            ignore = true;
                            break;
                        }
                    }

                    if (!ignore) {
                        badWordsFound.add(wordToCheck);
                    }
                }
            }
        }

        String inputToReturn = input;
        for (String swearWord : badWordsFound) {
            char[] charsStars = new char[swearWord.length()];
            Arrays.fill(charsStars, '*');
            final String stars = new String(charsStars);

            inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);
        }

        return inputToReturn;
    } // end getCensoredText

    /**
    *Returns True , if the input string contains curse words
    */
    public static ArrayList<String> scanCurseWords (final String input) {

        ArrayList<String> curseWordsFound = new ArrayList<>();
        String changedInput = input;

        if (input == null) {
            return null;
        }
        getCurseWords();
        changedInput = input.toLowerCase();
       // changedInput = changedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

        // iterate over each letter in the word
        for (int start = 0; start < changedInput.length(); start++) {
            for (int offset = 1; offset < (changedInput.length() + 1 - start) && offset < largestWordLength; offset++) {
                String wordToCheck = changedInput.substring(start, start + offset);
                if (allBadWords.containsKey(wordToCheck)) {
                    String[] ignoreCheck = allBadWords.get(wordToCheck);
                    boolean ignore = false;
                    for (int stringIndex = 0; stringIndex < ignoreCheck.length; stringIndex++) {
                        if (changedInput.contains(ignoreCheck[stringIndex])) {
                            ignore = true;
                            break;
                        }
                    }

                    if (!ignore) {
                        curseWordsFound.add(wordToCheck);
                    }
                }
            }
        }
        return curseWordsFound;
    }

    /**
     * Retreives the curse words from a File from a URL
     */
    private static void getCurseWords() {
        int readCounter = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv").openConnection().getInputStream()));

            String currentLine = "";
            while ((currentLine = reader.readLine()) != null) {
                readCounter++;
                String[] content = null;
                try {
                    if (1 == readCounter) {
                        continue;
                    }

                    content = currentLine.split(",");
                    if (content.length == 0) {
                        continue;
                    }

                    final String word = content[0];

                    if (word.startsWith("-----")) {
                        continue;
                    }

                    if (word.length() > largestWordLength) {
                        largestWordLength = word.length();
                    }

                    String[] ignore_in_combination_with_words = new String[] {};
                    if (content.length > 1) {
                        ignore_in_combination_with_words = content[1].split("_");
                    }
                    allBadWords.put(word.replaceAll(" ", "").toLowerCase(), ignore_in_combination_with_words);
                } catch (Exception except) {
                }
            }
        } catch (IOException except) {
        }
    }

}