import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class PageParser {
    /** Путь к рабочей папке.
     */
    Path folderPath;
    /** Префикс строчки с зависимостью.
     */
    final String dependencyPrefix = "require '";
    /** Постфикс строчки с зависимостью.
     */
    final String dependencyPostfix = "'";
    /** Конструктор класса.
     * @param folderPath Путь к рабочей папке.
     */
    public PageParser(Path folderPath) {
        this.folderPath = folderPath;
    }

    /** Создает экземпляр класса <a href="#{@link}">{@link Page}</a> на основе пути к файлу.
     * @param path Путь к файлу.
     * @return Файл представленный как объект типа <a href="#{@link}">{@link Page}</a>.
     * @throws IOException Если файл не существует.
     */
    public Page parse(Path path) throws IOException {
        LinkedList<String> dependencies = new LinkedList<>();
        LinkedList<String> lines = new LinkedList<>(Files.readAllLines(path));
        Pattern pattern = Pattern.compile(dependencyPrefix + ".*" + dependencyPostfix);
        for (String line : lines) {
            if (pattern.matcher(line).matches()) {
                dependencies.add(
                        folderPath.toAbsolutePath().resolve(
                                line.substring(dependencyPrefix.length(), line.length() - dependencyPostfix.length())
                        ).toString()
                );
            } else {
                break;
            }
        }
        String text = String.join("\n", lines);
        return new Page(path.toAbsolutePath().toString(), dependencies, text);
    }
}
