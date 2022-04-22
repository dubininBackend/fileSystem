package lde.test.quest.fileSystem.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dir")
public class SubDirectory extends Node {
    public SubDirectory(String name) {
        super(name);
    }

    public SubDirectory() {

    }

    @Override
    public String toString() {
        return "SubDirectory{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                '}';
    }


    @Override
    public String getType() {
        return "Directory";
    }
}
