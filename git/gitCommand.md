# Gitコマンド
- メールアドレス設定
  * $ git config --global user.email "メールアドレス"
- ユーザ設定
  * $ git config --global user.name "アカウント名"
- 設定の確認
  * $ git config --global --list

- ブランチの削除
  * $ git switch main
  * $ git branch -D myedit
  * $ git push origin --delete myedit