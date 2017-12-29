package lsl.sendgrid.controller;

import lsl.sendgrid.model.EmailMongoBean;
import lsl.sendgrid.model.ResponseBean;
import lsl.sendgrid.model.request.SendGridParams;
import lsl.sendgrid.service.SendGridService;
import lsl.sendgrid.enums.ResponseEnums;
import lsl.sendgrid.model.request.SendGridRequestBean;
import lsl.sendgrid.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
@RestController
@RequestMapping("/sendgrid-email")
public class SendGridController {

    private static Logger LOGGER = LoggerFactory.getLogger(SendGridController.class);

    @Autowired
    private SendGridService sendGridService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public ResponseEntity sendEmail(@RequestBody SendGridParams sendGridParams) {
        LOGGER.info("[{}] send email , subject:{} , sendId:{}", sendGridParams.getFrom(), sendGridParams.getSubject(), sendGridParams.getSendId());
        try {
            if (CollectionUtils.isEmpty(sendGridParams.getEmailBodys()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBean.putEnum(ResponseEnums.EMAIL_NO_EXIT_TOSBS));

            if (!StringUtils.hasText(sendGridParams.getFrom()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBean.putEnum(ResponseEnums.EMAIL_NO_EXIT_FROM));

            EmailMongoBean load = null;
            if (StringUtils.hasText(sendGridParams.getSendId())) {
                load = emailService.load(sendGridParams.getSendId());
            }
            if (null == load)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBean.putEnum(ResponseEnums.EMAIL_NO_EXIT_SENDID));
            Set<String> strings = sendGridService.sendGrid(load, sendGridParams);
            if (strings.size() == 0)
                return ResponseEntity.ok(ResponseBean.putEnum(ResponseEnums.SUCCESS));
            else {
                ResponseBean responseBean = ResponseBean.putEnum(ResponseEnums.EMAIL_SEND_LOSE);
                responseBean.put("unsuccess", strings);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
        }


    }

    @PostMapping("/send/template")
    public ResponseEntity sendEmail(@RequestBody SendGridRequestBean sendGridParams) {

        if (!StringUtils.hasText(sendGridParams.getTemplateId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseBean.putEnum(ResponseEnums.EMAIL_TEMPLETEID));
        }
        try {
            LOGGER.info("request [{}] send email ,first subject:{} , templateId:{}", sendGridParams.getFrom().getEmail(), sendGridParams.getPersonalization().get(0).getSubject(), sendGridParams.getTemplateId());
            Set<String> strings = sendGridService.sendGrid(sendGridParams);
            if (strings.size() > 0) {
                ResponseBean responseBean = ResponseBean.putEnum(ResponseEnums.EMAIL_SEND_LOSE);
                responseBean.put("unsuccess", strings);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBean);
            }
            LOGGER.info("response [{}] send email ,first subject:{} , templateId:{}", sendGridParams.getFrom(), sendGridParams.getPersonalization().get(0).getSubject(), sendGridParams.getTemplateId());
            return ResponseEntity.ok(ResponseBean.putEnum(ResponseEnums.SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
        }

    }
}
