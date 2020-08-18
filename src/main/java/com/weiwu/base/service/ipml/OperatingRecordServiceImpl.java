package com.weiwu.base.service.ipml;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.weiwu.base.Enum.EnumCode;
import com.weiwu.base.entity.OperatingRecord;
import com.weiwu.base.mapper.OperatingRecordMapper;
import com.weiwu.base.pojo.dto.ParamsDto;
import com.weiwu.base.pojo.dto.ReqTotalDto;
import com.weiwu.base.pojo.dto.RequstOprDto;
import com.weiwu.base.service.OperatingRecordService;
import com.weiwu.base.utils.ResultUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class OperatingRecordServiceImpl extends ServiceImpl<OperatingRecordMapper, OperatingRecord> implements OperatingRecordService {

    /**
     * 查询操作记录
     *
     * @param dto
     * @return
     */
    public List<OperatingRecord> findOperatingRecordByPage(Page<OperatingRecord> page, ParamsDto dto) {
        return super.baseMapper.findOperatingRecordByPage(page, dto);
    }

    /**
     * 访问统计
     */
    public Object findUserReqTotal() {

        List<ReqTotalDto> reqList = super.baseMapper.findAllRequstCount();
        List<RequstOprDto> reqData1 = new ArrayList<>();
        List<RequstOprDto> reqData2 = new ArrayList<>();
        String[] arrNa = new String[reqList.size()];
        RequstOprDto reo = null;
        for (int i = 0, j = reqList.size(); i < j; i++) {
            if (reqList.get(i).getType().equals(1)) {
                // Method
                reo = new RequstOprDto();
                reo.setName(reqList.get(i).getNa());
                reo.setValue(reqList.get(i).getTotal());
                reqData1.add(reo);
            } else {
                // 源
                reo = new RequstOprDto();
                reo.setName(reqList.get(i).getNa());
                reo.setValue(reqList.get(i).getTotal());
                reqData2.add(reo);
            }
            arrNa[i] = reqList.get(i).getNa();
        }

        RequstOprDto r = new RequstOprDto();
        List<RequstOprDto> reqData3 = new ArrayList<>();
        r.setMetlist((ArrayList) reqData1);
        r.setUsrlist((ArrayList) reqData2);
        r.setArrName(arrNa);
        reqData3.add(r);

        return ResultUtil.result(EnumCode.OK.getValue(), EnumCode.OK.getText(), reqData3);
    }
}
