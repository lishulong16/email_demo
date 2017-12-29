package lsl.sendgrid.enums;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
public enum  ResponseEnums {

    SUCCESS("S0000","success"),
    FAILURE("S0001","system error"),


    EMAIL_NO_EXIT_SENDID("E0001","sendId not exist"),
    EMAIL_NO_EXIT_FROM("E0002","from not null"),
    EMAIL_NO_EXIT_TOSBS("E0003","to sb not null"),
    EMAIL_SEND_LOSE("E0004","exit send error"),

    EMAIL_TEMPLETEID("E0005","email templateId must not null");





    private String name;
    private String value;
    ResponseEnums(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
