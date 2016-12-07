package longteng.pda.vo;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class Order {
    public String OrderNO;
    public String CreateTime;
    public String AmendTime;
    public String Remark;
    public String Items;
    public String SUM;
    public String OrderName;

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getOrderNO() {
        return OrderNO;
    }

    public void setOrderNO(String orderNO) {
        OrderNO = orderNO;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getAmendTime() {
        return AmendTime;
    }

    public void setAmendTime(String amendTime) {
        AmendTime = amendTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getItems() {
        return Items;
    }

    public void setItems(String items) {
        Items = items;
    }

    public String getSUM() {
        return SUM;
    }

    public void setSUM(String SUM) {
        this.SUM = SUM;
    }


}
