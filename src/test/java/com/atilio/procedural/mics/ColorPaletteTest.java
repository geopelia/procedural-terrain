package com.atilio.procedural.mics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ColorPaletteTest {
    @Test
    void testGetStringList() {
        char c = (char) 65;
        assertEquals('A', c);
        List<String> result = makeStringList();
        assertEquals("A", result.get(0));
        assertEquals("Z", result.get(25));
        assertEquals("AA", result.get(26));
        assertEquals("IV", result.get(255));
        assertEquals("AZ", result.get(51));

        assertEquals("HE", result.get(212));
        assertEquals("BA", result.get(52));

    }

    @Test
    void testGetElementsFromList() {

        int rounds = 210;
        Random random = new Random(111441L);
        List<String> words = makeStringList();
        Map<Integer, String> wordsUsed = new HashMap<>();
        for (int i = 0; i < rounds; i++) {
            int pos = random.nextInt(1000);
            while (wordsUsed.containsKey(pos)) {
                pos = random.nextInt(1000);
            }
            fillWords(pos, words, random, wordsUsed);
        }
        assertEquals(rounds, wordsUsed.size());
        Map<String, Integer> wordsOcurrenceMap = new HashMap<>();
        for (String string : wordsUsed.values()) {
            if (wordsOcurrenceMap.containsKey(string)) {
                fail("ya se agrego la palabre");
            } else {
                wordsOcurrenceMap.put(string, 1);
            }
        }

    }

    private void fillWords(int pos, List<String> letters, Random random, Map<Integer, String> lettersUsed) {
        String result = "";
        if (pos >= letters.size()) {
            boolean gotPos = false;
            int nextPos = -1;
            while (!gotPos) {
                nextPos = random.nextInt(letters.size());

                result = letters.get(nextPos);
                gotPos = !lettersUsed.containsKey(nextPos) && !lettersUsed.containsValue(result);
            }
            lettersUsed.put(pos, result);

        } else {
            result = letters.get(pos);
            if (lettersUsed.containsValue(result)) {
                boolean gotPos = false;
                int nextPos = -1;
                while (!gotPos) {
                    nextPos = random.nextInt(letters.size());

                    result = letters.get(nextPos);
                    gotPos = !lettersUsed.containsKey(nextPos) && !lettersUsed.containsValue(result);
                }

            }
            lettersUsed.put(pos, result);
        }

    }

    private List<String> makeStringList() {
        List<String> words = new ArrayList<>();
        int limit = 256;
        int laps = -1;
        int mod = -1;
        int capacity = (int) (Math.log(limit) / Math.log(26)) + 1;
        char[] word = new char[capacity];
        for (int i = 1; i <= 256; i++) {
            laps = i;
            while (laps > 0) {
                int temp = (laps - 1) / 26;
                int pos = (laps > 1) ? (int) (Math.log(laps - 1) / Math.log(26)) : 0;
                mod = (laps - 1) % 26;
                laps = temp;
                if (laps == 2 && pos < 0) {
                    fail("sksks");
                }
                word[pos] = (char) (mod + 65);

            }

            words.add(new String(word).trim());
        }
        return words;

    }

}
