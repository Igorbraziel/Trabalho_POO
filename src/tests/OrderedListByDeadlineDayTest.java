package tests;

import entities.IList;
import entities.Task;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListByDeadlineDayTest extends ListDecoratorTest {
    public OrderedListByDeadlineDayTest(IList list){
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
