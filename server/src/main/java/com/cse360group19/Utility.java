package com.cse360group19;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;

public class Utility {
    public static Map<String, List<String>> parseQuery(String rawQuery) {
        Map<String, List<String>> output = new HashMap<String, List<String>>();

        String[] splitQuery = rawQuery.split("&");

        for(int i = 0; i < splitQuery.length; i++) {
            String[] parameter = splitQuery[i].split("=");
            List<String> valueList = output.get(parameter[0]);

            String key = parameter[0];
            String value = parameter[1];

            try {
                key = URLDecoder.decode(key, "UTF-8");
                value = URLDecoder.decode(value, "UTF-8");
            }
            catch(Exception e) {
                System.out.println(e);
            }

            if(valueList == null) {
                valueList = new LinkedList<String>();
                valueList.add(value);
                output.put(key, valueList);
            }
            else {
                valueList.add(value);
            }
        }


        return output;
    }
}
