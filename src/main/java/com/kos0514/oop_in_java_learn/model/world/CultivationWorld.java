package com.kos0514.oop_in_java_learn.model.world;

public class CultivationWorld implements World {
    @Override
    public String getName() {
        return "仙人道修行";
    }

    @Override
    public String getDescription() {
        return "仙人、妖怪、霊獣が存在し、修行によって不老不死も目指せる世界。";
    }
}