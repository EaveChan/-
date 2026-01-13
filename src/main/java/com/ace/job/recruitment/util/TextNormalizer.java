package com.ace.job.recruitment.util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文本标准化工具类
 * 用于处理和标准化健康描述文本
 */
public class TextNormalizer {
    
    // 近义词字典
    private static final Map<String, List<String>> SYNONYM_MAP = new HashMap<>();
    
    static {
        // 初始化近义词字典
        SYNONYM_MAP.put("高血压", Arrays.asList("高血压", "血压高", "高血压病", "原发性高血压"));
        SYNONYM_MAP.put("糖尿病", Arrays.asList("糖尿病", "血糖高", "高血糖", "2型糖尿病", "1型糖尿病"));
        SYNONYM_MAP.put("孕妇", Arrays.asList("孕妇", "怀孕", "妊娠", "孕期", "妊娠期"));
        SYNONYM_MAP.put("哮喘", Arrays.asList("哮喘", "支气管哮喘", "气喘"));
        SYNONYM_MAP.put("过敏", Arrays.asList("过敏", "过敏史", "过敏反应", "变态反应"));
        SYNONYM_MAP.put("心脏病", Arrays.asList("心脏病", "冠心病", "心肌梗死", "心绞痛", "心脏疾病"));
        SYNONYM_MAP.put("肝病", Arrays.asList("肝病", "肝炎", "肝硬化", "脂肪肝", "肝功能异常"));
        SYNONYM_MAP.put("肾病", Arrays.asList("肾病", "肾炎", "肾功能不全", "肾衰竭"));
        SYNONYM_MAP.put("健康", Arrays.asList("健康", "身体健康", "体健", "健康状况良好"));
    }
    
    /**
     * 标准化文本
     * 去除空格、标点符号，转换为小写
     */
    public static String normalize(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        
        // 转换为小写
        String normalized = text.toLowerCase();
        
        // 去除多余空格
        normalized = normalized.replaceAll("\\s+", " ").trim();
        
        return normalized;
    }
    
    /**
     * 提取关键词列表
     * 支持多种分隔符
     */
    public static List<String> extractKeywords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        // 使用多种分隔符分割
        String[] keywords = text.split("[,，、;；\\s]+");
        
        return Arrays.stream(keywords)
                .map(String::trim)
                .filter(k -> !k.isEmpty())
                .collect(Collectors.toList());
    }
    
    /**
     * 检查文本是否包含关键词（支持近义词匹配）
     */
    public static boolean containsKeyword(String text, String keyword) {
        if (text == null || keyword == null) {
            return false;
        }
        
        String normalizedText = normalize(text);
        String normalizedKeyword = normalize(keyword);
        
        // 精确匹配
        if (normalizedText.contains(normalizedKeyword)) {
            return true;
        }
        
        // 近义词匹配
        List<String> synonyms = SYNONYM_MAP.get(normalizedKeyword);
        if (synonyms != null) {
            for (String synonym : synonyms) {
                if (normalizedText.contains(normalize(synonym))) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 检查文本是否包含任意一个关键词
     */
    public static boolean containsAnyKeyword(String text, List<String> keywords) {
        if (text == null || keywords == null || keywords.isEmpty()) {
            return false;
        }
        
        for (String keyword : keywords) {
            if (containsKeyword(text, keyword)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 检查文本是否包含所有关键词
     */
    public static boolean containsAllKeywords(String text, List<String> keywords) {
        if (text == null || keywords == null || keywords.isEmpty()) {
            return false;
        }
        
        for (String keyword : keywords) {
            if (!containsKeyword(text, keyword)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 计算关键词匹配数量
     */
    public static int countMatchedKeywords(String text, List<String> keywords) {
        if (text == null || keywords == null || keywords.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        for (String keyword : keywords) {
            if (containsKeyword(text, keyword)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * 获取匹配的关键词列表
     */
    public static List<String> getMatchedKeywords(String text, List<String> keywords) {
        if (text == null || keywords == null || keywords.isEmpty()) {
            return new ArrayList<>();
        }
        
        return keywords.stream()
                .filter(keyword -> containsKeyword(text, keyword))
                .collect(Collectors.toList());
    }
}
