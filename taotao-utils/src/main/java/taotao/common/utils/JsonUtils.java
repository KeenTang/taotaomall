package taotao.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-07
 * Time: 15:57
 */
public class JsonUtils {

    private final static ObjectMapper MAPPER=new ObjectMapper();

    {
        //MAPPER.setConfig(new DeserializationConfig(), true);
        MAPPER.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        //从JSON到java object
        //没有匹配的属性名称时不作失败处理
        MAPPER.configure(MapperFeature.AUTO_DETECT_FIELDS, true);

        //反序列化
        //禁止遇到空原始类型时抛出异常，用默认值代替。
        MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        //禁止遇到未知（新）属性时报错，支持兼容扩展
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        MAPPER.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        MAPPER.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        //序列化
        //禁止序列化空值
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        MAPPER.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        //不包含空值属性
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        MAPPER.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
        //是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列
        MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);

        //开启序列化和反序列化时为对象附加@class属性
        MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);
    }

    public static<T> String objToString(T t){
        try {
            return MAPPER.writer().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T stringToObj(String json,Class<T> cls){
        try {
            return MAPPER.readValue(json,cls);
        } catch (IOException e) {
            return null;
        }
    }
}
