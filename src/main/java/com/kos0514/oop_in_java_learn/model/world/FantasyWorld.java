package com.kos0514.oop_in_java_learn.model.world;

public class FantasyWorld implements World {
    @Override
    public String getName() {
        return "剣と魔法の世界";
    }

    @Override
    public String getDescription() {
        return "ドラゴンや魔物が存在し、冒険者ギルドも活動する典型的なファンタジー世界。";
    }
}