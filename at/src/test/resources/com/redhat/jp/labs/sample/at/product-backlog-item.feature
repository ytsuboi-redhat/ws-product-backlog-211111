Feature: Product Backlog Item の登録・編集・削除
  各種 Planning Event における Product Backlog Item の（登録）詳細化・分割・見積りに関する Team 協業の効果性・効率性を向上させる。

  Scenario: Product Backlog Item の name, label, story point, attachment が登録できること。
    Given Product Backlog Item 登録画面を開く
    When  Prodct Backlog Item として name, label, story point, attachment に以下を設定して登録する
      | name        | TODO:item_21          |
      | label       | TODO: label_A,label_C |
      | story point | 8                     |
      | attachment  | src/test/resources/attachment/product-backlog-item.feature/ui-img.png |
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name         | label                 | story point |
      | TODO:item_21 | TODO: label_A,label_C | 8           |
    And   name が "TODO:item_21" の Product Backlog Item 画面にて attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の name, label, story point, attachment が表示できること。
    Given 3個の Product Backlog Item が登録されている
    And   Product Backlog 画面を開く
    When  name が "TODO:item_1" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    Then  Prodcut Backlog Item 画面にて以下の通り name, label, story point が表示される
      | name        | TODO:item_1           |
      | label       | TODO: label_A,label_C |
      | story point | 8                     |
    And   attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の name, label, story point, attachment が更新できること。
    Given 3個の Product Backlog Item が登録されている
    And   Product Backlog 画面を開く
    And   name が "TODO:item_2" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    When  Prodct Backlog Item として name, label, story point, attachment に以下を設定して登録する
      | name        | TODO:item_2          |
      | label       | TODO: label_A,label_C,label_D |
      | story point | 5                     |
      | attachment  | src/test/resources/attachment/product-backlog-item.feature/ui-img.png |
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name        | label                         | story point |
      | TODO:item_1 | TODO: label_A,label_C         | 8           |
      | TODO:item_2 | TODO: label_A,label_C,label_D | 5           |
      | TODO:item_3 |                               | 3           |
    And   name が "TODO:item_2" の Product Backlog Item 画面にて attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の削除ができること。
    Given 3個の Product Backlog Item が登録されている
    And   Product Backlog 画面を開く
    When  name が "TODO:item_1" の Product Backlog Item を削除する
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name        | label                 | story point |
      | TODO:item_2 | TODO: label_A,label_C | 3           |
      | TODO:item_3 |                       | 3           |