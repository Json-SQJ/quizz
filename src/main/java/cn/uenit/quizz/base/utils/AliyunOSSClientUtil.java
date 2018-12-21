package cn.uenit.quizz.base.utils;


import org.springframework.stereotype.Component;

/**
 * @author: SQJ
 * @data: 2018/5/11 09:20
 * @version:
 */
@Component
public class AliyunOSSClientUtil {


//    @Autowired
//    private Config config;
//
//
//    private static Logger logger = LoggerFactory.getLogger(AliyunOSSClientUtil.class);
//
//
//    public OSSClient getOSSClient() {
//        return new OSSClient(config.getEndPoint(), config.getAccessKeyId(), config.getAccessKeySecret());
////        return new OSSClient("oss-cn-hangzhou.aliyuncs.com", "LTAI1kMvM11dt4sx", "xpHFpdWLkMGHFfXQKFXxgYw7wShveG");
//    }
//
//
//    /**
//     * 创建存储空间
//     *
//     * @param ossClient  OSS连接
//     * @param bucketName 存储空间
//     * @return
//     */
//    public String createBucketName(OSSClient ossClient, String bucketName) {
//        final String bucketNames = bucketName;
//        if (!ossClient.doesBucketExist(bucketName)) {
//            Bucket bucket = ossClient.createBucket(bucketName);
//            logger.info("创建存储空间成功");
//            return bucket.getName();
//        }
//        return bucketNames;
//    }
//
//    /**
//     * 删除存储空间buckName
//     *
//     * @param ossClient  oss对象
//     * @param bucketName 存储空间
//     */
//    public void deleteBucket(OSSClient ossClient, String bucketName) {
//        ossClient.deleteBucket(bucketName);
//        logger.info("删除" + bucketName + "Bucket成功");
//    }
//
//    /**
//     * 创建模拟文件夹
//     *
//     * @param ossClient  oss连接
//     * @param bucketName 存储空间
//     * @param folder     模拟文件夹名如"qj_nanjing/"
//     * @return 文件夹名
//     */
//    public String createFolder(OSSClient ossClient, String bucketName, String folder) {
//        final String keySuffixWithSlash = folder;
//        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
//            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
//            logger.info("创建文件夹成功");
//            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
//            String fileDir = object.getKey();
//            return fileDir;
//        }
//        return keySuffixWithSlash;
//    }
//
//    /**
//     * 根据key删除OSS服务器上的文件
//     *
//     * @param ossClient  oss连接
//     * @param bucketName 存储空间
//     * @param folder     模拟文件夹名 如"qj_nanjing/"
//     * @param key        Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
//     */
//    public void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
//        ossClient.deleteObject(bucketName, folder + key);
//        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
//    }
//
//    /**
//     * 上传图片至OSS
//     *
//     * @param ossClient  oss连接
//     * @param file       上传文件（文件全路径如：D:\\image\\cake.jpg）
//     * @param bucketName 存储空间
//     * @param folder     模拟文件夹名 如"qj_nanjing/"
//     * @return String 返回的唯一MD5数字签名
//     */
//    public String uploadObject2OSS(OSSClient ossClient, File file, String bucketName, String folder) {
//        String resultStr = null;
//        try {
//            InputStream is = new FileInputStream(file);
//            String fileName = file.getName();
//            Long fileSize = file.length();
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentLength(is.available());
//            metadata.setCacheControl("no-cache");
//            metadata.setHeader("Pragma", "no-cache");
//            metadata.setContentEncoding("utf-8");
//            metadata.setContentType(getContentType(fileName));
//            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
//            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
//            resultStr = putResult.getETag();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
//        }
//        return resultStr;
//    }
//
//    /**
//     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
//     *
//     * @param fileName 文件名
//     * @return 文件的contentType
//     */
//    public String getContentType(String fileName) {
//        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
//        if (".bmp".equalsIgnoreCase(fileExtension)) {
//            return "image/bmp";
//        }
//        if (".gif".equalsIgnoreCase(fileExtension)) {
//            return "image/gif";
//        }
//        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
//            return "image/jpeg";
//        }
//        if (".html".equalsIgnoreCase(fileExtension)) {
//            return "text/html";
//        }
//        if (".txt".equalsIgnoreCase(fileExtension)) {
//            return "text/plain";
//        }
//        if (".vsd".equalsIgnoreCase(fileExtension)) {
//            return "application/vnd.visio";
//        }
//        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
//            return "application/vnd.ms-powerpoint";
//        }
//        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
//            return "application/msword";
//        }
//        if (".xml".equalsIgnoreCase(fileExtension)) {
//            return "text/xml";
//        }
//        return "image/jpeg";
//    }
//
//    /**
//     *  上传图片到oss
//     * @param fileName 文件名字
//     * @param fileUrl 上传本地过度地址
//     * @param folder 上传目的文件夹
//     * @return
//     */
//    public String upLoadPicToOss(String fileName, String fileUrl, String folder) {
//        OSSClient ossClient = getOSSClient();
//        File file = new File(fileUrl);
//        String md5key = uploadObject2OSS(ossClient, file, config.getBacketName(), folder);
//        if (!Strings.isNullOrEmpty(md5key)) {
//            return config.getOssUrl() + folder + fileName;
//        } else {
//            return null;
//        }
//    }


}
