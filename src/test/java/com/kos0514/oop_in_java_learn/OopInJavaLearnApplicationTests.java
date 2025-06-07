package com.kos0514.oop_in_java_learn;

import com.kos0514.oop_in_java_learn.service.TransmigrationService;
import com.kos0514.oop_in_java_learn.util.system.ExitHandler;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OopInJavaLearnApplicationTests {

    @MockitoBean
    private TransmigrationService transmigrationService;

    @MockitoBean
    private ExitHandler exitHandler;

    @Autowired
    private OopInJavaLearnApplication application;

    @Test
    void contextLoads() {
        // Spring コンテキストが正常にロードされることを確認
        assertThat(application).isNotNull();
        assertThat(transmigrationService).isNotNull();
        assertThat(exitHandler).isNotNull();
    }

    @Nested
    class RunMethod {

        @Test
        void callsStartTransmigrationProcess() {
            // Arrange
            String[] args = new String[0];

            // Act
            application.run(args);

            // Assert
            // TransmigrationService の startTransmigrationProcess(UserInputProvider) メソッドが呼び出されたことを検証
            verify(transmigrationService, times(1)).startTransmigrationProcess();
            // ExitHandler の exit() メソッドが呼び出されたことを検証
            verify(exitHandler, times(1)).exit();
        }
    }
}
