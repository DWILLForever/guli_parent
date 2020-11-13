package com.dwill.edu.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dwill.edu.entity.Subject;
import com.dwill.edu.entity.excel.SubjectData;
import com.dwill.edu.service.SubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
   //因为SubjectExcelListener不能交给spring进行管理，需要自己new一个对象，不能注入其它对象
   //否则实现不了数据库的操作
    public SubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
