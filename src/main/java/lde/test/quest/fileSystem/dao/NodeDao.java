package lde.test.quest.fileSystem.dao;

import lde.test.quest.fileSystem.models.Directory;
import lde.test.quest.fileSystem.models.Node;
import org.springframework.data.repository.CrudRepository;

public interface NodeDao extends CrudRepository<Node, Long> {

}
