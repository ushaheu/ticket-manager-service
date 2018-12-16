package com.odilium.ticket.management;

import com.google.common.base.Predicate;
import com.odilium.ticket.management.kafka.interfaces.MongoSubscriberChannel;
import com.odilium.ticket.management.kafka.interfaces.RedisSubscriberChannel;
import com.odilium.ticket.management.repositories.TicketInformationRepository;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableBinding({RedisSubscriberChannel.class, MongoSubscriberChannel.class})
@IntegrationComponentScan
@EnableRedisRepositories(basePackageClasses = TicketInformationRepository.class)
@EnableSwagger2
public class TicketManagerServiceApplicationConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();

    }
    @Bean
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Odilum Ticket Manager Service")
                .description("Ticket Manager Service")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
    //Here is an example where we select any api that matches one of these paths
    private static Predicate<String> paths() {
        return or(
                regex("/v1.*")
        );
    }
}
