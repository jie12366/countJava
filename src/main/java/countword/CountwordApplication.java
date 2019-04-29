package countword;

import countword.filter.FilterProcessManager;
import countword.scan.ScanFile;
import countword.service.TotalLines;
import countword.service.TotalWords;
import countword.thread.ScanJavaThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 熊义杰
 */

@SpringBootApplication
public class CountwordApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CountwordApplication.class, args);
    }

    private static final Logger log = LoggerFactory.getLogger(CountwordApplication.class);

    @Autowired
    ScanFile scanFile;

    @Autowired
    FilterProcessManager filterProcessManager;

    @Autowired
    TotalWords totalWords;

    @Autowired
    TotalLines totalLines;

    @Autowired
    ExecutorService executorService;

    @Override
    public void run(String... args) throws Exception {

        long start = System.currentTimeMillis();

        List<String > files = scanFile.getFilePaths(args[0]);
        log.info("java类的总数 = [{}]", files.size());

        //使用线程池创建多线程来扫描处理文件
        for (String file : files){
             executorService.execute(new ScanJavaThread(file));
        }

        // 执行后停止接受新任务，会把队列的任务执行完毕。
        executorService.shutdown();

        //每隔5毫秒检查一次，观察线程任务是否终止
        final int timeout = 5;
        while (!executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS)){
            log.info("多线程工作还在运行");
        }

        //如果线程已经终止，再进行计算总数
        //long total = totalWords.total();

        long allLines = totalLines.total();
        long spaceLines = totalLines.totalSpace();
        long commentLines = totalLines.totalComment();
        long codeLines = totalLines.totalCode();

        //多线程总耗时
        long time = System.currentTimeMillis() - start;

        log.info("运行时间 = [{}]ms",time);
        log.info("总行数 = [{}]",allLines);
        log.info("空行数 = [{}]",spaceLines);
        log.info("注释行数 = [{}]",commentLines);
        log.info("代码行数 = [{}]",codeLines);
    }
}
