package lsl.sendgrid.repository;

import lsl.sendgrid.model.EmailMongoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @todo
 */
public interface EmailRepository extends MongoRepository<EmailMongoBean, String> {

    Page<EmailMongoBean> findAllByStatus(Pageable pageable,String status);


    List<EmailMongoBean> findAllByNameIsLikeAndStatus(String name,String status);

    EmailMongoBean findBy_idAndStatus(String _id,String status);


}
