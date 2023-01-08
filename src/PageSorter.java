import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
/** Класс, отвечающий за сортировку списка файлов.
 */
public class PageSorter {
    /** Путь к рабочей папке.
     */
    Path folderPath;
    /** Конструктор класса.
     * @param folderPath Путь к рабочей папке.
     */
    public PageSorter(Path folderPath) {
        this.folderPath = folderPath;
    }

    /** Сортирует объекты таким образом, что каждый последующий элемент зависит исключительно от предыдущих. Алгоритм деструктивный, исходный список в процессе разрушается.
     * @param list Список объектов типа <a href="#{@link}">{@link Page}</a>.
     * @return Отсортированный список, в котором все зависимости каждого файла находятся раньше него.
     * @throws IllegalArgumentException Если сортировка невозможна.
     */
    LinkedList<Page> sortPageList(LinkedList<Page> list) throws IllegalArgumentException { // destructive
        LinkedList<Page> sorted = new LinkedList<>();
        HashSet<String> hashset = new HashSet<>();
        for (Page page : list) {
            for (String dependency : page.dependencies) {
                if (list.stream().noneMatch((Page p) -> Objects.equals(p.path, dependency))) {
                    throw new IllegalArgumentException("Файл " + page.path + " зависит от файла " + dependency + " который не существует.");
                }
            }
        }
        while (!list.isEmpty()) {
            Iterator<Page> iterator = list.iterator();
            boolean hasDeletedAPage = false;
            while (iterator.hasNext()) {
                Page page = iterator.next();
                if (hashset.containsAll(page.dependencies)) {
                    sorted.add(page);
                    hashset.add(page.path);
                    iterator.remove();
                    hasDeletedAPage = true;
                }
            }
            if (!hasDeletedAPage){
                throw new IllegalArgumentException("Существует циклическая зависимость.");
            }
        }
        return sorted;
    }
}
