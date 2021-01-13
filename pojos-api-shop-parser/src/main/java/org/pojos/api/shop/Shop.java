package org.pojos.api.shop;

import org.pojos.api.category.Category;

import java.util.List;

public interface Shop {
     void addCategory(Category category);
     List<Category> getCategories();
}
