package lde.test.quest.fileSystem.services;

import java.nio.file.Path;
import java.util.HashMap;

public interface ServiceIo {


    HashMap<String, String> checkPath(String str);

    String sumValue(String path);
}
