package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActFrontPageDto;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import com.example.tradeintechniqueapp.service.ActService;
import com.example.tradeintechniqueapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/acts")
public class ActRestController {
    private final ActService actService;
    private final UserService userService;

    @GetMapping
    public Optional<List<ActFrontPageDto>> getActs() {
        return actService.getActs();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createAct(@RequestBody ActCreateEditDto act) {
        return actService.create(act) != null;
    }

    @PostMapping(value = "/checkWorks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<List<WorkCheckDto>>> checkWork(@RequestBody Work work, @RequestParam Long id) {
        Optional<List<WorkCheckDto>> workCheckDtos = actService.checkWork(work, id);
        if (workCheckDtos.get().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(workCheckDtos, HttpStatus.FOUND);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable Long id){
        actService.deleteAct(id);
        return false;
    }

    @PostMapping(value = "/uploadFile")
    @ResponseStatus(HttpStatus.OK)
    public boolean uploadFile(@RequestBody MultipartFile file){
        return actService.uploadFile(file);
    }


}
