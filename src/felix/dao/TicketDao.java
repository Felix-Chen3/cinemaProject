package felix.dao;

import felix.entity.Ticket;

import java.util.ArrayList;

public interface TicketDao {
    ArrayList<Ticket> queryTicketAllBySid(int sid);

    boolean createTicket(Ticket ticket);
}
