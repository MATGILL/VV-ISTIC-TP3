package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

        @Test
        void testIsBalanced_emptyString() {
            assertTrue(isBalanced(""));
        }

        @Test
        void testIsBalanced_nullInput() {
            assertThrows(IllegalArgumentException.class, () -> isBalanced(null));// Null input
        }

        @Test
        void testIsBalanced_singleTypeBalanced() {
            assertTrue(isBalanced("()")); // Single type, balanced
        }

        @Test
        void testIsBalanced_singleTypeUnbalanced() {
            assertFalse(isBalanced("((")); // Single type, unbalanced
        }

        @Test
        void testIsBalanced_multipleTypeBalanced() {
            assertTrue(isBalanced("{[()]}")); // Multiple types, balanced
        }

        @Test
        void testIsBalanced_multipleTypeUnbalanced() {
            assertFalse(isBalanced("({[}])")); // Multiple types, unbalanced
        }

        @Test
        void testIsBalanced_nestedBalanced() {
            assertTrue(isBalanced("[{({[]})}]")); // Nested balanced brackets
        }

        @Test
        void testIsBalanced_nestedUnbalanced() {
            assertFalse(isBalanced("[{({[}])}]")); // Nested unbalanced brackets
        }

        @Test
        void testIsBalanced_mixedCharacterBalanced() {
            assertTrue(isBalanced("a(b)c{d}[e]")); // Mixed characters, balanced
        }

        @Test
        void testIsBalanced_mixedCharacterUnbalanced() {
            assertFalse(isBalanced("a(b{c)d}")); // Mixed characters, unbalanced
        }

        @Test
        void testIsBalanced_specialCharacterBalancedBracket() {
            assertTrue(isBalanced("@{[()]!}")); // Special characters with balanced brackets
        }

        @Test
        void testIsBalanced_specialCharacterWithUnbalancedBrackets() {
            assertFalse(isBalanced("@{[()!}]")); // Special characters with unbalanced brackets
        }

        @Test
        void testIsBalanced_noBrackets() {
            assertTrue(isBalanced("abc123")); // No brackets
        }

        @Test
        void testIsBalanced_longInputBalanced() {
            assertTrue(isBalanced("({[{}()]}){}[()]")); // Long input, balanced
        }

        @Test
        void testIsBalanced_longInputUnbalanced() {
            assertFalse(isBalanced("({[{}()]}){}[()]})")); // Long input, unbalanced
        }

        @Test
        void testIsBalanced_singleClosingBracketNoOpening() {
            assertFalse(isBalanced(")")); // Single closing bracket without opening
        }

        @Test
        void testIsBalanced_SingleOpeningBracketWithClosing() {
            assertFalse(isBalanced("(")); // Single opening bracket without closing
        }
    }