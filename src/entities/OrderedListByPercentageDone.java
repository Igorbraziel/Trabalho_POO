package entities;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedListByPercentageDone extends ListDecorator {
    public OrderedListByPercentageDone(IList list){
        super(list);
    }

    @Override
    public List<Task> getTasks(){ // TESTAR
        return getList()
                .getTasks()
                .stream()
                .sorted(
                        (task, other) -> task.getPercentageDone().compareTo(other.getPercentageDone())
                )
                .collect(Collectors.toList());
    }

    @Override
    public void showList(){
        for(Task t : getTasks()){
            System.out.println(t);
        }
    }
}
