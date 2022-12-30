import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageParser {
    final String dependencyPrefix = "require '";
    final String dependencyPostfix = "'";

    public Page parse(Path path) throws IOException {
        LinkedList<String> dependencies = new LinkedList<String>();
        LinkedList<String> lines = new LinkedList<String>(Files.readAllLines(path));
        Pattern pattern = Pattern.compile(dependencyPrefix + ".*" + dependencyPostfix);
        for (String line : lines) {
            if (pattern.matcher(line).matches()) {
                dependencies.add(line.substring(dependencyPrefix.length(), line.length() - dependencyPostfix.length()));
            }
            else{
                break;
            }
        }
        String text = String.join("\n", lines);
        return new Page(path.toAbsolutePath().toString(), dependencies, text);
    }
}
