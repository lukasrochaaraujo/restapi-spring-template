package com.template.api.company.clients.speedio;

import com.template.api.shared.constants.ApiMediaTypes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "speedio", url = "${clients.speedio.url}", configuration = SpeedioClientDecoder.class)
public interface SpeedioClient {

    @GetMapping(path = "/buscarcnpj", consumes = ApiMediaTypes.ApplicationJson)
    GetRegistrationDetailsResponse getRegistrationDetails(@SpringQueryMap GetRegistrationDetailsQueryParams params);
}
