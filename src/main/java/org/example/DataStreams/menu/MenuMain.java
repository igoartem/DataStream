package org.example.DataStreams.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.DataStreams.menu.Menu;
import org.example.DataStreams.menu.MenuItem;
import org.example.DataStreams.service.Handler;
import org.example.DataStreams.service.RepeaterCounterHandler;
import org.example.DataStreams.service.RepeaterPauseHandler;
import org.example.DataStreams.service.TextHandler;
import org.example.DataStreams.service.WordsHandler;
import org.example.DataStreams.utils.ConsoleUtils;
import org.springframework.stereotype.Service;

@Service
public class MenuMain {

    private List<Handler> handlers = new ArrayList<>();

    public void mainMenu() {
        Menu menu = new Menu();
        menu.setTitle("*** Menu ***");
        Arrays.asList(Handler.HandlerType.values()).forEach(handlerType -> {
            menu.addItem(new MenuItem(handlerType.getName(), this, handlerType.name().toLowerCase()));
        });
        menu.addItem(new MenuItem("Закончить ввод обработчиков", this, "run"));
        menu.execute();
    }

    public void text() {
        System.out.println("Ввведите параметры: ");
        boolean work = true;
        String path = null;
        while (work) {
            path = ConsoleUtils.readString("Путь до файла: ");
            File file = new File(path);
            if (file.exists()) {
                work = false;
            } else {
                System.out.println("Файл не существует!");
            }
        }
        String nameOutputTopic = ConsoleUtils.readString("Название topic для очереди слов: ");
        TextHandler stringHandler = new TextHandler(path);
        handlers.add(stringHandler);
        System.out.println("Обработчик добавлен");
    }

    public void repeater_counter() {
        System.out.println("Ввведите параметры: ");
        long sec = ConsoleUtils.readNumbers("Количество секунд через сколько выводить на экран:", input -> Long.valueOf(input));
        String nameInputTopic = ConsoleUtils.readString("Название topic для создания: ");
        String nameOutputTopic = ConsoleUtils.readString("Название topic для создания: ");
        handlers.add(RepeaterCounterHandler.builder().timeOut(sec * 1000L).build());
        System.out.println("Обработчик добавлен");
    }

    public void words() {
        String nameInputTopic = ConsoleUtils.readString("Название topic для создания: ");
        String nameOutputTopic = ConsoleUtils.readString("Название topic для создания: ");
        handlers.add(WordsHandler.builder().build());
        System.out.println("Обработчик добавлен");
    }

    public void repeater_pause() {
        System.out.println("Ввведите параметры: ");
        int sec = ConsoleUtils.readNumbers("Количество секунд паузы:", input -> Integer.valueOf(input));
        String nameInputTopic = ConsoleUtils.readString("Название topic для создания: ");
        String nameOutputTopic = ConsoleUtils.readString("Название topic для создания: ");
        handlers.add(RepeaterPauseHandler.builder().timeOut(sec * 1000L).build());
        System.out.println("Обработчик добавлен");
    }

    public void run() {

    }
}
