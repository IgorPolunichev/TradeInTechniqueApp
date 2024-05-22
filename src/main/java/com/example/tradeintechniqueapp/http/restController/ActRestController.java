package com.example.tradeintechniqueapp.http.restController;

import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.actsDto.ActCreateEditDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActFrontPageDto;
import com.example.tradeintechniqueapp.dto.actsDto.ActReadDto;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import com.example.tradeintechniqueapp.service.ActService;
import com.example.tradeintechniqueapp.service.FileService;
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
    private final FileService uploadFileService;

    @GetMapping
    public Optional<List<ActFrontPageDto>> getActs() {
        return actService.getActs();
    }
    @GetMapping(value = "/{id}")
    public Optional<ActReadDto> getAct(@PathVariable Long id) {
        return actService.getActById(id);
    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> createAct(@RequestPart(name = "act") ActCreateEditDto act,
                                             @RequestPart(name = "files", required = false) MultipartFile[] files) {
        ActReadDto actReadDto = actService.create(act);
        if (uploadFileService.uploadFilesForAct(files, actReadDto.getPathFiles())) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public boolean delete(@PathVariable Long id) {
        actService.deleteAct(id);
        return false;
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public boolean uploadFile(@RequestPart(name = "act") ActCreateEditDto act,
                              @RequestPart(name = "files") MultipartFile[] files
            , @RequestParam String pathFiles) {
//        act.getActDescription();
        System.out.println();
//        uploadFileService.uploadFilesForAct(files, pathFiles);
        return false;
    }
}
