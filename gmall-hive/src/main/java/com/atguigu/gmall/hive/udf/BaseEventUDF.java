package com.atguigu.gmall.hive.udf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseEventUDF extends UDF {

    public String evaluate(String log, String key) throws JSONException {

        String[] split = log.split("\\|");

        if(split.length!=2 || StringUtils.isBlank(split[1])){
            return "";
        }

        JSONObject base = new JSONObject(split[1]);

        if("et".equals(key)){

            if(!base.has("et")){
                return "";
            }

            String et= base.getString(key);
            if(et!=null && !"".equals(et)){
                return et;
            }
        }else if("st".equals(key)) {
            return split[0];
        }else {

            if(!base.has("cm")){
                return "";
            }

            JSONObject cm = base.getJSONObject("cm");
            if(cm.has(key)){
                return cm.getString(key);
            }
        }
        return "";
    }

}
