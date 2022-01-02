package zhiming.practice.leetcodemain;


import java.io.*;

import static zhiming.practice.leetcodemain.writeaddjson.quickResult;

public class writeaddjson {
    public static void main(String[] args) {
        quickResultStr = quickResult(495);
        jsonWrite(quickResultStr, 888);
        quickResultStr = quickResult(490);
        jsonWrite(quickResultStr, 888);
    }
    private static String quickResultReadPath = "D:\\Projects\\quickResultWriteFile\\";
    private static String quickResultStr;
    public static String quickResult(Integer topicReplayTaskId) {
        char cbuf[] = new char[10000000];        //10MB
        int length = 0;
        try {
            InputStreamReader input = new InputStreamReader(new FileInputStream(new File(quickResultReadPath +
                    "quickResultWrite" + topicReplayTaskId + ".json")),"GBK");
            length = input.read(cbuf);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String quickResult = new String(cbuf,0, length);
        return quickResult;
    }
    public static void jsonWrite(String quickResultStr, Integer topicReplayTaskId){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream( quickResultReadPath +
                    "quickResultWriteAdd" + topicReplayTaskId + ".json", true),"UTF-8");
            osw.write(quickResultStr);
            osw.flush();    //清空缓冲区，强制输出数据
            osw.close();    //关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
