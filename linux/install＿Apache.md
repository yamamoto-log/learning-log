# Apache HTTP Serverのインストール（RHEL系Alma(Rocky)Linux9,VirtualBox環境）
## 1. Apacheのインストール
* 標準リポジトリから「dnf install」コマンドでインストール
```bash
$ sudo dnf -y install httpd httpd-tools mod_ssl
```

* インストールしたApacheのバージョンを確認
```bash
$ httpd -V
```

## 2. systemdによるApacheの自動起動設定
* psコマンドでsystemdのプロセスID(PID)を確認
```bash
$ ps -e
```

* 利用可能なサービスの中にApache(httpd.server)があるか確認
```bash
$ systemctl list-unit-files -t service
```

* Apache(httpd.service)の状態確認(qで終了)
```bash
$ systemctl status httpd.service
```

* Apache(httpd.service)の起動
```bash
$ sudo systemctl start httpd
```

* Apache(httpd.service)の停止
```bash
$ sudo systemctl stop httpd
```

* Apache(httpd.service)の再起動（設定値を変更した場合や動作不具合のとき）
```bash
$ sudo systemctl restart httpd
```

* Apache(httpd.service)のリロード（サービスを実行したまま停止させずに、設定値のみを再読み込みする）
```bash
$ sudo systemctl reload httpd
```

* Apache(httpd.service)の自動起動が有効か無効かの状態確認
```bash
$ systemctl is-enabled httpd
// $ systemctl list-unit-files | grep httpd や
// $ systemctl status httpd でも確認できる。
```

* Apache(httpd.service)の自動起動を有効化（シンボリックリンクを作成することで自動起動の設定を有効にしている）
```bash
$ sudo systemctl enable httpd
```

* Apache(httpd.service)の自動起動を無効化（シンボリックリンクを削除することで自動起動の設定を無効にしている）
```bash
$ sudo systemctl disable httpd
```

* AlmaLinuxの再起動（自動機能の確認）
```bash
$ sudo shutdown -r now
sshログインする
$ systemctl status httpd
```

## 3. ファイアウォールのパケットフィルタリング設定
* firewallの設定を確認
```bash
$ sudo firewall-cmd --list-all
```

* http(WWWサーバ用)とhttpsの通信を許可する追加設定
```bash
$ sudo firewall-cmd --add-service=http
$ sudo firewall-cmd --add-service=https
```

* 上記許可設定を一時的ではなく永続的に設定
```bash
$ sudo firewall-cmd --runtime-to-permanent
```

* AlmaLinuxを再起動して設定を確認する
* Windowsのブラウザでhttp://192.168.56.101/を開き、AlmaLinux Test Pageが表示されたら動作確認完了。