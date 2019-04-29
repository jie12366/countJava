package countword.service;

import countword.filter.FilterProcess;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 17:50
 */
public class WarpFilterProcess implements FilterProcess {

    @Override
    public String process(String msg) {

        //把所有的空白字符用空值替代
        //（正则表达式\s+表示匹配任意个空白字符，而\在java中是转义字符，所以需要写成\\s+）
        msg = msg.replaceAll("\\s+","");
        return msg;
    }
}