package com.kos0514.oop_in_java_learn.model.world;

public class MagicTechWorld implements World {
    @Override
    public String getName() {
        return "魔導先進国";
    }

    @Override
    public String getDescription() {
        return "魔法の力で動く車や飛行船があり、工場では魔法道具が製造される世界。";
    }
}