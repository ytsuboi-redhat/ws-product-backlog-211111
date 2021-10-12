package com.redhat.jp.labs.sample.at;

import com.codeborne.selenide.*;
import io.cucumber.datatable.*;
import io.cucumber.java.en.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductBacklogItemStepdefs {

    @Given("Product Backlog Item 登録画面を開く")
    public void productBacklogItem登録画面を開く() {
        $("#register-button").click();
    }

    @When("Prodct Backlog Item として name, label, story point, attachment に以下を設定して登録する")
    public void prodctBacklogItemとしてNameLabelStoryPointAttachmentに以下を設定して登録する(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        $("#name").setValue(dataMap.get("name"));
        $("#labels").setValue(dataMap.get("label").replaceAll(",", " "));
        $("#story-point").setValue(dataMap.get("story point"));
        File attachmentFile = new File(dataMap.get("attachment"));
        $("#attachment").setValue(attachmentFile.getAbsolutePath());

        $("#register-button").click();
    }

    @And("Product Backlog Item 画面にて name が {string} の attachment に {string} の画像が表示される")
    public void productBacklogItem画面にてNameがのAttachmentにの画像が表示される(String name, String collectAttachmentFilePath) throws IOException {
        openBacklogItem(name);
        assertAttachment(collectAttachmentFilePath);
    }

    @When("name が {string} の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く")
    public void nameがのProductBacklogItemを選択してProdcutBacklogItem画面を開く(String name) {
        openBacklogItem(name);
    }

    private void openBacklogItem(String name) {
        ElementsCollection trCollection = $$("#product-backlog tbody tr");
        for (int i = 0; i < trCollection.size(); i++) {
            SelenideElement tr = trCollection.get(i);
            if (name.equals(tr.$("td.name").text())) {
                tr.doubleClick();
                break;
            }
        }
    }

    private void assertAttachment(String collectAttachmentFilePath) {
        try {
            $("#attachment img").should(Condition.visible);
            File actualAttachment = $("#attachment img").download();
            File expectedAttachment = new File(collectAttachmentFilePath);
            byte[] actualBytes = Files.readAllBytes(actualAttachment.toPath());
            byte[] expectedBytes = Files.readAllBytes(expectedAttachment.toPath());
            assertThat(actualBytes).contains(expectedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("Prodcut Backlog Item 画面にて以下の通り name, label, story point が表示される")
    public void prodcutBacklogItem画面にて以下の通りNameLabelStoryPointが表示される(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        $("#product-backlog-item .name").should(Condition.text(dataMap.get("name")));

        if (dataMap.get("label") == null) {
            $("#product-backlog-item .labels").should(Condition.empty);
        } else {
            String[] collectLabels = dataMap.get("label").split(",");
            $$("#product-backlog-item .labels .badge").shouldHaveSize(collectLabels.length);
            $$("#product-backlog-item .labels .badge").shouldHave(CollectionCondition.exactTexts(collectLabels));
        }

        $("#product-backlog-item .story-point").should(Condition.text(dataMap.get("story point")));
    }

    @And("attachment に {string} の画像が表示される")
    public void attachmentにの画像が表示される(String collectAttachmentFilePath) {
        assertAttachment(collectAttachmentFilePath);
    }

    @When("Product Backlog Item を削除する")
    public void productBacklogItemを削除する() {
        $("#delete-button").click();
    }

    @When("story point 項目に {string} を入力する")
    public void storyPoint項目にを入力する(String storyPoint) {
        $("#story-point").setValue(storyPoint);
    }

    @Then("story point 項目のエラーメッセージとして {string} が表示される")
    public void storyPoint項目のエラーメッセージとしてが表示される(String message) {
        $("#story-point-error").should(Condition.visible);
        $("#story-point-error").should(Condition.text(message));
    }

    @Then("story point 項目のエラーメッセージが表示されない")
    public void storyPoint項目のエラーメッセージが表示されない() {
        $("#story-point-error").shouldNot(Condition.visible);
    }

    @And("update ボタンはクリック可能である")
    public void updateボタンはクリック可能である() {
        $("#update-button").should(Condition.enabled);
    }

    @And("update ボタンはクリック可能ではない")
    public void updateボタンはクリック可能ではない() {
        $("#update-button").should(Condition.disabled);
    }
}
