package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public List<Coffee> index() {
       return coffeeRepository.findAll();
    }

    public Coffee create(CoffeeForm form) {
        Coffee newCoffee = form.toEntity();
        log.info(newCoffee.toString());
       return coffeeRepository.save(newCoffee);

    }

    public Coffee update(Long id, CoffeeForm dto) {
        // 클라이언트에서 전달받은 수정값
        Coffee updateCoffee = dto.toEntity();

        log.info("update::" + updateCoffee.toString());
        // db에서 값 검색

        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null || !id.equals(updateCoffee.getId())){

           return null;
        }
        coffee.copyWith(updateCoffee);
        return coffeeRepository.save(coffee);
    }

    public Coffee delete(Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee == null){
            return null;
        }
        coffeeRepository.delete(coffee);
        return coffee;
    }
}
