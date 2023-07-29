package manager;

import java.util.concurrent.*;

public class ThreadPoolManager {
    private final ExecutorService threadPool;

    public ThreadPoolManager() {
        this.threadPool = new ThreadPoolExecutor(5, 10, 1,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10)); // Evil dynamic thread pool sizing
    }

    public void startManager() {
        this.threadPool.execute(new StateManager());
        this.threadPool.execute(new InputManager());

        this.threadPool.shutdown();
    }
}
