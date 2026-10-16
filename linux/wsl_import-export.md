# WSLディストリビューションのエクスポートとインポート

## 1. エクスポートするディストリビューションを確認
```bash
> wsl -l -v
```

## 2. Ubuntuディストリビューションをデスクトップにファイル名「Ubuntu.tar」でエクスポート（STATEがStoppedか確認）
```bash
> wsl --export Ubuntu C:\Users\itsys\Desktop\Ubuntu.tar
```
(※自宅へ持って帰る場合)「Ubuntu.tar」をUSBメモリにコピーする。

## 3. インポートするPCに同名のディストリビューションがあれば解除（削除）する
```bash
PS C:\Users\〇〇〇> wsl --unregister Ubuntu3
```

## 4. インポートコマンドを入力
```bash
> wsl --import Ubuntu3 ${env:LOCALAPPDATA}\Packages\Ubuntu D:\Ubuntu.tar
// 実習機の場合
> wsl --import Ubuntu3 ${env:LOCALAPPDATA}\Packages\Ubuntu C:\Users\itsys\Desktop\Ubuntu.tar
```

## 5. インポートできたことを確認
```bash
> wsl -l -v
```

## 6. インポートしたディストリビューションの起動確認
```bash
> wsl -d Ubuntu3
```

## 7. デフォルト起動にする設定
```bash
> wsl -s Ubuntu3
```