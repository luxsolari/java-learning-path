package state;

import enums.InputCommand;

public class SomeOtherState extends LoopState {
    @Override
    public void start() {

    }
    @Override
    public void processInput() {
        // code to process player input
        //System.out.println("Gameplay State processing input...");
        LoopState.getCommandPool().forEach(command -> {
            // if command in queue is -1, we exit.
            if (command == InputCommand.SURRENDER_COMMAND) {
                this.stop();
            }

            LoopState.getCommandPool().remove();
        });
    }

    @Override
    public void update(float deltaTime) {
        // code to update game state
        //System.out.println("Gameplay State updating game state...");
    }

    @Override
    public void render(float deltaTime) {
        // code to render current frame
        //System.out.println("Gameplay State rendering...");
    }

    @Override
    public void finish() {
        // clean-up code that may be necessary to be executed before stopping a state.
        System.out.println("Finalized SomeOther State!");
        this.isRunning = false;
    }
}
