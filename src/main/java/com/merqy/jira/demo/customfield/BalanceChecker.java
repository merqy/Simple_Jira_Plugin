package com.merqy.jira.demo.customfield;

import com.merqy.jira.demo.api.BalanceCheckerPlugin;

public class BalanceChecker implements BalanceCheckerPlugin {

    @Override
    public Boolean checkBalance(String string) {

        Integer stringLength = string.length();
        Integer countParenthesis = 0;

        for (int i = 0; i < stringLength; i++) {
            if (string.charAt(i) == '(') countParenthesis++;
            if (string.charAt(i) == ')') countParenthesis--;
            if (countParenthesis < 0) return false;
        }

        if (countParenthesis > 0) return false;
        else return true;
    }
}
