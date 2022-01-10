package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroupById(final Long id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewGroup(@RequestBody final GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group savedGroup = groupService.saveGroup(group);
        return groupMapper.mapToGroupDto(savedGroup);
    }
}

