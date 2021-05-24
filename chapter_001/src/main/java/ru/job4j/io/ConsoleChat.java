package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> botAnswersList;

    public ConsoleChat(String path, String botAnswers) throws IOException {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            boolean isWorking = true;
            boolean isStopped = false;
            while (isWorking) {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите вопрос: ");
                String question = in.nextLine();
                bw.write(question + System.lineSeparator());
                if (question.equals(OUT)) {
                    isWorking = false;
                }
                if (question.equals(STOP)) {
                    isStopped = true;
                }
                if (question.equals(CONTINUE)) {
                    isStopped = false;
                }
                if (isWorking && !isStopped) {
                    String answer = takeAnswer();
                    System.out.println("Бот отвечает: " + answer);
                    bw.write(answer + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String takeAnswer() {
        if (botAnswersList == null) {
            botAnswersList = new ArrayList<String>();
            try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.equals("")) {
                        botAnswersList.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return botAnswersList.get((int) (Math.random() * botAnswersList.size()));
    }

    public static void main(String[] args) throws IOException {
        String sourceAnswers = "./chapter_001/data/botanswers.txt";
        String logOutFile = "./chapter_001/data/botlogfile.txt";
        ConsoleChat cc = new ConsoleChat(logOutFile, sourceAnswers);
        cc.run();
    }
}
