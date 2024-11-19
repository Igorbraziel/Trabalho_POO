package entities;

import java.util.List;

public abstract class ListDecorator implements IList {
    private IList list;

    public ListDecorator(IList list){
        this.list = list;
    }

    public void addTask(Task task){
        list.addTask(task);
    }

    public void removeTask(Task task){
        list.removeTask(task);
    }

    public void updateTask(Task currentTask, Task newTask){
        list.updateTask(currentTask, newTask);
    }

    public Task getTask(int id){
        return list.getTask(id);
    }

    public List<Task> getTasks(){
        return list.getTasks();
    }

    public void showList(){
        list.showList();
    }

    public int size(){
        return list.size();
    }

    public IList getList(){
        return list;
    }
}
