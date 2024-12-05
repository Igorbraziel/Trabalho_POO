package exceptions;

import application.swingInterface.ToDoListGUI;

public class IdException extends RuntimeException {
    public IdException(String message){
        super(message);
        ToDoListGUI.showError(this);
    }
}
