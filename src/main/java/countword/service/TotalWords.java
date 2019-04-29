package countword.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author www.xyjz123.xyz
 * @description 计算总字数
 * @date 2019/4/29 18:38
 */
@Component
public class TotalWords {

    /**
     * 使用AtomicLong用于多线程计数,保证线程安全（计数操作的原子性）。
     */
    private AtomicLong total = new AtomicLong();

    /**
     * 计数
     * @param count
     */
    public void sum(int count){
        total.addAndGet(count);
    }

    /**
     * 返回总数
     * @return
     */
    public Long total(){
        return total.get();
    }
}