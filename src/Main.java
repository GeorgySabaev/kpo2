import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("input");
        PageParser pp = new PageParser();
        FolderReader fr = new FolderReader();
        for (Path p : fr.readFolder(path)) {
            Page page = pp.parse(p);
            System.out.println(page.contents);
            System.out.println("--------\nDependencies:");
            for (String dependency : page.dependencies) {
                System.out.println(dependency);
            }
            System.out.println("========");
        }
    }
}