package lsl.sendgrid.model;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/25
 * @doing
 */
public enum StatusEnum {

    Normal("1"),
    NoNormal("0");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
