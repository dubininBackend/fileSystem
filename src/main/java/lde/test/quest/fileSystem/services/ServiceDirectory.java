package lde.test.quest.fileSystem.services;

import lde.test.quest.fileSystem.dto.ModalFilesDto;
import lde.test.quest.fileSystem.dto.TableDto;
import lde.test.quest.fileSystem.models.Directory;
import lde.test.quest.fileSystem.models.Node;

import java.util.List;
import java.util.Set;

public interface ServiceDirectory {

    void create(Directory directory);

    Set<TableDto> getAll();

    Set<ModalFilesDto> getFilesById(Long id);
}
