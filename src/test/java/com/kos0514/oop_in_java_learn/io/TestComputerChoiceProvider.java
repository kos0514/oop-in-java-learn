package com.kos0514.oop_in_java_learn.io;

import java.util.LinkedList;
import java.util.Queue;

/**
 * テスト用のコンピュータの手の選択を提供する実装
 * 事前に設定した選択肢を順番に返します
 */
public class TestComputerChoiceProvider implements ComputerChoiceProvider {
    private final Queue<Integer> choices = new LinkedList<>();
    
    /**
     * テスト用の選択肢を追加します
     *
     * @param choice 選択肢 (1: グー, 2: チョキ, 3: パー)
     * @return this (メソッドチェーン用)
     */
    public TestComputerChoiceProvider addChoice(int choice) {
        choices.add(choice);
        return this;
    }
    
    /**
     * 複数の選択肢を一度に追加します
     *
     * @param choices 選択肢の配列
     * @return this (メソッドチェーン用)
     */
    public TestComputerChoiceProvider addChoices(int... choices) {
        for (int choice : choices) {
            this.choices.add(choice);
        }
        return this;
    }
    
    @Override
    public int chooseHand() {
        if (choices.isEmpty()) {
            throw new IllegalStateException("テスト用の選択肢が不足しています");
        }
        return choices.poll();
    }
}