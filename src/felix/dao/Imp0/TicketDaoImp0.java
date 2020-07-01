/**
 * @author Felix
 * @describe
 * @date 2020/6/29 19:43
 */
package felix.dao.Imp0;

import felix.dao.TicketDao;
import felix.entity.Ticket;
import org.junit.Test;

import java.util.ArrayList;

public class TicketDaoImp0 extends BaseDao  implements TicketDao {
    @Override
    public ArrayList<Ticket> queryTicketAllBySid(int sid) {
        return queryAll(Ticket.class,"select id,uid,sid,row_java as 'row',column_java as 'column' from ticket where sid = ?",sid);
    }

    @Override
    public boolean createTicket(Ticket ticket) {
        String sql = "insert into ticket(uid,sid,row_java,column_java)values(?,?,?,?)";
        int rs = update(sql, ticket.getUid(), ticket.getSid(),ticket.getRow(),ticket.getColumn());
        if (rs == 0) {
            return false;
        } else {
            return true;
        }
    }
}
