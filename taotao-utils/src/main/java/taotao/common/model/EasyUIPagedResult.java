package taotao.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-04
 * Time: 18:27
 */
public class EasyUIPagedResult implements Serializable {

    private static final long serialVersionUID = -6397095353272382440L;

    private long total;
    private List<?>rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
