Feature: Product Backlog の俯瞰的な可視化と Product Backlog Item の優先順位付け
  これにより各種 Sprint Planning または Product Backlog Refinement における Team の情報共有とディスカッションによる意思決定を効率化させる。

  Scenario: Product Backlog Item の一覧が確認できること。
    Product Backlog 画面にアクセスすると、作成されている　Product Backlog Item がリストで表示される。
    Given 5個の Product Backlog Item が登録されている
    When  Product Backlog 画面を開く
    Then  5個の Product Backlog Item が一覧で表示される

  Scenario: Product Backlog Item は優先順位づけされた順序で表示されること。
    Given 一意の優先順位が設定された5個の Product Backlog Item が登録されている
    When  Product Backlog 画面を開く
    Then  5個の Product Backlog Item が一覧で表示される
    And   Product Backlog Item の一覧は上から優先順位が高い順に並んでいる

  Scenario: Product Backlog Item の優先順位付けができること。
    Given 一意の優先順位が設定された5個の Product Backlog Item が登録されている
    And   Product Backlog 画面を開く
    When  以下の順序通りに Product Backlog Item の一覧を並び変える
      | name        |
      | TODO:item_3 |
      | TODO:item_2 |
      | TODO:item_4 |
      | TODO:item_5 |
      | TODO:item_1 |
    Then  以下のnameの順序通りに Product Backlog Item が表示される
      | name        |
      | TODO:item_3 |
      | TODO:item_2 |
      | TODO:item_4 |
      | TODO:item_5 |
      | TODO:item_1 |

  Scenario: Product Backlog Item の id, name, label, story point が確認できること。
    Given 以下の Product Backlog Item が登録されている
      | name         | label                 | story point |
      | TODO:item_11 | TODO: label_A         | 3           |
      | TODO:item_12 | TODO: label_A,label_B | 5           |
      | TODO:item_13 | (None)                | 1           |
    When  Product Backlog 画面を開く
    Then  以下の通りに Product Backlog Item が表示される
      | name         | label                 | story point |
      | TODO:item_11 | TODO: label_A         | 3           |
      | TODO:item_12 | TODO: label_A,label_B | 5           |
      | TODO:item_13 |                       | 1           |
    And   3つの Product Backlog Item の id が自動的に重複無く採番され表示されていること