package example;

public class DemoService {

    //传输int类型参数  如果参数在strings 的索引值范围内  则返回对应元素   否则返回“NO_DATA_FOUND”代码
    public String getElement(int index) {
        String[] strings = {"零", "壹", "貳", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        return index >= 0 && index < strings.length ? strings[index] : "NO_DATA_FOUND";
    }
}
