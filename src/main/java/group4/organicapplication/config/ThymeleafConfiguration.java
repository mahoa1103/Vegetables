package group4.organicapplication.config;//package group4.organicapplication.config;
//
//import org.apache.commons.lang3.CharEncoding;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//@Configuration
//public class ThymeleafConfiguration {
//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5 emails")
//    public ClassLoaderTemplateResolver emailTemplateResolver() {
//        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
//        emailTemplateResolver.setPrefix("admin/");
//        emailTemplateResolver.setSuffix(".html");
//        emailTemplateResolver.setTemplateMode("HTML5");
//        emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
//        emailTemplateResolver.setOrder(1);
//        return emailTemplateResolver;
//    }
//}
