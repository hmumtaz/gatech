import java.util.ArrayList;
import java.util.List;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class StringSearching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm that relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the failure table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text)
        throws IllegalArgumentException {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("text or Pattern is null");
        }

        List<Integer> finList = new ArrayList<>();
        int tLen = text.length();
        int tCheck = text.length() - pattern.length() + 1;
        int pLen = pattern.length();
        if (pLen == 0) {
            throw new IllegalArgumentException("text or Pattern is empty");
        }

        int[] fail = buildFailureTable(pattern);
        int tCount = 0;
        int pCount = 0;
        while (tCount < tCheck) {
            //Special Case: pLen == 1
            int txt = text.charAt(tCount);
            if (pLen == 1) {
                if (txt == pattern.charAt(0)) {
                    finList.add(tCount);
                }
                tCount++;
            } else if (txt == pattern.charAt(pCount)) {
                if (pCount == pLen - 1) {
                    finList.add(tCount - pLen + 1);
                    pCount = fail[pCount - 1]; //reset pattern count
                }
                tCount++;
                pCount++;
            } else if (pCount > 0) {
                pCount = fail[pCount - 1];
            } else {
                tCount++;
            }
        }
        //Special Case: tCount > tCheck but pCount is still active
        //indicating another match might be possible
        while (pCount > 0 && tCount < tLen) {
            int txt = text.charAt(tCount);
            if (txt == pattern.charAt(pCount)) {
                if (pCount == pLen - 1) {
                    finList.add(tCount - pLen + 1);
                    pCount = fail[pCount - 1]; //reset pattern count
                }
                tCount++;
                pCount++;
            } else if (pCount > 0) {
                pCount = fail[pCount - 1];
            } else {
                tCount++;
            }
        }

        return finList;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will be the largest prefix of the pattern
     * indices [0..i] that is also a suffix of the pattern indices [1..i].
     * This means that index 0 of the returned table will always be equal to 0
     *
     * Ex. ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building failure table for
     * @return integer array of size text.length that you are building a failure
     * table for
     */
    public static int[] buildFailureTable(CharSequence pattern)
        throws IllegalArgumentException {
        if (pattern == null) {
            throw new IllegalArgumentException("Empty Pattern");
        }

        int counter = pattern.length();
        int[] fail = new int[counter];
        int idx = 1;
        int pos = 0;
        while (idx < counter) {
            if (pattern.charAt(idx) == pattern.charAt(pos)) {
                fail[idx] = pos + 1;
                idx++;
                pos++;
            } else if (pos > 0) {
                pos = fail[pos - 1];
            } else {
                idx++;
            }
        }
        return fail;
    }

    /**
     * Boyer Moore algorithm that relies on last table. Works better with large
     * alphabets.
     *
     * Make sure to implement the table before implementing this method.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern the pattern you are searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
            CharSequence text) throws IllegalArgumentException {
        if (text == null || pattern == null) {
            throw new IllegalArgumentException("text or Pattern is null");
        }

        List<Integer> finList = new ArrayList<>();
        int tLen = text.length();
        int pLen = pattern.length();
        if (pLen == 0) {
            throw new IllegalArgumentException("Pattern is empty");
        }

        int[] uCodeArr = buildLastTable(pattern);
        int pPos = pLen - 1;
        int tPos = pLen - 1;


        while (tPos < tLen) {
            int txt = text.charAt(tPos);
            if (pLen == 1) {
                if (txt == pattern.charAt(0)) {
                    finList.add(tPos);
                }
                tPos++;
            } else {
                if (txt == pattern.charAt(pPos)) {
                    if (pPos == 0) {
                        finList.add(tPos);
                        tPos += 2 * (pLen - 1);
                        pPos = pLen - 1;
                    } else {
                        tPos--;
                        pPos--;
                    }
                } else {
                    tPos += pLen - Math.min(pPos, 1 + uCodeArr[txt]);
                    pPos = pLen - 1;
                }
            }
        }
        return finList;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table[x].
     * Each entry should be -1 if x is not in the pattern or the last index of x
     * where x is a particular character in your pattern.
     *
     * Ex. octocat
     *
     * table[o] = 3
     * table[c] = 4
     * table[t] = 6
     * table[a] = 5
     * table[everything else] = -1
     *
     * HINT: Characters auto cast to their corresponding int in Unicode (UTF-16)
     *
     * @throws IllegalArgumentException if the pattern is null
     * @param pattern a {@code CharSequence} you are building last table for
     * @return integer array of size {@code (Character.MAX_VALUE + 1)}
     * containing the mapping for all characters in Unicode
     */
    public static int[] buildLastTable(CharSequence pattern)
        throws IllegalArgumentException {
        if (pattern == null) {
            throw new IllegalArgumentException("null pattern");
        }

        int[] uCodeArr = new int[Character.MAX_VALUE + 1];

        for (int x = 0; x < uCodeArr.length; x++) {
            uCodeArr[x] = -1;
        }

        for (int y = 0; y < pattern.length(); y++) {
            int idx = pattern.charAt(y);
            uCodeArr[idx] = y;
        }

        return uCodeArr;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 433;

    /**
     * Runs Rabin-Karp algorithm. Generate initial hash, and compare it with
     * hash from substring of text same length as pattern. If the two
     * hashes match compare their individual characters, else update hash
     * and continue.
     *
     * @throws IllegalArgumentException if the pattern is null or of length 0
     * @throws IllegalArgumentException if text is null
     * @param pattern a string you're searching for in a body of text
     * @param text the body of text where you search for pattern
     * @return list of integers representing the first index a match occurs or
     * an empty list if the text is of length 0
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
            CharSequence text) throws IllegalArgumentException {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("Pattern is null or empty");
        } else if (text == null) {
            throw new IllegalArgumentException("Text is null");
        } else {
            List<Integer> rkList = new ArrayList<>();
            int tLen = text.length();
            int pLen = pattern.length();
            int tPos = pattern.length() - 1;
            int tStart = tPos - pLen + 1;

            int patHash = generateHash(pattern, pLen);
            int txtHash = generateHash(text, pLen);
            while (tPos < tLen - 1) {
                if (patHash == txtHash) {
                    rkList.add(tStart);
                }
                tPos++;
                txtHash = updateHash(txtHash, pLen, text.charAt(tStart),
                        text.charAt(tPos));
                tStart++;
            }
            if (patHash == txtHash) {
                rkList.add(tStart);
            }

            return rkList;
        }
    }

    /**
     * Hash function used for Rabin-Karp. The formula for hashing a string is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i), where c is the integer
     * value of the current character, and i is the index of the character
     *
     * For example: Hashing "bunn" as a substring of "bunny" with base 433 hash
     * = b * 433 ^ 3 + u * 433 ^ 2 + n * 433 ^ 1 + n * 433 ^ 0 = 98 * 433 ^ 3 +
     * 117 * 433 ^ 2 + 110 * 433 ^ 1 + 110 * 433 ^ 0 = 7977892179
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if current is null
     * @throws IllegalArgumentException if length is negative or greater
     *     than the length of current
     * @param current substring you are generating hash function for
     * @param length the length of the string you want to generate the hash for,
     * starting from index 0. For example, if length is 4 but current's length
     * is 6, then you include indices 0-3 in your hash (and pretend the actual
     * length is 4)
     * @return hash of the substring
     */
    public static int generateHash(CharSequence current, int length)
            throws IllegalArgumentException {
        if (current == null) {
            throw new IllegalArgumentException("Current is null");
        } else if (length > current.length() || length < 0) {
            throw new IllegalArgumentException("Length is not appropriate");
        } else {
            int hash = 0;
            for (int i = 0; i < length; i++) {
                hash += current.charAt(i) * power(BASE, (length - 1 - i));
            }
            return hash;
        }
    }

    /**
     * Power function
     * @param base gets exponentiated
     * @param exponent indicates exponent for base
     * @return exponentiated base
     */
    private static int power(int base, int exponent) {
        int ans = 1;
        if (exponent != 0) {
            for (int i = 1; i <= exponent; i++) {
                ans *= base;
            }
        }
        return ans;
    }

    /**
     * Updates a hash in constant time to avoid constantly recalculating
     * entire hash. To update the hash:
     *
     *  remove the oldChar times BASE raised to the length - 1, multiply by
     *  BASE, and add the newChar.
     *
     * For example: Shifting from "bunn" to "unny" in "bunny" with base 433
     * hash("unny") = (hash("bunn") - b * 433 ^ 3) * 433 + y * 433 ^ 0 =
     * (7977892179 - 98 * 433 ^ 3) * 433 + 121 * 433 ^ 0 = 9519051770
     *
     * The computation of BASE raised to length - 1 may require O(n) time,
     * but the method should otherwise run in O(1).
     *
     * Do NOT use {@code Math.pow()} in this method.
     *
     * @throws IllegalArgumentException if length is negative
     * @param oldHash hash generated by generateHash
     * @param length length of pattern/substring of text
     * @param oldChar character we want to remove from hashed substring
     * @param newChar character we want to add to hashed substring
     * @return updated hash of this substring
     */
    public static int updateHash(int oldHash, int length, char oldChar,
            char newChar) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("length is negative");
        } else {
            int newHash = oldHash - (oldChar * power(BASE, length - 1));
            newHash = newHash * BASE;
            newHash += newChar;
            return newHash;
        }
    }
}