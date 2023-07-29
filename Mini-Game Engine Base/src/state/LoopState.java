package state;

import enums.InputCommand;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class LoopState {
    protected boolean isRunning;
    private static final ConcurrentLinkedQueue<InputCommand> commandPool = new ConcurrentLinkedQueue<>();

    public static ConcurrentLinkedQueue<InputCommand> getCommandPool() {
        return commandPool;
    }

    abstract public void start();
    abstract public void processInput();
    abstract public void update(float deltaTime);
    abstract public void render(float deltaTime);
    abstract public void finish();

    public void play() throws InterruptedException {
        this.isRunning = true;

        this.start();

        while (isRunning) {
            this.processInput();
            this.update(0);
            this.render(0);
            Thread.sleep(16);
        }

    }

    public void stop() {
        if (!isRunning) {
            System.err.println(this.getClass().getSimpleName() + " - Cannot stop an state that is not running.");
            return;
        }
        this.isRunning = false;
        this.finish();
    }

    public boolean isRunning() {
        return isRunning;
    }
    public LoopState running(boolean running) {
        this.isRunning = running;
        return this;
    }
}
