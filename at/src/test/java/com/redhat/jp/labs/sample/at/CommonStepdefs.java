package com.redhat.jp.labs.sample.at;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CommonStepdefs {

    private static final Logger LOGGER = LoggerFactory.getLogger("CommonStepdefs");

    private static final Path TARGET_FILE_PATH = Paths.get("../frontend/src/assets/data/backlog.json");

    @Given("{int} 個の Product Backlog Item が登録されている")
    public void n個のProductBacklogItemが登録されている(int count) {
        replaceBacklogData(String.format("/data/%d.json", count));
    }

    @Given("一意の優先順位が設定された {int} 個の Product Backlog Item が登録されている")
    public void 一意の優先順位が設定された個のProductBacklogItemが登録されている(int count) {
        replaceBacklogData(String.format("/data/%d_prioritized.json", count));
    }

    @Given("以下の Product Backlog Item が登録されている")
    public void 以下のProductBacklogItemが登録されている(DataTable dataTable) {
        // TODO
        replaceBacklogData("/data/3.json");
    }

    @When("Product Backlog 画面を開く")
    public void productBacklog画面を開く() {
        open("/backlog");
    }

    @Then("Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される")
    public void productBacklog画面にて以下の通りにProductBacklogItemが一覧で表示される(DataTable dataTable) {
        productBacklog画面を開く();

        List<Map<String, String>> dataTableRows = dataTable.asMaps();
        ElementsCollection trCollection = $$("#product-backlog tbody tr");
        trCollection.shouldHaveSize(dataTableRows.size());
        for (int i = 0; i < dataTableRows.size(); i++) {
            SelenideElement tr = trCollection.get(i);
            Map<String, String> dataTableMap = dataTableRows.get(i);

            tr.$("td.name").should(Condition.text(dataTableMap.get("name")));

            if (dataTableMap.get("label") == null) {
                tr.$("td.labels").should(Condition.empty);
            } else {
                String[] collectLabels = dataTableMap.get("label").split(",");
                tr.$$("td.labels .badge").shouldHaveSize(collectLabels.length);
                tr.$$("td.labels .badge").shouldHave(CollectionCondition.exactTexts(collectLabels));
            }

            tr.$("td.story-point").should(Condition.text(dataTableMap.get("story point")));
        }
    }

    private void replaceBacklogData(String testDataFile) {
        try {
            URL url = getClass().getResource(testDataFile);
            Path file = Paths.get(url.toURI());
            Files.copy(file, TARGET_FILE_PATH, StandardCopyOption.REPLACE_EXISTING);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
