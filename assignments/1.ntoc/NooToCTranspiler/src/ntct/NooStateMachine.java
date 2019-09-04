package ntct;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class NooStateMachine {
    private int state = -1;
    private boolean returnsNegative = false;
    private Integer returnValue     = null;
    private List<NooStateMachine> substates;

    public NooStateMachine(FileReader reader, boolean isSubstate) throws IOException {
        if (isSubstate) state = 0;

        while (true) {
            int read = reader.read();

            if (read == -1 || read == (int)'\n') break;
            else if (read == (int)'\'' ) {
                if (state < 0) state = 0;
                else {
                    if (substates == null) substates = new Vector<NooStateMachine>();
                    substates.add(new NooStateMachine(reader, true));
                }
            }
            else if (read == (int)'"') {
                if (state < 0 || state > 5) {
                    Program.crash("[ERROR] Invalid state transition, STOP");
                } else {
                    state++;
                }
            }
            else if (read == (int)'-') {
                if (returnsNegative) {
                    Program.crash("[ERROR] Duplicated negate directive, STOP");
                } else {
                    returnsNegative = true;
                }
            }
        }
    }

    public int getState() {
        return this.state;
    }

    public NooStateMachine getSubstate(int idx) {
        if (idx > substates.size() - 1) return null;
        return substates.get(idx);
    }

    public int getReturn() {
        return (returnsNegative ? -1 : 1) * this.returnValue;
    }
}
