import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisTestPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("w"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test
    public void testOffByOnePalindrome() {
        assertTrue(palindrome.isPalindrome("flake", new OffByOne()));
        assertTrue(palindrome.isPalindrome("", new OffByOne()));
        assertTrue(palindrome.isPalindrome("w", new OffByOne()));
    }
    @Test
    public void testOffByNPalindrome() {
        assertTrue(palindrome.isPalindrome("af", new OffByN(5)));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("w"));
    }
}
