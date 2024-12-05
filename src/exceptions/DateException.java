package exceptions;

import application.swingInterface.ToDoListGUI;

public class DateException extends RuntimeException {
    public DateException(String message){
        super(message);
        ToDoListGUI.showError(this);
    }
}
