# Java OOPプロジェクト - ガイドライン

## プロジェクト概要
このプロジェクトはSpring Bootを使用したコンソールベースの「異世界転生」（Isekai Transmigration）ゲームです。プレイヤーは異世界に転生するキャラクターを作成し、世界と種族を選択して、その選択に基づいてキャラクターのステータスを受け取ることができます。

## プロジェクト構造

```
oop-in-java-learn/
├── src/main/java/com/kos0514/oop_in_java_learn/
│   ├── entity/           # データベースエンティティクラス
│   ├── enums/            # 列挙型
│   ├── factory/          # オブジェクト生成のためのファクトリークラス
│   ├── mapper/           # データベースアクセス用のMyBatisマッパー
│   ├── model/            # ドメインモデルクラス
│   │   └── value/        # 値オブジェクト
│   ├── repository/       # リポジトリインターフェース
│   ├── service/          # サービスクラス
│   │   ├── game/         # ゲームメカニクスサービス
│   │   ├── race/         # 種族選択サービス
│   │   └── world/        # 世界選択サービス
│   └── util/             # ユーティリティクラス
```

## コーディング規約

### 1. オブジェクト指向設計原則
- **カプセル化**: 適切なgetterとsetterを持つプライベートフィールドの使用
- **継承**: 共通機能に継承を使用
- **ポリモーフィズム**: 柔軟な設計のためのインターフェースと抽象クラスの使用
- **抽象化**: 明確に定義されたインターフェースの背後に実装の詳細を隠す

### 2. 値オブジェクト
- ドメインコンセプト（例：`SoulName`、`Age`）には不変の値オブジェクトを使用
- コンストラクタまたはファクトリーメソッドで入力を検証
- 適切なequals/hashCode/toStringメソッドの実装（適宜Lombokを使用）

### 3. デザインパターン
- **ファクトリーパターン**: 複雑なオブジェクトの作成に使用（例：`TransmigratorFactory`、`PlayableStatusesFactory`）
- **ビルダーパターン**: オプションパラメータが多いオブジェクトに使用
- **ストラテジーパターン**: 交換可能なアルゴリズムにストラテジーインターフェースを使用
- **リポジトリパターン**: データアクセス抽象化にリポジトリを使用

### 4. コード構成
- 関連する機能をパッケージにグループ化
- 説明的なクラスとメソッド名を使用
- Javaの命名規則に従う（メソッド/変数にはキャメルケース、クラスにはパスカルケース）

### 5. ドキュメント
- すべての公開クラスとメソッドをJavadocでドキュメント化
- パラメータの説明と戻り値の説明を含める
- スローされる可能性のある例外をドキュメント化

## データベースガイドライン

### 1. MyBatis統合
- データベースアクセスにMyBatisを使用
- マッパーは`mapper`パッケージで定義
- ボイラープレートコードにはMyBatis Generatorを使用

### 2. エンティティクラス
- エンティティクラスは`entity`パッケージに保持
- 生成されたエンティティは`entity.generated`パッケージに配置
- 生成されたコードを直接変更しない

## ゲームメカニクス

### 1. 転生プロセス
1. 基本情報（名前、年齢）の収集
2. 世界の選択
3. じゃんけんで利用可能な種族レア度を決定
4. 種族の選択
5. 年齢、種族、ランダム要素に基づくキャラクターステータスの生成

### 2. 種族レア度システム
- **STANDARD（一般）**: デフォルトで利用可能
- **UNIQUE（レア）**: じゃんけん1回勝利で選択可能
- **LEGENDARY（スーパーレア）**: じゃんけん2回連続勝利で選択可能
- **SECRET（ウルトラレア）**: じゃんけん3回連続勝利で選択可能

## 開発ワークフロー

### 1. 新機能の追加
1. ドメインモデルと値オブジェクトを定義
2. ビジネスロジックを持つサービスクラスを実装
3. 必要に応じてファクトリークラスを作成
4. 必要に応じてデータベーススキーマとマッパーを更新
5. 適切なテストを追加

### 2. テスト
- すべてのビジネスロジックに対してユニットテストを作成
- テストにはJUnitを使用
- 適切な場合は依存関係をモック化

## ベストプラクティス

1. **不変性**: 可能な限り不変オブジェクトを優先
2. **検証**: 入力を早期に検証し、明確なエラーメッセージを提供
3. **関心の分離**: クラスを単一の責任に集中させる
4. **依存性注入**: 依存関係の管理にSpringのDIコンテナを使用
5. **例外処理**: 適切な例外タイプを使用し、適切なレベルで例外を処理

## 技術スタック
- Java 21
- Spring Boot 3.4.4
- MyBatis
- MySQL
- Lombok

# Java ユニットテスト生成ガイドライン

## 基本要件
- **フレームワーク**: JUnit 5 と Mockito を使用すること
- **Java バージョン**: Java 21
- **プロジェクト環境**: Lombok、Gradle を使用
- **Spring Boot バージョン**: 3.4.4

## テスト構造と命名規則

### クラス命名規則
- テストクラス名は `[テスト対象クラス名]Test` とすること
- 例: `UserService` のテストクラスは `UserServiceTest`

### メソッド命名規則と階層構造
- テスト対象メソッドごとに`@Nested`クラスを作成し、階層構造にすること
- 外側のクラス名は`[テスト対象メソッド名]`とすること
- 内側のテストメソッド名は`[テスト条件]_[期待される結果]`とすること

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    class FindById {

        @Test
        void userExists_returnsUser() {
            // テストコード
        }

        @Test
        void userNotFound_throwsException() {
            // テストコード
        }
    }

    @Nested
    class SaveUser {

        @Test
        void validUser_savesSuccessfully() {
            // テストコード
        }

        @Test
        void duplicateEmail_throwsException() {
            // テストコード
        }
    }
}
```

### Spring統合テストでのモックアノテーション
Spring Boot 3.4.0以降では、以下のアノテーションを使用すること：

#### 純粋なMockitoテスト（Spring Context不要）
```java
@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock  // 通常のMockitoの@Mockは継続使用可能
    private Repository repository;

    @Spy   // 通常のMockitoの@Spyも継続使用可能
    private EmailService emailService;

    @InjectMocks
    private Service service;
}
```

#### Spring統合テスト（Spring Context必要）
```java
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest
class IntegrationTest {

    @MockitoBean  // Spring Boot 3.4.0以降の新しいアノテーション
    private UserRepository userRepository;

    @MockitoSpyBean  // Spring Boot 3.4.0以降の新しいアノテーション
    private EmailService emailService;

    @Autowired  // Spring統合テストでは@Autowiredを使用
    private UserService userService;
}
```

**重要：** Spring Boot 3.4.0では `@MockBean` と `@SpyBean` が非推奨となり、Spring Framework 6.2の `@MockitoBean` と `@MockitoSpyBean` が推奨されています。

## スタブとモックの定義と使い分け

### 定義

#### **スタブ（Stub）**
- **定義**: テスト対象が依存するオブジェクトの代替品で、**事前に決められた値を返すだけ**
- **目的**: テスト対象に必要な入力データを提供する
- **検証**: **戻り値のみ**を検証（呼び出されたかどうかは関心なし）

#### **モック（Mock）**
- **定義**: テスト対象が依存するオブジェクトの代替品で、**期待される相互作用を検証する**
- **目的**: テスト対象が依存オブジェクトと正しく相互作用しているかを検証
- **検証**: **メソッド呼び出し（回数、引数、順序など）**を検証

### 使い分けの基準

```java
// スタブとして使用（戻り値のみ関心）
@Test
void findById_userExists_returnsUser() {
    // Arrange - スタブの設定
    var userId = 1L;
    var expectedUser = createTestUser(userId, "TestUser");
    when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

    // Act
    var actualUser = userService.findById(userId);

    // Assert - 戻り値のみ検証（相互作用は検証しない）
    assertThat(actualUser.getName()).isEqualTo("TestUser");
}

// モックとして使用（相互作用も検証）
@Test
void createUser_validUser_savesAndSendsNotification() {
    // Arrange - スタブとモックの設定
    var newUser = createTestUser(null, "NewUser");
    var savedUser = createTestUser(1L, "NewUser");
    when(userRepository.save(any(User.class))).thenReturn(savedUser); // スタブ

    // Act
    userService.createUser(newUser);

    // Assert - 戻り値の検証
    assertThat(savedUser.getId()).isEqualTo(1L);

    // Assert - 相互作用の検証（モック）
    verify(userRepository).save(any(User.class));
    verify(notificationService).sendWelcomeNotification(savedUser.getEmail());
}
```

### Mockitoでの実装

**重要**: Mockitoの`@Mock`アノテーションは「テストダブル」を作成し、用途に応じてスタブまたはモックとして使用できます。

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock  // テストダブルを作成
    private UserRepository userRepository;

    @Mock  // テストダブルを作成
    private NotificationService notificationService;

    @InjectMocks
    private UserService userService;
}
```

## テスト記述ガイドライン

### 1. 変数宣言の原則
- **基本方針**: 右辺の式から型推論できる場合は`var`を使用すること
- **例外**: 型推論できない場合は明示的な型宣言を使用

```java
@Test
void userExists_returnsUser() {
    // Arrange - varを使用（型推論可能）
    var userId = 1L;
    var expectedUser = createTestUser(userId, "TestUser");

    // 型推論できない場合は明示的型宣言
    Optional<User> userOptional = Optional.of(expectedUser);
    when(userRepository.findById(userId)).thenReturn(userOptional);

    // Act
    var actualUser = userService.findById(userId);

    // Assert
    assertThat(actualUser).isNotNull();
    assertThat(actualUser.getId()).isEqualTo(userId);
}
```

### 2. テストの基本構造 (AAA パターン)
各テストは以下の3つのセクションを明確に区分すること:
- **Arrange** (準備): テストデータとモックの設定
- **Act** (実行): テスト対象メソッドの呼び出し
- **Assert** (検証): 結果の検証

```java
@Nested
class FindUserById {

    @Test
    void userExists_returnsUser() {
        // Arrange
        var userId = 1L;
        var expectedUser = createTestUser(userId, "TestUser");
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        var actualUser = userService.findById(userId);

        // Assert
        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getId()).isEqualTo(userId);
        assertThat(actualUser.getName()).isEqualTo("TestUser");
    }
}
```

### 3. スタブとモックの設定と検証

#### スタブの基本設定
```java
// 戻り値の設定（スタブ）
when(repository.findById(anyLong())).thenReturn(Optional.of(expectedEntity));

// void メソッドの場合（スタブ）
doNothing().when(emailService).sendEmail(anyString());

// 例外をスローする場合（スタブ）
when(repository.save(any())).thenThrow(new DataIntegrityViolationException("制約違反"));
```

#### モックの検証
```java
// メソッド呼び出しの検証（モック）
verify(repository, times(1)).save(any(User.class));
verify(emailService, never()).sendEmail(anyString());

// 引数の詳細検証（モック）
verify(repository).save(argThat(user -> 
    user.getName().equals("TestUser") && user.getAge() == 25));

// 呼び出し順序の検証（モック）
InOrder inOrder = inOrder(repository, emailService);
inOrder.verify(repository).save(any(User.class));
inOrder.verify(emailService).sendEmail(anyString());
```

#### スタブとモックの組み合わせ使用
```java
@Test
void processUser_validUser_savesAndNotifies() {
    // Arrange
    var user = createTestUser(null, "TestUser");
    var savedUser = createTestUser(1L, "TestUser");

    // スタブの設定（戻り値を提供）
    when(userRepository.save(any(User.class))).thenReturn(savedUser);
    when(emailValidator.isValid(anyString())).thenReturn(true);

    // Act
    userService.processUser(user);

    // Assert - 戻り値の検証
    // （この例では戻り値がvoidなので省略）

    // Assert - 相互作用の検証（モック）
    verify(userRepository).save(any(User.class));
    verify(notificationService).sendWelcomeEmail(savedUser.getEmail());
    verify(auditService).logUserCreation(savedUser.getId());
}
```

### 4. 例外テスト
```java
@Nested
class FindById {

    @Test
    void userNotFound_throwsException() {
        // Arrange
        var nonExistentId = 999L;
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(
            UserNotFoundException.class, 
            () -> userService.findById(nonExistentId)
        );

        assertThat(exception.getMessage()).contains("User not found with id: " + nonExistentId);
    }
}
```

### 5. パラメータ化テスト
```java
@Nested
class ValidateUserId {

    @ParameterizedTest
    @CsvSource({
        "1, true, 'Valid user'",
        "0, false, 'Invalid ID'",
        "-1, false, 'Negative ID'"
    })
    void withDifferentInputs_returnsExpectedResult(
            long userId, boolean expectedValid, String description) {
        // Act
        var result = userService.isValidUserId(userId);

        // Assert
        assertThat(result).as(description).isEqualTo(expectedValid);
    }
}
```

## 高度なテストダブル技術

### 1. 引数キャプチャ（モック機能）
```java
@Nested
class SaveUser {

    @Test
    void validUser_savesWithCorrectData() {
        // Arrange
        var inputUser = createTestUser(null, "NewUser");
        var userCaptor = ArgumentCaptor.forClass(User.class);

        // Act
        userService.createUser(inputUser);

        // Assert
        verify(userRepository).save(userCaptor.capture());
        var capturedUser = userCaptor.getValue();
        assertThat(capturedUser.getName()).isEqualTo("NewUser");
        assertThat(capturedUser.getCreatedDate()).isNotNull();
    }
}
```

### 2. スタブの連続呼び出し
```java
@Test
void failsThenSucceeds_eventuallySaves() {
    // Arrange - 連続したスタブの設定
    var user = createTestUser(1L, "TestUser");
    when(repository.save(any(User.class)))
        .thenThrow(new DataAccessException("一時的エラー"))
        .thenThrow(new DataAccessException("再度エラー"))
        .thenReturn(user);

    // Act
    var result = userService.saveWithRetry(user);

    // Assert - 戻り値の検証
    assertThat(result).isNotNull();

    // Assert - 相互作用の検証（モック）
    verify(repository, times(3)).save(any(User.class));
}
```

### 3. 部分モック（Spy）の使用
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Spy
    private UserService userServiceSpy;

    @Nested
    class ComplexMethod {

        @Test
        void callsInternalMethod_verifyInteraction() {
            // Arrange
            doReturn(true).when(userServiceSpy).validateUser(any());

            // Act
            userServiceSpy.processUser(createTestUser());

            // Assert
            verify(userServiceSpy).validateUser(any());
        }
    }
}
```

## テストデータとヘルパーメソッド

### テストデータファクトリ
```java
class UserTestDataFactory {

    public static User createTestUser() {
        return createTestUser(1L, "DefaultUser");
    }

    public static User createTestUser(Long id, String name) {
        return User.builder()
            .id(id)
            .name(name)
            .email(name.toLowerCase() + "@example.com")
            .age(25)
            .createdDate(LocalDateTime.now())
            .build();
    }

    public static List<User> createUserList(int count) {
        return IntStream.range(1, count + 1)
            .mapToObj(i -> createTestUser((long) i, "User" + i))
            .collect(Collectors.toList());
    }
}
```

## アサーション推奨事項

### AssertJの使用を推奨
```java
// 基本的なアサーション
assertThat(actualUser).isNotNull();
assertThat(actualUser.getName()).isEqualTo("ExpectedName");

// コレクションのアサーション
assertThat(userList)
    .hasSize(3)
    .extracting(User::getName)
    .containsExactly("User1", "User2", "User3");

// 例外のアサーション
assertThatThrownBy(() -> userService.findById(-1L))
    .isInstanceOf(IllegalArgumentException.class)
    .hasMessageContaining("Invalid ID");

// 条件付きアサーション
assertThat(user).satisfies(u -> {
    assertThat(u.getName()).isNotBlank();
    assertThat(u.getAge()).isBetween(0, 150);
    assertThat(u.getEmail()).contains("@");
});
```

## 実装例：完全なテストクラス

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Nested
    class FindById {

        @Test
        void userExists_returnsUser() {
            // Arrange
            var userId = 1L;
            var expectedUser = UserTestDataFactory.createTestUser(userId, "TestUser");
            when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

            // Act
            var actualUser = userService.findById(userId);

            // Assert
            assertThat(actualUser).isNotNull();
            assertThat(actualUser.getId()).isEqualTo(userId);
            assertThat(actualUser.getName()).isEqualTo("TestUser");
        }

        @Test
        void userNotFound_throwsException() {
            // Arrange
            var nonExistentId = 999L;
            when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            // Act & Assert
            var exception = assertThrows(
                UserNotFoundException.class, 
                () -> userService.findById(nonExistentId)
            );

            assertThat(exception.getMessage()).contains("User not found with id: " + nonExistentId);
        }
    }

    @Nested
    class CreateUser {

        @Test
        void validUser_savesAndSendsEmail() {
            // Arrange
            var newUser = UserTestDataFactory.createTestUser(null, "NewUser");
            var savedUser = UserTestDataFactory.createTestUser(1L, "NewUser");
            when(userRepository.save(any(User.class))).thenReturn(savedUser);

            // Act
            var result = userService.createUser(newUser);

            // Assert
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);

            verify(userRepository).save(any(User.class));
            verify(emailService).sendWelcomeEmail(savedUser.getEmail());
        }

        @Test
        void duplicateEmail_throwsException() {
            // Arrange
            var duplicateUser = UserTestDataFactory.createTestUser(null, "Duplicate");
            when(userRepository.save(any(User.class)))
                .thenThrow(new DataIntegrityViolationException("Email already exists"));

            // Act & Assert
            assertThatThrownBy(() -> userService.createUser(duplicateUser))
                .isInstanceOf(UserCreationException.class)
                .hasMessageContaining("Email already exists");

            verify(emailService, never()).sendWelcomeEmail(anyString());
        }
    }
}
```

## テストカバレッジの要件

- 各クラスの主要なパブリックメソッドには必ずテストを作成すること
- 各メソッドの正常系と異常系の両方をテストすること
- 境界値のテストを含めること
- 分岐条件（if文、switch文）のすべてのパスをカバーすること
- エッジケースと例外ケースを網羅すること

## ベストプラクティス

### 1. テストの独立性
- 各テストは他のテストに依存せず、独立して実行できること
- テスト間で状態を共有しないこと

### 2. テストの可読性
- `@Nested`クラスを使用してテストを論理的にグループ化
- テストメソッド名で何をテストしているかが明確に分かること
- Given-When-Then構造またはAAA構造を明確にすること

### 3. テストの保守性
- 重複コードをヘルパーメソッドに抽出
- テストデータの作成を標準化（ファクトリパターン使用）
- モックの設定を再利用可能にする

### 4. パフォーマンスの考慮
- `@MockitoSettings(strictness = Strictness.STRICT_STUBS)`を使用して未使用スタブを検出
- 重いセットアップは`@BeforeAll`で一度だけ実行
- 必要最小限のテストダブルのみ作成

## トラブルシューティング

### よくある問題と解決策

1. **モックが正しく注入されない**
    - `@ExtendWith(MockitoExtension.class)`が設定されているか確認
    - `@InjectMocks`と`@Mock`の組み合わせが正しいか確認

2. **Spring Boot 3.4.0での非推奨警告**
    - Spring統合テストでは`@MockitoBean`と`@MockitoSpyBean`を使用
    - 純粋なMockitoテストでは従来の`@Mock`と`@Spy`を継続使用

3. **UnfinishedStubbingException**
    - `when()`メソッドチェーンが完了しているか確認
    - 静的メソッドのスタブ化にはMockito Staticが必要

4. **テストが不安定（Flaky Test）**
    - 時間に依存するテストはClockをスタブ化
    - 並行処理のテストは適切な同期機構を使用

5. **スタブとモックの混同**
    - 戻り値のみ必要な場合はスタブとして使用
    - 相互作用の検証が必要な場合はモックとして使用
    - 一つのテストダブルで両方の用途を組み合わせることも可能
