package com.base.自然语言处理类;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 自然语言处理类 - 处理语言：en英语
public class NLPUtil_V2 {

    // 最长的内容跑了12分钟
    public static void main(String[] args) throws Exception {
        String text = "D:\\工作\\数据之家\\项目档案\\[上海外国语]多语种舆情数据管理平台\\需求\\new 1.txt";
        File file = new File(text);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bw = new BufferedReader(new FileReader(file));
            String line;
            //因为不知道有几行数据，所以先存入list集合中
            while ((line = bw.readLine()) != null) {
                sb.append(line.trim());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = sb.toString();
//        getNER(text);
        String startTime = getSysTime();
        getSentiment3(text);
        String endTime = getSysTime();
        System.out.println("startTime:" + startTime + " endTime:" + endTime);
    }

    // 实体抽取
    public static HashMap<String, Object> getNER(String text) throws Exception {

        // 7种实体类型：地名 人名 组织结构 资金 百分比 日期 时间
        String[] labels = new String[]{"Location", "Person", "Organization", "Money", "Percent", "Date", "Time"};
        HashMap<String, Object> map = new HashMap<>();
        Resource resource = new ClassPathResource("classifiers/ner/english.muc.7class.distsim.crf.ser.gz");
        String localPath = resource.getFile().getAbsolutePath();
        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(localPath);
        String xml = classifier.classifyWithInlineXML(text.trim());
        for (String label : labels) {
            ArrayList<String> values = labelFinds(xml, label.toUpperCase());
            HashMap<String, Long> labelMap = new HashMap<>();
            values.forEach(value -> {
                Long index = labelMap.get(value);
                if (index == null) {
                    labelMap.put(value, 1L);
                } else {
                    labelMap.put(value, index + 1);
                }
            });
            map.put(label, labelMap);
        }
        return map;
    }

    // 情感分析
    public static HashMap<String, Object> getSentiment1(String text) throws Exception {

        // 准备容器
        HashMap<String, Object> map = new HashMap<>();
        Integer sizePositive = 0;
        Integer sizeNeutral = 0;
        Integer sizeNegative = 0;
        HashMap<String, Object> mapPositive = new HashMap<>();
        HashMap<String, Object> mapNeutral = new HashMap<>();
        HashMap<String, Object> mapNegative = new HashMap<>();
        ArrayList<String> listPositive = new ArrayList<>();
        ArrayList<String> listNeutral = new ArrayList<>();
        ArrayList<String> listNegative = new ArrayList<>();

        // 装载配置
        Resource resource = new ClassPathResource("classifiers/sentiment/sentiment.ser.gz");
        String localPath = resource.getFile().getAbsolutePath();
        Properties tokenizerProps = new Properties();
        tokenizerProps.setProperty("annotators", "tokenize, ssplit");
        Properties pipelineProps = new Properties();
        pipelineProps.setProperty("annotators", "parse, sentiment");
        pipelineProps.setProperty("sentiment.model", localPath);
        pipelineProps.setProperty("parse.binaryTrees", "true");
        pipelineProps.setProperty("parse.buildgraphs", "false");
        pipelineProps.setProperty("enforceRequirements", "false");

        // 实例化语言处理器
        StanfordCoreNLP tokenizer = new StanfordCoreNLP(tokenizerProps);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(pipelineProps);

        // 注入待处理文本
        Annotation annotation = new Annotation(text.trim());
        tokenizer.annotate(annotation);
        pipeline.annotate(annotation);

        // 解析文本
        Iterator var18 = ((List) annotation.get(CoreAnnotations.SentencesAnnotation.class)).iterator();
        while (var18.hasNext()) {
            CoreMap sentence = (CoreMap) var18.next();
            String type = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            System.out.println(type + " " + sentence);
            switch (type) {
                case "Positive": // 积极
                    sizePositive++;
                    listPositive.add(sentence + "");
                    break;
                case "Neutral": // 中性
                    sizeNeutral++;
                    listNeutral.add(sentence + "");
                    break;
                case "Negative": // 消极
                    sizeNegative++;
                    listNegative.add(sentence + "");
                    break;
            }
        }

        mapPositive.put("size", sizePositive);
        mapPositive.put("data", listPositive);
        mapPositive.put("sentiment", "Positive");
        map.put("Positive", mapPositive);
        mapNeutral.put("size", sizeNeutral);
        mapNeutral.put("data", listNeutral);
        mapNeutral.put("sentiment", "Neutral");
        map.put("Neutral", mapNeutral);
        mapNegative.put("size", sizeNegative);
        mapNegative.put("data", listNegative);
        mapNegative.put("sentiment", "Negative");
        map.put("Negative", mapNegative);
        Map<String, Object> max = sizePositive >= sizeNeutral ? (sizePositive >= sizeNegative ? mapPositive : mapNegative) : (sizeNeutral >= sizeNegative ? mapNeutral : mapNegative);
        String sentiment = max.get("sentiment").toString();
        map.put("sentiment", sentiment);
        return map;
    }

    public static void getSentiment3(String text) throws Exception {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        for (CoreSentence sentence : document.sentences()) {
            String sentiment = sentence.sentiment();
            String s =  sentence.toString();
            System.out.println(sentiment + " " + s);
        }
    }

    public static ArrayList<String> labelFinds(String text, String label) {
        Pattern p = Pattern.compile("\\<" + label + "\\>(?<value>.*?)\\<\\/" + label + "\\>");
        Matcher m = p.matcher(text);
        ArrayList<String> values = new ArrayList<>();
        while (m.find()) {
            values.add(m.group("value"));
        }
        return values;
    }

    public static String getSysTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
