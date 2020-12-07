package com.github.tangmonkmeat.vo;

/**
 * 用户的诚信值 和 预约时间
 *
 * @author zwl
 * @date 2020/12/6 14:21
 */
public class IntergrityAndTimeVO {

    /**
     * 用户当前诚信值
     * 例如：95
     *
     */
    private Integer userIntergrity;

    /**
     * 诚信值下限
     * 例如：80
     *
     */
    private Integer lowIntergrity=80;

    /**
     * 今天的预约时间，
     * 例如：12:00-14:00
     *
     */
    private String todayTime="8:00-20:00";

    /**
     * 明天的预约时间，
     * 例如：12:00-14:00
     *
     */
    private String tomTime="8:00-20:00";

    @Override
    public String toString() {
        return "IntergrityAndTimeVO{" +
                "userIntergrity=" + userIntergrity +
                ", lowIntergrity=" + lowIntergrity +
                ", todayTime='" + todayTime + '\'' +
                ", tomTime='" + tomTime + '\'' +
                '}';
    }

    public IntergrityAndTimeVO() {
    }

    public IntergrityAndTimeVO(Integer userIntergrity) {
        this.userIntergrity = userIntergrity;
    }

    public Integer getUserIntergrity() {
        return userIntergrity;
    }

    public void setUserIntergrity(Integer userIntergrity) {
        this.userIntergrity = userIntergrity;
    }

    public Integer getLowIntergrity() {
        return lowIntergrity;
    }

    public void setLowIntergrity(Integer lowIntergrity) {
        this.lowIntergrity = lowIntergrity;
    }

    public String getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(String todayTime) {
        this.todayTime = todayTime;
    }

    public String getTomTime() {
        return tomTime;
    }

    public void setTomTime(String tomTime) {
        this.tomTime = tomTime;
    }
}
