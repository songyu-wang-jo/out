package com.songyuwong.infrastructure.api.starter.http;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/22
 */
public class ApiServerExample {


    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        ApiServerExample apiServerExample = new ApiServerExample();
        // 厂商编码
        String clientId = "jhids-base";
        // 密码
        String clientSecret = "123456";
        // 接口服务程序 ip
        String serverIp = "localhost";
        // 接口服务端口
        String serverPort = "9004";

        // 获取 token
        String token = apiServerExample.requestForToken(clientId,
                clientSecret,
                serverIp,
                serverPort);

        System.err.println("获取到 token >> " + token);

        // 服务编码
        String serverCode = "JHIDS-BAS-PAT-001";
        // 构造查询条件
        List<Map<String, Object>> conditions = new LinkedList<>();
        Map<String, Object> condition1 = buildRequestCondition("HIS_PAT_ID", "lt", "1000");
        conditions.add(condition1);
        Map<String, Object> condition2 = buildRequestCondition("HIS_PAT_ID", "gt", 10);
        conditions.add(condition2);
        // 构造排序条件 (可选)
        List<Map<String, Object>> orderConditions = new LinkedList<>();
        Map<String, Object> orderCondition = buildRequestOrderCondition("HIS_KEY", "ASC");
        orderConditions.add(orderCondition);

        // 获取数据
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> response = apiServerExample.requestForData(serverIp,
                serverPort,
                token,
                serverCode,
                clientId,
                conditions,
                orderConditions);
        System.err.println("获取到数据 >> " + response.getBody());
    }

    /**
     * 通过 token 获取数据
     *
     * @param serverIp        服务 ip
     * @param serverPort      服务 端口
     * @param token           令牌
     * @param serverCode      接口编码
     * @param sysCode         厂商编码
     * @param conditions      筛选查询条件
     * @param orderConditions 排序查询条件（可选）
     * @return 请求响应
     */
    @SuppressWarnings("rawtypes")
    public ResponseEntity<List> requestForData(String serverIp,
                                               String serverPort,
                                               String token,
                                               String serverCode,
                                               String sysCode,
                                               List<Map<String, Object>> conditions,
                                               List<Map<String, Object>> orderConditions) {
        // 组装调用参数
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("pageSize", 100000);
        body.put("pageNo", 1);
        body.put("maxResultSize", 1000);
        body.put("serverCode", serverCode);
        body.put("sysCode", sysCode);
        body.put("condition", conditions);
        if (orderConditions.size() > 0) {
            body.put("orders", orderConditions);
        }
        // 构建请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        // 调用接口
        URI url;
        try {
            url = new URI(String.format("http://%s:%s/api/jhids4s/common/server/dataQuery", serverIp, serverPort));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        RequestEntity<Map<String, Object>> requestEntity = new RequestEntity<>(body, httpHeaders, HttpMethod.POST, url);
        ResponseEntity<List> response;
        try {
            response = restTemplate.exchange(requestEntity, List.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.BadRequest) {
                // todo 获取数据失败请求处理
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
            throw new RuntimeException("调用获取数据接口失败");
        }
        return response;
    }

    public static Map<String, Object> buildRequestCondition(String column, String type, Object value) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("column", column);
        condition.put("type", type);
        condition.put("value", value);
        return condition;
    }

    public static Map<String, Object> buildRequestOrderCondition(String orderColumn, String orderType) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("orderColumn", orderColumn);
        condition.put("orderType", orderType);
        return condition;
    }


    /**
     * 使用厂商认证信息获取调用通用数据请求接口所需的 token 令牌
     *
     * @param clientId     厂商唯一号 厂商编码
     * @param clientSecret 厂商账号对应密码
     * @param serverIp     接口服务程序部署 ip
     * @param serverPort   服务端口
     * @return token 授权令牌
     */
    public String requestForToken(String clientId,
                                  String clientSecret,
                                  String serverIp,
                                  String serverPort) {
        // 组装调用参数
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("grant_type", "client_credentials");
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        // 通过 rest template 调用获取 token 接口
        ResponseEntity<String> oauth2TokenDtoResponseEntity;
        try {
            oauth2TokenDtoResponseEntity =
                    restTemplate.postForEntity(
                            String.format("http://%s:%s/auth/oauth/token", serverIp, serverPort),
                            parameters,
                            String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("调用获取 token 接口失败");
        }
        // 获取响应数据
        String result = oauth2TokenDtoResponseEntity.getBody();
        // 解析响应数据
        JSONObject jsonObject;
        jsonObject = JSONObject.parseObject(result);
        if (Objects.isNull(jsonObject)) {
            throw new RuntimeException("获取 token 解析对应的 json 串失败");
        }
        JSONObject data = (JSONObject) jsonObject.get("data");
        // 获取响应数据中的 token 并返回
        return String.valueOf(data.get("token"));
    }
}
