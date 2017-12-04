package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunjian.
 */
@Service
public class ExportSalesInfo
{
    @Resource
    private SalesInfoMapper salesInfoMapper;

    private static final String BASE_FILE_NAME = "D:\\项目\\红旗\\红旗-核心\\红旗二期\\数据\\数据导入\\销量表\\171129新增国产8月的数据\\sales_info";
    private static final String CSV_POSTFIX = ".csv";
    private static final int PER_COUNT = 500000;

    private final String[] header = new String[]{"id", "car_series_code", "car_series_name", "car_base_type_code", "car_type", "year_month", "region", "primary_area", "secondary_area", "sales", "car_base_type_name"};


    /**
     * 导出csv使用前切换到测试环境数据库,到处完成后改回本地库
     */
    public void exportCsv(int start,int end,int i) throws IOException
    {
        //查询总数确定循环次数
//        int loopTimes = (total / PER_COUNT) + 1;
//        int start;
//        int end;
        String fileName;
        StringBuilder stringBuilder;
        List<SalesInfo> salesInfoList;
        Map<String, Object> map;
        ICsvMapWriter mapWriter;
        // write the header
//        for (int i = 0; i < loopTimes; i++)
//        {
//            start = i * PER_COUNT;
//            end = (i + 1) * PER_COUNT;
            //查询数据
            salesInfoList = salesInfoMapper.exportSalesInfo(start, end);
            //写文件
            stringBuilder = new StringBuilder(BASE_FILE_NAME);
            fileName = stringBuilder.append(i).append(CSV_POSTFIX).toString();
            mapWriter = new CsvMapWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);
            //写头
            mapWriter.writeHeader(header);
            //写数据
            for (SalesInfo salesInfo : salesInfoList)
            {
                map = new HashMap<>();
                map.put(header[0], salesInfo.getId());
                map.put(header[1], salesInfo.getCarSeriesCode());
                map.put(header[2], salesInfo.getCarSeriesName());
                map.put(header[3], salesInfo.getCarBaseTypeCode());
                map.put(header[4], salesInfo.getCarType());
                map.put(header[5], salesInfo.getYearMonth());
                map.put(header[6], salesInfo.getRegion());
                map.put(header[7], salesInfo.getPrimaryArea());
                map.put(header[8], salesInfo.getSecondaryArea());
                map.put(header[9], salesInfo.getSales());
                map.put(header[10], salesInfo.getCarBaseTypeName());
                mapWriter.write(map, header);
                map = null;
            }
            mapWriter.close();
            salesInfoList = null;
//        }
    }
}
