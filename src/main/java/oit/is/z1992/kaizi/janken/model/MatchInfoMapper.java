package oit.is.z1992.kaizi.janken.model;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MatchInfoMapper {
    @Insert("INSERT INTO MATCHINFO (USER1, USER2, USER1HAND, ISACTIVE)  VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
    boolean insertMatchInfo(MatchInfo matchInfo);

    @Select("SELECT * FROM MATCHINFO WHERE ISACTIVE = TRUE")
    ArrayList<MatchInfo> selectActiveMatches();
}