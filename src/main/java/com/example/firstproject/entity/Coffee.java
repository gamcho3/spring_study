package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String price;

    public void copyWith(Coffee coffee){
        if(coffee.name != null){
            this.name = coffee.name;
        }

        if(coffee.price !=null){
            this.price = coffee.price;
        }
    }
}
