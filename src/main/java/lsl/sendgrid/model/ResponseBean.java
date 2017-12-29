package lsl.sendgrid.model;

import lsl.sendgrid.enums.ResponseEnums;

import java.util.HashMap;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
public class ResponseBean extends HashMap<String,Object>{

    public static ResponseBean putEnum(ResponseEnums responseEnums){
        ResponseBean responseBean = new ResponseBean();
        responseBean.put("code",responseEnums.getName());
        responseBean.put("msg",responseEnums.getValue());
        return responseBean;
    }
}
