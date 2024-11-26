package tests;

import entities.IList;
import entities.Task;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListByPercentageDoneTest extends ListDecoratorTest {
    public OrderedListByPercentageDoneTest(IList list){
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
