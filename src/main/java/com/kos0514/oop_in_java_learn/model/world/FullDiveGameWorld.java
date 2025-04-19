package com.kos0514.oop_in_java_learn.model.world;

public class FullDiveGameWorld implements World {
    @Override
    public String getName() {
        return "フルダイブゲーム";
    }

    @Override
    public String getDescription() {
        return "99.9%のリアリティを持つVRMMOゲーム。死亡すると記憶を失うシビアなルールがある。";
    }
}