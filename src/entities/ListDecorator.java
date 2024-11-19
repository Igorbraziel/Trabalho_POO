package entities;

import java.util.List;

public abstract class ListDecorator implements IList {
    private IList list;

    public ListDecorator(IList list){
        this.list = list;
    }

    public void addTask(Task task){
        list.getTasks().add(task);
    }

    public void removeTask(Task task){
        list.getTasks().remove(task);
    }

    public void updateTask(Task currentTask, Task newTask){ // Conferir o funcionamento
        Task task = list.getTasks().stream().filter(t -> t.getId() == currentTask.getId()).findFirst().orElse(null);
        if(task != null){
            task = newTask;
        }
    }

    public Task getTask(int id){
        return list.getTasks().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public List<Task> getTasks(){
        return list.getTasks();
    }

    public void showList(){
        for(Task t : list.getTasks()){
            System.out.println(t);
        }
    }

    public int size(){
        return list.getTasks().size();
    }

    public IList getList(){
        return list;
    }
}
