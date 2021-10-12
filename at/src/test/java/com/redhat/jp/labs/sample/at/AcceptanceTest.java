package com.redhat.jp.labs.sample.at;

import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.IOperationListener;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

import java.sql.DriverManager;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"html:target/cucumber-reports.html" }, monochrome = true, snippets = SnippetType.UNDERSCORE, tags = "not @Ignore")
public class AcceptanceTest {

	@ClassRule
	public static TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

	@BeforeClass
	public static void beforeClass() throws Exception {
		configureLogging();
		configureE2ETester();
	}

	private static void configureLogging() {
		// JUL -> SLF4J
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
	}

	private static void configureE2ETester() {
		Configuration.reportsFolder = "target/surefire-reports";
		if (System.getProperty("selenide.browser") == null) {
			Configuration.browser = WebDriverRunner.CHROME;
		}
		Configuration.headless = false;
	}

}
