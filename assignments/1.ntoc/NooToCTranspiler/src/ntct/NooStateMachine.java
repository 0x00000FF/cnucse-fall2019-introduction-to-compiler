package ntct;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NooStateMachine {
    private int state = -1;
    private List<NooStateMachine> substates;

    public NooStateMachine(FileReader reader) throws IOException {
        while (true) {
            int read = reader.read();

            if (read == -1 || read == (int)'\n') break;
            else if (read == (int)'\'' ) {
                if (state < 0) state = 0;
                else substates.add(new NooStateMachine(reader));
            }
            else if (read == (int)'"') {
                if (state < 0 || state > 5) {
                    System.out.println("[ERROR] Invalid instruction fragment, STOP");
                    System.exit(-1);
                } else {
                    state++;
                }
            }
        }
    }

    public int getState() {
        return state;
    }

    public NooStateMachine getSubstate(int idx) {
        if (idx > substates.size() - 1) return null;

        return substates.get(idx);
    }
}
