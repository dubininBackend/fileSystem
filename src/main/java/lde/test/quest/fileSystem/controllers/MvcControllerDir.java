package lde.test.quest.fileSystem.controllers;


import lde.test.quest.fileSystem.models.Directory;
import lde.test.quest.fileSystem.services.ServiceDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MvcControllerDir {

    private final ServiceDirectory serviceDirectory;

    @Autowired
    public MvcControllerDir(ServiceDirectory serviceDirectory) {
        this.serviceDirectory = serviceDirectory;
    }

    @GetMapping(value = "/dirs_and_files")
    public String table(Model model) {
        model.addAttribute("table", serviceDirectory.getAll());
        return "dirsAndFiles";
    }

    @PostMapping(value = "/dirs_and_files/add")
    public String addDirectory(@RequestParam("path") String path) {
        serviceDirectory.create(new Directory(path));

        return "redirect:/dirs_and_files";
    }
}
