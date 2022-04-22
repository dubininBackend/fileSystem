package lde.test.quest.fileSystem.controllers;

import lde.test.quest.fileSystem.dto.ModalFilesDto;
import lde.test.quest.fileSystem.services.ServiceDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RestControllerDir {

    private final ServiceDirectory serviceDirectory;

    @Autowired
    public RestControllerDir(ServiceDirectory serviceDirectory) {
        this.serviceDirectory = serviceDirectory;
    }


    @GetMapping(value = "/dirs_and_files/files/{id}")
    public ResponseEntity<Set<ModalFilesDto>> attachedFiles(@PathVariable long id) {
        return ResponseEntity.accepted().body(serviceDirectory.getFilesById(id));

    }
}
