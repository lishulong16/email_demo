package lsl.sendgrid.cache;

import lsl.sendgrid.model.EmailMongoBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/26
 * @doing email 模版加载本地缓存
 */
public class EmailTempletCache {

    private static Map<String, EmailMongoBean> map = new HashMap<>();


    /***
     * load local cache
     * @param id id
     * @return EmailMongoBean
     */
    public static EmailMongoBean loadEmailMongoBeanById(String id) {
        return map.get(id);
    }

}
