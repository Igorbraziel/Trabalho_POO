package tests;

import entities.IList;
import entities.Task;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderedListByNameTest extends ListDecoratorTest {
    public OrderedListByNameTest(IList list){
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
