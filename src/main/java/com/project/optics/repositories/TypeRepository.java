package com.project.optics.repositories;

import com.project.optics.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findById(int id);
    Long deleteById(int id);
}
