package oy.tol.tira.books;

import java.io.*;

public class HashTable implements Book {
    private String bookFilePath;
    private String ignoreFilePath;
    private KeyValueHashTable<String, Integer> wordCounts;
    private WordFilter wordFilter;
    private int totalWordCount = 0;
    private int uniqueWordCount = 0;
    private int ignoredWordsTotal = 0;

    public HashTable() {
        this.wordCounts = new KeyValueHashTable<>(); 
        this.wordFilter = new WordFilter(); 
    }

    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
        this.bookFilePath = fileName;
        this.ignoreFilePath = ignoreWordsFile;
        verifyFileExists(bookFilePath);
        verifyFileExists(ignoreFilePath);
        try {
            wordFilter.readFile(ignoreFilePath); 
        } catch (IOException e) {
            throw new FileNotFoundException("Could not read ignore words file: " + ignoreWordsFile);
        }
    }

    private void verifyFileExists(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    @Override
    public void countUniqueWords() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(bookFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        }
        uniqueWordCount = wordCounts.size(); 
    }

    private void processLine(String line) {
        for (String word : line.split("\\P{IsAlphabetic}+")) {
            word = word.toLowerCase();
            if (word.length() > 1) {
                if (!wordFilter.shouldFilter(word)) {
                    Integer count = wordCounts.find(word);
                    if (count == null) {
                        uniqueWordCount++; 
                    }
                    count = (count == null) ? 1 : count + 1;
                    wordCounts.add(word, count);
                    totalWordCount++;
                } else {
                    ignoredWordsTotal++; 
                }
            }
        }
    }

    @Override
    public void report() {
        Pair<String, Integer>[] sortedWords = wordCounts.toSortedArray();
        for (int i = 0; i < Math.min(sortedWords.length, 100); i++) {
            String word = String.format("%-20s", sortedWords[i].getKey()).replace(' ', '.');
            System.out.format("%4d. %s %6d%n", i + 1, word, sortedWords[i].getValue());
        }

        System.out.println("\nStatistics:");
        System.out.println("Total number of words: " + totalWordCount);
        System.out.println("Number of unique words: " + uniqueWordCount);
        System.out.println("Number of words ignored: " + wordFilter.ignoreWordCount());
        System.out.println("Ignored words count in the book file: " + ignoredWordsTotal);
    }

    @Override
    public void close() {
        wordCounts = new KeyValueHashTable<>(); 
        wordFilter.close(); 
        totalWordCount = 0;
        uniqueWordCount = 0; 
        ignoredWordsTotal = 0; 
    }

    @Override
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }

    @Override
    public String getWordInListAt(int position) {
        Pair<String, Integer>[] sortedWords = wordCounts.toSortedArray();
        if (position >= 0 && position < sortedWords.length) {
            return sortedWords[position].getKey();
        }
        return null;
    }

    @Override
    public int getWordCountInListAt(int position) {
        Pair<String, Integer>[] sortedWords = wordCounts.toSortedArray();
        if (position >= 0 && position < sortedWords.length) {
            return sortedWords[position].getValue();
        }
        return -1;
    }
}