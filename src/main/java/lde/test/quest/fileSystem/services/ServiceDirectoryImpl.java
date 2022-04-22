package lde.test.quest.fileSystem.services;

import lde.test.quest.fileSystem.dao.DaoDirectory;
import lde.test.quest.fileSystem.dto.ModalFilesDto;
import lde.test.quest.fileSystem.dto.TableDto;
import lde.test.quest.fileSystem.models.ComputersFile;
import lde.test.quest.fileSystem.models.Directory;
import lde.test.quest.fileSystem.models.Node;
import lde.test.quest.fileSystem.models.SubDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServiceDirectoryImpl implements ServiceDirectory {


    private final DaoDirectory daoDirectory;

    private final ServiceNode serviceNode;

    private final ServiceIo serviceIo;


    @Autowired
    public ServiceDirectoryImpl(DaoDirectory daoDirectory, ServiceNode serviceNode, ServiceIo serviceIo) {
        this.daoDirectory = daoDirectory;
        this.serviceNode = serviceNode;
        this.serviceIo = serviceIo;
    }


    @Override
    @Transactional
    public void create(Directory directory) {
        Map<String, String> filesMap = serviceIo.checkPath(directory.getPath());
        if(filesMap == null){
            return;
        }
        Set<Node> filesSet = new LinkedHashSet<>();
        if (!(filesMap.isEmpty())) {
            filesMap.forEach((name, size) -> {
                if (size == null) {
                    SubDirectory subDirectory = new SubDirectory(name);
                    serviceNode.create(subDirectory);
                    filesSet.add(subDirectory);
                } else {
                    ComputersFile files = new ComputersFile(name, size);
                    serviceNode.create(files);
                    filesSet.add(files);
                }
            });
        }

        directory.setFilesAndDir(filesSet);
        daoDirectory.save(directory);
    }

    @Override
    public Set<TableDto> getAll() {
        Set<Directory> result = new LinkedHashSet<>();
        daoDirectory.findAll().forEach(result::add);
        return TableDto.convertToTableDto(result, serviceIo::sumValue);
    }

    @Override
    public Set<ModalFilesDto> getFilesById(Long id) {
        Directory directory = daoDirectory.findById(id).orElse(new Directory());
        return directory.getFilesAndDir() != null ?
                ModalFilesDto.convertToModalFilesDto(directory.getFilesAndDir()) : new HashSet<>();
    }


}
