
package com.mg.elasticdemospring.controller;

import com.mg.elasticdemospring.dto.Person;
import com.mg.elasticdemospring.repository.UnitBridgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
public class Controller
{

    private UnitBridgeRepository unitBridgeRepository;

    @PostMapping("/new")
    public String create(@RequestBody String name){

        Random random = new Random();
        int id = 0 + random.nextInt(100);

        Person pp = Person
           .builder()
           .id(id)
           .name(name)
           .build();

        return unitBridgeRepository
           .save(pp)
           .toString();
    }

    @GetMapping("/byid/{id}")
    public String getById(@PathVariable Integer id){

        Person pp = unitBridgeRepository
           .findById(id)
           .get();


        return pp.getName();
    }

    @GetMapping("/all")
    public String getAll(){
        return StreamSupport.stream(unitBridgeRepository
                .findAll()
                .spliterator(),
           false)
           .map(p -> String.valueOf(p.getId()))
           .collect(Collectors.joining(";"));
    }

    @GetMapping("byname")
    public String getByNamePageable(@RequestParam String name, Pageable pageable){

       List<Person> p = unitBridgeRepository
           .findByName(name, pageable)
           .stream()
           .collect(Collectors.toList());

       return p
        .stream()
        .map(Person::toString)
        .collect(Collectors.joining(";"));

    }
}

