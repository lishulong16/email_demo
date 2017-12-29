package lsl.sendgrid.controller;

import lsl.sendgrid.enums.ResponseEnums;
import lsl.sendgrid.model.EmailMongoBean;
import lsl.sendgrid.model.EmailTemplateVo;
import lsl.sendgrid.model.ResponseBean;
import lsl.sendgrid.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @todo
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody EmailTemplateVo vo) {
        try {
            EmailMongoBean emailTemplate = emailService.createEmailTemplate(vo);
            if (null != emailTemplate)
                return ResponseEntity.ok(ResponseBean.putEnum(ResponseEnums.SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
    }

    @PostMapping("/modify")
    public ResponseEntity modifyEmailTemplate(@RequestBody EmailTemplateVo vo) {
        try {
            Boolean aBoolean = emailService.modifyEmailTemplate(vo);
            if (aBoolean)
                return ResponseEntity.ok(ResponseBean.putEnum(ResponseEnums.SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
    }

    @PostMapping("/list/{page}/{size}")
    public ResponseEntity list(@PathVariable int page, @PathVariable int size) {
        try {
            Pageable pageInfo = new QPageRequest(page, size);
            Page<EmailMongoBean> list = emailService.list(pageInfo);
            ResponseBean responseBean = ResponseBean.putEnum(ResponseEnums.SUCCESS);
            responseBean.put("content", list);
            return ResponseEntity.ok(responseBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
    }

    @PostMapping("/search/{name}")
    public ResponseEntity search(@PathVariable String name) {
        try {
            List<EmailMongoBean> search = emailService.search(name);
            ResponseBean responseBean = ResponseBean.putEnum(ResponseEnums.SUCCESS);
            responseBean.put("content", search);
            return ResponseEntity.ok(responseBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseBean.putEnum(ResponseEnums.FAILURE));
    }


}
