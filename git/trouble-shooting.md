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