package zhiming.practice.leetcodemain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class readandwriteCSV {
    public static void main(String[] args) throws Exception {
        JsonParser();
        JsonWrite();
        /*String filePath = "D:\\hzmFiles\\readandwriteCSV.csv";
        readCSV(filePath);
        String writeFilePath = "D:\\hzmFiles\\write.csv";
        writeCSV(writeFilePath);*/
    }
    //Json文件解析
    public static void JsonParser() throws Exception{

        char cbuf[] = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(new File("D:\\hzmFiles\\example.json")),"GBK");
        int len =input.read(cbuf);
        String text = new String(cbuf,0,len);
        //1.构造一个json对象
        JSONObject obj = new JSONObject(text.substring(text.indexOf("{")));   //过滤读出的utf-8前三个标签字节,从{开始读取

        //2.通过getXXX(String key)方法获取对应的值
        System.out.println("FLAG:"+obj.getString("FLAG"));
        System.out.println("NAME:"+obj.getString("NAME"));

        //获取数组
        JSONArray arr = obj.getJSONArray("ARRAYS");
        System.out.println("数组长度:"+arr.length());
        for(int i=0;i<arr.length();i++)
        {
            JSONObject subObj = arr.getJSONObject(i);
            System.out.println("数组Name:"+subObj.getString("Name")+" String:"+subObj.getString("String"));
        }

    }
    //写JSON文件
    public static void JsonWrite() throws Exception{

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\hzmFiles\\exampleWrite.json"),"UTF-8");

        JSONObject obj = new JSONObject();//创建JSONObject对象

        obj.put("FLAG","1");
        for(Integer i=1; i<4; i++) {
            JSONObject subObj = new JSONObject();//创建对象数组里的子对象
            subObj.put("Name","array"+i);
            subObj.put("String","小白"+i);
            obj.accumulate("ARRAYS",subObj);
        }
        System.out.println(obj.toString());

        osw.write(obj.toString());
        osw.flush();//清空缓冲区，强制输出数据
        osw.close();//关闭输出流
    }

    /*public static void readCSV(String filePath) throws IOException {
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        DataInputStream in = new DataInputStream(new FileInputStream(new File(filePath)));

        //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
        BufferedReader br = new BufferedReader(new InputStreamReader(in,"GBK"));//这里如果csv文件编码格式是utf-8,改成utf-8即可

        //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
        String lineDta = "";
        while ((lineDta = br.readLine()) != null){
            System.out.println(lineDta);
        }
    }
    public static void readCSV02(String filePath) {     //使用记事本将CSV文件更改为utf-8，后可成功读取文件
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File(filePath);

        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                System.out.println(lineDta);
            }

        } catch (FileNotFoundException e) {
            System.out.println("没有找到指定文件");
        } catch (IOException e) {
            System.out.println("文件读写出错");
        }
    }
    public static void writeCSV(String writeFilePath) {
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File(writeFilePath);
        try{
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            for(int i=1; i<=10; i++){
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write("新用户"+i+",男,"+(18+i));
            }
            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
    }*/
}
