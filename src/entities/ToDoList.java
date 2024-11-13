package entities;

public class ToDoList {
    private static ToDoList instance;

    private ToDoList(){}

    public static ToDoList getInstance(){
        if(instance == null){
            instance = new ToDoList();
        }

        return instance;
    }
}
