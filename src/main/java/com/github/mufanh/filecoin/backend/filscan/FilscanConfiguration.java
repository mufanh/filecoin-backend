package com.github.mufanh.filecoin.backend.filscan;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.github.mufanh.jsonrpc4j.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @author xinquan.huangxq
 */
@Configuration
@Slf4j
public class FilscanConfiguration {

    @Bean
    public FilscanService filscanService(FilscanProperties filscanProperties) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(filscanProperties.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(filscanProperties.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(filscanProperties.getWriteTimeout(), TimeUnit.SECONDS)
                .build();

        JsonRpcRetrofit jsonRpcRetrofit = new JsonRpcRetrofit.Builder()
                .httpUrl(filscanProperties.getApiGateway())
                .jsonBodyConverter(new FilscanJsonBodyConverter())
                .callFactory(client)
                .build();
        return jsonRpcRetrofit.create(FilscanService.class);
    }

    private static class FilscanJsonBodyConverter implements JsonBodyConverter {

        private static final ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        @Override
        public String convertRequest(JsonRpcRequest request) throws JsonConvertException {
            try {
                String requestJson = mapper.writeValueAsString(request);

                log.debug("JSON-RPC request : \n{}", requestJson);

                return requestJson;
            } catch (JsonProcessingException e) {
                throw new JsonConvertException("JSON-RPC request convert error.", e);
            }
        }

        @Override
        public <T> JsonRpcResponse<T> convertResponse(Type type, String response) throws JsonConvertException {
            log.debug("JSON-RPC response : \n{}", response);

            try {
                JsonRpcResponse<T> jsonRpcResponse = new JsonRpcResponse<>();

                JsonNode jsonNode = mapper.readTree(response);
                if (jsonNode == null) {
                    return jsonRpcResponse;
                }

                JsonNode id = jsonNode.get("id");
                if (id != null) {
                    jsonRpcResponse.setId(id.asLong());
                }

                JsonNode jsonrpc = jsonNode.get("jsonrpc");
                if (jsonrpc != null) {
                    jsonRpcResponse.setJsonrpc(jsonrpc.asText());
                }

                jsonRpcResponse.setError(parseJsonNode(jsonNode.get("error"), JsonRpcResponse.Error.class));

                JsonNode resultNode = jsonNode.get("result");
                if (resultNode != null) {
                    jsonRpcResponse.setResult(parseJsonNode(resultNode.get("data"), type));
                }
                return jsonRpcResponse;
            } catch (Exception e) {
                throw new JsonConvertException("JSON-RPC response convert error.", e);
            }
        }

        private static <T> T parseJsonNode(JsonNode jsonNode, Type type) throws IOException {
            if (jsonNode == null) {
                return null;
            }
            JsonParser parser = mapper.treeAsTokens(jsonNode);
            JavaType javaType = mapper.getTypeFactory().constructType(type);
            return mapper.readValue(parser, javaType);
        }
    }
}
