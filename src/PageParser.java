import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class PageParser {
    Path folderPath;
    final String dependencyPrefix = "require '";
    final String dependencyPostfix = "'";

    public PageParser(Path folderPath) {
        this.folderPath = folderPath;
    }

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
