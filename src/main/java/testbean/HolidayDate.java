package testbean;

import lombok.Data;

import java.util.List;

@Data
public class HolidayDate {
    //
    private Integer id;
    //假期类型
    private Integer type;
    //组成
    private List<ComponentForm> component_form;

}

   