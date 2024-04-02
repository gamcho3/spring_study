package com.example.firstproject;

import com.example.firstproject.entity.TestClass;
import org.junit.jupiter.api.Test;


public class test {
    @Test
    void test(){
        TestClass nee = new TestClass("hi","hello");
        System.out.println(nee);
    }


}
