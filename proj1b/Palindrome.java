public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            returnDeque.addLast(character);
        }
        return returnDeque;
    }
    private boolean isPalindromeHelper(Deque<Character> helper) {
        while (helper.size() > 1) {
            return helper.removeFirst() == helper.removeLast() && isPalindromeHelper(helper);
        }
        return true;
    }
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }
    private boolean isPalindromeHelper(Deque<Character> helper, CharacterComparator cc) {
        while (helper.size() > 1) {
            return cc.equalChars(helper.removeFirst(), helper.removeLast())
                    && isPalindromeHelper(helper, cc);
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelper(wordToDeque(word), cc);
    }
}



