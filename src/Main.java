import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("test.txt");
        PageParser pp = new PageParser();
        Page page = pp.parse(path);
        System.out.println(page.contents);
    }
}