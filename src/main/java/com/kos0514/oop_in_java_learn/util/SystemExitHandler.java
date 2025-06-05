package com.kos0514.oop_in_java_learn.util;

import org.springframework.stereotype.Component;

/**
 * ExitHandlerインターフェースの実装クラス。
 * 実際にSystem.exit()を呼び出してJVMを終了させます。
 *
 * <p>このクラスはSpring Bootの依存性注入（DI）の仕組みを利用しており、
 * {@code @Component}アノテーションによってDIコンテナに登録されます。
 * {@code SystemExitConfiguration}クラスによって、パブリックな{@code util.ExitHandler}
 * インターフェースのBeanとして公開されます。</p>
 *
 * <p>この設計はストラテジーパターンを採用しており、システム終了処理を抽象化することで、
 * テスト時にモック実装に置き換えることができます。これにより、テスト実行中に
 * 実際にJVMが終了することを防ぎ、テストの実行を継続することが可能になります。</p>
 *
 * <p>アプリケーションでは、{@code OopInJavaLearnApplication}クラスが
 * コンストラクタインジェクションを通じて{@code ExitHandler}を受け取り、
 * 転生プロセス完了後に{@code exitHandler.exit()}を呼び出してアプリケーションを
 * 正常終了させています。</p>
 *
 * <p>このクラスは同じパッケージ内のpackage-privateな{@code ExitHandler}インターフェースを
 * 実装していますが、Spring DIによる注入を可能にするため、クラス自体はpublicとして
 * 宣言されています。</p>
 *
 * @see ExitHandler
 * @see com.kos0514.oop_in_java_learn.OopInJavaLearnApplication
 */
@Component
public class SystemExitHandler implements ExitHandler {

    @Override
    public void exit() {
        // Default exit with status 0
        System.exit(0);
    }
}
