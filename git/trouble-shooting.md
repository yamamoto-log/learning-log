# 🔰 Gitトラブルシューティング & リポジトリ構造の整理

## 1. ディレクトリ構造と `.gitkeep` の役割
* **空ディレクトリの追跡**
  * Git はファイルが存在しない空フォルダ（ディレクトリ）を認識・管理（追跡）できない。
  * フォルダ構造だけを保持したい場合は、中に `.gitkeep` という空ファイルを作成する。
  ```bash
  mkdir -p git linux php docker
  touch git/.gitkeep linux/.gitkeep php/.gitkeep docker/.gitkeep

## 2.意図しない場所へのディレクトリ作成と削除 (`git rm`)
* **間違えて別のリポジトリ内で作成・コミットした場合**
  * Git の管理・追跡対象からも外すために git rm -r を使用する。
  ```bash
  # 誤って作成したフォルダをGitの管理から一括削除
  git rm -r git linux php docker
  git commit -m "fix: 誤って作成した学習用ディレクトリを削除"
  git push origin main
  ```


## 3. 親フォルダで誤って `git init` した際のエラー対処
* **症状**
  * ターミナルのプロンプト表示に (refactor/diretory-clean) などの見覚えのないブランチ名が残り続ける。
  * git switch main や git branch -d を試しても fatal: invalid reference や branch not found になる。
* **原因**
  * ホームディレクトリ（~）や Documents 直下など、意図しない上位の階層で git init を実行してしまっていた。
* **対処法**
  1. 上位階層（~ や Documents）に移動し、誤って作成された .git 隠しフォルダを削除する。
  ```bash
  cd ~
  rm -rf .git
  ```
  2. プロンプトのブランチ表示が消えたことを確認し、本来の作業用ディレクトリに移動し直す。


## 4. よく使うコマンドとオプションの備忘録
  * mkdir -p : Parent。親ディレクトリも含めて一括作成する。すでに存在していてもエラーを出さずにスキップする。
  * git branch -M main : Move。既存のブランチ名を強制的に main へ変更する（-M は強行指定）。  
<br>
<br>
<br>


# 📝 Gitトラブルシューティング：複数端末（自宅・学校）でのブランチ同期の不具合と強制解決

## 🔍 1. 発生した事象
自宅PCで新しく作成したファイルを `myedit` ブランチとして GitHub に `push` し、プルリクエストを作成した。
翌日、学校PCでその変更を取り込もうとしたが、以下のチグハグな現象が発生した。

1. 学校PCのターミナルで `git pull` を実行しても `Already up to date.` と表示され、処理が拒否される。
2. VSCodeの画面や `ls` コマンドの結果には、自宅で作成した新しいファイルが表示されない。
3. しかし、`git log --oneline` で確認すると、自宅PCでのコミットログは学校PCの裏側（リモート追跡ブランチ）に届いている。

---

## 🚨 2. 原因（なぜ起きたか）
学校PCのローカルにある古い `myedit` ブランチの歴史と、GitHub上にある自宅の最新の `myedit` ブランチの歴史を、Gitがうまく自動合流（マージ）させられず、**「すでに最新状態である」とGitが勘違いしてサボってしまったこと**が原因。

裏側（`origin/myedit`）にはデータがダウンロードされているのに、表側（ローカルブランチのワークツリー）が過去の状態で固まってしまっていた。

---

## 🛠️ 3. 解決した手順（コマンド）

学校PCのターミナル（`myedit` ブランチにいる状態）で、以下の手順を実行して強制解決した。

### ステップ1：画面（ワークツリー）を自宅の最新状態に強制同期する
自動マージが拒否されたため、学校PCのブランチの状態を、GitHubから届いている自宅の最新状態（`origin/myedit`）と完全に同じ状態へ強制リセット（上書き）した。
```bash
git reset --hard origin/myedit
```
👉 **効果：** このコマンドを実行した瞬間、VSCodeの画面やファイル一覧に、自宅で書いた新しいファイルがパッと出現した。

### ステップ2：本流（main）ブランチに切り替える
成果を全体に反映させるため、ベースとなるブランチへ移動。
```bash
git switch main
```

### ステップ3：最新になった作業分を main に合流させる
学校PCの作業と、出現した自宅PCの作業をガッチャンコ（マージ）させる。（触っていたファイルが別々だったため、コンフリクトなしで自動合体）。
```bash
git merge myedit
```

### ステップ4：最終成果を GitHub に送り届ける
すべてがまとまった最新の `main` を GitHub へ送信。
```bash
git push origin main
```
👉 **効果：** GitHub上のプルリクエストも自動的に「マージ済み（Merged）」に切り替わり、すべての同期が完了した。

---

## 💡 4. 今後の対策（ゴールデンルール）
複数端末で作業する場合のデータ移動のトラブルを防ぐため、以下の習慣を徹底する。

1. 🏡 **離脱時（家や学校を出るとき）：** 作業途中であっても、必ずコミットして `git push` を行う。
2. 🏫 **開始時（次の場所でPCを開いたとき）：** コードを1文字も書く前に、必ず `git pull` を行う。

「移動する前は push、移動した後は pull」をセットにすることで、常に一本の綺麗な歴史の上で作業を継続できる。

<br>
<br>
<br>
<br>

# Gitトラブルシューティング手順書：未追跡ファイルの復元とPR履歴衝突の解消

## 1. 発生したトラブル
- `git reset --hard` により未コミット・未追跡ファイルが消失。
- `git fsck --lost-found` を用いてオブジェクト（dangling tree `b47691f`）からファイルを復元し、ブランチ `fix/dev-app` を作成。
- GitHub上で `main` ブランチへプルリクエストを作成しようとした際、以下のエラーが発生して比較・マージが不能となった。
  > **There isn’t anything to compare. main and fix/dev-app are entirely different commit histories.**

---

## 2. 原因
復元したオブジェクトから直接作成したブランチ（`fix/dev-app`）は、`main` ブランチのコミット履歴（親コミット）を引き継いでおらず、**完全に独立した履歴（Root Commit）** になっていたため。

---

## 3. 解決手順（クリーンなブランチへの移行）

`main` ブランチをベースにした新しいブランチを作成し、復元したファイルの「状態」のみを移植してPushします。

### Step 1. main ブランチを最新化する
```bash
git switch main
git pull origin main
```

### Step 2. main から新作業ブランチを作成する
```bash
git switch -c fix/dev-app-new
```

### Step 3. 旧復元ブランチからファイル群をコピーする
```bash
git checkout fix/dev-app -- .
```

### Step 4. コミットして GitHub へ Push する
```bash
git add .
git commit -m "復元したファイルを反映"
git push -u origin fix/dev-app-new
```

### Step 5. 新しいプルリクエストを作成する
* GitHubの画面を開き、fix/dev-app-new 用の黄色い帯にある「Compare & pull request」ボタンを押す。

* ベース（base: main）← 比較（compare: fix/dev-app-new）になっていること、および「Able to merge」と表示されていることを確認してPRを作成する。

## 4. 関連Tips・よくある質問
### Q1. 旧ブランチ（fix/dev-app）用の「Compare & pull request」案内はどうすればいい？
* 対応: 完全に無視（放置）して問題ありません。

* 理由: Push通知バナーは生成から約1時間で自動的に消去されます。もし誤って旧PRを作成してしまっていた場合は、PRページ最下部の「Close pull request」で閉じておきます。

### Q2. ローカルの旧ブランチ（fix/dev-app）が削除できない時は？
* 原因: 削除対象のブランチ上に滞在しているか、main に未マージのためGitの安全装置（-d）が働いている。

* 対処法:
  * 別のブランチへ移動する:
  ```bash
  git switch main
  ```
  * 大文字の -D オプションで強制削除する:
  ```bash
  git branch -D fix/dev-app
  ```

<br>
<br>
<br>
<br>
# Gitトラブルシューティング手順書：mainブランチへの誤コミットを別ブランチへ退避する方法

## 1. 発生したトラブル
`main` ブランチのまま作業を進め、誤って複数回（今回は2回）`git commit` を実行してしまった。

---

## 2. 仕組みと対処の考え方
現在の状態から新しいブランチを作成すると、そのコミット履歴は新ブランチへ引き継がれます。
ただし、**それだけでは `main` ブランチ側にも誤コミットが残ったまま**になってしまうため、以下の3ステップで対処します。

1. **新ブランチの作成**: 現在のコミット状態を新ブランチへ退避する
2. **main の巻き戻し**: `main` ブランチに戻り、誤ったコミット分だけ過去の状態へリセットする
3. **新ブランチの Push**: 退避先のブランチへ移動し、リモート（GitHubなど）へ Push する

---

## 3. 実行手順（2コミット分を退避する場合）

### Step 1. 現在の状態で新作業ブランチを作成して移動する
```bash
git switch -c feature-branch
```
### Step 2. main ブランチに戻り、コミットを巻き戻す
```bash
git switch main
git reset --hard HEAD~2
```
### Step 3. 新作業ブランチへ切り替えて Push する
```bash
git switch feature-branch
git push -u origin feature-branch
```

## 4. 補足・注意点
### Q1. リモートの main と完全に同じ状態に戻したい場合は？
* git reset --hard HEAD~2 の代わりに、以下を実行してリモートの main と同調させることも可能です。
```bash
git reset --hard origin/main
```
### Q2. すでに main をリモートに Push してしまっている場合は？
* この手順（git reset --hard）は main への誤コミットをまだリモートへ Push していない場合にのみ有効です。すでに Push 済みの場合は、履歴を強制上書きするか git revert で打ち消しコミットを作る必要があります。