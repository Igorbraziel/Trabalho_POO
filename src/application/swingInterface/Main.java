package application.swingInterface;

import java.util.Locale;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        File outputFile = new File("Trabalho_POO/listContent.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(outputFile))){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
                String line = br.readLine();
                while(line != null){
                    line = br.readLine();
                }
            } catch(IOException writeException){
                System.out.println("Erro de escrita no arquivo: " + writeException.getMessage());
                return;
            }
        } catch (IOException readException){
            System.out.println("Erro de leitura no arquivo: " + readException.getMessage());
            return;
        }
        ListFrame frame = new ListFrame("Lista de Afazeres");
    }
}
