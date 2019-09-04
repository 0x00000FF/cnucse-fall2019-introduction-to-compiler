package ntct;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
    public static void crash(String message) {
        System.out.println(message);
        System.exit(-1);
    }

    public static void main(String[] args) {
        System.out.println("Noo to C Transpiler\n" +
                           "Copyright (c) Kangjun Heo @ Chungnam National University\n" +
                           "========================================================");

        if (args.length == 0) {
            System.out.println("USAGE: java -jar ntct.jar <filename>\n");
            System.exit(1);
        }

        try {
            File file = new File(args[1]);
            if (!file.exists()) {
                System.out.println("[ERROR] File Not Found, Exit.");
                System.exit(1);
            }

            FileReader      reader  = new FileReader(file);
            NooStateMachine nsm     = new NooStateMachine(reader, false);

            String code = NooCodeGenerator.generate(nsm);


            reader.close();

        } catch (IOException e) {
            System.out.println("[ERROR] IOException thrown, cannot be proceed");
            System.exit(-1);
        }
    }
}
