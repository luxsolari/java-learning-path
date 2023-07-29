import dictionary.CommandDictionary;
import manager.ThreadPoolManager;

public class Program {
    private static final ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    public static void main(String[] args) throws InterruptedException {
        CommandDictionary.initCommandDictionary();
        threadPoolManager.startManager();
    }
}
