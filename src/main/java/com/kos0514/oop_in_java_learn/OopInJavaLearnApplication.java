package com.kos0514.oop_in_java_learn;

import com.kos0514.oop_in_java_learn.service.TransmigrationService;
import com.kos0514.oop_in_java_learn.util.system.ExitHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.endPrintSeparator;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.info;
import static com.kos0514.oop_in_java_learn.util.log.LoggingUtils.startPrintSeparator;

/**
 * 異世界転生サービスのメインアプリケーションクラス。
 * このアプリケーションはSpring Bootフレームワークを使用したコンソールアプリケーションとして実装されており、
 * コマンドラインから異世界転生プロセスを実行することができます。
 *
 * <p>CommandLineRunnerインターフェースを実装することで、アプリケーション起動時に
 * 自動的に転生プロセスが開始されます。</p>
 *
 * @author kos0514
 * @version 1.0
 */
@SpringBootApplication
@RequiredArgsConstructor
public class OopInJavaLearnApplication implements CommandLineRunner {

    /**
     * 転生プロセスを実行するためのサービス。
     * 依存性注入によって自動的に初期化されます。
     */
    private final TransmigrationService transmigrationService;

    /**
     * システム終了処理を行うハンドラー。
     * 依存性注入によって自動的に初期化されます。
     */
    private final ExitHandler exitHandler;

    /**
     * アプリケーションのエントリーポイント。
     * Spring Bootアプリケーションを起動します。
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(OopInJavaLearnApplication.class, args);
    }

    /**
     * アプリケーション起動時に自動的に実行されるメソッド。
     * 転生サービスのウェルカムメッセージを表示し、転生プロセスを開始します。
     *
     * @param args コマンドライン引数（未使用）
     */
    @Override
    public void run(String... args) {
        startPrintSeparator();
        info("異世界転生トランスミッションサービスへようこそ！");
        endPrintSeparator();

        transmigrationService.startTransmigrationProcess();
        exitHandler.exit();
    }
}
