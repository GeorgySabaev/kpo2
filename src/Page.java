import java.util.LinkedList;

/** Хранит информацию о файле и его зависимости.
 */
public class Page {
    /** Путь к файлу.
     */
    public String path;
    /** Список зависимостей.
     */
    public LinkedList<String> dependencies;
    /** Содержимое файла.
     */
    public String contents;

    /** Конструктор класса.
     * @param path Путь к файлу.
     * @param dependencies Список зависимостей файла.
     * @param contents Содержимое файла.
     */
    public Page(String path, LinkedList<String> dependencies, String contents){
        this.contents = contents;
        this.dependencies = dependencies;
        this.path = path;
    }
}
