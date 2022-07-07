package com.base.fastDFS;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FastdfsTest {

    // /group1/M00/00/00/wKgBnGIfNAeAAKXfAAFOjgcCN_Q296.jpg
    // /group2/M00/00/00/wKgBnWIfNI2ASM10AAFOjgcCN_Q771.jpg
    // /group1/M00/00/00/wKgBnmIfNyiANWXzAALTEGTM7x4935.jpg
    public static void main(String[] args) {
        FastdfsTest fastdfsTest = new FastdfsTest();
        System.out.println(fastdfsTest.upload("123", "D:\\发来的文件\\网络图片\\(2).jpg"));
    }

    public FastdfsTest() {
        try {
            ClientGlobal.init("C:\\Users\\itchun\\Desktop\\fdfs_client.conf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String upload(String babyId, String filePath) {
        return upload(3, filePath, babyId);
    }

    public String upload(int retry, String filePath, String babyid) {
        boolean bl = false;
        int count = 1;
        String fileName = "";

        //init
        TrackerServer trackerServer = createTrackerServer();
        if (trackerServer == null) return fileName;
        StorageClient storageClient = createStorageClient(trackerServer);
        if (storageClient == null) return fileName;

        while (!bl && count <= retry) {
            try {
                //参数
                NameValuePair[] metadatas = new NameValuePair[1];
                metadatas[0] = new NameValuePair("babyId", babyid);

                //上传
                String[] results = storageClient.upload_file(filePath, null, metadatas);
                if (results == null) {
                    throw new Exception("上传失败：" + storageClient.getErrorCode());
                }

                //解析结果
                String groupName = results[0];
                fileName = "/" + groupName + "/" + results[1];
                //成功标记
                bl = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                count++;
            }
        }
        try {
            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public String dowmFile(String fastDFSpath, String tempPath, String tempName) {

        // init
        TrackerServer trackerServer = createTrackerServer();
        if (trackerServer == null) return null;
        StorageClient storageClient = createStorageClient(trackerServer);
        if (storageClient == null) return null;
        String groupName = "group1";
        try {
            byte[] bytes = storageClient.download_file(groupName, fastDFSpath.replace("/group1/", ""));
            File imageFile = new File(tempPath);
            if (!imageFile.exists()) imageFile.mkdirs();
            imageFile = new File(tempPath + tempName);
            imageFile.createNewFile();
            FileOutputStream fileOutStream = new FileOutputStream(imageFile);
            fileOutStream.write(bytes);
            fileOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.csource.common.MyException e) {
            e.printStackTrace();
        } finally {
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tempPath + File.separator + tempName;
    }

    public void delete(String fileName) {
        //init
        TrackerServer trackerServer = createTrackerServer();
        StorageClient storageClient = createStorageClient(trackerServer);

        boolean bl = false;
        int count = 1;
        if (fileName == null) {
            return;
        }

        while (!bl && count <= 3) {
            try {
                //参数
                String groupName = "group1";

                //删除
                int status = storageClient.delete_file(groupName, fileName.replace("/group1/", ""));
                if (status == 0) {
                    bl = Boolean.TRUE;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                count++;
            }
        }
        try {
            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TrackerServer createTrackerServer() {
        try {
            TrackerClient trackerClient = new TrackerClient();
            return trackerClient.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StorageClient createStorageClient(TrackerServer trackerServer) {
        try {
            return new StorageClient(trackerServer, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}