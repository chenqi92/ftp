package com.lyc.ftp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenqi
 * @DESCRIPTION:
 * @params:
*/
@Getter
@AllArgsConstructor
public enum UploadStatus {
    /**
     * 
     */
    CREATE_DIRECTORY_FAIL("远程服务器相应目录创建失败"),
    CREATE_DIRECTORY_SUCCESS("远程服务器闯将目录成功"),
    UPLOAD_NEW_FILE_SUCCESS("上传新文件成功"),
    UPLOAD_NEW_FILE_FAILED("上传新文件失败"),
    FILE_EXITS("文件已经存在"),
    REMOTE_BIGGER_LOCAL("远程文件大于本地文件"),
    UPLOAD_FROM_BREAK_SUCCESS("断点续传成功"),
    UPLOAD_FROM_BREAK_FAILED("断点续传失败"),
    DELETE_REMOTE_FAILD("删除远程文件失败");

    private final String desc;
}