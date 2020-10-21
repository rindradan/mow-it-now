package fr.publicis.sapient.service;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class ParserService {

    public List<String> readFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        Stream<String> lines = Files.lines(path);
        List<String> data = lines.collect(Collectors.toList());
        lines.close();
        return data;
    }
}
