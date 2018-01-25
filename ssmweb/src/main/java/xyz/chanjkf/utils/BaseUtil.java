package xyz.chanjkf.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yi on 2017/7/31.
 */
public class BaseUtil {



    /*返回给前台页面成功码，和成功信息*/
    public static void buildSuccessResult(Map<String, Object> map, String message){
        if(map == null){
            return;
        }

        map.put("code", "success");
        if(message != null) {
            map.put("message", message);
        }

        return;
    }

    /*返回给前台页面错误码，和错误信息*/
    public static void buildErrorResult(Map<String, Object> map, String message){
        if(map == null){
            return;
        }

        map.put("code", "failed");
        if (message != null) {
            map.put("message", message);
        }

        return;
    }

    public static String getUuid() {
        String s = UUID.randomUUID().toString();
        // 去掉"-"符号
        return s.replace("-", "");
    }


    public static String getPartitionString(String partition) {
        String partitionTmp = "";

        if (StringUtils.isNotEmpty(partition)) {
            JSONObject jsonObject = JSONObject.fromObject(partition);
            Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
            for (Map.Entry<String, Object> entry : mapJson.entrySet()) {
                partitionTmp += entry.getKey() + "='" + entry.getValue() + "',";
            }
            partitionTmp = partitionTmp.substring(0, partitionTmp.length() - 1);
        }

        return partitionTmp;
    }

    public static String generateDopAppId(String accessKeyId, Long userId) {
        return accessKeyId + String.format("%03x", userId);
    }






    /**
     * 转义sql语句中的特殊字符
     */
    public static String escapeSQL(String likeStr) {

        if(StringUtils.isBlank(likeStr)) {
            return likeStr;
        }
        String str = StringUtils.replace(likeStr, "\\",    "\\\\");
        str = StringUtils.replace(str, "_", "\\_");
        str = StringUtils.replace(str, "%",    "\\%");
        return str;
    }

    

    /*设置查询的时间参数*/
    public static StringBuilder setTimeCondition(String entityStr, Date startTime, Date endTime) {
        StringBuilder query = new StringBuilder();
        String startTimeStr;
        String endTimeStr;
        //指定查询的开始时间和结束时间
        if ((null != startTime) && (null != endTime)) {
            //开始时间和结束时间不相同
            if (startTime.equals(endTime)) {
                //查询指定日期
                startTimeStr = BaseTime.dateAddDaysTranStr(startTime, 0, "yyyy-MM-dd");
                endTimeStr = BaseTime.dateAddDaysTranStr(startTime, 1, "yyyy-MM-dd");
                query.append(entityStr).append(" between '").append(startTimeStr).append("' and '").append(endTimeStr)
                        .append("'");
            } else {
                startTimeStr = BaseTime.dateAddDaysTranStr(startTime, 0, "yyyy-MM-dd");
                endTimeStr = BaseTime.dateAddDaysTranStr(endTime, 1, "yyyy-MM-dd");
                query.append(entityStr).append(" between '").append(startTimeStr).append("' and '").append(endTimeStr)
                        .append("'");
            }
        } else if ((null == startTime) && (null != endTime)) {
            //查询指定日期
            endTimeStr = BaseTime.dateAddDaysTranStr(endTime, 1, "yyyy-MM-dd");
            query.append(entityStr).append(" < '").append(endTimeStr).append("'");
        } else if ((null != startTime) && (null == endTime)) {
            //查询指定日期
            startTimeStr = BaseTime.dateAddDaysTranStr(startTime, 0, "yyyy-MM-dd");
            query.append(entityStr).append(" >= '").append(startTimeStr).append("'");
        }

        return query;
    }
}
