package it.com.merqy.jira.demo;

import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.merqy.jira.demo.customfield.BalancedParenthesis;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

@RunWith(AtlassianPluginsTestRunner.class)
public class MyComponentWiredTest
{

    private final ApplicationProperties applicationProperties;
    private final BalancedParenthesis balanceCheckerPlugin;

    public MyComponentWiredTest(@JiraImport ApplicationProperties applicationProperties,
                                @ComponentImport BalancedParenthesis balanceCheckerPlugin)
    {
        this.applicationProperties = applicationProperties;
        this.balanceCheckerPlugin = balanceCheckerPlugin;
    }

    @Test
    public void testComponentExist()
    {
        assertNotNull(applicationProperties);
        assertNotNull(balanceCheckerPlugin);
    }

    @Test(expected = FieldValidationException.class)
    public void testCustomField()
    {
        balanceCheckerPlugin.getSingularObjectFromString("-1");
    }


}
