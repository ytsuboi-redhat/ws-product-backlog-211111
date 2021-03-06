@Ignore
Feature: Product Backlog Item の登録・編集・削除
  各種 Planning Event における Product Backlog Item の（登録）詳細化・分割・見積りに関する Team 協業の効果性・効率性を向上させる

  Background:
    Given Product Backlog 画面を開く

  Scenario: Product Backlog Item の name, label, description, story point, memo, attachment が登録できること
    Given Product Backlog Item 登録画面を開く
    When  Prodct Backlog Item として name, label, description, story point, memo, attachment に以下を設定して登録する
      | name        | TODO:item_21              |
      | label       | TODO:label_A,TODO:label_C |
      | description | TODO:description          |
      | story point | 8                         |
      | memo        | TODO:memo                 |
      | attachment  | src/test/resources/attachment/product-backlog-item.feature/ui-img.png |
    And   name が "TODO:item_21" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    Then  Prodcut Backlog Item 画面にて以下の通り name, label, description, story point, memo が表示される
      | name        | TODO:item_21              |
      | label       | TODO:label_A,TODO:label_C |
      | description | TODO:description_21       |
      | story point | 8                         |
      | memo        | TODO:memo_21              |
    And   attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の name, label, description, story point, memo, attachment が表示できること
    Given 3 個の Product Backlog Item が登録されている
    When  name が "TODO:item_1" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    Then  Prodcut Backlog Item 画面にて以下の通り name, label, description, story point, memo が表示される
      | name        | TODO:item_1               |
      | label       | TODO:label_A,TODO:label_C |
      | description | TODO:description_1        |
      | story point | 8                         |
      | memo        | TODO:memo_1               |
    And   attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の name, label, description, story point, memo, attachment が更新できること
    Given 3 個の Product Backlog Item が登録されている
    And   name が "TODO:item_2" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    When  Prodct Backlog Item として name, label, description, story point, memo, attachment に以下を設定して登録する
      | name        | TODO:item_2                            |
      | label       | TODO:label_A,TODO:label_C,TODO:label_D |
      | description | TODO:description_2                     |
      | story point | 5                                      |
      | memo        | TODO:memo_2                            |
      | attachment  | src/test/resources/attachment/product-backlog-item.feature/ui-img.png |
    And   name が "TODO:item_2" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    Then  Prodcut Backlog Item 画面にて以下の通り name, label, description, story point, memo が表示される
      | name        | TODO:item_2                            |
      | label       | TODO:label_A,TODO:label_C,TODO:label_D |
      | description | TODO:description_2                     |
      | story point | 5                                      |
      | memo        | TODO:memo_2                            |
    And   attachment に "src/test/resources/attachment/product-backlog-item.feature/ui-img.png" の画像が表示される

  Scenario: Product Backlog Item の削除ができること
    Given 3 個の Product Backlog Item が登録されている
    And   name が "TODO:item_1" の Product Backlog Item を選択して Prodcut Backlog Item 画面を開く
    When  Product Backlog Item を削除する
    Then  Product Backlog 画面にて以下の通りに Product Backlog Item が一覧で表示される
      | name        | label                 | story point |
      | TODO:item_2 | TODO: label_A,label_C | 3           |
      | TODO:item_3 |                       | 3           |

  Scenario Outline: Product Backlog Item の登録において Story Point は 1, 2, 3, 5, 8, 13, 21, ? が設定可能であること
    Given Product Backlog Item 登録画面を開く
    When  story point 項目に "<story point>" を入力する
    Then  story point 項目のエラーメッセージが表示されない
    And   update ボタンはクリック可能である
    Examples:
      | story point |
      | 1           |
      | 2           |
      | 3           |
      | 5           |
      | 8           |
      | 13          |
      | 21          |
      | ?           |

  Scenario Outline: Product Backlog Item の登録において Story Point は 1, 2, 3, 5, 8, 13, 21, ? 以外は設定不可能であること
    Given Product Backlog Item 登録画面を開く
    When  story point 項目に "<story point>" を入力する
    Then  story point 項目のエラーメッセージとして "入力された story point は無効です" が表示される
    And   update ボタンはクリック可能ではない
    Examples:
      | story point |
      | 0           |
      | 4           |
      | 6           |
      | 7           |
      | 9           |
      | 10          |
      | 11          |
      | 12          |
      | 14          |
      | 15          |
      | 16          |
      | 17          |
      | 18          |
      | 19          |
      | 20          |
      | 22          |
      | 23          |
      | 24          |
      | 25          |
      | 26          |
      | 27          |
      | 28          |
      | 29          |
      | 30          |
      | 31          |
      | 32          |
      | 33          |
      | 34          |

