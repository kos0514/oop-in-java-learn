package com.kos0514.oop_in_java_learn.io;

import com.kos0514.oop_in_java_learn.enums.RockPaperScissors;

/**
 * コンピュータの手の選択を提供するインターフェース
 */
public interface ComputerChoiceProvider {
    /**
     * コンピュータの手を選択します
     * @return 選択された手 (RockPaperScissors)
     */
    RockPaperScissors chooseHand();
}
