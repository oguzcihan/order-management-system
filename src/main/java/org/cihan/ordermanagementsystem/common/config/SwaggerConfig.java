package org.cihan.ordermanagementsystem.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${ocihan.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("oguzcihan12@gmail.com");
        contact.setName("Oğuzhan Cihan");
        contact.setUrl("https://oguzcihan.github.io/#home");


        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        String description = "Order Management System API. Test report can be found at: " +
                "<a href='" + "http://localhost:63342/order-management-system/target/reports/surefire.html?_ijt=thlaaghe14283nl8v2h5ffu27h&_ij_reload=RELOAD_ON_SAVE" + "' target='_blank'>ORDER MANAGEMENT API TEST REPORT</a>";
        Info info = new Info()
                .title("Order Management System API")
                .version("1.0")
                .contact(contact)
                .description(description).termsOfService("https://oguzcihan.github.io/#home")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}