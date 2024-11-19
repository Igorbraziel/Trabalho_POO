package observer;

import entities.Task;

public interface ListObservable {
    void notifyTasks(Object obj);
    void notifyTask(Task task, Object obj);
}
