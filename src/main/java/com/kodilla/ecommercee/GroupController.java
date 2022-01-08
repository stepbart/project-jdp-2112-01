package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

    private GroupDto groupDto;

    @GetMapping()
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = new ArrayList<>();

        for(long x = 0; x <= 10; x++) {
            groups.add(
                    new GroupDto(
                            x,
                            "GroupName#" +x
                    ));
        }
        if(groups.size() == 0) {
            return new ResponseEntity(
                    groups,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } else
            return new ResponseEntity(
                    groups,
                    HttpStatus.OK
            );

    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") final Long id) {

        return new ResponseEntity(
                new GroupDto(
                        id,
                        "Group#"+id
                ), HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<GroupDto> addNewGroup(@RequestBody final GroupDto groupDto) {
        return new ResponseEntity(
                groupDto,
                HttpStatus.CREATED
        );
    }

    @PutMapping()
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity(
                groupDto,
                HttpStatus.ACCEPTED
        );
    }
}

