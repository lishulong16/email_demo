package lsl.sendgrid.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 */
@Document(collection = "email_template")
public class EmailMongoBean {

    @Id
    private String _id;

    //模版名字
    private String name;

    //模版描述
    private String desc;

    // 主题
//    private String subject;

    //填充变量
    private Map<Integer,ParamsEntity> paramsEntityMap;
    //html源码
    private String sourceHtml;
    //状态 0，正常使用 1，失效
    private String status;

    private Date createTime;

    private Date updateTime;

    public static class ParamsEntity{
        private String fileds;
        private String content;

        public ParamsEntity(String fileds, String content) {
            this.fileds = fileds;
            this.content = content;
        }

        public String getFileds() {
            return fileds;
        }

        public void setFileds(String fileds) {
            this.fileds = fileds;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<Integer, ParamsEntity> getParamsEntityMap() {
        return paramsEntityMap;
    }

    public void setParamsEntityMap(Map<Integer, ParamsEntity> paramsEntityMap) {
        this.paramsEntityMap = paramsEntityMap;
    }

    public String getSourceHtml() {
        return sourceHtml;
    }

    public void setSourceHtml(String sourceHtml) {
        this.sourceHtml = sourceHtml;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
