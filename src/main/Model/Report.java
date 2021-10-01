package main.Model;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles Report objects.
 */
public class Report {

    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private String var5;
    private String var6;
    private String var7;

    /**
     *
     * @param var1 String one
     * @param var2 String two
     * @param var3 String three
     * @param var4 String four
     * @param var5 String five
     * @param var6 String six
     * @param var7 String seven
     */
    public Report(String var1, String var2, String var3, String var4, String var5, String var6, String var7) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.var5 = var5;
        this.var6 = var6;
        this.var7 = var7;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }

    public String getVar5() {
        return var5;
    }

    public void setVar5(String var5) {
        this.var5 = var5;
    }

    public String getVar6() {
        return var6;
    }

    public void setVar6(String var6) {
        this.var6 = var6;
    }

    public String getVar7() {
        return var7;
    }

    public void setVar7(String var7) {
        this.var7 = var7;
    }

    @Override
    public String toString() {
        return "Report{" +
                "var1='" + var1 + '\'' +
                ", var2='" + var2 + '\'' +
                ", var3='" + var3 + '\'' +
                ", var4='" + var4 + '\'' +
                ", var5='" + var5 + '\'' +
                ", var6='" + var6 + '\'' +
                ", var7='" + var7 + '\'' +
                '}';
    }
}
