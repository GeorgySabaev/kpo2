import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.stream.Collectors;
/** Класс, отвечающий за поиск всех файлов из всех подпапок данной папки.
 */
public class FolderReader {

    /** Возвращает список всех файлов из всех подпапок данной папки.
     * @param folderPath путь к папке.
     * @return Список всех файлов из всех подпапок данной папки.
     */
    LinkedList<Path> readFolder(Path folderPath) throws IOException {
        return Files.walk(folderPath).filter(Files::isRegularFile).collect(Collectors.toCollection(LinkedList::new));
    }
}