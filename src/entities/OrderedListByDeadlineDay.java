package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import java.time.Duration;

public class OrderedListByDeadlineDay extends ListDecorator {
    public OrderedListByDeadlineDay(IList list){
        super(list);
    }

    @Override
    public List<Task> getTasks(){
        return getList()
                .getTasks()
                .stream()
                .sorted(
                        (task, other) -> -((int) Duration.between(task.getDeadlineDay().atStartOfDay(), other.getDeadlineDay().atStartOfDay()).toDays())
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
