package com.tron.jsonrpc.utils.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Classname GsonUtils
 * @Description
 * @Date 2020/12/1 9:40
 * @Created by project-172
 */
public class GsonUtils {

    private static final Gson GSON;

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    static {
        GSON = new GsonBuilder()
                .setDateFormat(DATE_TIME_PATTERN)
                .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                    @Override
                    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
                    }
                })
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        String datetime = json.getAsJsonPrimitive().getAsString();
                        return LocalDate.parse(datetime, DateTimeFormatter.ofPattern(DATE_PATTERN));
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        String datetime = json.getAsJsonPrimitive().getAsString();
                        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
                    }
                })
                .disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .create();
    }

    private GsonUtils() {
    }

    public static Gson getInstance() {
        return GSON;
    }

    /**
     * 将对象转换为json字符串
     *
     * @param src
     * @return
     */
    public static String toJsonString(Object src) {
        return GSON.toJson(src);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJsonString(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    /**
     * 将json字符串转换为对象
     *
     * @param json
     * @param typeOfT
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T fromJsonString(String json, Type typeOfT) throws JsonSyntaxException {
        return GSON.fromJson(json, typeOfT);
    }
}
