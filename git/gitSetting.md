# Git・GitHubの設定まとめ

<br>

## Gitのインストール・設定

### 1. インストール手順【LinuxMintの場合】

```bash
sudo apt update
sudo apt install git
```

### 2. 初期設定（ユーザー登録）

- メールアドレスとアカウント名をGitに登録

```bash
git config --global user.email "Githubで発行されたメールアドレス"
git config --global user.name "アカウント名"
```

- コミットコメント入力用エディタを vsCode に指定

```bash
git config --global core.editor "code --wait"
```

- デフォルトブランチ名を master ではなく main に設定

```bash
git config --global init.defaultBranch main
```

- 設定が反映されているか確認

```bash
git config --global --list
```

- 間違いがある場合の取り消しコマンド

```bash
git config --global --unset 設定項目
```

<br>
<br>

## GitHubの設定

### 1. 公開鍵認証

- 秘密鍵と公開鍵のペアを作成

```bash
ssh -keygen
(任意のパスフレーズを決めて入力)
(再度パスフレーズを入力)
```

- 作成されたかを確認する
  - ローカルフォルダに下記2つのファイルがあればOK
  - 秘密鍵「id_xxxxxxx」
  - 公開鍵「id_xxxxxxx.pub」

### 2. 公開鍵をGItHubのサーバに登録

- GitHubにログイン。
- 右上の「プロフィールアイコン」をクリック
- [Settings]をクリック。
- 左サイドバーの[Access]セクション、[SSH and GPG keys]を選択。
- 「SSH keys」項目の右側にある[New SSH key]ボタンをクリック。
- 「Title」→ 自宅PC など任意の名前を入力。
- 「Key」→「id_ed25519.pub」(公開鍵)を TeraPadなどのテキストエディタで開き、その内容(文字列)をそのままコピーし貼り付け
  る。
- [Add SSH key]ボタンをクリック。

### 3. 正しく鍵が登録されたか確認

```bash
ssh -T git@github.com
(yes を入力)
(パスフレーズを入力)
```

- 下記のように表示されたら完了。

```bash
Hi GitHub のアカウント名! You've successfully authenticated, but GitHub does not provide shell access.
```

<br>
<br>

## リポジトリの接続

- 下記の2通りがある。
  - ローカルリポジトリを作成した後にGitHubにpushする場合
  - GitHubでリモートリポジトリを作成した後にローカルにcloneする場合

### 【ローカルリポジトリから作成する場合】
**★git initを使う**  
**★接続設定（上流ブランチ）は手動で設定が必要**
* ローカルリポジトリとなるディレクトリを作成
```bash
cd ~/Documents
mkdir gitTest
```
* ローカルリポジトリの初期化
```bash
cd Documents/gitTest
git init
```
* .gitが作成されたことを確認
```bash
ls -la
```
* 現在の状態を確認
```bash
git status
```
* ステージングエリアに追加
```bash
git add .
```
* コミット
```bash
git commit -m "任意のコミットメッセージ"
```
* GitHub上に空のリモートリポジトリを作成する
* 空のリモートリポジトリを、「origin」というエイリアス名で、Git の gitTest ローカルリ
ポジトリに登録
```bash
git remote add origin git@github.com: GitHubのアカウント名/
gitTest.git
```
* Git のgitTestリポジトリを、空のリモートリポジトリ「gitTest2.git」へ登録する
```bash
git push -u origin main
(パスフレーズを入力)
```
* -uは--set-upstream
* GitのgitTestリポジトリに、GitHubのgitTestリポジトリのmainブランチの位置を映したリモート追跡ブランチ「origin/main」を作成できた
* GitHubのgitTestリポジトリに、GitのgitTestリポジトリを登録できた
* GitのgitTestリポジトリのmainブランチと同期する上流ブランチとして、Gitのリモート追跡ブランチである「origin/main」を設定できた


### 【リモートリポジトリから作成する場合】
**★すでにGitHub上にリポジトリがある場合**  
**★git cloneを使う**  
**★接続設定は自動で完了**
* git cloneする
```bash
cd ~/Documents
git clone git@github.com: GitHubのアカウント名/gitTest.git
(パスフレーズを入力)
```
* Documentsフォルダに、GitHubリモートリポジトリと同名の「gitTest」ディレクトリが作成された
* この中に、GitHubから「.git」リポジトリがコピーされた
* リモート追跡ブランチと上流ブランチが自動で設定された

## 
