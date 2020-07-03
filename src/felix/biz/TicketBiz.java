package felix.biz;

import felix.entity.Ticket;

import java.util.ArrayList;

public interface TicketBiz {
    ArrayList<Ticket> queryTicketBySidAndSeat(Ticket ticket);

    boolean create(Ticket ticket);

    ArrayList<Ticket> queryTicketByUid(int uid);
}
