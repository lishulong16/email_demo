package lsl.sendgrid.utils;

import com.alibaba.fastjson.JSON;
import lsl.sendgrid.model.EmailMongoBean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/26
 * @doing
 */
public class REUtils {

    private static Pattern filedsPattern = Pattern.compile("\\{(.*?)\\}");

    public static Matcher patterN(String content) {
        return filedsPattern.matcher(content);
    }


    public static void main(String[] args) {
        String content = "<!DOCTYPE html><html lang='en'><head>    <meta charset='UTF-8'>    <title></title></head><body><html><head></head><body><div>name:{name},code:{code}</div></body></html></body></html>{}";
//         content = "<html>name:,age:,desckkkkk{end}";
        Matcher matcher = REUtils.patterN(content);

        EmailMongoBean bean = new EmailMongoBean();
        int start = 0;
        int end;
        Map<Integer, EmailMongoBean.ParamsEntity> map = new HashMap<>();
        bean.setParamsEntityMap(map);
        int index = 1;
        while (matcher.find()) {
            end = content.indexOf(matcher.group());
            String c = content.substring(start, end);
            start = end + matcher.group().length();
            map.put(index, new EmailMongoBean.ParamsEntity(matcher.group(1), c));
            index++;
        }

        System.out.println(JSON.toJSON(bean));

        System.out.println("=======");

        StringBuilder tar = new StringBuilder();
        for (int i = 1; i <= map.keySet().size(); i++) {
            tar.append(map.get(i).getContent());
            tar.append(map.get(i).getFileds());
        }
        System.out.println(tar.toString());

    }
}
