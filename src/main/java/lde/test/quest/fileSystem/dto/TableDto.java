package lde.test.quest.fileSystem.dto;

import lde.test.quest.fileSystem.models.Directory;
import lde.test.quest.fileSystem.services.ServiceIo;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class TableDto {


    private ServiceIo serviceIo;

    private Long id;

    private Date date;

    private String path;

    private String countDirs;

    private String countFiles;

    private String size;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCountDirs() {
        return countDirs;
    }

    public void setCountDirs(String countDirs) {
        this.countDirs = countDirs;
    }

    public String getCountFiles() {
        return countFiles;
    }

    public void setCountFiles(String countFiles) {
        this.countFiles = countFiles;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public TableDto(Long id, Date date, String path,
                    String countDirs, String countFiles,
                    String size) {
        this.id = id;
        this.date = date;
        this.path = path;
        this.countDirs = countDirs;
        this.countFiles = countFiles;
        this.size = size;
    }


    public static Set<TableDto> convertToTableDto(Set<Directory> directories, UnaryOperator<String> function) {
        Set<TableDto> result = new LinkedHashSet<>();
        for (Directory directory : directories) {
            result.add(new TableDto(directory.getId(),
                    directory.getDate(),
                    directory.getPath(),
                    String.valueOf(directory.getFilesAndDir().stream()
                            .filter((a) -> a.getType().equals("Directory"))
                            .count()),
                    String.valueOf(directory.getFilesAndDir().stream()
                            .filter((a) -> a.getType().equals("Files"))
                            .count()), function.apply(directory.getPath())
            ));
        }
        return result;
    }

}
