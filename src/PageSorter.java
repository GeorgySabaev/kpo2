import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class PageSorter {
    Path folderPath;

    public PageSorter(Path folderPath) {
        this.folderPath = folderPath;
    }

    LinkedList<Page> sortPageList(LinkedList<Page> list) throws IllegalArgumentException { // destructive
        LinkedList<Page> sorted = new LinkedList<>();
        HashSet<String> hashset = new HashSet<>();
        for (Page page : list) {
            for (String dependency : page.dependencies) {
                if (list.stream().noneMatch((Page p) -> Objects.equals(p.path, dependency))) {
                    throw new IllegalArgumentException("File " + dependency + " required in " + page.path + " does not exist.");
                }
            }
        }
        while (!list.isEmpty()) {
            Iterator<Page> iterator = list.iterator();
            while (iterator.hasNext()) {
                Page page = iterator.next();
                if (hashset.containsAll(page.dependencies)) {
                    sorted.add(page);
                    hashset.add(page.path);
                    iterator.remove();
                }
            }
        }
        return sorted;
    }
}
