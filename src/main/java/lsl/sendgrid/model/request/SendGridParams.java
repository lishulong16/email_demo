package lsl.sendgrid.model.request;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lsl.sendgrid.model.EmailMongoBean;

import java.util.List;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
public class SendGridParams {

    /**
     * 发送模版的内容的EmailMongoBean._id
     * {@link EmailMongoBean} _id
     */
    @JsonProperty("send_id")
    private String sendId;

    //send to somebody
    @JsonProperty("email_bodys")
    private List<EmailBody> emailBodys;

    private String from;

    private String subject;


    public static class EmailBody{
        private String sb;
        @JsonProperty("email_content")
        private JSONObject emailContent;

        public JSONObject getEmailContent() {
            return emailContent;
        }

        public void setEmailContent(JSONObject emailContent) {
            this.emailContent = emailContent;
        }

        public String getSb() {

            return sb;
        }

        public void setSb(String sb) {
            this.sb = sb;
        }
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<EmailBody> getEmailBodys() {
        return emailBodys;
    }

    public void setEmailBodys(List<EmailBody> emailBodys) {
        this.emailBodys = emailBodys;
    }
}
