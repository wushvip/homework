package com.chinamobile.cmss.oms.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableDiscoveryClient
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run( ServiceGatewayApplication.class, args );
    }

    @Bean
    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }

    //predicate
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        String httpUri = "http://httpbin.org:80";
//        return builder.routes()
//            .route(p -> p
//                .path("/get")
//                .filters(f -> f.addRequestHeader("Hello", "World"))
//                .uri(httpUri))
//            .route(p -> p
//                .host("*.hystrix.com")
//                .filters(f -> f
//                    .hystrix(config -> config
//                        .setName("mycmd")
//                        .setFallbackUri("forward:/fallback")))
//                .uri(httpUri))
//            .build();
//    }
    // end::route-locator[]

    // tag::fallback[]
//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("fallback");
//    }



//filter
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
////将过滤器注册到router中
//        return builder.routes()
//                .route(r -> r.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter())
//                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
//                        .uri("http://httpbin.org:80/get")
//                        .order(0)
//                        .id("customer_filter_router")
//                )
//                .build();
//
//    }
//
//    //将TokenFilter在工程的启动类中注入到Spring Ioc容器中
//    @Bean
//    public TokenFilter tokenFilter(){
//        return new TokenFilter();
//    }
//    //将bean注册到ioc容器
//    @Bean
//    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
//        return new RequestTimeGatewayFilterFactory();
//    }



//limiter
//    @Bean
//    public HostAddrKeyResolver hostAddrKeyResolver() {
//    return new HostAddrKeyResolver();
//}
//    @Bean
//    public UriKeyResolver uriKeyResolver() {
//        return new UriKeyResolver();
//    }
//    @Bean
//    KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }
}
