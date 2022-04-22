package lde.test.quest.fileSystem.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

@Service
public class ServiceIoImpl implements ServiceIo {


    private final static long ONE_KBYTE = 1024;
    private final static long ONE_MBYTE = ONE_KBYTE * 1024;

    private final static long ONE_GBYTE = ONE_MBYTE * 1024;

    private final static long ONE_TBYTE = ONE_GBYTE * 1024;


    private final static String BYTES_SUFIX = "Bytes";
    private final static String KBYTES_SUFIX = "KB";
    private final static String MBYTES_SUFIX = "MB";
    private final static String GBYTES_SUFIX = " GB";
    private final static String TBYTES_SUFIX = " TB";

    public HashMap<String, String> checkPath(String str) {
        HashMap<String, String> map = new HashMap<>();
        File file = new File(str);
        File[] fileList = file.listFiles();
        if (file.isDirectory() && fileList != null && fileList.length != 0) {
            for (File item : fileList) {
                long fileSize = item.length();
                String fileName = item.getName();
                String value;
                if (item.isDirectory()) {
                    value = null;
                } else if (fileSize >= ONE_MBYTE) {
                    value = (fileSize / ONE_MBYTE) + MBYTES_SUFIX;
                } else if (fileSize >= ONE_KBYTE) {
                    value = (fileSize / ONE_KBYTE) + KBYTES_SUFIX;
                } else value = fileSize + BYTES_SUFIX;
                map.put(fileName, value);
            }
        }
        if(!file.isDirectory()){
            map = null;
        }
        return map;
    }

    public String sumValue(String url) {
        Path path = Path.of(url);
        long size = 0;
        String finalSize = "";
        try {
            size = Files.walk(path).mapToLong(p -> p.toFile().length()).sum();
            if (size >= ONE_TBYTE) {
                finalSize = size / ONE_TBYTE + TBYTES_SUFIX;
            } else if (size >= ONE_GBYTE) {
                finalSize = size / ONE_GBYTE + GBYTES_SUFIX;
            } else if (size >= ONE_MBYTE) {
                finalSize = size / ONE_MBYTE + MBYTES_SUFIX;
            } else if (size >= ONE_KBYTE) {
                finalSize = size / ONE_KBYTE + KBYTES_SUFIX;
            } else finalSize = size + BYTES_SUFIX;
            return finalSize;

        } catch (IOException ignored) {
        }
        return finalSize;
    }

}
