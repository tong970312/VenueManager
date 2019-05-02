package com.graduation.sportsvenue.util;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Data
public class FTPUtils {
    //ip 地址  用户名  密码
    private static String FTPIP = "192.168.163.129";
    private static String FTPUser = "ftpUser";
    private static String FTPPASSWORD = "lt127496";

    private String ftpIp;
    private String ftpUser;
    private String frpPass;
    private Integer port;

    public FTPUtils(String ftpIp, String ftpUser, String frpPass, Integer port) {
        this.ftpIp = ftpIp;
        this.ftpUser = ftpUser;
        this.frpPass = frpPass;
        this.port = port;
    }


    /**
     * 图片上传到FTP
     */

    public static boolean uploadFile(List<File> fileList) {

        FTPUtils ftpUtils = new FTPUtils(FTPIP, FTPUser, FTPPASSWORD, 21);
        System.out.println("连接FTP服务器");

        ftpUtils.uploadFile("img",fileList);

        return false;
    }


    /*
    * File file1 = new File(path,newFile)
    *
    * FTPUtils.upload(Lists.newArrayList(file1))
    *
    *本地删除应用服务器上的图片
    *
    * file1.delete();
    * */

    /**
     * 上传
     * @param remotePath FTP服务器子目录 ->img
     * @param fileList
     * @return
     */
    public boolean uploadFile(String remotePath, List<File> fileList) {

        FileInputStream fileInputStream = null;

        //连接FTP服务器
        if (connectFTPServer(FTPIP, FTPUser, FTPPASSWORD)) {
            try {
                //切换目录到子目录
                ftpClient.changeWorkingDirectory(remotePath);
                //缓存区大小
                ftpClient.setBufferSize(1024);
                //设置字符集
                ftpClient.setControlEncoding("UTF-8");
                //设置二进制类型
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //被动传输模式
                ftpClient.enterLocalActiveMode();
                for (File file : fileList) {
                    fileInputStream = new FileInputStream(file);
                    //将对应文件读到FTP服务器上
                    ftpClient.storeFile(file.getName(), fileInputStream);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件上传出错");
            } finally {
                try {
                    fileInputStream.close();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 连接FTP服务器
     */

    FTPClient ftpClient = null;

    public boolean connectFTPServer(String ftpIp, String ftpUser, String frpPass) {
        System.out.println("ftpIp+\"-\"+ftpUser+\"-\"+frpPass = " + ftpIp + "-" + ftpUser + "-" + frpPass);

        ftpClient = new FTPClient();
        //IP地址
        try {
            ftpClient.connect(ftpIp);
           return ftpClient.login(ftpUser, frpPass);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("连接FTP服务器异常");
        }
        return false;
    }

}
