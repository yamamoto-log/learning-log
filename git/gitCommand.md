# Gitコマンド
- メールアドレス設定
  * $ git config --global user.email "メールアドレス"
- ユーザ設定
  * $ git config --global user.name "アカウント名"
- 設定の確認
  * $ git config --global --list

- ブランチの作成
  * $ git branch ブランチ名

- ブランチの切り替え
  * $ git switch ブランチ名

- ブランチの作成と切り替えを同時に行う
  * $ git switch -c ブランチ名

  - 変更ファイルをステージングエリアへ移動
  * $ git add ファイル名
  * $ git add . // すべてのファイル

- コミットする
  * $ git commit ファイル名 // vsCodeでコミットメッセージを入力。本文も書きたい場合
  * $ git commit -m "コミットメッセージ" ファイル名
  // ファイル名を指定しない場合、ステージングエリアにあるすべてのファイルが一括でコミットされる

- 上流ブランチの設定
  * $ git push -u origin ブランチ名 // はじめてそのブランチでpushするとき
  // push後はGitHubでmainブランチにmergeする

- 最新情報の取得
  * $ git fetch // リモート追跡リポジトリのみ最新にする

- リモート追跡ブランチ(origin/main)をローカルブランチ(main)に取り込む
  * $ git merge origin/main

- git fetch + git merge
  * $ git pull origin main // 上流ブランチを指定
  // 上流ブランチが指定されている場合は $ git pull のみで実行可

- ブランチの削除
  * $ git switch main
  * $ git branch -D ブランチ名
  * $ git push origin --delete ブランチ名

- コミットとpushの確認
  * $ git status -sb
  * $ git log origin/main..HEAD --oneline


