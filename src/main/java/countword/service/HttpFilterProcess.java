package countword.service;

import countword.filter.FilterProcess;

/**
 * @author www.xyjz123.xyz
 * @description 处理一些超链接文本，用空值替代
 * @date 2019/4/29 17:44
 */
public class HttpFilterProcess implements FilterProcess {

    @Override
    public String process(String msg) {

        //利用正则表达式，把所有超链接文本用空文本替代
        msg = msg.replaceAll("^((https | http | ftp | rtsp | mms)?:\\/\\/)[^\\s] +","");

        return msg;
    }
}