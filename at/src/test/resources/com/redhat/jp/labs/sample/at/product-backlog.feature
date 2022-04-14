Feature: Product Backlog の俯瞰的な可視化
  各種 Sprint Planning または Product Backlog Refinement における Team の情報共有とディスカッションによる意思決定を効率化させる

  Scenario: Product Backlog Item が一覧表示されること
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

  Scenario: Product Backlog Item の id, name, story point が確認できること
    Given 以下の Product Backlog Item が登録されている
      | name         | story point |
      | TODO:item_11 | 3           |
      | TODO:item_12 | 5           |
      | TODO:item_13 | 1           |
    When  Product Backlog 画面を開く
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name         | story point |
      | TODO:item_11 | 3           |
      | TODO:item_12 | 5           |
      | TODO:item_13 | 1           |
    And   Product Backlog Item の id が自動的に重複無く採番され表示されていること