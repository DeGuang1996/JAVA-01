package question2.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author deguang
 * @date 2021/02/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String Sno;          //学号
    private String Sname;        //姓名
    private String Ssex;         //性别
    private String Sbirthday;    //生日
    private String Sclass;       //班级

}
