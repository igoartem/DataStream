package org.example.DataStreams;

//@Configuration
//public class DataStreamConfig {
//    //
//    @Bean
//    public StringHandler commandLineRunner() {
//
//        Queue<StringEvent> inputStringQueue = new LinkedList();
//        Queue<SymbolEvent> outputStringQueue = new LinkedList();
//
//        StringHandler stringHandler = StringHandler.builder().inputEvents(inputStringQueue).outputEvents(outputStringQueue).build();
//        //        stringHandler.start();
//
//        int i = 0;
//        Boolean exit = false;
//        while (!exit) {
//            inputStringQueue.add(new StringEvent("test" + i++));
//            if (i == 1000) {
//                exit = true;
//            }
//        }
//
//        return stringHandler;
//    }
//}
