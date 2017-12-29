package lsl.sendgrid.utils;

import com.sendgrid.*;

import java.io.IOException;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/27
 * @doing
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Mail mail = new Mail();

        Email fromEmail = new Email();
        fromEmail.setName("Example User");
        fromEmail.setEmail("lishulong@wecash.net");
        mail.setFrom(fromEmail);

        mail.setSubject("Hello World from the SendGrid Java Library");

        Personalization personalization = new Personalization();
        Email to = new Email();
        to.setName("Example User");
        to.setEmail("lishulong@wecash.net");
        personalization.addTo(to);
//        to.setName("Example User");
//        to.setEmail("lishulong@wecash.net");
//        personalization.addTo(to);
        Email cc = new Email();
        cc.setName("Example User");
        cc.setEmail("lishulong@wecash.net");
        personalization.addCc(cc);
//        cc.setName("Example User");
//        cc.setEmail("lishulong@wecash.net");
//        personalization.addCc(cc);
        Email bcc = new Email();
        bcc.setName("Example User");
        bcc.setEmail("lishulong@wecash.net");
        personalization.addBcc(bcc);
//        bcc.setName("Example User");
//        bcc.setEmail("lishulong@wecash.net");
//        personalization.addBcc(bcc);
        personalization.setSubject("Hello World from the Personalized SendGrid Java Library");
        personalization.addHeader("X-Test", "test");
        personalization.addHeader("X-Mock", "true");
        personalization.addSubstitution("%name%", "Example User");
        personalization.addSubstitution("%city%", "Denver");
        personalization.addCustomArg("user_id", "343");
        personalization.addCustomArg("type", "marketing");
        personalization.setSendAt(1443636843);
        mail.addPersonalization(personalization);

        Personalization personalization2 = new Personalization();
        Email to2 = new Email();
        to2.setName("Example User");
        to2.setEmail("lishulong@wecash.net");
        personalization2.addTo(to2);
//        to2.setName("Example User");
//        to2.setEmail("lishulong@wecash.net");
//        personalization2.addTo(to2);
        Email cc2 = new Email();
        cc2.setName("Example User");
        cc2.setEmail("lishulong@wecash.net");
        personalization2.addCc(cc2);
//        cc2.setName("Example User");
//        cc2.setEmail("lishulong@wecash.net");
//        personalization2.addCc(cc2);
        Email bcc2 = new Email();
        bcc2.setName("Example User");
        bcc2.setEmail("lishulong@wecash.net");
        personalization2.addBcc(bcc2);
//        bcc2.setName("Example User");
//        bcc2.setEmail("lishulong@wecash.net");
//        personalization2.addBcc(bcc2);
        personalization2.setSubject("Hello World from the Personalized SendGrid Java Library");
        personalization2.addHeader("X-Test", "test");
        personalization2.addHeader("X-Mock", "true");
        personalization2.addSubstitution("%name%", "Example User");
        personalization2.addSubstitution("%city%", "Denver");
        personalization2.addCustomArg("user_id", "343");
        personalization2.addCustomArg("type", "marketing");
        personalization2.setSendAt(1443636843);
        mail.addPersonalization(personalization2);

        Content content = new Content();
        content.setType("text/plain");
        content.setValue("some text here");
        mail.addContent(content);
        content.setType("text/html");
        content.setValue("<html><body>some text here</body></html>");
        mail.addContent(content);

        Attachments attachments = new Attachments();
        attachments.setContent("TG9yZW0gaXBzdW0gZG9sb3Igc2l0IGFtZXQsIGNvbnNlY3RldHVyIGFkaXBpc2NpbmcgZWxpdC4gQ3JhcyBwdW12");
        attachments.setType("application/pdf");
        attachments.setFilename("balance_001.pdf");
        attachments.setDisposition("attachment");
        attachments.setContentId("Balance Sheet");
        mail.addAttachments(attachments);

        Attachments attachments2 = new Attachments();
        attachments2.setContent("BwdW");
        attachments2.setType("image/png");
        attachments2.setFilename("banner.png");
        attachments2.setDisposition("inline");
        attachments2.setContentId("Banner");
        mail.addAttachments(attachments2);

        mail.setTemplateId("13b8f94f-bcae-4ec6-b752-70d6cb59f932");

        mail.addSection("%section1%", "Substitution Text for Section 1");
        mail.addSection("%section2%", "Substitution Text for Section 2");

        mail.addHeader("X-Test1", "1");
        mail.addHeader("X-Test2", "2");

        mail.addCategory("May");
        mail.addCategory("2016");

        mail.addCustomArg("campaign", "welcome");
        mail.addCustomArg("weekday", "morning");

        mail.setSendAt(1443636842);

        ASM asm = new ASM();
        asm.setGroupId(99);
        asm.setGroupsToDisplay(new int[] {4,5,6,7,8});
        mail.setASM(asm);

        // This must be a valid [batch ID](https://sendgrid.com/docs/API_Reference/SMTP_API/scheduling_parameters.html) to work
        // mail.setBatchId("sendgrid_batch_id");

        mail.setIpPoolId("23");

        MailSettings mailSettings = new MailSettings();
        BccSettings bccSettings = new BccSettings();
        bccSettings.setEnable(true);
        bccSettings.setEmail("lishulong@wecash.net");
        mailSettings.setBccSettings(bccSettings);
        Setting sandBoxMode = new Setting();
        sandBoxMode.setEnable(true);
        mailSettings.setSandboxMode(sandBoxMode);
        Setting bypassListManagement = new Setting();
        bypassListManagement.setEnable(true);
        mailSettings.setBypassListManagement(bypassListManagement);
        FooterSetting footerSetting = new FooterSetting();
        footerSetting.setEnable(true);
        footerSetting.setText("Footer Text");
        footerSetting.setHtml("<html><body>Footer Text</body></html>");
        mailSettings.setFooterSetting(footerSetting);
        SpamCheckSetting spamCheckSetting = new SpamCheckSetting();
        spamCheckSetting.setEnable(true);
        spamCheckSetting.setSpamThreshold(1);
        spamCheckSetting.setPostToUrl("https://spamcatcher.sendgrid.com");
        mailSettings.setSpamCheckSetting(spamCheckSetting);
        mail.setMailSettings(mailSettings);

        TrackingSettings trackingSettings = new TrackingSettings();
        ClickTrackingSetting clickTrackingSetting = new ClickTrackingSetting();
        clickTrackingSetting.setEnable(true);
        clickTrackingSetting.setEnableText(false);
        trackingSettings.setClickTrackingSetting(clickTrackingSetting);
        OpenTrackingSetting openTrackingSetting = new OpenTrackingSetting();
        openTrackingSetting.setEnable(true);
        openTrackingSetting.setSubstitutionTag("Optional tag to replace with the open image in the body of the message");
        trackingSettings.setOpenTrackingSetting(openTrackingSetting);
        SubscriptionTrackingSetting subscriptionTrackingSetting = new SubscriptionTrackingSetting();
        subscriptionTrackingSetting.setEnable(true);
        subscriptionTrackingSetting.setText("text to insert into the text/plain portion of the message");
        subscriptionTrackingSetting.setHtml("<html><body>html to insert into the text/html portion of the message</body></html>");
        subscriptionTrackingSetting.setSubstitutionTag("Optional tag to replace with the open image in the body of the message");
        trackingSettings.setSubscriptionTrackingSetting(subscriptionTrackingSetting);
        GoogleAnalyticsSetting googleAnalyticsSetting = new GoogleAnalyticsSetting();
        googleAnalyticsSetting.setEnable(true);
        googleAnalyticsSetting.setCampaignSource("some source");
        googleAnalyticsSetting.setCampaignTerm("some term");
        googleAnalyticsSetting.setCampaignContent("some content");
        googleAnalyticsSetting.setCampaignName("some name");
        googleAnalyticsSetting.setCampaignMedium("some medium");
        trackingSettings.setGoogleAnalyticsSetting(googleAnalyticsSetting);
        mail.setTrackingSettings(trackingSettings);

        Email replyTo = new Email();
        replyTo.setName("Example User");
        replyTo.setEmail("lishulong@wecash.net");
        mail.setReplyTo(replyTo);

        System.out.println(mail.build());
    }
}
