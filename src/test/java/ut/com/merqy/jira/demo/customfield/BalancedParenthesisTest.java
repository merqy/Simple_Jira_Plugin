package ut.com.merqy.jira.demo.customfield;

import com.merqy.jira.demo.customfield.BalanceChecker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BalancedParenthesisTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    BalanceChecker balanceChecker = new BalanceChecker();

    @Test
    public void balanceTest() {

        List<String> balanced = new ArrayList<>();
        balanced.add("(some(balance(here)for)sure)");
        balanced.add("()()(())");
        balanced.add("(1(2(3)(4)(5)))");
        for (String b : balanced)
            assertTrue("Balanced parenthesis test failed.", balanceChecker.checkBalance(String.valueOf(balanced)));

    }

    @Test
    public void unbalanceTest(){

        List<String> unbalanced = new ArrayList<>();
        unbalanced.add("(no(balance here)for)sure)");
        unbalanced.add("())(");
        unbalanced.add("(1(2(3)(4)(5))");
        for (String ub : unbalanced)
            assertFalse("Unbalanced parenthesis test failed", balanceChecker.checkBalance(String.valueOf(unbalanced)));

    }

}
