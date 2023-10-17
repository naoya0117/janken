package oit.is.z1992.kaizi.janken.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MatchMapper {
    @Select("SELECT id, user1, user2, user1Hand, user2Hand from matches")
    ArrayList <Match> selectAllMatches();
}