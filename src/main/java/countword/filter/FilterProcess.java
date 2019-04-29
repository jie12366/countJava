package countword.filter;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 17:42
 */
public interface FilterProcess {

    /**
     * 处理文本，过滤特殊字符
     * @param msg
     * @return
     */
    String process(String msg);
}