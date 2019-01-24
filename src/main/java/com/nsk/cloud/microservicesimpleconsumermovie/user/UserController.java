package com.nsk.cloud.microservicesimpleconsumermovie.user;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author nsk
 * 2018/6/26 22:04
 */
@Import(FeignClientsConfiguration.class)
@RestController
public class UserController {

    private UserFeignClient userUserFeignClient;

    private UserFeignClient adminUserFeignClient;

    @Autowired
    public UserController(Decoder decoder, Encoder encoder, Client client,
                          Contract contract){
        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1"))
                .target(UserFeignClient.class,"http://microservice-provider-user/");
        this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2"))
                .target(UserFeignClient.class,"http://microservice-provider-user/");
    }

    @GetMapping("/user/{id}")
    public User findByUserId(@PathVariable Long id){
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/admin/{id}")
    public User findByAdminId(@PathVariable Long id){
        return  adminUserFeignClient.findById(id);
    }

}
