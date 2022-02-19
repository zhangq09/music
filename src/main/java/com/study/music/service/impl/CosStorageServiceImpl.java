package com.study.music.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.region.Region;
import com.study.music.dto.FileUploadDto;
import com.study.music.service.StorageService;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;

@Service("COS")
public class CosStorageServiceImpl implements StorageService {

    @Value("${cos.storage.secretId}")
    private String secretId;

    @Value("${cos.storage.secretKey}")
    private String secretKey;

    @Value("${cos.storage.bucket}")
    private String bucket;

    @Value("${cos.storage.region}")
    private String region;

    @Override
    public FileUploadDto initFileUpload() throws IOException {
        return CosStsCredential();
    }

    @Override
    public void download(String key, HttpServletResponse response) throws IOException {
        COSObjectInputStream cosObjectInputStream = CosGetObject(key);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        IOUtils.copy(cosObjectInputStream, response.getOutputStream());
        response.flushBuffer();
    }

    private COSObjectInputStream CosGetObject(String key) {

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig(new Region(region));

        // 设置请求协议, http 或者 https
        // 5.6.53 及更低的版本，建议设置使用 https 协议
        // 5.6.54 及更高版本，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 设置 socket 读取超时，默认 30s
        clientConfig.setSocketTimeout(30 * 1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30 * 1000);

        COSClient cosClient = new COSClient(cred, clientConfig);

        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

        COSObject cosObject = cosClient.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
        return cosObjectInput;
    }

    private FileUploadDto CosStsCredential() throws IOException {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        config.put("secretId", secretId);
        config.put("secretKey", secretKey);
        config.put("bucket", bucket);
        // 临时密钥有效时长，单位是秒，默认 1800 秒，目前主账号最长 2 小时
        config.put("durationSeconds", 1800);
        config.put("region", region);
        //允许访问的文件 *表示所有
        config.put("allowPrefixes", new String[]{
                "*"
        });

        String[] allowActions = new String[]{
                // 简单上传
                "name/cos:PutObject",
                // 表单上传、小程序上传
                "name/cos:PostObject",
                // 分块上传
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload"
        };
        config.put("allowActions", allowActions);
        Response credential = CosStsClient.getCredential(config);
        return new FileUploadDto().setSecretId(credential.credentials.tmpSecretId)
                .setBucket(bucket)
                .setSecretKey(credential.credentials.tmpSecretKey)
                .setRegion(region)
                .setSessionToken(credential.credentials.sessionToken)
                .setExpiredTime(new Date(System.currentTimeMillis() + 7200000).getTime() / 1000);

    }

}