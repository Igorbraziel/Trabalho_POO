package entities;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedListById extends ListDecorator {
    public OrderedListById(IList list){
        super(list);
    }

    @Override
    public List<Task> getTasks(){ //Devo testar
        return getList().getTasks().stream().sorted((task, other) -> task.getId().compareTo(other.getId())).collect(Collectors.toList());
    }

    @Override
    public void showList(){
        for(Task t : getTasks()){
            System.out.println(t);
        }
    }
}
