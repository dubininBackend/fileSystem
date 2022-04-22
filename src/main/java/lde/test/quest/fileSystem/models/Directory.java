package lde.test.quest.fileSystem.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Directory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String path;

    @Column
    private Date date;

    @OneToMany
    private Set<Node> filesAndDir;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Node> getFilesAndDir() {
        return filesAndDir;
    }

    public void setFilesAndDir(Set<Node> filesAndDir) {
        this.filesAndDir = filesAndDir;
    }


    public Directory(String path) {
        this.path = path;
        this.date = new Date();
    }

    public Directory() {
    }


}
