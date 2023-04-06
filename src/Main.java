import java.util.*;

public class Main {
    static Map<Integer, String> units = new HashMap<>();
    public static void main(String[] args) throws Exception {
        FillUnits();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число (от 1 до 1 000 000):");
        System.out.println(NumberToText(scanner.nextInt()));

        String code = "\n\nint a = 25;\n/*This is comments 234\npepeg\nkekl*/\na+=12;\n//comm2\n/*comm4*/int b;\nSystem.out.println(a.ToString());";
        System.out.println(code+"\n\n\n");

        String res = RemoveComments(code);
        System.out.println(res);
    }
    private static void FillUnits()
    {
        units.put(0, "ноль");     units.put(10, "десять");
        units.put(1, "один");     units.put(11, "одиннадцать");
        units.put(2, "два");      units.put(12, "двенадцать");
        units.put(3, "три");      units.put(13, "тринадцать");
        units.put(4, "четыре");   units.put(14, "четырнадцать");
        units.put(5, "пять");     units.put(15, "пятнадцать");
        units.put(6, "шесть");    units.put(16, "шестнадцать");
        units.put(7, "семь");     units.put(17, "семнадцать");
        units.put(8, "восемь");   units.put(18, "восемнадцать");
        units.put(9, "девять");   units.put(19, "девятнадцать");
        units.put(20, "двадацать");

        units.put(100, "сто");
        units.put(200, "двести");     units.put(20, "двадцать");
        units.put(300, "триста");     units.put(30, "тридцать");
        units.put(400, "четыреста");  units.put(40, "сорок");
        units.put(500, "пятьсот");    units.put(50, "пятьдесят");
        units.put(600, "шестьсот");   units.put(60, "шестьдесят");
        units.put(700, "семьсот");     units.put(70, "семьдесят");
        units.put(800, "восемьсот");   units.put(80, "восемьдесят");
        units.put(900, "девятьсот");   units.put(90, "девяносто");
    }
    public static String NumberToText(Integer number) throws Exception {
        String num_str = number.toString();
        String final_text = "";

        switch (num_str.length())
        {
            case 1:
                return units.get(number);
            case 2:
                if((number % 10) == 0)
                    return units.get(number);
                if(number < 20)
                    return units.get(number);
                else
                {
                    var nms = GetNumbers(number);
                    for (int i = 0; i < nms.size();i++)
                    {
                        final_text += (NumberToText(nms.get(i)) + " ");
                    }
                }
                break;
            case 3:
                if((number % 100) == 0)
                    return units.get(number);
                else
                {
                    var num_inf = IntegrateNumber(number,100);
                    return units.get(num_inf[0]) + " " + NumberToText(num_inf[1]);
                }
            default:
                var thousands = IntegrateNumber(number,1000);
                String thr_text = "";
                if(number == 1000000) return "один миллион";
                if(number >  1000000) return "Вне диапазона!";
                if(GetNumbers(thousands[2]).get(GetNumbers(thousands[2]).size()-1) == 1)  thr_text = " тысяча ";
                else if(GetNumbers(thousands[2]).get(GetNumbers(thousands[2]).size()-1) > 1
                && GetNumbers(thousands[2]).get(GetNumbers(thousands[2]).size()-1) < 5)   thr_text = " тысячи ";
                else
                    thr_text = " тысяч ";
                final_text+=(NumberToText(thousands[2]).replace(" один "," одна ").replace(" два "," две ")
                        + thr_text + NumberToText(thousands[1]));
        }
        return final_text;
    }
    public static String RemoveComments(String code_text)
    {
        String result;
        result = code_text.replaceAll("(//)(.)+(\\n)","");
        result = result.replaceAll("(//*)(.)+(/*/)(\\n)","");
        result = result.replaceAll("(//*)(.|\\n)+(/*/)(\\n)","");
        result = result.replaceAll("(//*)(.)+(/*/)","");
        result = result.replaceAll("(//*)(.|\\n)+(/*/)","");


        return result;
    }
    public static List<Integer> GetNumbers(Integer number)
    {
        List<Integer> result = new ArrayList<Integer>();
        String num_str = number.toString();

        for (int i = 0; i < num_str.length(); i++)
        {
            String str = ""; str += num_str.charAt(i);
            result.add(Integer.parseInt(str) * (int)Math.pow(10,num_str.length() - (i+1)));
        }

        return result;
    }
    public static Integer[] IntegrateNumber(int number, int categories) throws Exception {
        Integer[] result = new Integer[3];

        if((categories % 10) != 0) return null;
        int high_c = ((int)(number / categories))*categories;
        int low_c = number - high_c;
        result[0] = high_c; result[1] = low_c; result[2] = high_c / categories;

        return result;
    }
}