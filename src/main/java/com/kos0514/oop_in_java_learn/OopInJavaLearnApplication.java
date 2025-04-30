package com.kos0514.oop_in_java_learn;

import com.kos0514.oop_in_java_learn.service.TransmigrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.kos0514.oop_in_java_learn.util.LoggingUtils.*;

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
public class OopInJavaLearnApplication implements CommandLineRunner {

	/**
	 * 転生プロセスを実行するためのサービス。
	 * 依存性注入によって自動的に初期化されます。
	 */
	private final TransmigrationService transmigrationService;

	public OopInJavaLearnApplication(TransmigrationService transmigrationService) {
		this.transmigrationService = transmigrationService;
	}

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
		System.exit(0);
	}
}