package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.dto.machinesDto.MachineCreateEditDto;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineFilter;
import com.example.tradeintechniqueapp.dto.machinesDto.MachineReadDto;
import com.example.tradeintechniqueapp.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(("api/v2/machines"))
public class MachineRestController {
    private final MachineService machineService;

    @GetMapping
    public Page<MachineReadDto> getAllMachinesByFilter(MachineFilter machineFilter, @PageableDefault(size = 20) Pageable pageable) {
        return machineService.findAll(machineFilter, pageable);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MachineReadDto create(@RequestBody MachineCreateEditDto machine) {
        return machineService.create(machine);
    }


    @PutMapping()
    public Optional<MachineReadDto> update(@RequestBody MachineCreateEditDto machine) {
        return machineService.update(machine.getId(), machine);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public boolean deleteMachine(@PathVariable Long id) {
        return machineService.delete(id);
    }

}
