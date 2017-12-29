package lsl.sendgrid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
public class EmailTemplateVo {

    private String _id;
    //模版名字
    private String name;
    //模版描述
    private String desc;
    //html源码
    @JsonProperty("source_html")
    private String sourceHtml;
    //状态 0，正常使用 1，失效
    private String status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSourceHtml() {
        return sourceHtml;
    }

    public void setSourceHtml(String sourceHtml) {
        this.sourceHtml = sourceHtml;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
