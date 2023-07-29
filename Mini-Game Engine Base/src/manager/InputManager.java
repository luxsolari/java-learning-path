package manager;

import dictionary.CommandDictionary;
import enums.InputCommand;
import state.LoopState;

import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputManager implements Runnable {
    private volatile boolean isRunning;
    private final ConcurrentLinkedQueue<InputCommand> commandPool = LoopState.getCommandPool();
    final Scanner inputScanner = new Scanner(System.in);

    public InputManager() {
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                String currentStateName = null;
                if (StateManager.getStates().peek() != null) {
                    currentStateName = StateManager.getStates().peek().getClass().getSimpleName();

                    System.out.print(currentStateName + " >>> ");
                    String command = inputScanner.nextLine();
                    this.processCommand(command);
                }
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    synchronized void processCommand(String command) {
        if (!command.isBlank()) {
            InputCommand commandToAdd = lookUpCommand(command);
            if (commandToAdd != null) {
                commandPool.add(commandToAdd);
            }
            //System.out.println(">>> Command queued: " + ">>> " + command + " <<<");
            synchronized (commandPool) {
                //System.out.println(">>> notified!");
                commandPool.notify();
            }

            if(commandToAdd == InputCommand.EXIT_COMMAND && StateManager.getStates().size() == 1) {
                isRunning = false;
            }
        }
    }

    synchronized private InputCommand lookUpCommand(String command) {
        return CommandDictionary.commandDictionary.get(command);
    }
}
