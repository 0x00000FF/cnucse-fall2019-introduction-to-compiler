package ntct;

import java.util.List;

public class NooCodeGenerator {
    private StringBuilder   builder = null;
    private NooStateMachine masterMachine = null;

    private NooCodeGenerator(NooStateMachine masterMachine) {
        this.masterMachine = masterMachine;
    }

    public static NooCodeGenerator initialize(NooStateMachine masterMachine) {
        return new NooCodeGenerator(masterMachine);
    }

    public NooCodeGenerator build() {
        builder = new StringBuilder();

        builder.append("//Transpiled with Ntct by K. Heo, CSE-CNU\n");
        builder.append("#include <stdio.h>\n\n");
        builder.append("int main() {\n");
        builder.append("    int r = 0");

        for (int i = 1; i <= Program.returnDepth; ++i) {
            String registerNo = Integer.toString(i);
            builder.append(", r" + registerNo + " = 0");
        }

        builder.append(";\n");

        buildCodes(masterMachine, 0, 0);

        builder.append("\treturn r;\n");
        builder.append("}\n");

        return this;
    }

    private NooCodeGenerator buildCodes(NooStateMachine machine, int currentReturnDepth, int argNo) {
        NooStateMachine currentMachine = machine;
        boolean         hasSubStates   = currentMachine.getSubstates()
                                                       .size() > 0;
        List<NooStateMachine> subStates = null;

        if (hasSubStates) {
            subStates = currentMachine.getSubstates();
            if (machine.getState() == 5) {
                buildCodes(subStates.get(0), currentReturnDepth + 1, 1);
                builder.append("\tif ( r");
                builder.append(currentReturnDepth + 1);
                builder.append(" ) {\n");
                buildCodes(subStates.get(1), currentReturnDepth + 1, 1);
                builder.append("\t} else {\n ");
                buildCodes(subStates.get(2), currentReturnDepth + 1, 2);
                builder.append("\t}\n");
                return this;
            } else {
                for (int i = 0; i < subStates.size(); ++i) {
                    buildCodes(subStates.get(i), currentReturnDepth + 1, i + 1);
                }
            }
        }

        switch (currentMachine.getState()) {
            case 1:
                if (hasSubStates) {
                    builder.append("\tprintf(\"%d\\n\", r");
                    builder.append(currentReturnDepth + 1);
                    builder.append(");\n");

                    builder.append("\tr");
                    builder.append(currentReturnDepth == 0 ? "" : currentReturnDepth);
                    builder.append(" = r");
                    builder.append(currentReturnDepth + 1);
                    builder.append(";\n");
                } else {
                    builder.append("\tprintf(\"%d\\n\", ");
                    builder.append(currentMachine.getReturn());
                    builder.append(");\n");

                    builder.append("\tr");
                    builder.append(currentReturnDepth == 0 ? "" : currentReturnDepth);
                    builder.append(" = ");
                    builder.append(currentMachine.getReturn());
                    builder.append(";\n");
                }
                break;

            case 2:
                if (hasSubStates) {
                    builder.append("\tr");
                    builder.append(currentReturnDepth == 0 ? "" : currentReturnDepth);
                    builder.append(" = r");
                    builder.append(currentReturnDepth + 1);
                    builder.append(" + 1;\n");
                } else {
                    builder.append("\tr");
                    builder.append(currentReturnDepth == 0 ? "" : currentReturnDepth);
                    builder.append(" = ");
                    builder.append(currentMachine.getReturn());
                    builder.append(" + 1;\n");
                }
                break;

            case 3:
                builder.append("\tr");
                builder.append(currentReturnDepth);
                builder.append(" = 0;\n");
                break;

            case 4:
                builder.append("\tr");
                builder.append(currentReturnDepth == 0 ? "" : currentReturnDepth);
                builder.append(" = ");
                builder.append("r");
                builder.append(currentReturnDepth + 1);
                builder.append(";\n");
                break;

            case 5:

                break;

            default:
                System.out.println("[ERROR] Unavailable state, Error");
                System.exit(-1);
                break;
        }

        return this;
    }

    public String toString() {
        return builder.toString();
    }
}
