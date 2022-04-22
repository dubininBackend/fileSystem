package lde.test.quest.fileSystem.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Files")
public class ComputersFile extends Node {

    @Column(name = "files")
    private String fileSize;

    public ComputersFile() {
    }

    public ComputersFile(String name, String fileSize) {
        super(name);
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }


    @Override
    public String getType() {
        return "Files";
    }

    @Override
    public String toString() {
        return "ComputersFile{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }
}
