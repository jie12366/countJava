package countword.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import countword.filter.FilterProcess;
import countword.service.HttpFilterProcess;
import countword.service.WarpFilterProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 17:11
 */
@Configuration
public class ProcessConfig {

    /**
     * 线程池基本大小
     */
    private int corePoolSize = 4;
    /**
     * 线程池最大线程大小
     */
    private int maxPoolSize = 4;

    @Bean
    public ExecutorService sendMessageExecutor(){
        //使用setNameFormat给线程命名
        ThreadFactory threadFactory = new ThreadFactoryBuilder().
                setNameFormat("scan-number-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024),threadFactory,new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

    @Bean("httpFilterProcess")
    public FilterProcess httpFilterProcess(){
        return new HttpFilterProcess();
    }

    @Bean("warpFilterProcess")
    public FilterProcess warpFilterProcess(){
        return new WarpFilterProcess();
    }
}