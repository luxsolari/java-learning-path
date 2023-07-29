package manager;

import state.MainMenuState;
import state.LoopState;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class StateManager implements Runnable {
    private static final Queue<LoopState> states = Collections.asLifoQueue(new ArrayDeque<>());
    private static LoopState currentState;

    public static Queue<LoopState> getStates() {
        return states;
    }

    @Override
    public void run() {
        try {
            getStates().add(new MainMenuState().running(true));
            currentState = getStates().peek();
            this.init();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws InterruptedException {
        while (currentState != null) {
            if (!currentState.isRunning()) {
                currentState = null;
                getStates().remove();

                if (getStates().peek() != null) {
                    currentState = getStates().peek();
                    currentState.running(true);
                }
            }
            else {
                currentState.play();
            }
        }
    }

    public static void enqueueState (LoopState newState) {
        currentState.running(false);
        currentState = newState;
        getStates().add(newState.running(true));
    }
}
