package lde.test.quest.fileSystem.dto;

import lde.test.quest.fileSystem.models.ComputersFile;
import lde.test.quest.fileSystem.models.Node;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModalFilesDto implements  Comparable<ModalFilesDto>{

    private String name;

    private String size;


    public ModalFilesDto(String name, String size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public static Set<ModalFilesDto> convertToModalFilesDto(Set<Node> files) {
        Set<ModalFilesDto> result = new TreeSet<>();
        for (Node file : files) {
            if (file.getType().equals("Directory")) {
                result.add(new ModalFilesDto(file.getName(), "<DIR>"));
            } else {
                result.add(new ModalFilesDto(file.getName(), ((ComputersFile) file).getFileSize()));
            }
        }
        return result;
    }


    @Override
    public int compareTo(ModalFilesDto o) {
        if (size.equals("<DIR>") && !o.getSize().equals("<DIR>")) {
            return -1;
        } else if (!size.equals("<DIR>") && o.getSize().equals("<DIR>")) {
            return 1;
        } else {
            Iterator<String> i1 = splitStringPreserveDelimiter(name).iterator();
            Iterator<String> i2 = splitStringPreserveDelimiter(o.getName()).iterator();
            while (true) {
                if (!i1.hasNext() && !i2.hasNext()) {
                    return 0;
                }
                if (!i1.hasNext() && i2.hasNext()) {
                    return -1;
                }
                if (i1.hasNext() && !i2.hasNext()) {
                    return 1;
                }

                String data1 = i1.next();
                String data2 = i2.next();
                int result;
                try {
                    result = Long.compare(Long.valueOf(data1), Long.valueOf(data2));
                    if (result == 0) {
                        result = -Integer.compare(data1.length(), data2.length());
                    }
                } catch (NumberFormatException ex) {
                    result = data1.compareToIgnoreCase(data2);
                }

                if (result != 0) {
                    return result;
                }

            }
        }
    }

    private List<String> splitStringPreserveDelimiter(String str) {
        Pattern splitPattern = Pattern.compile("\\d+|\\.|\\s");
        Matcher matcher = splitPattern.matcher(str);
        List<String> list = new ArrayList<String>();
        int pos = 0;
        while (matcher.find()) {
            list.add(str.substring(pos, matcher.start()));
            list.add(matcher.group());
            pos = matcher.end();
        }
        list.add(str.substring(pos));
        return list;
    }

}
