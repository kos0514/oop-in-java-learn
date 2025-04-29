package com.kos0514.oop_in_java_learn.mapper;

import com.kos0514.oop_in_java_learn.mapper.generated.RaceStatusModifierGeneratedMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RaceStatusModifierMapper extends RaceStatusModifierGeneratedMapper {
    // No need to add selectByRaceId method as we can use selectByPrimaryKey
}