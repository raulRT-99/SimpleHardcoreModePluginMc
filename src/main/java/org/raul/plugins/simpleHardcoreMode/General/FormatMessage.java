package org.raul.plugins.simpleHardcoreMode.General;

import java.util.Map;

public class FormatMessage {

    //FORMAT MESSAGES WITH ARGUMENTS
    public static String replaceArgs(String msg, Map<String, String> args){
        for(String key : args.keySet()){
            msg = msg.replace(key, args.get(key));
        }
        return msg;
    }


}
