package application.swingInterface;

import entities.IList;
import entities.OrderedListById;
import entities.Task;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ListWindowListener extends WindowAdapter {
    private ListFrame listFrame;
    private IList toDoList;
    private File outputFile;

    public ListWindowListener(ListFrame listFrame, IList toDoList, File outputFile){
        this.listFrame = listFrame;
        this.toDoList = toDoList;
        this.outputFile = outputFile;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            toDoList = new OrderedListById(toDoList);
            for(Task task: toDoList.getTasks()){
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(task.getId() + ",");
                stringBuilder.append(task.getName() + ",");
                stringBuilder.append(task.getDifficultyLevel().toString() + ","); // testar essa linha
                stringBuilder.append(task.getDeadlineDay().format(Task.dateTimeFormatter) + ",");
                stringBuilder.append(task.getProgressingStatus().toString() + ",");
                stringBuilder.append(task.getPercentageDone());
                bw.write(stringBuilder.toString());
                bw.newLine();
            }
        } catch(IOException writeException){
            ToDoListGUI.showError(writeException);
        }
        listFrame.dispose();
    }
}
