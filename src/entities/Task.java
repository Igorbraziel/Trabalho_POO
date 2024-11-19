package entities;

import enums.Level;

public class Task {
    private Integer id;
    private String name;
    private Level difficultyLevel;

    private static Integer idNumber = 1;

    public Task(String name, Level difficultyLevel){
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.id = idNumber;
        idNumber += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Level difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getId(){
        return id;
    }

    @Override
    public String toString(){
        return "[id - " + id + ", nome - " + name + ", nível de dificuldade - " + difficultyLevel + "]";
    }
}
