package com.robotcms.oss.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robotcms.common.base.CoreServiceImpl;
import com.robotcms.common.config.IFastConfig;
import com.robotcms.common.utils.DateUtils;
import com.robotcms.common.utils.FileType;
import com.robotcms.oss.dao.FileDao;
import com.robotcms.oss.domain.FileDO;
import com.robotcms.oss.sdk.QiNiuOSSService;
import com.robotcms.oss.service.FileService;

/**
 * <pre>
 * </pre>
 * 
 * |
 */
@Service
public class FileServiceImpl extends CoreServiceImpl<FileDao, FileDO> implements FileService {

    @Autowired
    private IFastConfig ifastConfig;
    @Autowired
    private QiNiuOSSService qiNiuOSS;

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        fileName = fileName.substring(0, fileName.indexOf(".")) + "-" + System.currentTimeMillis() + fileName.substring(fileName.indexOf("."));
        fileName = ifastConfig.getProjectName() + "/" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_8)
                + "/" + fileName;
        String url = qiNiuOSS.upload(uploadBytes, fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), url, new Date());
        super.insert(sysFile);
        return url;
    }
}
