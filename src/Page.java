import java.util.LinkedList;

public class Page {
    public String path;
    public LinkedList<String> dependencies;
    public String contents;
    public Page(String path, LinkedList<String> dependencies, String contents){
        this.contents = contents;
        this.dependencies = dependencies;
        this.path = path;
    }
}
