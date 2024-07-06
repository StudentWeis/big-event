package com.why.bigevent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.why.bigevent.pojo.Category;

@Mapper
public interface CategoryMapper {

    @Insert("INSERT into category(category_name, category_alias, create_user, create_time, update_time)" +
            "VALUES (#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void add(Category category);

    @Select("SELECT * FROM category WHERE create_user = #{id}")
    List<Category> list(Integer id);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);

    @Update("UPDATE category SET category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now() WHERE id = #{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void delete(Integer id);
}
