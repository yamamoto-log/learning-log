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

## 初めてリポジトリを作成する

- 下記の2通りがある。
  - ローカルリポジトリを作成した後にGitHubにpushする場合
  - GitHubでリモートリポジトリを作成した後にローカルにcloneする場合

### 【ローカルリポジトリから作成する場合】

### 【リモートリポジトリから作成する場合】
