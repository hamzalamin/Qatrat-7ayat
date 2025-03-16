package com.wora.qatrat7ayat.security.controller;

import com.wora.qatrat7ayat.security.DTO.Role.CreateRoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.RoleDto;
import com.wora.qatrat7ayat.security.DTO.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.security.services.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> create(@Valid @RequestBody CreateRoleDto createRoleDto) {
        return new ResponseEntity<>(roleService.create(createRoleDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<RoleDto>> findAll(int pageNumber, int size) {
        return new ResponseEntity<>(roleService.findAllPage(pageNumber, size), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(
            @Valid @RequestBody UpdateRoleDto updateRoleDto,
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(roleService.update(updateRoleDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        roleService.delete(id);
        return new ResponseEntity<>("Role with id " + id + " Deleted successfully", HttpStatus.OK);
    }

}
