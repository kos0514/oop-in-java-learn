package com.kos0514.oop_in_java_learn.mapper;

import com.kos0514.oop_in_java_learn.entity.generated.Race;
import com.kos0514.oop_in_java_learn.enums.RaceRarity;
import com.kos0514.oop_in_java_learn.mapper.generated.RaceGeneratedMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;

import static com.kos0514.oop_in_java_learn.enums.RaceRarity.LEGENDARY;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.SECRET;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.STANDARD;
import static com.kos0514.oop_in_java_learn.enums.RaceRarity.UNIQUE;
import static com.kos0514.oop_in_java_learn.mapper.generated.RaceDynamicSqlSupport.rarity;

@Mapper
public interface RaceMapper extends RaceGeneratedMapper {

    /**
     * 指定された希少度以下の種族リストを取得します。
     *
     * @param maxRarityLevel 取得する種族の最大希少度
     * @return 指定された希少度以下の種族リスト
     */
    default List<Race> selectUpToRarity(RaceRarity maxRarityLevel) {
        // 希少度の順序に基づいて、指定された希少度以下の種族を取得
        var rarityLevels = getRarityLevelsUpTo(maxRarityLevel);

        SelectDSLCompleter completer = c ->
                c.where(rarity, SqlBuilder.isIn(rarityLevels))
                        .orderBy(rarity);
        return select(completer);
    }

    /**
     * 指定された希少度以下の希少度レベルの配列を取得します。
     *
     * @param maxRarity 最大希少度
     * @return 希少度レベルの配列
     */
    default String[] getRarityLevelsUpTo(RaceRarity maxRarity) {
        return switch (maxRarity) {
            case SECRET -> new String[]{
                    STANDARD.name(),
                    UNIQUE.name(),
                    LEGENDARY.name(),
                    SECRET.name()
            };
            case LEGENDARY -> new String[]{
                    STANDARD.name(),
                    UNIQUE.name(),
                    LEGENDARY.name()
            };
            case UNIQUE -> new String[]{
                    STANDARD.name(),
                    UNIQUE.name()
            };
            default -> new String[]{STANDARD.name()};
        };
    }
}
