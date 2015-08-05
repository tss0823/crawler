package com.usefullc.crawler.service;

import com.usefullc.crawler.common.dto.TaskExecuteDto;

/**
 * Created by shengshan.tang on 7/29/2015 at 11:00 AM
 */
public interface ITaskExecuteService {

    TaskExecuteDto start(Long taskTpId);

    TaskExecuteDto startMulti(Long taskTpId,Long parseContentId);


}
