package com.why.bigevent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.why.bigevent.pojo.Article;

@Mapper
public interface ArticleMapper {

    @Insert("INSERT INTO article(title, content, cover_img, state, category_id, create_user, create_time, update_time)VALUES (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{id}, now(), now())")
    public void add(Article article);

    public List<Article> list(Integer userId, Integer categoryId, String state);

    @Select("SELECT * FROM article WHERE id = #{id}")
    public Article detail(Integer id);

    @Update("UPDATE article SET title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = now() WHERE id = #{id}")
    public void update(Article article);

    @Delete("DELETE FROM article WHERE id = #{id}")
    public void delete(Integer id);

}
