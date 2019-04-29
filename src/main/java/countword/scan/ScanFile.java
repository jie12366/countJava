package countword.scan;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/29 19:08
 */
@Component
public class ScanFile {

    /**
     * 所有文件路径的集合
     */
    private List<String> filePaths = new ArrayList<>();

    /**
     * 指定文件类型，这里为java文件
     */
    private String type = ".java";

    public List<String> getFilePaths(String path){

        File file = new File(path);
        //获取传入的目录下的所有文件或目录的集合
        File[] files = file.listFiles();

        //遍历这个集合
        for (File file1 : files){
            if (file1.isDirectory()){
                String directoryPath = file1.getPath();
                //如果这是个目录，就递归调用getFilePaths
                getFilePaths(directoryPath);
            }else {
                String filePath = file1.getPath();
                //如果不是type指定类型的文件，则跳过
                if (!filePath.endsWith(type)){
                    continue;
                }
                filePaths.add(filePath);
            }
        }
        return filePaths;
    }
}