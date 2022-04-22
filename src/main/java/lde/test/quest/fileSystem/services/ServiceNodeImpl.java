package lde.test.quest.fileSystem.services;

import lde.test.quest.fileSystem.dao.NodeDao;
import lde.test.quest.fileSystem.models.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceNodeImpl implements ServiceNode {


    private final NodeDao nodeDao;

    @Autowired
    public ServiceNodeImpl(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }


    @Override
    @Transactional
    public void create(Node node) {
        nodeDao.save(node);
    }
}
