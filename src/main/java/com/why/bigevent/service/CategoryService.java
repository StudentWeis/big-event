package com.why.bigevent.service;

import java.util.List;

import com.why.bigevent.pojo.Category;

public interface CategoryService {

    void add(Category category);

    List<Category> list(Integer id);

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);

}
