import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FolderReader {
    LinkedList<Path> readFolder(Path folderPath) throws IOException, IllegalArgumentException {
        if (!Files.isDirectory(folderPath)){
            throw new IllegalArgumentException("Argument is not a folder.");
        }
        return Files.walk(folderPath).filter(Files::isRegularFile).collect(Collectors.toCollection(LinkedList::new));
    }
}