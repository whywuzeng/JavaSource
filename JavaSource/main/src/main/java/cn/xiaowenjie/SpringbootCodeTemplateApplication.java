package cn.xiaowenjie;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.servlet.MultipartConfigElement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages="cn.xiaowenjie")
@ServletComponentScan
@EnableSwagger2
@MapperScan(basePackages = "cn.xiaowenjie.daos")
public class SpringbootCodeTemplateApplication {

		private	Logger logger = LoggerFactory.getLogger(SpringbootCodeTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCodeTemplateApplication.class, args);
	}

	/**
	 *  国际化配置
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/messages"); // , "i18n/errormsg"
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Value("${file.uploadFolder}")
	private String uploadFolder;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(uploadFolder);
		logger.info("uploadFolder"+uploadFolder);
		return factory.createMultipartConfig();
	}

}
