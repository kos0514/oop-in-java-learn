package com.kos0514.oop_in_java_learn.io;

/**
 * コンピュータの手の選択を提供するインターフェース
 */
public interface ComputerChoiceProvider {
    /**
     * コンピュータの手を選択します (1: グー, 2: チョキ, 3: パー)
     * @return 選択された手
     */
    int chooseHand();
}