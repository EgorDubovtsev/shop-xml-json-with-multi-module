package org.pojos.api.category;

import org.pojos.api.subcategory.Subcategory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as = SimpleCategory.class)
public interface Category {
    void addSubcategory(Subcategory subcategory);

    List<Subcategory> getSubcategories();

    String getName();

    void setName(String name);

    void setSubcategories(List<Subcategory> subcategories);
}
