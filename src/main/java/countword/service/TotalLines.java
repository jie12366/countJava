package countword.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 20:31
 */
@Component
public class TotalLines {

    /**
     * 使用AtomicLong用于多线程计数,保证线程安全（计数操作的原子性）。
     */

    public static AtomicLong lineSpace = new AtomicLong(0);

    public static AtomicLong lineComment= new AtomicLong(0);

    public static AtomicLong lineCode = new AtomicLong(0);

    public static AtomicLong lineTotal = new AtomicLong(0);

    public Long totalSpace(){
        return lineSpace.get();
    }

    public Long totalComment(){
        return lineComment.get();
    }

    public Long totalCode(){
        return lineCode.get();
    }

    public Long total(){
        return lineTotal.get();
    }
}