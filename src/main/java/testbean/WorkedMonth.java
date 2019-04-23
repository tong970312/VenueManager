package testbean;

import lombok.Data;

@Data
public class WorkedMonth {

    //入职满几月
    private Integer entryMonths;
    //累计工作几月
    private Integer  workMonths;
    //所享受年假天数
    private Integer  annualLeaveDays;
    //累计工作满
    private Integer minEntryYears;
    //累计工作不满
    private Integer maxEntryYears;
    //累计工作满
    private Integer  minEntryYearAmount;
    //累计工作不满
    private Integer maxEntryYearAmount;
    //请假类型：1：事假；2：倒休；3：病假
    private Integer leaveType;
    //请假天数
    private Integer leaveDays;
    //大于几天（小数）
    private Integer minDays;
    //小于几天（小数）
    private Integer maxDays;

}
