import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @Description TODO
 * @Author tanxinzheng
 * @Date 2020/8/2
 */
public class Test {

    public static String getMaxsubHuisu(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int start = 0;//滑动窗口的开始值
        int maxlen = 0;
        int len = 0;
        int startMaxIndex = 0;//最长子串的开始值
        Map<Character, Integer> map = new HashMap<>();//存储窗口内字符跟位置
        int i;
        for (i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer value = map.get(ch);
            if (map.containsKey(ch)) {//map中包含字符，则出现重复字符
                start = value + 1;//下一次开始的位置是，存在map中重复字符的下一个位置
                len = 0;//重新开始新的窗口，len置为0
                map = new HashMap<>();//map置空
                i=value;//下次从重复的值开始回溯
            } else {
                map.put(ch, i);//不存在重复的，就存入map
                len++;//每次进来长度自增1
                if (len >= maxlen) {//如果当前的窗口长度>最长字符串则，更新最长串，跟最长子串开始位置
                    maxlen = len;
                    startMaxIndex = start;
                }
            }
        }
        return s.substring(startMaxIndex, (startMaxIndex + maxlen));
    }

    public static void main(String[] str){
        // 这个结果应该有两个最长的不重复字符串：advxyz，xyzdcv
        // 正序递增的话应该是：advxyz
        // 倒序递减的话应该是：xyzdcv
        String data = "abcdadvxyzdcvddsa";
        StringBuffer reverseStr = new StringBuffer(data);
        String result = getMaxsubHuisu(data);
        System.out.println(result);
    }

}
