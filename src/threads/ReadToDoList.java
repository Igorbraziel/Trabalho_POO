package threads;

import application.swingInterface.ToDoListGUI;
import entities.IList;
import entities.Task;
import enums.Level;
import enums.Status;

import java.io.*;
import java.time.LocalDate;

public class ReadToDoList extends Thread {
    private IList toDoList;
    private File outputFile;
    private ToDoListGUI toDoListGUI;

    public ReadToDoList(IList toDoList, File outputFile){
        this.toDoList = toDoList;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new FileReader(outputFile))){
            String line;
            while(true){
                line = br.readLine();
                if(line == null) break;

                String[] fields = line.split(",");

                String taskName = fields[1];
                Level taskDifficultyLevel = Level.valueOf(fields[2]);
                LocalDate taskDeadlineDay = LocalDate.parse(fields[3], Task.dateTimeFormatter);
                Status taskStatus = Status.valueOf(fields[4]);
                Integer taskPercentageDone = Integer.valueOf(fields[5]);

                Task task = new Task(taskName, taskDifficultyLevel, taskDeadlineDay);
                task.setProgressingStatus(taskStatus);
                task.setPercentageDone(taskPercentageDone);

                toDoList.addTask(task);
            }
            toDoListGUI.showList();
        } catch (IOException readException){
            ToDoListGUI.showError(readException);
        }
    }

    public void setToDoListGUI(ToDoListGUI toDoListGUI) {
        this.toDoListGUI = toDoListGUI;
    }
}
