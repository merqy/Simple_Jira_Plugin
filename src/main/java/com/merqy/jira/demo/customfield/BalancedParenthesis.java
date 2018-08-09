package com.merqy.jira.demo.customfield;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.merqy.jira.demo.api.BalanceCheckerPlugin;

@ExportAsService({BalanceCheckerPlugin.class})
@Scanned
public class BalancedParenthesis extends AbstractSingleFieldType<String> {

    public BalancedParenthesis(@ComponentImport
                                       CustomFieldValuePersister customFieldValuePersister,
                               @ComponentImport
                                       GenericConfigManager genericConfigManager){
        super(customFieldValuePersister, genericConfigManager);
    }

    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_LIMITED_TEXT;
    }

    @Override
    protected Object getDbValueFromObject(String s) {
        return getStringFromSingularObject(s);
    }

    @Override
    protected String getObjectFromDbValue(Object o) throws FieldValidationException {
        return getSingularObjectFromString((String) o);
    }

    @Override
    public String getStringFromSingularObject(String singularObject) {
        return singularObject;
    }

    @Override
    public String getSingularObjectFromString(String string) throws FieldValidationException {

        BalanceChecker checkerPlugin = new BalanceChecker();

        if (!checkerPlugin.checkBalance(string))
            throw new FieldValidationException("Parenthesis is unbalanced!");

        return string;

    }
}