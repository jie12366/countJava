package countword.thread;

import countword.service.TotalLines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 20:28
 */
public class ScanJavaThread implements Runnable{

    private String path;

    public ScanJavaThread(String path){
        this.path = path;
    }

    @Override
    public void run() {
        try {
            File file = new File(path);
            boolean comment = false;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null){
                //AtomicLong自增api，总行数+1
                TotalLines.lineTotal.incrementAndGet();
                line = line.trim();
                //这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾(这就是空行)
                if (line.matches("^[//s&&[//n]]*$")){
                    TotalLines.lineSpace.incrementAndGet();
                }
                //匹配"/*  */"注释的多行注释
                else if (line.startsWith("/*") && !line.endsWith("*/")){
                    TotalLines.lineComment.incrementAndGet();
                    comment = true;
                }else if (comment){
                    TotalLines.lineComment.incrementAndGet();
                    if (line.endsWith("*/")){
                        comment = false;
                    }
                }
                //匹配以//开头的单行注释，及以/*......*/括住的单行注释
                else if(line.startsWith("//") || (line.startsWith("/*") && line.endsWith("*/"))){
                    TotalLines.lineComment.incrementAndGet();
                }
                //其他的都是代码行
                else {
                    TotalLines.lineCode.incrementAndGet();
                }
            }
            if (br != null){
                br.close();
                br = null;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}