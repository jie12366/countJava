package countword.filter;

import countword.service.TotalWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 17:43
 */
@Component
public class FilterProcessManager{

    @Autowired
    private TotalWords totalWords;

    @Resource(name = "httpFilterProcess")
    private FilterProcess httpFilterProcess;

    @Resource(name = "warpFilterProcess")
    private FilterProcess warpFilterProcess;

    private List<FilterProcess> filterProcessList = new ArrayList<>();

    /**
     * 把处理方法加到list数组中
     */
    @PostConstruct
    public void start(){
        this.addProcess(httpFilterProcess)
                .addProcess(warpFilterProcess);
    }

    private FilterProcessManager addProcess(FilterProcess process){
        filterProcessList.add(process);
        return this;
    }

    /**
     * 处理文本，过滤特殊字符，计算总字数
     * @param msg
     */
    public void process(String msg){
        for (FilterProcess filterProcess:filterProcessList){
            msg = filterProcess.process(msg);
        }
        totalWords.sum(msg.toCharArray().length);
    }
}