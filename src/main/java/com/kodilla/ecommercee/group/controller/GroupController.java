package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.GenericEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @GetMapping("/all")
    public List<GenericEntity> getAllGroups() {
        List<GenericEntity> all = genericEntityRepository.findAll();
        return all;
    }

    @GetMapping("/{id}")
    public GenericEntity getGroupById(@PathVariable("id") final Long id) {
        GenericEntity generic = genericEntityRepository.findById(id).orElseThrow(null);
        return generic;
    }

    @PostMapping("/addGroup")
    public GenericEntity addNewGroup(@RequestBody final GenericEntity genericEntity) {
        GenericEntity save = genericEntityRepository.save(genericEntity);
        return save;
    }

    @PutMapping("/updateGroup")
    public GenericEntity updateGroup(@RequestBody GenericEntity genericEntity) {
        GenericEntity updated = genericEntityRepository.saveAndFlush(genericEntity);
        return updated;
    }

}

