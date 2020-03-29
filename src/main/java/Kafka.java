import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.TextToWordsUtil;

public class Kafka {
    private static final Logger log = LoggerFactory.getLogger(Kafka.class);

    public static void main(String[] args) {
        log.debug("{}", TextToWordsUtil.convert("test 111, 1121323. sdfsdf. "));
        
       
    }
}
