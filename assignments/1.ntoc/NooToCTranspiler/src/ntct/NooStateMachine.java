package ntct;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Vector;

public class NooStateMachine {
    private int state = -1;

    private boolean eofEncountered = false;
    private boolean returnsNegative = false;
    private StringBuilder constantBuffer = null;

    private Integer returnValue     = null;
    private Vector<NooStateMachine> substates;

    public NooStateMachine(Reader reader, boolean isSubstate) throws IOException {
        if (isSubstate)
            state = 0;

        substates = new Vector<NooStateMachine>();
        constantBuffer = new StringBuilder();

        char read;

        do
        {
            int iRead = reader.read();
            Program.lastRead = iRead;

            if (iRead == -1 || iRead == (int)'\n') {
                break;
            }

            read = (char)iRead;

            if (read == '\'') {
                if (state < 0) {
                    state = 0;
                    read = 0;
                } else {
                    Program.returnDepth++;

                    if (this.state == 3) {
                        break;
                    }

                    while (Program.lastRead != -1 && Program.lastRead != 10) {
                        NooStateMachine substate = new NooStateMachine(reader, true);
                        substates.add(substate);
                    }
                }
            } else if (read == '\"') {
                state++;
            } else {
                if (Character.isDigit(read)) {
                    constantBuffer.append(read);
                } else if (read == '#') {
                    break;
                } else {
                    Program.crash("[ERROR] Unallowed instruction fragment, HALT");
                }
            }
        } while (Program.lastRead != -1 && Program.lastRead != 10);

        if (this.constantBuffer.length() > 0) {
            this.returnValue = Integer.parseInt(constantBuffer.toString());
        }

        System.out.println((isSubstate ? "Sub" : "Master") + " Machine, State " + state
                            + ", Return/Argument "
                            + (returnValue == null ?
                                (substates.size() > 0 ?  substates.size() + " Substate(s)" : "Nothing") : returnValue));
    }

    public int getState() {
        return this.state;
    }

    public List<NooStateMachine> getSubstates() {
        return this.substates;
    }

    public Integer getReturn() {
        if (this.returnValue == null) return -1;
        else return (returnsNegative ? -1 : 1) * this.returnValue;
    }
}
