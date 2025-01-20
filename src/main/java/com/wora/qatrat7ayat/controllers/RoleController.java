package com.wora.qatrat7ayat.controllers;

import com.wora.qatrat7ayat.models.DTOs.Role.CreateRoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.RoleDto;
import com.wora.qatrat7ayat.models.DTOs.Role.UpdateRoleDto;
import com.wora.qatrat7ayat.services.inter.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid CreateRoleDto dto){
        return new ResponseEntity<>(roleService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateSubject(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRoleDto dto
    ){
        return new ResponseEntity<>(roleService.update(dto, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll(){
        List<RoleDto> subjectDtoList = roleService.findAll();
        return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
