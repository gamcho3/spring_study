package com.example.firstproject.repository;

import com.example.firstproject.entity.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee,Long> {

    @Override
    ArrayList<Coffee> findAll();
}
