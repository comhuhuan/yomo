package yomo.study;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:BigDTest
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/11/1 13:02
 */
public class BigDTest {


    public static void main(String[] args) {
        List<String> arrayList = new ArrayList();
        arrayList.add("1284.8");
        arrayList.add("2483.25");

        BigDecimal allDrAmount = BigDecimal.ZERO;
        // 贷款总额
        BigDecimal allCrAmount = new BigDecimal("0");
        for (String s : arrayList) {
            allDrAmount = allDrAmount.add(new BigDecimal(String.valueOf(s)));
        }
        System.out.println(allDrAmount);


        String a = "6.146";
        Double d = Double.parseDouble(a);
        DecimalFormat df = new DecimalFormat("0.00");
        String s = df.format(d);

        System.out.println(s);
//
//        BigDecimal abc = BigDecimal.ZERO;
//        for (String s1 : arrayList) {
//
//        }


    }


    private void checkDrAndCrInfo() {

    }

}