package com.kos0514.oop_in_java_learn.mapper;

import com.kos0514.oop_in_java_learn.mapper.generated.RaceParameterModifierGeneratedMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RaceParameterModifierMapper extends RaceParameterModifierGeneratedMapper {
    // No need to add selectByRaceId method as we can use selectByPrimaryKey
}