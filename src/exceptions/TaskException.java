package exceptions;

import application.swingInterface.ToDoListGUI;

public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super(message);
        ToDoListGUI.showError(this);
    }
}
