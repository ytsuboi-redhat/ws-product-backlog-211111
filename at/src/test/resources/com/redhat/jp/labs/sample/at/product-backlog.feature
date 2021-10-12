Feature: Product Backlog の俯瞰的な可視化と Product Backlog Item の優先順位づけ
  各種 Sprint Planning または Product Backlog Refinement における Team の情報共有とディスカッションによる意思決定を効率化させる

  Scenario: Product Backlog Item は優先順位づけされた順序で一覧表示されること
    Given 一意の優先順位が設定された 5 個の Product Backlog Item が登録されている
    When  Product Backlog 画面を開く
    Then  5 個の Product Backlog Item が一覧で表示される
    And   以下の name の順序通りに Product Backlog Item が表示される
      | name        |
      | TODO:item_2 |
      | TODO:item_3 |
      | TODO:item_1 |
      | TODO:item_4 |
      | TODO:item_5 |

  @Ignore
  Scenario: Product Backlog Item の優先順位づけができること
    Given 一意の優先順位が設定された 5 個の Product Backlog Item が登録されている
    And   Product Backlog 画面を開く
    When  name が "TODO:item_3" の Product Backlog Item を 1 番目に移動する
    And   name が "TODO:item_1" の Product Backlog Item を 5 番目に移動する
    And   Product Backlog 画面を開く
    Then  以下の name の順序通りに Product Backlog Item が表示される
      | name        |
      | TODO:item_3 |
      | TODO:item_2 |
      | TODO:item_4 |
      | TODO:item_5 |
      | TODO:item_1 |

  Scenario: Product Backlog Item の id, name, label, story point が確認できること
    Given 以下の Product Backlog Item が登録されている
      | name         | label                     | story point |
      | TODO:item_11 | TODO:label_A              | 3           |
      | TODO:item_12 | TODO:label_A,TODO:label_B | 5           |
      | TODO:item_13 |                           | 1           |
    When  Product Backlog 画面を開く
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name         | label                     | story point |
      | TODO:item_11 | TODO:label_A              | 3           |
      | TODO:item_12 | TODO:label_A,TODO:label_B | 5           |
      | TODO:item_13 |                           | 1           |
    And   Product Backlog Item の id が自動的に重複無く採番され表示されていること