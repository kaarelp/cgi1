package com.kaarelp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtil {

    private static final String VISITORS_FILE = "kylastusajad.txt";

    public static Stream<String> getVisitorsFileLines() throws URISyntaxException, IOException {
        URL url = FileUtil.class.getClassLoader().getResource(VISITORS_FILE);
        return Files.lines(Paths.get(url.toURI()));
    }
}
