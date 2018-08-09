package com.merqy.jira.demo.customfield;/*
package com.merqy.jira.demo.customfield;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.TextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BalancedParenthesis extends TextCFType {
    private static final Logger log = LoggerFactory.getLogger(BalancedParenthesis.class);

    public BalancedParenthesis(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager) {
    super(customFieldValuePersister, genericConfigManager);
}
    
    @Override
    public Map<String, Object> getVelocityParameters(final Issue issue,
                                                     final CustomField field,
                                                     final FieldLayoutItem fieldLayoutItem) {
        final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);

        // This method is also called to get the default value, in
        // which case issue is null so we can't use it to add currencyLocale
        if (issue == null) {
            return map;
        }

         FieldConfig fieldConfig = field.getRelevantConfig(issue);
         //add what you need to the map here

        return map;
    }
}
*/

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