import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.Handler;
import utils.TextToWordsUtil;

public class DataStream {
    private static final Logger log = LoggerFactory.getLogger(DataStream.class);

    public static void main(String[] args) {
        log.debug("{}", TextToWordsUtil.convert("test 111, 1121323. sdfsdf. "));

        Boolean exit = false;
        while (exit) {
            System.out.println("Выберите обработчик: ");

        }

        Queue<String> list = new LinkedList<>();

        list.add("F");
        list.add("B");
        list.add("D");
        list.add("E");
        list.add("C");
        System.out.println("Содержимое: " + list);

        String val = list.element();
        System.out.println("Содержимое после изменения: " + val + " " + list);

        System.out.println(list.poll());
        System.out.println("" + list);
        System.out.println("" + list);
        list.add("12");
        System.out.println("" + list);

        List<Handler> handlers = new ArrayList<>();
        

    }
}
