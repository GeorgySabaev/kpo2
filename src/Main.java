import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Main {
    static Path folderPath;
    public static void main(String[] args) throws IOException {
        folderPath = Paths.get("input");
        PageParser pp = new PageParser(folderPath);
        FolderReader fr = new FolderReader();
        LinkedList<Page> list = new LinkedList<>();
        for (Path p : fr.readFolder(folderPath)) {
            list.add(pp.parse(p));
        }
        LinkedList<Page> sorted = new PageSorter(folderPath).sortPageList(list);
        for (Page page : sorted) {
            System.out.println(page.contents);
        }
    }
}