package testbean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ComponentForm {

    //是否计算年假 0:无年假；1:有年假
    private Integer isAnnualLeave;
    //是否区分入职年限 0:不区分；1:区分
    private Integer isDiffEntryYears;
    private List<WorkedMonth> annualLeaveSettingColum1;

    private List<WorkedMonth> annualLeaveSettingColum2;

    //没有年假的人员id//"1,2,3"
    private List<Integer> noAnnualLeaveUserIds;
    //没有年假的人员姓名//"郑凯,王立帅,何伟杰"
    private List<String> noAnnualLeaveUserNames;
    //年假可申请最小单位：1：0.5天；2：1天
    private List<String> annualLeaveMinUnit;
    private List<WorkedMonth> annualLeaveSettingColum3;

    //是否包括法定节假日
    private Integer isIncludeWeekendAndHoliday;
    private List<WorkedMonth> annualLeaveSettingColum4;

    //是否规定时间内修完：0：否；1：是
    private Integer isRuleTimeUse;//年假失效日期
    //年假失效日期
    private Date annualLeaveExpirationDate;


}
