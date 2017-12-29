package lsl.sendgrid.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sendgrid.*;
import lsl.sendgrid.model.EmailMongoBean;
import lsl.sendgrid.model.request.SendGridParams;
import lsl.sendgrid.model.request.SendGridRequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @todo
 */
@Service
public class SendGridService {

    private static Logger LOGGER = LoggerFactory.getLogger(SendGridService.class);

    @Value("${email.templateApi}")
    private String sendEmailApi;

    @Autowired
    private SendGrid sendGrid;

    public Set<String> sendGrid(EmailMongoBean load, SendGridParams sendGridParams) {
        Email from = new Email(sendGridParams.getFrom());
        Set<String> unsuccess = new HashSet<>();
        LOGGER.info("[{}] send email , subject:{} , sendId:{}", sendGridParams.getFrom(), sendGridParams.getSubject(), sendGridParams.getSendId());
        for (SendGridParams.EmailBody bean : sendGridParams.getEmailBodys()) {
            if (StringUtils.isEmpty(bean.getSb()))
                continue;

            Email to = new Email(bean.getSb());
            Content content = new Content("text/html", this.fillContent(load.getParamsEntityMap(), bean.getEmailContent()));
            Mail mail = new Mail(from, sendGridParams.getSubject(), to, content);
            Request request = new Request();
            LOGGER.info("send to:{}", bean.getSb());
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sendGrid.api(request);
                if (response.getStatusCode() != 202)
                    unsuccess.add(bean.getSb());
            } catch (IOException ex) {
                unsuccess.add(bean.getSb());
                ex.printStackTrace();
            }
        }
        if (unsuccess.size() > 0)
            LOGGER.info("send unsuccess list:{}", JSON.toJSON(unsuccess).toString());
        return unsuccess;
    }

    /***
     *
     * @param map map
     * @param emailContent content
     * @return content
     */
    private String fillContent(Map<Integer, EmailMongoBean.ParamsEntity> map, JSONObject emailContent) {

        if (CollectionUtils.isEmpty(map))
            return null;

        StringBuilder tar = new StringBuilder();
        for (int i = 1; i <= map.keySet().size(); i++) {
            tar.append(map.get(i).getContent());
            Object obj = emailContent.get(map.get(i).getFileds());
            tar.append(obj == null ? "" : obj);
        }
        System.out.println(tar.toString());
        return tar.toString();
    }

    /***
     * send grid
     * @param sendGridRequestBean bean
     * @return set
     */
    public Set<String> sendGrid(SendGridRequestBean sendGridRequestBean) {
        Set<String> unsuccess = new HashSet<>();
        Request request = new Request();
        request.setBaseUri(sendEmailApi);
        try {
            request.setBody(sendGridRequestBean.build());
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            Response response = sendGrid.api(request);
            System.out.println(response.getHeaders());
            System.out.println(response.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
            unsuccess.add(e.getMessage());
        }
        return unsuccess;
    }

    public static void main(String[] args) {
        String a = "{\"custom_args\":{\"New Argument 1\":\"New Value 1\",\"activationAttempt\":\"1\",\"customerAccountNumber\":\"[CUSTOMER ACCOUNT NUMBER GOES HERE]\"},\"from\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"},\"attachments\":[{\"name\":\"file1\",\"filename\":\"file1.jpg\",\"content\":\"[BASE64 encoded content block here]\",\"disposition\":\"inline\",\"content_id\":\"ii_139db99fdb5c3704\",\"type\":\"jpg\"}],\"personalizations\":[{\"to\":[{\"email\":\"john.doe@example.com\",\"name\":\"John Doe\"}],\"cc\":[{\"email\":\"jane.doe@example.com\",\"name\":\"Jane Doe\"}],\"bcc\":[{\"email\":\"sam.doe@example.com\",\"name\":\"Sam Doe\"}],\"custom_args\":{\"New Argument 1\":\"New Value 1\",\"activationAttempt\":\"1\",\"customerAccountNumber\":\"[CUSTOMER ACCOUNT NUMBER GOES HERE]\"},\"headers\":{\"X-Accept-Language\":\"en\",\"X-Mailer\":\"MyApp\"},\"send_at\":1409348513,\"substitutions\":{\"type\":\"object\",\"id\":\"substitutions\"},\"subject\":\"Hello, World!\"}],\"subject\":\"Hello, World!\",\"ip_pool_name\":\"[YOUR POOL NAME GOES HERE]\",\"content\":[{\"type\":\"text/html\",\"value\":\"<html><p>Hello, world!</p><img src=[CID GOES HERE]></img></html>\"}],\"headers\":{},\"asm\":{\"groups_to_display\":[1,2,3],\"group_id\":1},\"batch_id\":\"[YOUR BATCH ID GOES HERE]\",\"tracking_settings\":{\"subscription_tracking\":{\"text\":\"If you would like to unsubscribe and stop receiveing these emails <% click here %>.\",\"enable\":true,\"html\":\"If you would like to unsubscribe and stop receiving these emails <% clickhere %>.\",\"substitution_tag\":\"<%click here%>\"},\"open_tracking\":{\"enable\":true,\"substitution_tag\":\"%opentrack\"},\"click_tracking\":{\"enable\":true,\"enable_text\":true},\"ganalytics\":{\"utm_campaign\":\"[NAME OF YOUR REFERRER SOURCE]\",\"enable\":true,\"utm_name\":\"[NAME OF YOUR CAMPAIGN]\",\"utm_term\":\"[IDENTIFY PAID KEYWORDS HERE]\",\"utm_content\":\"[USE THIS SPACE TO DIFFERENTIATE YOUR EMAIL FROM ADS]\",\"utm_medium\":\"[NAME OF YOUR MARKETING MEDIUM e.g. email]\"}},\"mail_settings\":{\"footer\":{\"text\":\"Thanks,/n The SendGrid Team\",\"enable\":true,\"html\":\"<p>Thanks</br>The SendGrid Team</p>\"},\"spam_check\":{\"threshold\":3,\"post_to_url\":\"http://example.com/compliance\",\"enable\":true},\"bypass_list_management\":{\"enable\":true},\"sandbox_mode\":{\"enable\":false},\"bcc\":{\"enable\":true,\"email\":\"ben.doe@example.com\"}},\"reply_to\":{\"email\":\"sam.smith@example.com\",\"name\":\"Sam Smith\"},\"sections\":{\"section\":{\":sectionName2\":\"section 2 text\",\":sectionName1\":\"section 1 text\"}},\"template_id\":\"[YOUR TEMPLATE ID GOES HERE]\",\"categories\":[\"category1\",\"category2\"],\"send_at\":1409348513}";
        System.out.println(a);

    }

}
