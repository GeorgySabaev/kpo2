import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Main {
    /** Путь к рабочей папке.
     */
    static Path folderPath;

    /** Точка входа.
     */
    public static void main(String[] args) {
        folderPath = Paths.get("input");
        PageParser pp = new PageParser(folderPath);
        FolderReader fr = new FolderReader();
        LinkedList<Page> list = new LinkedList<>();
        try {
            for (Path p : fr.readFolder(folderPath)) {
                list.add(pp.parse(p));
            }
        } catch (IOException exception){
            System.out.println("Ошибка при чтении файла.");
            return;
        }
        try {
            LinkedList<Page> sorted = new PageSorter(folderPath).sortPageList(list);
            for (Page page : sorted) {
                System.out.println(page.contents);
            }
        } catch (IllegalArgumentException exception){
            System.out.println("Некорректные зависимости:");
            System.out.println(exception.getMessage());
            System.out.println("Решение задачи для данной папки невозможно.");
        }
    }
}