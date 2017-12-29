package lsl.sendgrid.service;

import lsl.sendgrid.model.EmailMongoBean;
import lsl.sendgrid.model.EmailTemplateVo;
import lsl.sendgrid.model.StatusEnum;
import lsl.sendgrid.repository.EmailRepository;
import lsl.sendgrid.utils.REUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @todo
 */
@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    /**
     * load one record by id
     *
     * @param id sendId
     * @return emailMongo
     */
    public EmailMongoBean load(String id) {
        return emailRepository.findBy_idAndStatus(id, StatusEnum.Normal.getValue());
    }

    /***
     * list records
     * @return page
     */
    public Page<EmailMongoBean> list(Pageable pageable) {
        return emailRepository.findAllByStatus(pageable,StatusEnum.Normal.getValue());
    }

    /***
     * search records
     */
    public List<EmailMongoBean> search(String name) {
        return emailRepository.findAllByNameIsLikeAndStatus(name,StatusEnum.Normal.getValue());
    }

    /**
     * add record
     *
     * @param vo request
     * @return response bean
     */
    public EmailMongoBean createEmailTemplate(EmailTemplateVo vo) {
        EmailMongoBean bean = new EmailMongoBean();
        bean.setSourceHtml(vo.getSourceHtml());
        bean.setName(vo.getName());
        bean.setDesc(vo.getDesc());
        bean.set_id(new ObjectId().toString());
        bean.setStatus(StatusEnum.Normal.getValue());
        bean.setParamsEntityMap(this.getParamsEntity(vo.getSourceHtml()));
        Date date = new Date();
        bean.setCreateTime(date);
        bean.setUpdateTime(date);
        return emailRepository.insert(bean);
    }

    /***
     * split html
     * @param content sourceHtml
     */
    private Map<Integer, EmailMongoBean.ParamsEntity> getParamsEntity(String content) {
        content += "{}";
        Map<Integer, EmailMongoBean.ParamsEntity> map = new HashMap<>();
        Matcher matcher = REUtils.patterN(content);
        int start = 0, end, index = 1;
        while (matcher.find()) {
            end = content.indexOf(matcher.group());
            String c = content.substring(start, end);
            start = end + matcher.group().length();
            map.put(index++, new EmailMongoBean.ParamsEntity(matcher.group(1).trim(), c));
        }
        return map;
    }


    /**
     * 修改数据
     *
     * @param vo request bean
     * @return 0 修改失败,1 修改成功
     */
    public Boolean modifyEmailTemplate(EmailTemplateVo vo) {

        EmailMongoBean bean = null;
        //id不为null
        if (StringUtils.hasText(vo.get_id()))
            bean = emailRepository.findOne(vo.get_id());
        else
            return false;
        //错误的id
        if (bean == null)
            return false;
        //传递的变量
        if (StringUtils.hasText(vo.getSourceHtml())){
            bean.setSourceHtml(vo.getSourceHtml());
            bean.setParamsEntityMap(this.getParamsEntity(vo.getSourceHtml()));
        }
        if (StringUtils.hasText(vo.getName()))
            bean.setName(vo.getName());
        if (StringUtils.hasText(vo.getDesc()))
            bean.setDesc(vo.getDesc());
        if (StringUtils.hasText(vo.getStatus())) {
            String status = vo.getStatus().trim();
            if (status.equals("1") || status.equals("0"))
                bean.setStatus(status);
        }
        bean.setUpdateTime(new Date());
        // 更新操作
        if (emailRepository.save(bean) != null)
            return true;
        return false;
    }
}
