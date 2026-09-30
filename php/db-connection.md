## データベースへ接続するコード

```php
<?php
$dsn = 'mysql:host=localhost;dbname=school_db;charset=utf8mb4';
$user = 'ユーザー名';
$password = ''

try {
	// PDOインスタンスを生成する
	$pdo = new PDO($dsn, $user, $password, $opt);
	echo "接続完了!"
} catch (PDOException $e) {
	echo "接続失敗: " . $e->getMessage();
}
```

## データベースからデータを取得する（SELECT文）

```php
<?php
// 1. SQL文を準備して実行する
$sql = "SELECT * FROM students";
$stmt = $pdo->prepare($sql);
$stmt->execute();

// 2. 取得したデータをすべて配列として取り出す
$students = $stmt->fetchAll(PDO::FETCH_ASSOC); // 連想配列で取得

// 3. ループして名前を表示する
foreach ($students as $student) {
	echo $student['name'] . "<br>";
}
```

## データを安全に挿入する（INSERTとプレースホルダー）

```php
<?php
// 1. INSERT文の準備（名前付きプレースホルダー）
$sql = "INSERT INTO students(name, age) VALUES (:name, :age)";
$stmt = $pdo->prepare($sql);

// 2. 登録するデータ
$name = "Yamada";
$age = 20;

// 3. 連想配列でプレースホルダーに値をバインド（関連付け）して実行する
$stmt->execute([
	'name' => $name,
	'age' => $age
]);

echo "データを追加しました！"
```
