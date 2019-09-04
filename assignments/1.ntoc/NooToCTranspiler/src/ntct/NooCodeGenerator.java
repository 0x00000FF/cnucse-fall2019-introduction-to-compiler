package ntct;

import java.util.HashMap;

public class NooCodeGenerator {
    static HashMap<Integer, String> stateMap;

    private static void initializeStateMap() {
        stateMap = new HashMap<Integer, String>();
        stateMap.put(1, "");
        stateMap.put(1, "");
        stateMap.put(1, "");
        stateMap.put(1, "");
        stateMap.put(1, "");
    }

    static String generate(NooStateMachine nsm) {
        StringBuilder codeBuilder = new StringBuilder();

        if (stateMap == null)
            initializeStateMap();

        codeBuilder.append("");

        return codeBuilder.toString();
    }
}
