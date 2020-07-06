/**
 * @author Felix
 * @describe
 * @date 2020/6/29 19:44
 */
package felix.biz.imp0;

import felix.biz.TicketBiz;
import felix.dao.Imp0.TicketDaoImp0;
import felix.entity.Ticket;

import java.util.ArrayList;

public class TicketBizImp0 implements TicketBiz {
    private TicketDaoImp0 tdi0 = new TicketDaoImp0();

    @Override
    public ArrayList<Ticket> queryTicketBySidAndSeat(Ticket ticket) {
        return tdi0.queryAll(Ticket.class, "SELECT\n" +
                "\tticket.id, \n" +
                "\tticket.uid, \n" +
                "\tticket.sid, \n" +
                "\tticket.row_java AS `row`, \n" +
                "\tticket.column_java AS `column`\n" +
                "FROM\n" +
                "\tticket\n" +
                "WHERE\n" +
                "\tticket.sid = ? AND\n" +
                "\tticket.row_java = ? AND\n" +
                "\tticket.column_java = ?", ticket.getSid(),ticket.getRow(), ticket.getColumn());
    }

    public boolean create(Ticket ticket) {
        ArrayList<Ticket> rs = queryTicketBySidAndSeat(ticket);
        if (rs == null) {
            return tdi0.createTicket(ticket);
        }
        if (rs.contains(ticket)) {
            return false;
        } else {
            return tdi0.createTicket(ticket);
        }
    }

    @Override
    public ArrayList<Ticket> queryTicketByUid(int uid) {
        return tdi0.queryAll(Ticket.class, "SELECT\n" +
                "\tticket.id, \n" +
                "\tticket.uid, \n" +
                "\tticket.sid, \n" +
                "\tticket.row_java AS `row`, \n" +
                "\tticket.column_java AS `column`\n" +
                "FROM\n" +
                "\tticket\n" +
                "WHERE\n" +
                "\tticket.uid = ?",uid);
    }

    @Override
    public ArrayList<Ticket> queryTicketAll() {
        return tdi0.queryAll(Ticket.class,"select id,uid,sid from ticket");
    }
}
