package ntct;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class NooStateMachine {
    private int state = -1;

    private boolean eofEncountered = false;
    private boolean returnsNegative = false;
    private boolean machineReceivedConstant = false;
    private StringBuilder constantBuffer = null;

    private Integer returnValue     = null;
    private Vector<NooStateMachine> substates;

    private void transState() {

    }

    public NooStateMachine(FileReader reader, boolean isSubstate) throws IOException {
        if (isSubstate)
            state = 0;

        substates = new Vector<NooStateMachine>();

        char read;

        do
        {
            int iRead = reader.read();
            if (iRead == -1) {
                eofEncountered = true;
                break;
            }

            read = (char)iRead;

            if (read == '\'' && state < 0) {
                state = 0;
                continue;
            } else if (read == '\"') {
                state++;
            } else {
                if (Character.isDigit(read)) {
                    constantBuffer.append(read);
                } else if (read == '#') {
                    eofEncountered = true;
                    break;
                } else {
                    Program.crash("[ERROR] Unallowed instruction fragment, HALT");
                }
            }
        }
        while (read != '\'');

        if (this.constantBuffer.length() > 0) {
            returnValue = Integer.parseInt(constantBuffer.toString());
        } else if (!eofEncountered) {
            substates.add(new NooStateMachine(reader, true));
        }
    }

    public int getState() {
        if (state == -1) {

        }

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
