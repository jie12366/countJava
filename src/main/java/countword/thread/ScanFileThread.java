package countword.thread;

import countword.filter.FilterProcessManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author www.xyjz123.xyz
 * @description 线程类，将文本从文件中提取出来并进行处理
 * @date 2019/4/29 19:18
 */
public class ScanFileThread implements Runnable{

    private String path;

    private FilterProcessManager processManager;

    public ScanFileThread(String path,FilterProcessManager processManager){
        this.path = path;
        this.processManager = processManager;
    }

    @Override
    public void run() {
        Stream<String> stringStream = null;

        try {
            //Java8用流的方式读文件,返回字符串流
            stringStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }

        //将字符串流转为字符集合
        List<String> collect = stringStream.collect(Collectors.toList());

        //遍历集合，进行文本处理
        for (String msg : collect){
            processManager.process(msg);
        }
    }
}