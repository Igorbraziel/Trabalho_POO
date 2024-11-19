package entities;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedListByName extends ListDecorator {
    public OrderedListByName(IList list){
        super(list);
    }

    @Override
    public List<Task> getTasks(){
        return getList().getTasks().stream().sorted((task, other) -> task.getName().compareTo(other.getName())).collect(Collectors.toList());
    }

    @Override
    public void showList(){
        for(Task t : getTasks()){
            System.out.println(t);
        }
    }
}
