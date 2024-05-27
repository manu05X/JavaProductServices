package dev.manish.productservicemanish.repositories;

import dev.manish.productservicemanish.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    Category findCategoriesById(Long id);

}
