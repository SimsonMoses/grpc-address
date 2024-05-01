//package com.learn.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.util.List;
//
//public class OpenApiConfiguration {
//
//    @Value("springCrud.mindgraph.url")
//    private String devUrl;
//    public OpenAPI openAPI(){
//        Server server = new Server();
//        server.setUrl(devUrl);
//        server.description("SpringCrud development server");
//        Info info  = new Info()
//                .title("SpringCrud MindGraph")
//                .version("0.0.1");
//        return new OpenAPI().info(info).servers(List.of(server));
//    }
//
//}
